package com.exception;

public class AutomationException extends RuntimeException{

    public AutomationException(final String message){
        super(message);
    }

    public AutomationException(final String message,final Exception e){
        super(message,e);
    }
}
