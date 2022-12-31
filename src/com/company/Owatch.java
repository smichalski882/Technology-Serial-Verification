package com.company;

import com.company.AbstractProduct;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

public final class Owatch extends AbstractProduct {

    //Fields
    private SerialNumber serialNumber;
    private Optional<Set<String>> description;

    //Constructor
    Owatch(SerialNumber serialNumber, Optional<Set<String>> description){
        super(serialNumber, description);
    }

    public static Owatch getInstance(SerialNumber serialNumber, Optional<Set<String>> description){
        return new Owatch(serialNumber, description);
    }

    //Methods
    public ProductType getProductType(){
        return ProductType.OWATCH;
    }

    @Override
    public String getProductName() {
        return ProductType.OWATCH.getName();
    }


    public static boolean isValidSerialNumber(SerialNumber serialNumber){
        return serialNumber.isOdd()&&serialNumber.gcd(new SerialNumber(BigInteger.valueOf(630))).compareTo(BigInteger.valueOf(14)) > 0 && serialNumber.gcd(new SerialNumber(BigInteger.valueOf(630))).compareTo(BigInteger.valueOf(42)) <= 0;
    }

    @Override
    public Optional<Set<String>> getDescription() {
        return Optional.empty();
    }


    //Process a product exchange
    @Override
    public void process(Exchange request, RequestStatus status) throws ProductException {
        Optional<SerialNumber> smallestCompatible = Optional.ofNullable(request.getCompatibleProducts().ceiling(this.getSerialNumber()));
        if(smallestCompatible.isPresent()){
            status.setResult(returnSerialNumberBigInt(smallestCompatible));
            status.setStatusCode(RequestStatus.StatusCode.OK);
        }
        else {
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
            status.setResult(Optional.empty());
        }
    }

    //Process a refund
    @Override
    public void process(Refund request, RequestStatus status) throws ProductException {
        if(validRmaSerialStatus(request)){
            status.setStatusCode(RequestStatus.StatusCode.OK);
            status.setResult(Optional.empty());
        }
        else{
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
            status.setResult(Optional.empty());
        }
    }

    //Check the legality of the request
    public boolean validRmaSerialStatus(Refund request){
        return (getSerialNumber().getSerialNumber().xor(request.getRma()).compareTo(BigInteger.valueOf(14)) > 0);
    }

    //Return the biginteger optional given a serialnumber optional
    public Optional<BigInteger> returnSerialNumberBigInt(Optional<SerialNumber> serialNumber){
        SerialNumber value = serialNumber.get();
        return Optional.of(value.getSerialNumber());
    }

}