package co.bk.javabasics.main.lombok;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;

public class PriceTest {

    @Test
    public void testBigDecimalWithLombokData() {

        Price price = new Price();
        price.setAmount(new BigDecimal("1.500"));

        Price priceTwo = new Price();
        priceTwo.setAmount(new BigDecimal("1.5"));

        assertFalse(price.equals(priceTwo));
    }

}
