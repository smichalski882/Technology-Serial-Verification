package Test;
import com.company.*;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.gen5.api.Assertions.assertEquals;

public class OtvTest {

    @Test
    void testGetProduct(){
        Set<String> otvSet = new HashSet<>();
        otvSet.add("for all your entertainment needs. ");
        otvSet.add("starting at the low low price of $20,000!!");
        Optional<Set<String>> description = Optional.of(otvSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(5));
        Otv otv = Otv.getInstance(serialNumber, description);
        assertEquals(ProductType.OTV, otv.getProductType());
    }

    @Test
    void testGetName(){
        Set<String> otvSet = new HashSet<>();
        otvSet.add("for all your entertainment needs. ");
        otvSet.add("starting at the low low price of $20,000!!");
        Optional<Set<String>> description = Optional.of(otvSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(5));
        Otv otv = Otv.getInstance(serialNumber, description);
        assertEquals("oTv", otv.getProductName());
    }

    @Test
    void testIsValidSerialNumber(){
        Set<String> otvSet = new HashSet<>();
        otvSet.add("for all your entertainment needs. ");
        otvSet.add("starting at the low low price of $20,000!!");
        Optional<Set<String>> description = Optional.of(otvSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(5));
        Otv otv = Otv.getInstance(serialNumber, description);
        assertEquals(true, otv.isValidSerialNumber(otv.getSerialNumber()));
    }

    @Test
    void testRefundProcess() throws RequestException, ProductException {
        Set<String> otvSet = new HashSet<>();
        otvSet.add("for all your entertainment needs. ");
        otvSet.add("starting at the low low price of $20,000!!");
        Optional<Set<String>> description = Optional.of(otvSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(5));
        Otv otv = Otv.getInstance(serialNumber, description);
        Refund.Builder builder = new Refund.Builder();
        builder.setRma(BigInteger.valueOf(24));
        Refund refund = builder.build();
        RequestStatus requestStatus = new RequestStatus();
        otv.process(refund, requestStatus);
        assertEquals(true, requestStatus.getStatusCode().equals(RequestStatus.StatusCode.OK));
    }

    @Test
    void testExchangeProcess() throws ProductException {
        Set<String> otvSet = new HashSet<>();
        otvSet.add("for all your entertainment needs. ");
        otvSet.add("starting at the low low price of $20,000!!");
        Optional<Set<String>> description = Optional.of(otvSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(5));
        Otv otv = Otv.getInstance(serialNumber, description);
        Exchange.Builder builder = new Exchange.Builder();
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(7)));
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(187)));
        Exchange request = builder.build();
        RequestStatus requestStatus = new RequestStatus();
        otv.process(request, requestStatus);
        assertEquals(true, requestStatus.getStatusCode().equals(RequestStatus.StatusCode.OK));
    }
}
