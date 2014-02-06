package proj1;

import java.util.*;

public class Patron {
	private int ID; //id of the patron
	private LinkedList<Book> books = new LinkedList<Book>(); // the list of books that patron borrowed
	private Iterator ibooks;
	private String list = ""; // list of the title that patron had borrowed
	private int numBook = 0; //number of books that patron borrowed
	/**
	 * initiate constructor
	 */
	public Patron(){
		this(00000);
	}
	/**
	 * constructor
	 * @param ID - the id of patron
	 */
	public Patron(int ID){
		this.ID = ID;
	}
	/**
	 * add to list books, when patron borrow book
	 * @param book - the title of book
	 */
	public void patronBorrow(String book){  // to borrow a book
		Book foo = new Book(book);
		books.add(foo);
		list += foo.toString() + "\n";
		numBook++;
	}
	/**
	 * delete the book from the book list
	 * @param book - the title of book that patron returned
	 */
	public void patronReturn(String book){ // to return a book
		Book instBook = new Book(book);
		this.ibooks = books.iterator();
		int i = 0;
		while(ibooks.hasNext()){
			if(ibooks.next().equals(instBook)){
				books.remove(i);
			}
			++i;
		}
	}
	/**
	 * @return the id of patron
	 */
	public int getID(){
		return this.ID;
	}
	public String toString(){
		return ""+ this.ID;
	}
	/**
	 * @return the number of book
	 */
	public int getNumBook() {
		return numBook;
	}
	/**
	 * @return the list of books that user had borrowed
	 */
	public int patronList(){          // print a list of books borrowed, one per line
		System.out.print(list);
		return numBook;
	}
}
