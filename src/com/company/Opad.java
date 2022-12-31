package com.company;

import com.company.AbstractProduct;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

public final class Opad extends AbstractProduct {

    //Fields
    private SerialNumber serialNumber;
    private Optional<Set<String>> description;

    //Constructor
    Opad(SerialNumber serialNumber, Optional<Set<String>> description){
        super(serialNumber, description);
    }

    public static Opad getInstance(SerialNumber serialNumber, Optional<Set<String>> description){
        return new Opad(serialNumber, description);
    }

    //Methods
    public ProductType getProductType(){
        return ProductType.OPAD;
    }

    @Override
    public String getProductName() {
        return ProductType.OPAD.getName();
    }

    public static boolean isValidSerialNumber(SerialNumber serialNumber){
        return serialNumber.isEven()&&serialNumber.testBit(3);
    }

    //Returns an optional of big integer given an optional serial number
    public Optional<BigInteger> returnSerialNumberBigInt(Optional<SerialNumber> serialNumber){
        SerialNumber value = serialNumber.get();
        return Optional.of(value.getSerialNumber());
    }

    //Processes an exchange request
    @Override
    public void process(Exchange request, RequestStatus status) throws ProductException {
        Optional<SerialNumber> largestCompatible = Optional.ofNullable(request.getCompatibleProducts().floor(this.getSerialNumber()));
        if(largestCompatible.isPresent()){
            status.setResult(returnSerialNumberBigInt(largestCompatible));
            status.setStatusCode(RequestStatus.StatusCode.OK);
        }
        else {
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
            status.setResult(Optional.empty());
        }
    }

    //Processes a refund request
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

    //Tests if serial and rma meet predicate to be refunded
    public boolean validRmaSerialStatus(Refund request){
        return (request.getRma().gcd(getSerialNumber().getSerialNumber()).compareTo(BigInteger.valueOf(12)) >= 0);
    }
}
