package Test;
import com.company.*;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.gen5.api.Assertions.assertEquals;
import static org.junit.gen5.api.Assertions.assertThrows;

public class OpadTest {

    @Test
    void testGetProduct() {
        Set<String> opadSet = new HashSet<>();
        opadSet.add("for all your tablet utility needs. ");
        opadSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(opadSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(24));
        Opad opad = Opad.getInstance(serialNumber, description);
        assertEquals(ProductType.OPAD, opad.getProductType());
    }

    @Test
    void testGetName() {
        Set<String> opadSet = new HashSet<>();
        opadSet.add("for all your tablet utility needs. ");
        opadSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(opadSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(24));
        Opad opad = Opad.getInstance(serialNumber, description);
        assertEquals("oPad", opad.getProductName());
    }

    @Test
    void testIsValidSerialNumber() {
        Set<String> opadSet = new HashSet<>();
        opadSet.add("for all your tablet utility needs. ");
        opadSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(opadSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(24));
        Opad opad = Opad.getInstance(serialNumber, description);
        assertEquals(true, opad.isValidSerialNumber(opad.getSerialNumber()));
    }

    @Test
    void testRefundProcess() throws RequestException, ProductException {
        Set<String> opadSet = new HashSet<>();
        opadSet.add("for all your tablet utility needs. ");
        opadSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(opadSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(24));
        Opad opad = Opad.getInstance(serialNumber, description);
        Refund.Builder builder = new Refund.Builder();
        builder.setRma(BigInteger.valueOf(24));
        Refund refund = builder.build();
        RequestStatus requestStatus = new RequestStatus();
        opad.process(refund, requestStatus);
        assertEquals(true, requestStatus.getStatusCode().equals(RequestStatus.StatusCode.OK));
    }

    @Test
    void testExchangeProcess() throws ProductException {
        Set<String> opadSet = new HashSet<>();
        opadSet.add("for all your tablet utility needs. ");
        opadSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(opadSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(24));
        Opad opad = Opad.getInstance(serialNumber, description);
        Exchange.Builder builder = new Exchange.Builder();
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(7)));
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(18)));
        Exchange request = builder.build();
        RequestStatus requestStatus = new RequestStatus();
        opad.process(request, requestStatus);
        assertEquals(true, requestStatus.getStatusCode().equals(RequestStatus.StatusCode.OK));
    }

    @Test
    void testExchangeProcessTwo() throws ProductException{
        Set<String> opadSet = new HashSet<>();
        opadSet.add("for all your tablet utility needs. ");
        opadSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(opadSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(1048));
        Opad opad = Opad.getInstance(serialNumber, description);
        Exchange.Builder builder = new Exchange.Builder();
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(1032)));
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(1244)));
        Exchange request = builder.build();
        RequestStatus requestStatus = new RequestStatus();
        opad.process(request, requestStatus);
        assertEquals(true, requestStatus.getStatusCode().equals(RequestStatus.StatusCode.OK));
    }
}
