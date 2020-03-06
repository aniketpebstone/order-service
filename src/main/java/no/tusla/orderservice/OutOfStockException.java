package no.tusla.orderservice;

public class OutOfStockException extends RuntimeException{

	private static final long serialVersionUID = -3624222317342513948L;

	public OutOfStockException(String message) {
		super(message);
	}

}
