package com.addressbook.exceptions;

/**
 * Created by sharatjagannath on 7/24/17.
 */
public class BaseException extends Exception {

    private String message;

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}