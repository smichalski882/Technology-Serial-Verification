package Test;
import com.company.*;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.gen5.api.Assertions.assertEquals;
import static org.junit.gen5.api.Assertions.assertThrows;

public class AbstractProductTest {
    @Test
    void testEquals() {
        Set<String> opadSet = new HashSet<>();
        opadSet.add("for all your tablet utility needs.");
        opadSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(opadSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(24));
        Opad opad = Opad.getInstance(serialNumber, description);
        Opad opad2 = Opad.getInstance(serialNumber, description);
        assertEquals(true, opad.equals(opad2));
    }

    @Test
    void testHashCode() {
        Set<String> opadSet = new HashSet<>();
        opadSet.add("for all your tablet utility needs.");
        opadSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(opadSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(24));
        Opad opad = Opad.getInstance(serialNumber, description);
        Opad opad2 = Opad.getInstance(serialNumber, description);
        assertEquals(true, opad.hashCode() == opad2.hashCode());
    }

    @Test
    void testToString() {
        Set<String> opadSet = new HashSet<>();
        opadSet.add("for all your tablet utility needs. ");
        opadSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(opadSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(24));
        Opad opad = Opad.getInstance(serialNumber, description);
        String string = "The name of the product is oPad. It's serial number is com.company.SerialNumber@6d3af739. Starting at the low low price of $15,000!!For all your tablet utility needs. ";
        System.out.println(opad.toString());
        assertEquals(true, opad.toString().equals(string));
    }

    @Test
    void testMake() throws ProductException{
        Set<String> opadSet = new HashSet<>();
        opadSet.add("for all your tablet utility needs.");
        opadSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(opadSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(24));
        Opad opad = Opad.getInstance(serialNumber, description);
        assertEquals(opad, AbstractProduct.make(ProductType.OPAD, serialNumber, description));

        Set<String> opadSet2 = new HashSet<>();
        opadSet.add("for all your tablet utility needs.");
        opadSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description2 = Optional.of(opadSet2);
        SerialNumber serialNumber2 = new SerialNumber(BigInteger.valueOf(25));
        Opad opad2 = Opad.getInstance(serialNumber, description);
        assertThrows(ProductException.class, () -> AbstractProduct.make(ProductType.OPAD, serialNumber2, description2));
    }
}
