package proj1;

public class NoPatronException extends Exception {
	public NoPatronException(){
		super("No patron exception error");
	}
	public NoPatronException(String msg){
		super(msg);
	}
}
