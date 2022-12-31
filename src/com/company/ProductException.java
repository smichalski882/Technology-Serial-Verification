package com.company;

public class ProductException extends Exception{

    private ProductType productType;
    private SerialNumber serialNumber;
    private ErrorCode errorCode;

    //Error code enumerated type
    public enum ErrorCode{
        INVALID_SERIAL_NUMBER, INVALID_PRODUCT_TYPE, UNSUPPORTED_OPERATION
    }

    //CONSTRUCTOR
    public ProductException(ProductType productType, SerialNumber serialNumber, ErrorCode errorCode){
        this.productType = productType;
        this.serialNumber = serialNumber;
        this.errorCode = errorCode;
    }

    public ProductType getProductType(){
        return this.productType;
    }

    public String getProductName(){
        return this.getProductType().getName();
    }

    public SerialNumber getSerialNumber(){
        return this.serialNumber;
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }

}
