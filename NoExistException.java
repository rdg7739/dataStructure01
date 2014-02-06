package proj1;

public class NoExistException extends Exception{
	public NoExistException(){
		super("There is no item");
	}
	public NoExistException(String msg){
		super(msg);
	}
}
