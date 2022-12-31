package Test;

import com.company.Refund;
import com.company.RequestException;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class RefundTest {

    @Test
    void testGetRma() throws RequestException {
        //Testing the builder's getRma method
        Refund.Builder builder = new Refund.Builder();
        builder.setRma(BigInteger.valueOf(2));
        assertEquals(true, builder.getRma() == BigInteger.valueOf(2));

        //Testing the getRma of the Refund after it has been built
        Refund refund = builder.build();
        assertEquals(true, refund.getRma() == BigInteger.valueOf(2));

        //Testing to make sure changing the builder doesn't change the class information, immutability
        builder.setRma(BigInteger.valueOf(5));
        assertEquals(false, refund.getRma() == builder.getRma());
    }
}