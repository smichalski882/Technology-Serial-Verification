package com.company;

public class RequestException extends Exception{

    private RequestException.ErrorCode errorCode;

    public enum ErrorCode{
        NULL_RMA, INVALID_SERIAL_NUMBER
    }

    public RequestException(RequestException.ErrorCode errorCode){
        this.errorCode = errorCode;
    }
}
