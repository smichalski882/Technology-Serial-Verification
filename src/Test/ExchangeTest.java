package Test;

import com.company.Exchange;
import com.company.SerialNumber;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeTest {

    @Test
    void testGetCompatibleProducts(){
        Exchange.Builder builder = new Exchange.Builder();
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(8)));

        //Test builder's getCompatibleProducts
        assertInstanceOf(NavigableSet.class, builder.getCompatibleProducts());

        //Test exchange's getCompatibleProducts
        Exchange exchange = builder.build();
        assertInstanceOf(NavigableSet.class, exchange.getCompatibleProducts());

        //Check if the build is immutable
        builder.addCompatible(new SerialNumber(BigInteger.valueOf(17)));
        assertEquals(false, builder.getCompatibleProducts().equals(exchange.getCompatibleProducts()));
    }
}