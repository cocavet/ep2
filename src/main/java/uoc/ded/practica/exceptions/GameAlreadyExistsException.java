package uoc.ded.practica.exceptions;

public class GameAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public GameAlreadyExistsException(String string) {
		super(string);
	}
}
