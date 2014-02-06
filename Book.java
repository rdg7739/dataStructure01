package proj1;
import java.util.*;

public class Book {
	private boolean borrowed = false; //true if book borrowed, false if not
	private String title;	//title of book
	private LinkedList<Patron> waitlist = new LinkedList<Patron>(); //waiting list of book
	private Iterator iwaitlist;
	private Patron whoHas; 	//name of person who has book
	/**
	 * initiate book constructor
	 */
	public Book(){
		this("Foo");
		this.whoHas = new Patron();
	}
	/**
	 * constructor
	 * @param title  - of book
	 */
	public Book(String title){
		this.title = title;
		this.borrowed = false;
	}
	/**
	 * set to borrowed to false if person return a book and return the first person in the list
	 * @param borrowed - set to false if returned, set to true if borrowed.
	 * @param person - who borrow book, will have null if borrowed is false
	 * @return removeFromQ - is the first person from the wait list 
	 * @return person - null 
	 */
	public Patron setBorrowed(boolean borrowed, Patron person){
		this.borrowed = borrowed;
		this.iwaitlist = waitlist.iterator();
		if(iwaitlist.hasNext() && !borrowed){
			return removeFromQ();
		}
		else{
			this.whoHas = person;
			return whoHas;
		}
	}
	/**
	 * check that book is borrowed or not
	 * @return - true if book borrowed
	 */
	public boolean isBorrowed(){
		return this.borrowed;
	}
	/**
	 * @return who has the book
	 */
	public Patron getWhoHas(){
		return this.whoHas;
	}
	/**
	 * @param person  - add a patron to this book's waiting list
	 */
	public void addToQ(Patron person) {   
		waitlist.add(person);
	}
	/**
	 * empty the who wait list
	 * @return true when the book's wait list is empty
	 */
	public boolean emptyQ(){
		this.iwaitlist = waitlist.iterator();
		if(iwaitlist.hasNext()){
			return false;
		}
			return true;      // TRUE if and only if waiting list is empty
	}
	/**
	 * to get the next patron from the waiting list
	 * @return the first person from the wait list.
	 */
	public Patron removeFromQ(){ 
		Patron foo = waitlist.getFirst();
		waitlist.removeFirst();
		return foo; 
	}
	/**
	 * to print this book's waiting list, one patron per line
	 */
	public void printQ(){      
		this.iwaitlist = waitlist.iterator();
		while(iwaitlist.hasNext()){
			System.out.println(iwaitlist.next().toString());
		}
	}
	/**
	 * return the title of the book
	 */
	public String toString(){
		return this.title;
	}
	public Object findPatronQ(Patron patron) {
		this.iwaitlist = waitlist.iterator();
		int i =0;
		while(iwaitlist.hasNext()){
			if(iwaitlist.next().equals(patron.toString())){
				return waitlist.get(i);
			}
			++i;
		}
		return null;
	}
}
