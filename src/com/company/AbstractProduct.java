package com.company;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import java.util.*;
import java.util.function.Function;

public abstract class AbstractProduct implements Product{

    private SerialNumber serialNumber;
    private Optional<Set<String>> description;

    //product constructor needs serialnumber and a description
    public AbstractProduct(SerialNumber serialNumber, Optional<Set<String>> description){
        this.serialNumber = serialNumber;
        this.description = description;
    }

    @Override
    public SerialNumber getSerialNumber(){
        return this.serialNumber;
    }


    @Override
    public Optional<Set<String>> getDescription(){
        return this.description;
    }

    //two products are equal so long as their serial numbers are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractProduct that = (AbstractProduct) o;
        return Objects.equals(serialNumber, that.serialNumber);
    }

    //returns the same hash so long as the serial numbers are the same
    @Override
    public int hashCode() {
        return Objects.hash(serialNumber);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("The name of the product is " + this.getProductName() + ". ");
        builder.append("It's serial number is " + this.getSerialNumber() + ". ");
        this.getDescription().get().stream().forEach(e -> builder.append(e.substring(0, 1).toUpperCase() + e.substring(1)));
        return builder.toString();
    }

    public static Product make(ProductType productType, SerialNumber serialNumber, Optional<Set<String>> description) throws ProductException{
        MapMaker mapMaker = new MapMaker();
        HashMap<ProductType, Boolean> validMap = mapMaker.makeValidMap(productType, serialNumber, description);
        Boolean isValid = validMap.get(productType);
            if (isValid) {
                HashMap<ProductType, Product> productMap = mapMaker.makeProductMap(productType, serialNumber, description);
                return productMap.get(productType);
            } else {
                System.out.println("Invalid Serial Number");
                throw new ProductException(productType, serialNumber, ProductException.ErrorCode.INVALID_SERIAL_NUMBER);
            }
    }
}