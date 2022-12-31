package com.company;

import java.math.BigInteger;

public class SerialNumber implements Comparable<SerialNumber>{

    //field to store the serial number
    private BigInteger serialNumber;

    //Constructor for serial numbers
    public SerialNumber(BigInteger serialNumber) {
        this.serialNumber = serialNumber;
    }

    //return serial number
    public BigInteger getSerialNumber(){
        return this.serialNumber;
    }

    //find greatest common divisor of other serial number
    public BigInteger gcd(SerialNumber other){
        return this.getSerialNumber().gcd(other.getSerialNumber());
    }

    //returns this serial number modulus the other serial number
    public BigInteger mod(SerialNumber other){
        return this.getSerialNumber().mod(other.getSerialNumber());
    }

    //returns true iff the bit at the given index is set in this serial number
    public boolean testBit(int bit){
        return this.getSerialNumber().testBit(bit);
    }

    //return true if this serial number is even
    public boolean isEven(){
        BigInteger evenTester = new BigInteger("2");
        if(this.getSerialNumber().mod(evenTester).equals(BigInteger.ZERO)){
            return true;
        }
        else
            return false;
    }

    //return true if this serial number is odd
    //Should directly return conditional, reduces complexity
    public boolean isOdd(){
        BigInteger evenTester = new BigInteger("2");
        if(this.getSerialNumber().mod(evenTester).equals(BigInteger.ZERO)){
            return false;
        }
        else
            return true;
    }

    //compare two serial numbers
    @Override
    public int compareTo(SerialNumber other) {
        return this.getSerialNumber().compareTo(other.getSerialNumber());
    }
}

