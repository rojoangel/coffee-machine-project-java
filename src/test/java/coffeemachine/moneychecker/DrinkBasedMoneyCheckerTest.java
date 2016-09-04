package coffeemachine.moneychecker;

import coffeemachine.ports.MoneyReader;
import coffeemachine.domain.DrinkType;
import coffeemachine.domain.Money;
import coffeemachine.domain.Order;
import coffeemachine.domain.OrderableDrink;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DrinkBasedMoneyCheckerTest
{

    @Parameters(name = "{index}: insertedCents={0}, drinkPriceCents={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, 99},
                {44, 99},
                {99, 99},
                {123, 99}
        });
    }

    private int insertedCents;
    private int drinkPriceCents;
    private Mockery context;
    private MoneyReader moneyReader;

    @Before
    public void setUp() throws Exception {
        context = new Mockery();
        moneyReader = context.mock(MoneyReader.class);
    }

    public DrinkBasedMoneyCheckerTest(int insertedCents, int drinkPriceCents) {
        this.insertedCents = insertedCents;
        this.drinkPriceCents = drinkPriceCents;
    }

    @Test
    public void returnsMoneyDifference() throws Exception {
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
