package com.example.demo.rest.exception;

public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = 709745200295504715L;

	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
