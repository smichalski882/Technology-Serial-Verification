package com.company;

import java.math.BigInteger;
import java.util.Optional;

public class RequestStatus {

    private StatusCode statusCode;
    private Optional<BigInteger> result;


    public enum StatusCode{
        UNKNOWN, OK, FAIL
    }

    public RequestStatus(){
        this.statusCode = StatusCode.UNKNOWN;
        this.result = Optional.empty();
    }

    public StatusCode getStatusCode(){
        return this.statusCode;
    }

    public void setStatusCode(StatusCode statusCode){
        this.statusCode = statusCode;
    }

    public Optional<BigInteger> getResult(){
        return this.result;
    }

    public void setResult(Optional<BigInteger> result){
        this.result = result;
    }
}
