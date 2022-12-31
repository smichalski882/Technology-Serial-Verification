package Test;
import com.company.*;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.gen5.api.Assertions.assertEquals;

public class OwatchTest {

    @Test
    void testGetProduct(){
        Set<String> owatchSet = new HashSet<>();
        owatchSet.add("for all your time telling needs. ");
        owatchSet.add("starting at the low low price of $5,000!!");
        Optional<Set<String>> description = Optional.of(owatchSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(15));
        Owatch owatch = Owatch.getInstance(serialNumber, description);
        assertEquals(ProductType.OWATCH, owatch.getProductType());
    }

    @Test
    void testGetName(){
        Set<String> owatchSet = new HashSet<>();
        owatchSet.add("for all your time telling needs. ");
        owatchSet.add("starting at the low low price of $5,000!!");
        Optional<Set<String>> description = Optional.of(owatchSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(15));
        Owatch owatch = Owatch.getInstance(serialNumber, description);
        assertEquals("oWatch", owatch.getProductName());
    }

    @Test
    void testIsValidSerialNumber(){
        Set<String> owatchSet = new HashSet<>();
        owatchSet.add("for all your time telling needs. ");
        owatchSet.add("starting at the low low price of $5,000!!");
        Optional<Set<String>> description = Optional.of(owatchSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(15));
        Owatch owatch = Owatch.getInstance(serialNumber, description);
        assertEquals(true, owatch.isValidSerialNumber(owatch.getSerialNumber()));
    }

    @Test
    void testRefundProcess() throws RequestException, ProductException {
        Set<String> owatchSet = new HashSet<>();
        owatchSet.add("for all your time telling needs. ");
        owatchSet.add("starting at the low low price of $5,000!!");
        Optional<Set<String>> description = Optional.of(owatchSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(15));
        Owatch owatch = Owatch.getInstance(serialNumber, description);
        Refund.Builder builder = new Refund.Builder();
        builder.setRma(BigInteger.valueOf(24));
        Refund refund = builder.build();
        RequestStatus requestStatus = new RequestStatus();
        owatch.process(refund, requestStatus);
        assertEquals(true, requestStatus.getStatusCode().equals(RequestStatus.StatusCode.OK));
    }

    @Test
    void testExchangeProcess() throws ProductException {
        Set<String> owatchSet = new HashSet<>();
        owatchSet.add("for all your time telling needs. ");
        owatchSet.add("starting at the low low price of $5,000!!");
        Optional<Set<String>> description = Optional.of(owatchSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(15));
        Owatch owatch = Owatch.getInstance(serialNumber, description);
        Exchange.Builder builder = new Exchange.Builder();
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(7)));
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(18)));
        Exchange request = builder.build();
        RequestStatus requestStatus = new RequestStatus();
        owatch.process(request, requestStatus);
        assertEquals(true, requestStatus.getStatusCode().equals(RequestStatus.StatusCode.OK));
    }

}
