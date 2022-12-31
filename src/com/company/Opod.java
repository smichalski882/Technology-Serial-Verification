package com.company;

import com.company.AbstractProduct;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

public final class Opod extends AbstractProduct {

    //Fields
    private SerialNumber serialNumber;
    private Optional<Set<String>> description;
    //declare final private field for productType
    private final ProductType productType = ProductType.OPOD;

    //Constructor
    Opod(SerialNumber serialNumber, Optional<Set<String>> description){
        super(serialNumber, description);
    }

    public static Opod getInstance(SerialNumber serialNumber, Optional<Set<String>> description){
        return new Opod(serialNumber, description);
    }

    //Methods
    public ProductType getProductType(){
        return ProductType.OPOD;
    }

    @Override
    public String getProductName() {
        return ProductType.OPOD.getName();
    }

    public static boolean isValidSerialNumber(SerialNumber serialNumber){
        return serialNumber.isEven()&&!serialNumber.testBit(3);
    }

    //Process an exchange request
    @Override
    public void process(Exchange request, RequestStatus status) throws ProductException {
        Optional<SerialNumber> newSerial = request.getCompatibleProducts().stream().findAny();
        if (newSerial.isPresent()) {
            status.setResult(returnSerialNumberBigInt(newSerial));
            status.setStatusCode(RequestStatus.StatusCode.OK);

        } else {
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
            status.setResult(Optional.empty());
        }
    }

    //Return a Optional of big integer given a big int
    public Optional<BigInteger> returnSerialNumberBigInt(Optional<SerialNumber> serialNumber){
        SerialNumber value = serialNumber.get();
        return Optional.of(value.getSerialNumber());
    }

    //Process a refund request
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

    //Test the RMA GCD SerialNumber to see if exchange is valid
    public boolean validRmaSerialStatus(Refund request){
        return (request.getRma().gcd(getSerialNumber().getSerialNumber()).compareTo(BigInteger.valueOf(24)) >= 0);
    }
}

