package com.company;

public interface Request {
    public void process(Product product, RequestStatus status) throws RequestException;
}
