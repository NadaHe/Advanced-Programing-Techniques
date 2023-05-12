package Lab5;
//Name : Nada Hesham Anwer 

//ID : 1190185

public class Book {
	int isbn; // isbn for books
	String title; // book title
	String author; // author name
	boolean is_borrowed;
	String borrower; // client name that currently borrowing the book.

//Constructor 
	public Book(int isbn, String title, String author) {
		this.isbn = isbn; // assume that the isbn is not long enough
		this.title = title;
		this.author = author;
		this.is_borrowed = false; // assuming that library starts with no books borrowed
		this.borrower = null; // assuming no borrowers at first
	}

}
