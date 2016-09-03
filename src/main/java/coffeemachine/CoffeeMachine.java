package coffeemachine;

public class CoffeeMachine
{
    private final DrinkMaker drinkMaker;
    private final UserInputReader userInputReader;

    public CoffeeMachine(DrinkMaker drinkMaker, UserInputReader userInputReader) {
        this.drinkMaker = drinkMaker;
        this.userInputReader = userInputReader;
    }

    public void makeDrink() {
        Order order = userInputReader.readInput();
        if ("Tea".equals(order.getDrinkType())) {
            drinkMaker.makeDrink("T:1:0");
            return;
        }
        drinkMaker.makeDrink("H::");

    }
}
