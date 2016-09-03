package coffeemachine;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogicTest {

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
    public void makesOneTeaWithOneSugarAndAStick() throws Exception
    {
        final Order order = new Order("Tea");
        order.addSugar();

        context.checking(new Expectations() {{
            oneOf(userInputReader).readInput();
            will(returnValue(order));

            oneOf(drinkMaker).makeDrink("T:1:0");
        }});

        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, userInputReader);
        coffeeMachine.makeDrink();
    }

    @After
    public void tearDown() throws Exception {
        context.assertIsSatisfied();
    }
}