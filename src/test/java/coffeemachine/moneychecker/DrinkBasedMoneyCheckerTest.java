package coffeemachine.moneychecker;

import coffeemachine.MoneyReader;
import coffeemachine.domain.DrinkType;
import coffeemachine.domain.Money;
import coffeemachine.domain.Order;
import coffeemachine.domain.OrderableDrink;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DrinkBasedMoneyCheckerTest
{
    private Mockery context;
    private MoneyReader moneyReader;

    @Before
    public void setUp() throws Exception {
        context = new Mockery();
        moneyReader = context.mock(MoneyReader.class);
    }
    
    @Test
    public void returnsNegatedDrinkPriceCentsWhenNoFunds() throws Exception {
        int drinkPriceCents = 99;
        OrderableDrink drink = new OrderableDrink(DrinkType.COFFEE, new Money(drinkPriceCents));
        final Order order = new Order(drink);
        final Money zeroMoney = new Money(0);

        context.checking(new Expectations() {{
            oneOf(moneyReader).readMoney();
            will(returnValue(zeroMoney));
        }});

        DrinkBasedMoneyChecker checker = new DrinkBasedMoneyChecker(moneyReader);
        assertEquals(new Money(-drinkPriceCents), checker.getDifference(order));

    }

    @Test
    public void returnsNegativeDifferenceWhenNotEnoughFunds() throws Exception {
        int drinkPriceCents = 99;
        int insertedCents = 44;
        OrderableDrink drink = new OrderableDrink(DrinkType.COFFEE, new Money(drinkPriceCents));
        final Order order = new Order(drink);
        final Money insertedMoney = new Money(insertedCents);

        context.checking(new Expectations() {{
            oneOf(moneyReader).readMoney();
            will(returnValue(insertedMoney));
        }});

        DrinkBasedMoneyChecker checker = new DrinkBasedMoneyChecker(moneyReader);
        assertEquals(new Money(insertedCents-drinkPriceCents), checker.getDifference(order));
    }

    @After
    public void tearDown() throws Exception {
        context.assertIsSatisfied();
    }
}
