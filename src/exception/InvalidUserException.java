package exception;

@SuppressWarnings("serial")
public class InvalidUserException extends Exception{
	
	public InvalidUserException(String mensaje) {
		super(mensaje);
	}
}
