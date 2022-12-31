package com.company;

import com.company.AbstractProduct;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public final class Otv extends AbstractProduct {

    //Fields
    private SerialNumber serialNumber;
    private Optional<Set<String>> description;

    //Constructor
    Otv(SerialNumber serialNumber, Optional<Set<String>> description){
        super(serialNumber, description);
    }

    public static Otv getInstance(SerialNumber serialNumber, Optional<Set<String>> description){
        return new Otv(serialNumber, description);
    }

    //Methods
    public ProductType getProductType(){
        return ProductType.OTV;
    }

    @Override
    public String getProductName() {
        return ProductType.OTV.getName();
    }

    public static boolean isValidSerialNumber(SerialNumber serialNumber){
        return serialNumber.isOdd()&&serialNumber.gcd(new SerialNumber(BigInteger.valueOf(630))).compareTo(BigInteger.valueOf(14)) <= 0;
    }

    @Override
    public Optional<Set<String>> getDescription() {
        return Optional.empty();
    }

    //Processes an exchange (McCabe's = 3)
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

    //Processes a refund (McCabe's = 3)
    @Override
    public void process(Refund request, RequestStatus status) throws ProductException {
        if (request.getRma().compareTo(BigInteger.ZERO) > 0) {
            status.setStatusCode(RequestStatus.StatusCode.OK);
            status.setResult(Optional.empty());
        } else {
            status.setStatusCode(RequestStatus.StatusCode.FAIL);
            status.setResult(Optional.empty());
        }
    }

    //Returns the compatible products based on the predicate (McCabe's = 1)
    public Optional<SerialNumber> findCompatibleExchange(Exchange request){
        BigInteger serialPlus1024 = getSerialNumber().getSerialNumber().add(BigInteger.valueOf(1024));
        Stream<SerialNumber> validCompatibles = returnLegalProductsFromCompatible(request);
        long count = validCompatibles.count();
        long sum = returnSumOfLegalProductsFromCompatible(request);
        BigInteger average = BigInteger.valueOf(sum).divide(BigInteger.valueOf(count));
        SerialNumber averageSerialNumber = new SerialNumber(average);
        return finalProductOptional(request, averageSerialNumber);
    }

    //Returns the biggest serial number that is greater than this object's and less than the average (McCabe's = 3)
    public Optional<SerialNumber> finalProductOptional(Exchange request, SerialNumber average){
        BigInteger serialPlus1024 = getSerialNumber().getSerialNumber().add(BigInteger.valueOf(1024));
        return request.getCompatibleProducts().stream()
                .filter(e -> e.getSerialNumber().compareTo(getSerialNumber().getSerialNumber()) > 0 && e.getSerialNumber().compareTo(serialPlus1024) < 0 && e.compareTo(average) < 0)
                .max(SerialNumber::compareTo);
    }

    //Returns the sum of the legal products from the compatible products (McCabe's = 2)
    public long returnSumOfLegalProductsFromCompatible(Exchange request){
        BigInteger serialPlus1024 = getSerialNumber().getSerialNumber().add(BigInteger.valueOf(1024));
        long sum = request.getCompatibleProducts().stream()
                .filter(e -> e.getSerialNumber().compareTo(getSerialNumber().getSerialNumber()) > 0 && e.getSerialNumber().compareTo(serialPlus1024) < 0)
                .mapToLong(e -> e.getSerialNumber().longValueExact()).sum();
        return sum;
    }

    //Returns a stream of the legal products (McCabe's = 2)
    public Stream<SerialNumber> returnLegalProductsFromCompatible(Exchange request){
        BigInteger serialPlus1024 = getSerialNumber().getSerialNumber().add(BigInteger.valueOf(1024));
        Stream<SerialNumber> validCompatibles = request.getCompatibleProducts().stream()
                .filter(e -> e.getSerialNumber().compareTo(getSerialNumber().getSerialNumber()) > 0 && e.getSerialNumber().compareTo(serialPlus1024) < 0);
        return validCompatibles;
    }

    //Returns the optionalBigInteger given a serial number optional (McCabe's = 0)
    public Optional<BigInteger> returnSerialNumberBigInt(Optional<SerialNumber> serialNumber){
        SerialNumber value = serialNumber.get();
        return Optional.of(value.getSerialNumber());
    }
}