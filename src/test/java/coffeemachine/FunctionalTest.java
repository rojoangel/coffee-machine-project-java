package coffeemachine;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FunctionalTest {

    private Mockery context;
    private DrinkMaker drinkMaker;
    private UserInputReader userInputReader;

    @Before
    public void setUp() throws Exception {
        context = new Mockery();
        drinkMaker = context.mock(DrinkMaker.class);
        userInputReader = context.mock(UserInputReader.class);
    }

    @Test
    public void forwardsOneTeaWithOneSugarAndAStickOrder() throws Exception
    {
        final Order order = new Order(DrinkType.TEA);
        order.addSugar();

        context.checking(new Expectations() {{
            oneOf(userInputReader).readInput();
            will(returnValue(order));

            oneOf(drinkMaker).process("T:1:0");
        }});

        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, userInputReader);
        coffeeMachine.makeDrink();
    }

    @Test
    public void forwardsOneHotChocolateWithNoSugarAndNoStickOrder() throws Exception
    {
        final Order order = new Order(DrinkType.HOT_CHOCOLATE);

        context.checking(new Expectations() {{
            oneOf(userInputReader).readInput();
            will(returnValue(order));

            oneOf(drinkMaker).process("H::");
        }});

        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, userInputReader);
        coffeeMachine.makeDrink();
    }

    @Test
    public void forwardsOneCoffeeWithTwoSugarsAndAStickOrder() throws Exception
    {
        final Order order = new Order(DrinkType.COFFEE);
        order.addSugar();
        order.addSugar();

        context.checking(new Expectations() {{
            oneOf(userInputReader).readInput();
            will(returnValue(order));

            oneOf(drinkMaker).process("C:2:0");
        }});

        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, userInputReader);
        coffeeMachine.makeDrink();
    }

    @Test
    public void forwardsMessages() throws Exception {

        context.checking(new Expectations() {{
            ignoring(userInputReader);

            oneOf(drinkMaker).process("M:Hello user");
        }});

        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, userInputReader);
        coffeeMachine.displayMessage("Hello user");

    }

    @After
    public void tearDown() throws Exception {
        context.assertIsSatisfied();
    }
}