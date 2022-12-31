package com.company;

import com.company.AbstractProduct;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public final class Ophone extends AbstractProduct {

    //Fields
    private SerialNumber serialNumber;
    private Optional<Set<String>> description;

    //Constructor
    Ophone(SerialNumber serialNumber, Optional<Set<String>> description){
        super(serialNumber, description);
    }

    public static Ophone getInstance(SerialNumber serialNumber, Optional<Set<String>> description){
        return new Ophone(serialNumber, description);
    }

    //Methods
    public ProductType getProductType(){
        return ProductType.OPHONE;
    }

    @Override
    public String getProductName() {
        return ProductType.OPHONE.getName();
    }

    public static boolean isValidSerialNumber(SerialNumber serialNumber){
        return serialNumber.isOdd()&&serialNumber.gcd(new SerialNumber(BigInteger.valueOf(630))).compareTo(BigInteger.valueOf(32)) >= 0;
    }

    public Optional<BigInteger> returnSerialNumberBigInt(Optional<SerialNumber> serialNumber){
        SerialNumber value = serialNumber.get();
        return Optional.of(value.getSerialNumber());
    }

    @Override
    public Optional<Set<String>> getDescription() {
        return Optional.empty();
    }

    //Processes an exchange request
    @Override
    public void process(Exchange request, RequestStatus status) throws ProductException {
        Optional<SerialNumber> newSerial = findCompatibleExchange(request);
        if (newSerial.isPresent()) {
            status.setResult(returnSerialNumberBigInt(newSerial));
            status.setStatusCode(RequestStatus.StatusCode.OK);
        } else {
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
            status.setResult(Optional.empty());
        }
    }

    //Processes a refund request
    @Override
    public void process(Refund request, RequestStatus status) throws ProductException {
        if (validSerialRmaShift(request)) {
            status.setStatusCode(RequestStatus.StatusCode.OK);
            status.setResult(Optional.empty());
        } else {
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
            status.setResult(Optional.empty());
        }
    }

    //If the left shifted RMA by 1 to 3 bits is equal to serial number, return true (McCabe's = 3)
    public boolean validSerialRmaShift(Refund request){
        BigInteger rma = request.getRma();
        return (request.getRma().shiftLeft(1).equals(this.getSerialNumber().getSerialNumber()) || request.getRma().shiftLeft(2).equals(this.getSerialNumber().getSerialNumber()) || request.getRma().shiftLeft(3).equals(this.getSerialNumber().getSerialNumber()));
    }

    //Finds compatible exchange for the request (McCabe's = 2)
    public Optional<SerialNumber> findCompatibleExchange(Exchange request){
        Stream<SerialNumber> greaterThanThisSerial = request.getCompatibleProducts()
                .stream()
                .filter(e -> e.getSerialNumber().compareTo(getSerialNumber().getSerialNumber()) > 0);
        long count = greaterThanThisSerial.count();
        //sum is equal to the filtered products and then finding the smaller ones than the current serial number
        long sum = request.getCompatibleProducts().stream().filter(
                e -> e.getSerialNumber().compareTo(getSerialNumber().getSerialNumber()) > 0)
                .mapToLong(e -> e.getSerialNumber().longValueExact()).sum();
        BigInteger average = BigInteger.valueOf(sum).divide(BigInteger.valueOf(count));
        SerialNumber averageSerialNumber = new SerialNumber(average);
        //Return the max from the stream that matches the predicate
        return request.getCompatibleProducts().stream()
                .filter(e -> e.getSerialNumber().compareTo(getSerialNumber().getSerialNumber()) > 0 && e.compareTo(averageSerialNumber) < 0)
                .max(SerialNumber::compareTo);
    }
}
