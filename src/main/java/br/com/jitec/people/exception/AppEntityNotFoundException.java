package br.com.jitec.people.exception;

public class AppEntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AppEntityNotFoundException(String message) {
		super(message);
	}

	public AppEntityNotFoundException() {
		super("The resource you were trying to reach is not found");
	}

}
