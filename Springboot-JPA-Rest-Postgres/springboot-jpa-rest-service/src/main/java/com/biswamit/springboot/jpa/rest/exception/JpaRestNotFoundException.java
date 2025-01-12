package com.biswamit.springboot.jpa.rest.exception;

public class JpaRestNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 9220059906808040206L;

    public JpaRestNotFoundException(JpaRestErrorCode error) {
        super(error.getMessage());
    }

    public JpaRestNotFoundException(String message) {
        super(message);
    }

    public JpaRestNotFoundException(JpaRestErrorCode error, Throwable cause) {
        super(error.getMessage(), cause);
    }

    public JpaRestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
