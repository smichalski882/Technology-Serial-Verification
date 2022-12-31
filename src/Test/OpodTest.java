package Test;
import com.company.*;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.gen5.api.Assertions.assertEquals;

//Test Class for OPOD product
public class OpodTest {

    @Test
    void testGetProduct(){
        Set<String> opodSet = new HashSet<>();
        opodSet.add("for all your music listening needs. ");
        opodSet.add("starting at the low low price of $10,000!!");
        Optional<Set<String>> description = Optional.of(opodSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(17));
        Opod opod = Opod.getInstance(serialNumber, description);
        assertEquals(ProductType.OPOD, opod.getProductType());
    }

    @Test
    void testGetName(){
        Set<String> opodSet = new HashSet<>();
        opodSet.add("for all your music listening needs. ");
        opodSet.add("starting at the low low price of $10,000!!");
        Optional<Set<String>> description = Optional.of(opodSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(17));
        Opod opod = Opod.getInstance(serialNumber, description);
        assertEquals("oPod", opod.getProductName());
    }

    @Test
    void testIsValidSerialNumber(){
        Set<String> opodSet = new HashSet<>();
        opodSet.add("for all your music listening needs. ");
        opodSet.add("starting at the low low price of $10,000!!");
        Optional<Set<String>> description = Optional.of(opodSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(18));
        Opod opod = Opod.getInstance(serialNumber, description);
        assertEquals(true, opod.isValidSerialNumber(opod.getSerialNumber()));
    }

    @Test
    void testRefundProcess() throws RequestException, ProductException {
        Set<String> opodSet = new HashSet<>();
        opodSet.add("for all your music listening needs. ");
        opodSet.add("starting at the low low price of $10,000!!");
        Optional<Set<String>> description = Optional.of(opodSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(24));
        Opod opod = Opod.getInstance(serialNumber, description);
        Refund.Builder builder = new Refund.Builder();
        builder.setRma(BigInteger.valueOf(24));
        Refund refund = builder.build();
        RequestStatus requestStatus = new RequestStatus();
        opod.process(refund, requestStatus);
        assertEquals(true, requestStatus.getStatusCode().equals(RequestStatus.StatusCode.OK));
    }

    @Test
    void testExchangeProcess() throws ProductException {
        Set<String> opodSet = new HashSet<>();
        opodSet.add("for all your music listening needs. ");
        opodSet.add("starting at the low low price of $10,000!!");
        Optional<Set<String>> description = Optional.of(opodSet);
        SerialNumber serialNumber = new SerialNumber(BigInteger.valueOf(18));
        Opod opod = Opod.getInstance(serialNumber, description);
        Exchange.Builder builder = new Exchange.Builder();
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(7)));
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(18)));
        Exchange request = builder.build();
        RequestStatus requestStatus = new RequestStatus();
        opod.process(request, requestStatus);
        assertEquals(true, requestStatus.getStatusCode().equals(RequestStatus.StatusCode.OK));
    }

}
