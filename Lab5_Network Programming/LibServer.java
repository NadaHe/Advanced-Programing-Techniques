package Lab5;

//Name : Nada Hesham Anwer 
//ID : 1190185

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class LibServer {
   
	public static final int BORROWING_PORT = 6666;
	public static final int RETURNING_PORT = 6667;
	public static Library Server_Library; // static to view all changes by all clients
	public static int clientNumber = 0; // to keep track of the number of clients connecting to the server.

	public static void main(String[] args) throws IOException {
		// one use scanner
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Books Path : ");
		String filename = sc.next();
		Server_Library = new Library(filename);
		sc.close();

		System.out.println("The library started .. ");

		new Thread() {
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(BORROWING_PORT);
					while (true) {
						new ServerLibrary(ss.accept()).start();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();

		new Thread() {
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(RETURNING_PORT);
					while (true) {
						new ServerLibrary(ss.accept()).start();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	private static class ServerLibrary extends Thread {
		Socket socket;

		public ServerLibrary(Socket s) {
			this.socket = s;
		}

		public void run() {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);

				// read borrower name
				String borrower_name = br.readLine();

				while (true) {
					int req = Integer.parseInt(br.readLine());
					if (req == -1)
						break;
					if (this.socket.getLocalPort() == BORROWING_PORT)
						out.println(Server_Library.Borrow(req, borrower_name));
					else
						out.println(Server_Library.Return_Back(req, borrower_name));
					Server_Library.Print();
				}
				out.close();
				br.close();
			} catch (IOException e) {
				System.out.println("Error handling client " + ": " + e);
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("Couldn't close a socket, what's going on?");
				}

				if (this.socket.getLocalPort() == BORROWING_PORT)
					System.out.println("Connection with borrowing client closed");
				else
					System.out.println("Return : Connection with returning client closed");
			}
		}
	}
}

/*
 * The code uses multithreading to handle requests from multiple clients
 * simultaneously. The methods Borrow() and Return_Back() in the Library class
 * are synchronized to ensure that only one client can borrow or return a book
 * at a time.
 */

