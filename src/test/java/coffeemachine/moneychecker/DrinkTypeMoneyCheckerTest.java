package coffeemachine.moneychecker;

import coffeemachine.MoneyReader;
import coffeemachine.domain.DrinkType;
import coffeemachine.domain.Money;
import coffeemachine.domain.Order;
import coffeemachine.domain.OrderableDrink;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import static org.junit.Assert.*;

public class DrinkTypeMoneyCheckerTest
{
    @Test
    public void returnsDifferenceWhenNoFunds() throws Exception {
        final Order order = new Order(OrderableDrink.TEA);
        final Money zeroMoney = new Money(0);
        final Money expectedMoney = new Money(-40);

        Mockery context = new Mockery();
        final MoneyReader moneyReader = context.mock(MoneyReader.class);

        context.checking(new Expectations() {{
            oneOf(moneyReader).readMoney();
            will(returnValue(zeroMoney));
        }});

        DrinkTypeMoneyChecker checker = new DrinkTypeMoneyChecker(moneyReader);
        assertEquals(expectedMoney, checker.getDifference(order));

        context.assertIsSatisfied();
    }
}
