package coffeemachine;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class LogicTest {

    @Test
    public void makesOneTeaWithOneSugarAndAStick() throws Exception {

        final Order order = new Order("Tea");
        order.addSugar();

        Mockery context = new Mockery();
        final DrinkMaker drinkMaker = context.mock(DrinkMaker.class);
        final UserInputReader userInputReader = context.mock(UserInputReader.class);
        context.checking(new Expectations() {{
            oneOf(userInputReader).readInput();
            will(returnValue(order));

            oneOf(drinkMaker).makeDrink("T:1:0");
        }});

        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, userInputReader);
        coffeeMachine.makeDrink();

        context.assertIsSatisfied();
    }
}