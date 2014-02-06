package proj1;

import java.io.File;
import java.util.*;


public class Proj1 {
	private int patronID; //patron's id
	private String title; //title of book
	private String choiced; //menu choice
	private Iterator ipatrons;
	private Iterator ibooks;
	private LinkedList<Patron> patrons = new LinkedList<Patron>(); //list of the patrons
	private Book instBook;	//instant book object
	private Patron instPatron; //instant patron object
	private LinkedList<Book> books = new LinkedList<Book>(); //list of the books
	/**
	 * constructor
	 * @param args - get the input file
	 */
	public Proj1(String[] args){
		try{
			File file = new File(args[0]);
			Scanner inFile = new Scanner(file);
			menu(inFile);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
	/**
	 * get the input and call the function
	 * @param input - what read from the input file
	 */
	public void menu(Scanner input){
		while(input.hasNext()){
			try{
				if(input.hasNextInt()){
					this.patronID = input.nextInt();
					instPatron = getPatron(new Patron(patronID));
					this.choiced = input.next();
					this.title = input.nextLine();
					instBook = getBook(new Book(title));
					System.out.println(this.patronID + " " + this.choiced + " " + this.title);
					if(this.choiced.equals("borrow")){
						if(instPatron == null){
							instPatron = new Patron(patronID);
							patrons.add(instPatron); 
						}
						if(instBook == null){
							instBook = new Book(title);
							books.add(instBook); 
						}
						borrowBook(instPatron, instBook);
					}
					else if(this.choiced.equals("return")){
						if(instPatron == null){
							instPatron = new Patron(patronID);
							patrons.add(instPatron);
						}
						if(instBook == null){
							throw new NoExistException("There is no " + this.title + " in the library system");	
						}
						if(instPatron != null && instBook != null){
							returnBook(instPatron, instBook);
						}
					}
					else if(this.choiced.equals("list")){
						if(patrons.contains(instPatron)){
							list(instPatron);	
						}
						else{
							System.out.println("there is no Patron with id: "+ this.patronID);	
						}
					}
				}
				else if(input.hasNext()){
					this.choiced = input.next();
					System.out.print(this.choiced);
					if(input.hasNext()){
						this.title = input.nextLine();
						System.out.print(" " + this.title);
						instBook = getBook(new Book(title));
						System.out.println(" ");
						if(instBook != null){
							if(choiced.equals("whohas")){
								whohas(instBook);	
							}
							else if(choiced.equals("waitlist")){
								waitlist(instBook);	
							}
						}
						else{
							throw new NoExistException("There is no " + title + " in the library");
						}
					}
					else if(choiced.equals("listpatrons")){
						listPatron();
					}
				}
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
	}
	/**
	 * if book is not in library, add book, and 
	 * check that book that can borrow or not, and
	 * if book is out put patron on wait list
	 * @param patron who borrow book
	 * @param book the book that patroon want to borrow
	 * @throws HasBorrowedException - when patron already borrowed and have the book
	 * @throws ExistInWaitListException - when patron is already in the waiting list of the book
	 */
	public void borrowBook(Patron patron, Book book) throws HasBorrowedException, ExistInWaitListException{
		if(patron.equals(book.getWhoHas())){
			throw new  HasBorrowedException("Patron " + patron.getID() + " already has a " + book.toString());
		}
		if(patron.equals(book.findPatronQ(patron))){
			throw new  ExistInWaitListException("Patron " + patron.getID() + "is already in a " + book.toString() + "'s waiting list");
		}
		else if(book.isBorrowed()){
			System.out.println("Patron " + patron.getID() + " is waiting for " + book.toString());
			//put in waitlist
			book.addToQ(patron);
		}
		else{
			System.out.println("Patron " + patron.getID() + " has borrowed " +book.toString());
			book.setBorrowed(true, patron);
			patron.patronBorrow(book.toString());
		}
	}
	/**
	 * get the patron from the library data base
	 * @param patron 
	 * @return patron if patron is already in the library
	 * @return null when patron is not in the library
	 */
	private Patron getPatron(Patron patron){
		this.ipatrons = patrons.iterator();
		int i = 0;
		while(ipatrons.hasNext()){
			if(ipatrons.next().toString().equals(patron.toString())){
				return patrons.get(i);
			}
			i++;
		}
		return null;
	}
	/**
	 * get the book from the library data base
	 * @param book 
	 * @return book if book is already in the library
	 * @return null when book is not in the library
	 */
	private Book getBook(Book book){
		this.ibooks = books.iterator();
		int i = 0;
		while(ibooks.hasNext()){
			if(ibooks.next().toString().equals(book.toString())){
				return books.get(i);
			}
			i++;
		}
		return null;
	}
	/**
	 * patron return the book. and if anyone is in the waiting list, then let him borrow it
	 * @param patron - who want to return book
	 * @param book - the book that patron want to return 
	 * @throws HasBorrowedException - throw if the patron want to borrow same book that he already had 
	 * @throws ExistInWaitListException 
	 */
	public void returnBook(Patron patron, Book book) throws HasBorrowedException, ExistInWaitListException{
		patron.patronReturn(book.toString());
		Patron foo = book.setBorrowed(false, null);
		System.out.println("Patron " + patron.getID() + " has returned " + book.toString());
		if(foo != null){
			borrowBook(foo, book);
		}
		
	}
	/**
	 * list of the book that patron had borrowed
	 * @param patron
	 */
	public void list(Patron patron){
		System.out.println("Patron " + patron.getID() + " has borrowed " + patron.getNumBook() + " item(s)");
		patron.patronList();
	}
	/**
	 * print which patron have to book
	 * @param book
	 */
	public void whohas(Book book){
		System.out.println("Patron " + book.getWhoHas().getID() + " has " + book.toString());
	}
	/**
	 * print the names of the patron in the waiting list
	 * @param book
	 */
	public void waitlist(Book book){
		System.out.println("Patron(s) waiting for " + book.toString());
		book.printQ();
	}
	/**
	 * list of the patron that is in the library
	 */
	public void listPatron(){
		System.out.println("Patron(s) include: ");
		this.ipatrons = patrons.iterator();
		while(ipatrons.hasNext()){
			System.out.println(ipatrons.next().toString());
		}
	}
	public static void main(String[] args){
		new Proj1(args);
	}

}
