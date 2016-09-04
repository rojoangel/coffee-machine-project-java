package coffeemachine;

import coffeemachine.domain.DrinkType;
import coffeemachine.domain.Money;
import coffeemachine.domain.Order;
import coffeemachine.domain.OrderableDrink;
import coffeemachine.moneychecker.AlwaysEnoughMoney;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FunctionalTest {

    private Mockery context;
    private DrinkMaker drinkMaker;
    private OrderReader orderReader;
    private MoneyChecker moneyChecker;

    @Before
    public void setUp() throws Exception {
        context = new Mockery();
        drinkMaker = context.mock(DrinkMaker.class);
        orderReader = context.mock(OrderReader.class);
        moneyChecker = context.mock(MoneyChecker.class);
    }

    private CoffeeMachine configureFreeMachine() {
        return new CoffeeMachine(drinkMaker, orderReader, new AlwaysEnoughMoney());
    }

    private CoffeeMachine configureMoneyCheckingMachine() {
        return new CoffeeMachine(drinkMaker, orderReader, moneyChecker);
    }

    @Test
    public void forwardsOneTeaWithOneSugarAndAStickOrder() throws Exception {
        final Order order = new Order(OrderableDrink.TEA);
        order.addSugar();

        context.checking(new Expectations() {{
            oneOf(orderReader).readInput();
            will(returnValue(order));

            oneOf(drinkMaker).process("T:1:0");
        }});

        CoffeeMachine coffeeMachine = configureFreeMachine();
        coffeeMachine.makeDrink();
    }

    @Test
    public void forwardsOneHotChocolateWithNoSugarAndNoStickOrder() throws Exception {
        final Order order = new Order(OrderableDrink.HOT_CHOCOLATE);

        context.checking(new Expectations() {{
            oneOf(orderReader).readInput();
            will(returnValue(order));

            oneOf(drinkMaker).process("H::");
        }});

        CoffeeMachine coffeeMachine = configureFreeMachine();
        coffeeMachine.makeDrink();
    }

    @Test
    public void forwardsOneCoffeeWithTwoSugarsAndAStickOrder() throws Exception {
        final Order order = new Order(OrderableDrink.COFFEE);
        order.addSugar();
        order.addSugar();

        context.checking(new Expectations() {{
            oneOf(orderReader).readInput();
            will(returnValue(order));

            oneOf(drinkMaker).process("C:2:0");
        }});

        CoffeeMachine coffeeMachine = configureFreeMachine();
        coffeeMachine.makeDrink();
    }

    @Test
    public void doesNotForwardOrderIfNotEnoughMoneyInserted() throws Exception {
        final Order order = new Order(new OrderableDrink(DrinkType.TEA, null));

        context.checking(new Expectations() {{
            oneOf(orderReader).readInput();
            will(returnValue(order));

            oneOf(moneyChecker).getDifference(order);
            will(returnValue(new Money(-40)));

            oneOf(drinkMaker).process("M:There are 0,40 € missing");
        }});

        CoffeeMachine coffeeMachine = configureMoneyCheckingMachine();
        coffeeMachine.makeDrink();
    }

    @Test
    public void forwardsOrangeJuice() throws Exception {
        final Order order = new Order(OrderableDrink.ORANGE_JUICE);

        context.checking(new Expectations() {{
            oneOf(orderReader).readInput();
            will(returnValue(order));

            oneOf(drinkMaker).process("O::");
        }});

        CoffeeMachine coffeeMachine = configureFreeMachine();
        coffeeMachine.makeDrink();
    }

    @Test
    public void forwardsOrderIfMoneyInsertedGreaterThanPrice() throws Exception {
        final Order order = new Order(new OrderableDrink(DrinkType.TEA, null));

        context.checking(new Expectations() {{
            oneOf(orderReader).readInput();
            will(returnValue(order));

            oneOf(moneyChecker).getDifference(order);
            will(returnValue(new Money(40)));

            oneOf(drinkMaker).process("T::");
        }});

        CoffeeMachine coffeeMachine = configureMoneyCheckingMachine();
        coffeeMachine.makeDrink();
    }

    @Test
    public void forwardsOrderIfMoneyInsertedEqualsThanPrice() throws Exception {
        final Order order = new Order(new OrderableDrink(DrinkType.TEA, null));

        context.checking(new Expectations() {{
            oneOf(orderReader).readInput();
            will(returnValue(order));

            oneOf(moneyChecker).getDifference(order);
            will(returnValue(new Money(0)));

            oneOf(drinkMaker).process("T::");
        }});

        CoffeeMachine coffeeMachine = configureMoneyCheckingMachine();
        coffeeMachine.makeDrink();
    }

    @After
    public void tearDown() throws Exception {
        context.assertIsSatisfied();
    }
}