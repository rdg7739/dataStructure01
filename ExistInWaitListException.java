package proj1;

public class ExistInWaitListException extends Exception {
	public ExistInWaitListException(){
		super("Patron is already in Waiting list");
	}
	public ExistInWaitListException(String msg){
		super(msg);
	}
}
