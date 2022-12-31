package Test;
import com.company.*;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.gen5.api.Assertions.assertEquals;

public class OphoneTest {

    @Test
    void testGetProduct(){
        Set<String> ophoneSet = new HashSet<>();
        ophoneSet.add("for all your cell phone calling needs. ");
        ophoneSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(ophoneSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(945));
        Ophone ophone = Ophone.getInstance(serialNumber, description);
        assertEquals(ProductType.OPHONE, ophone.getProductType());
    }

    @Test
    void testGetName(){
        Set<String> ophoneSet = new HashSet<>();
        ophoneSet.add("for all your cell phone calling needs. ");
        ophoneSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(ophoneSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(945));
        Ophone ophone = Ophone.getInstance(serialNumber, description);
        assertEquals("oPhone", ophone.getProductName());
    }

    @Test
    void testIsValidSerialNumber(){
        Set<String> ophoneSet = new HashSet<>();
        ophoneSet.add("for all your cell phone calling needs. ");
        ophoneSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(ophoneSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(945));
        Ophone ophone = Ophone.getInstance(serialNumber, description);
        assertEquals(true, ophone.isValidSerialNumber(ophone.getSerialNumber()));
    }

    @Test
    void testRefundProcess() throws RequestException, ProductException {
        Set<String> ophoneSet = new HashSet<>();
        ophoneSet.add("for all your cell phone calling needs. ");
        ophoneSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(ophoneSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(20));
        Ophone ophone = Ophone.getInstance(serialNumber, description);
        Refund.Builder builder = new Refund.Builder();
        builder.setRma(BigInteger.valueOf(5));
        Refund refund = builder.build();
        RequestStatus requestStatus = new RequestStatus();
        ophone.process(refund, requestStatus);
        assertEquals(true, requestStatus.getStatusCode().equals(RequestStatus.StatusCode.OK));
    }

    @Test
    void testExchangeProcess() throws ProductException {
        Set<String> ophoneSet = new HashSet<>();
        ophoneSet.add("for all your cell phone calling needs. ");
        ophoneSet.add("starting at the low low price of $15,000!!");
        Optional<Set<String>> description = Optional.of(ophoneSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(945));
        Ophone ophone = Ophone.getInstance(serialNumber, description);
        Exchange.Builder builder = new Exchange.Builder();
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(946)));
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(1047)));
        Exchange request = builder.build();
        RequestStatus requestStatus = new RequestStatus();
        ophone.process(request, requestStatus);
        assertEquals(true, requestStatus.getStatusCode().equals(RequestStatus.StatusCode.OK));
    }
}
