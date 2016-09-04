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

public class DrinkBasedMoneyCheckerTest
{
    @Test
    public void returnsNegatedDrinkPriceCentsWhenNoFunds() throws Exception {
        int drinkPriceCents = 99;
        OrderableDrink drink = new OrderableDrink(DrinkType.COFFEE, new Money(drinkPriceCents));
        final Order order = new Order(drink);
        final Money zeroMoney = new Money(0);

        Mockery context = new Mockery();
        final MoneyReader moneyReader = context.mock(MoneyReader.class);

        context.checking(new Expectations() {{
            oneOf(moneyReader).readMoney();
            will(returnValue(zeroMoney));
        }});

        DrinkBasedMoneyChecker checker = new DrinkBasedMoneyChecker(moneyReader);
        assertEquals(new Money(-drinkPriceCents), checker.getDifference(order));

        context.assertIsSatisfied();
    }

    @Test
    public void returnsNegativeDifferenceWhenNotEnoughFunds() throws Exception {
        int drinkPriceCents = 99;
        int insertedCents = 44;
        OrderableDrink drink = new OrderableDrink(DrinkType.COFFEE, new Money(drinkPriceCents));
        final Order order = new Order(drink);
        final Money insertedMoney = new Money(insertedCents);

        Mockery context = new Mockery();
        final MoneyReader moneyReader = context.mock(MoneyReader.class);

        context.checking(new Expectations() {{
            oneOf(moneyReader).readMoney();
            will(returnValue(insertedMoney));
        }});

        DrinkBasedMoneyChecker checker = new DrinkBasedMoneyChecker(moneyReader);
        assertEquals(new Money(insertedCents-drinkPriceCents), checker.getDifference(order));

        context.assertIsSatisfied();
    }
}
