package exception;

public class InvalidPasswordException extends Exception {

	public InvalidPasswordException(String mensaje) {
		super(mensaje);
	}
}
