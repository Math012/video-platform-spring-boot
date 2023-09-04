package br.com.math012.exceptions;

public class ExistingObjectException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExistingObjectException(String error){
        super(error);
    }
}
