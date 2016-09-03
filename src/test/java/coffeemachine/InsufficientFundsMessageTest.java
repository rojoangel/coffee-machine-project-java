package coffeemachine;

import org.junit.Test;

import static org.junit.Assert.*;

public class InsufficientFundsMessageTest {

    @Test
    public void generatesTheAppropriateTest() throws Exception {
        MoneyDifference difference = new MoneyDifference(-99, "EUR");
        InsufficientFundsMessage message = new InsufficientFundsMessage(difference);
        assertEquals("There are 0,99 € missing", message.getText());
    }
}