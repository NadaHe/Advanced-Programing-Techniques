package Lab5;

//Name : Nada Hesham Anwer 
//ID : 1190185
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class LibClient {

	public static final int BORROW_PORT = 6666;
	public static final int RETURN_PORT = 6667;

	public static void main(String[] args) throws UnknownHostException, IOException {

		System.out.println("Enter Client name : ");
		Scanner scanner = new Scanner(System.in);
		String borrower_name = scanner.next();
		
		// two ports , two sockets !
		Socket borrow_socket = new Socket("localhost", BORROW_PORT);
		Socket return_socket = new Socket("localhost", RETURN_PORT);
		
		// print writer for each
		PrintWriter borrow_printer = new PrintWriter(borrow_socket.getOutputStream(), true);
		PrintWriter return_printer = new PrintWriter(return_socket.getOutputStream(), true);
		// buffered reader for each
		BufferedReader borrow_buff = new BufferedReader(new InputStreamReader(borrow_socket.getInputStream()));
		BufferedReader return_buff = new BufferedReader(new InputStreamReader(return_socket.getInputStream()));

		// sending client names to servers
		borrow_printer.println(borrower_name);
		return_printer.println(borrower_name);

		while (true) {
			System.out.println("1-Borrow \t 2-Return \t -1 to exit");
			int op = scanner.nextInt();
			if (op == -1) {
				// eb3t exit 3l two ports
				borrow_printer.println("-1");
				return_printer.println("-1");
				break;
			}
			System.out.println("Enter ISBN : ");
			String isbn = scanner.next(); // to be parsed
			if (op == 1) {
				borrow_printer.println(isbn);
				System.out.println(borrow_buff.readLine()); // reads response from borrow in and print it
			} else if (op == 2) {
				return_printer.println(isbn);
				System.out.println(return_buff.readLine()); // reads response from return in and print it
			} else {
				System.out.println("Invalid option");
				continue;
			}
		}
		System.out.println("I am exiting now, bye.");
		// close all sockets , buffers and printers
		scanner.close();
		borrow_buff.close();
		borrow_printer.close();
		return_buff.close();
		return_printer.close();
		borrow_socket.close();
		return_socket.close();
	}
}
