package coffeemachine;

public class CoffeeMachine
{
    private final DrinkMaker drinkMaker;
    private final UserInputReader userInputReader;
    private final ProtocolAdapter orderAdapter;

    public CoffeeMachine(DrinkMaker drinkMaker, UserInputReader userInputReader) {
        this.drinkMaker = drinkMaker;
        this.userInputReader = userInputReader;
        this.orderAdapter = new ProtocolAdapter();
    }

    public void makeDrink() {
        Order order = userInputReader.readInput();
        String instructions = orderAdapter.adapt(order);
        drinkMaker.process(instructions);
    }

    public void displayMessage(String message) {
        String instructions = "M:" + message;
        drinkMaker.process(instructions);
    }
}
