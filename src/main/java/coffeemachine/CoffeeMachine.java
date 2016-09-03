package coffeemachine;

public class CoffeeMachine
{
    private final DrinkMaker drinkMaker;
    private final UserInputReader userInputReader;
    private final ProtocolAdapter protocolAdapter;

    public CoffeeMachine(DrinkMaker drinkMaker, UserInputReader userInputReader) {
        this.drinkMaker = drinkMaker;
        this.userInputReader = userInputReader;
        this.protocolAdapter = new ProtocolAdapter();
    }

    public void makeDrink() {
        Order order = userInputReader.readInput();
        String instructions = protocolAdapter.adapt(order);
        drinkMaker.makeDrink(instructions);
    }
}
