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
        String instructions = adapt(order);
        drinkMaker.makeDrink(instructions);

    }

    private String adapt(Order order) {
        String instructions = "H::";
        if (DrinkType.COFFEE.equals(order.getDrinkType())) {
            instructions = "C:2:0";
        }
        if (DrinkType.TEA.equals(order.getDrinkType())) {
            instructions = "T:1:0";
        }
        return instructions;
    }
}
