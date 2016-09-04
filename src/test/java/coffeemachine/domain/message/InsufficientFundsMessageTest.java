package coffeemachine.domain.message;

import coffeemachine.domain.MoneyDifference;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InsufficientFundsMessageTest {

    @Test
    public void generatesTheAppropriateTest() throws Exception {
        MoneyDifference difference = new MoneyDifference(-99);
        InsufficientFundsMessage message = new InsufficientFundsMessage(difference);
        assertEquals("There are 0,99 € missing", message.getText());
    }
}