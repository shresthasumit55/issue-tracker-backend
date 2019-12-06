package edu.baylor.cs.se.exception;

public class InsertFailureException extends Exception{

    public InsertFailureException() {
        super();
    }

    public InsertFailureException(String message) {
        super(message);
    }
}
