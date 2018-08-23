package com.exceptions;

public class ServiceException extends Exception {

    private String errorCode;
    private String message;

    public ServiceException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode(){
        return this.errorCode;
    }

    public String getErrorMessage(){
        return this.message;
    }

}
