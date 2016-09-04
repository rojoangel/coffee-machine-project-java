package coffeemachine;

import coffeemachine.domain.DrinkType;
import coffeemachine.domain.MoneyDifference;
import coffeemachine.domain.Order;
import coffeemachine.moneychecker.AlwaysEnoughMoney;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FunctionalTest {

    private Mockery context;
    private DrinkMaker drinkMaker;
    private UserInputReader userInputReader;
    private MoneyChecker moneyChecker;

    @Before
    public void setUp() throws Exception {
        context = new Mockery();
        drinkMaker = context.mock(DrinkMaker.class);
        userInputReader = context.mock(UserInputReader.class);
        moneyChecker = context.mock(MoneyChecker.class);
    }

    private CoffeeMachine configureFreeMachine() {
        return new CoffeeMachine(drinkMaker, userInputReader, new AlwaysEnoughMoney());
    }

    private CoffeeMachine configureMoneyCheckingMachine() {
        return new CoffeeMachine(drinkMaker, userInputReader, moneyChecker);
    }

    @Test
    public void forwardsOneTeaWithOneSugarAndAStickOrder() throws Exception {
        final Order order = new Order(DrinkType.TEA);
        order.addSugar();

        context.checking(new Expectations() {{
            oneOf(userInputReader).readInput();
            will(returnValue(order));

            oneOf(drinkMaker).process("T:1:0");
        }});

        CoffeeMachine coffeeMachine = configureFreeMachine();
        coffeeMachine.makeDrink();
    }

    @Test
    public void forwardsOneHotChocolateWithNoSugarAndNoStickOrder() throws Exception {
        final Order order = new Order(DrinkType.HOT_CHOCOLATE);

        context.checking(new Expectations() {{
            oneOf(userInputReader).readInput();
            will(returnValue(order));

            oneOf(drinkMaker).process("H::");
        }});

        CoffeeMachine coffeeMachine = configureFreeMachine();
        coffeeMachine.makeDrink();
    }

    @Test
    public void forwardsOneCoffeeWithTwoSugarsAndAStickOrder() throws Exception {
        final Order order = new Order(DrinkType.COFFEE);
        order.addSugar();
        order.addSugar();

        context.checking(new Expectations() {{
            oneOf(userInputReader).readInput();
            will(returnValue(order));

            oneOf(drinkMaker).process("C:2:0");
        }});

        CoffeeMachine coffeeMachine = configureFreeMachine();
        coffeeMachine.makeDrink();
    }

    @Test
    public void doesNotforwardTeaOrderIfNotEnoughMoney() throws Exception {
        final Order order = new Order(DrinkType.TEA);

        context.checking(new Expectations() {{
            oneOf(userInputReader).readInput();
            will(returnValue(order));

            oneOf(moneyChecker).getMoneyDifference(order);
            will(returnValue(new MoneyDifference(-40, "EUR")));

            oneOf(drinkMaker).process("M:There are 0,40 € missing");
        }});

        CoffeeMachine coffeeMachine = configureMoneyCheckingMachine();
        coffeeMachine.makeDrink();
    }

    @After
    public void tearDown() throws Exception {
        context.assertIsSatisfied();
    }
}