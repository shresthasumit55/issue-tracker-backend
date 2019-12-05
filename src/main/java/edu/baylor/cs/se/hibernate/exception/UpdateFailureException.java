package edu.baylor.cs.se.hibernate.exception;

public class UpdateFailureException extends Exception {

    public UpdateFailureException() {
        super();
    }

    public UpdateFailureException(String message) {
        super(message);
    }
}
