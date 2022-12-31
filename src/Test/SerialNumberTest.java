package Test;

import com.company.SerialNumber;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

//Tests SerialNumber methods
class SerialNumberTest{

    @Test
    void testGetSerialNumber(){
        SerialNumber serialNumber = new SerialNumber(BigInteger.ONE);
        assertEquals(BigInteger.ONE, serialNumber.getSerialNumber());
    }

    @Test
    void testGetGreatestCommonDivisor(){
        SerialNumber serialNumber1 = new SerialNumber(BigInteger.valueOf(9));
        SerialNumber serialNumber2 = new SerialNumber(BigInteger.valueOf(18));
        assertEquals(BigInteger.valueOf(9), serialNumber1.gcd(serialNumber2));
    }

    @Test
    void testMod(){
        SerialNumber serialNumber1 = new SerialNumber(BigInteger.valueOf(17));
        SerialNumber serialNumber2 = new SerialNumber(BigInteger.valueOf(4));
        assertEquals(BigInteger.ONE, serialNumber1.mod(serialNumber2));
    }

    @Test
    void testTestBit(){
        SerialNumber serialNumber1 = new SerialNumber(BigInteger.valueOf(9));
        assertEquals(true, serialNumber1.testBit(0));
    }

    @Test
    void testIsEven(){
        SerialNumber serialNumber1 = new SerialNumber(BigInteger.valueOf(8));
        assertEquals(true, serialNumber1.isEven());
    }

    @Test
    void testIsOdd(){
        SerialNumber serialNumber1 = new SerialNumber(BigInteger.valueOf(8));
        assertEquals(false, serialNumber1.isOdd());
    }

    @Test
    void testCompare(){
        SerialNumber serialNumber1 = new SerialNumber(BigInteger.valueOf(8));
        SerialNumber serialNumber2 = new SerialNumber(BigInteger.valueOf(8));
        System.out.println(serialNumber1.compareTo(serialNumber2));
        assertEquals(0, serialNumber1.compareTo(serialNumber2));
    }


}