package org.frusso.groceryservice.controller.rest.errorhandling;

public class GroceryEmptyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;

	public GroceryEmptyException() {
		this.message = "";
	}

	public GroceryEmptyException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
