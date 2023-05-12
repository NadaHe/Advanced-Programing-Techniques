package Lab5;

//Name : Nada Hesham Anwer 
//ID : 1190185
import java.io.*;
import java.util.*;

public class Library {
	Book books[];

	// Constructor:
	public Library(String filename) throws IOException, FileNotFoundException {

		File path_to_library = new File(filename);
		Scanner library_scan = new Scanner(path_to_library);
		int count_books = library_scan.nextInt();
		library_scan.nextLine(); // consuming the "\n eli b3d el count"
		books = new Book[count_books];

		for (int i = 0; i < count_books; i++) {
			// split each book data on meeting a ","
			String book_data[] = library_scan.next().split(",");
			
			// Parse the isbn into integer
			int isbn = Integer.parseInt(book_data[0]);
			// title and author are strings by default
			String title = book_data[1];
			String author = book_data[2];
			// create a new book object and add it to the array
			books[i] = new Book(isbn, title, author);
		}
		library_scan.close();
	}

	// Borrow:
	// for multithreading , it's more safer to synchronize the borrow and return
	public synchronized String Borrow(int isbn, String borrower) {

		for (int i = 0; i < books.length; i++) {
			if (books[i].isbn == isbn) {
				if (books[i].is_borrowed) {
					return "The isbn has been already borrowed.";
				} else {
					// assign as borrowed and edit the borrower data
					books[i].is_borrowed = true;
					books[i].borrower = borrower;
					return "The borrow is done successfully.";
				}
			}
		}
		return "The isbn is not found in library.";
	}

//ReturnBack:
// for multithreading , it's more safer to synchronize the borrow and return 
	public synchronized String Return_Back(int isbn, String borrower) {

		for (int i = 0; i < books.length; i++) {
			if (books[i].isbn == isbn) {
				if (books[i].is_borrowed) {
					if (books[i].borrower.equals(borrower)) {
						// check if the current client is the borrower
						books[i].is_borrowed = false;
						books[i].borrower = null;
						return "The return is done successfully.";
					} else {
						return "The isbn is borrowed by another borrower.";
					}
				} else {
					return "The isbn is not borrowed."; // this book is not borrowed 
				}
			}
		}
		return "The isbn is not found in library.";
	}

	// Print:
	// reminder that we need it after each server response
	public void Print() {
		for (int i = 0; i < books.length; i++) {
			if (books[i].is_borrowed)
				// lw mawgod , etb3 el borrower
				System.out.println(books[i].isbn + " " + books[i].borrower);
			else
				// etb3 el book bs
				System.out.println(books[i].isbn + " NULL ");
		}
	}

}
