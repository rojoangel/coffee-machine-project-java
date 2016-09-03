package coffeemachine;

public class CoffeeMachine
{
    private final DrinkMaker drinkMaker;
    private final UserInputReader userInputReader;
    private final MoneyChecker moneyChecker;
    private final OrderAdapter orderAdapter;
    private final MessageAdapter messageAdapter;

    public CoffeeMachine(DrinkMaker drinkMaker, UserInputReader userInputReader, MoneyChecker moneyChecker) {
        this.drinkMaker = drinkMaker;
        this.userInputReader = userInputReader;
        this.moneyChecker = moneyChecker;
        this.orderAdapter = new OrderAdapter();
        this.messageAdapter = new MessageAdapter();
    }

    public void makeDrink() {
        Order order = userInputReader.readInput();
        String instructions = orderAdapter.adapt(order);
        MoneyDifference difference = moneyChecker.getMoneyDifference(order);
        if (difference.getCents() < 0) {
            displayMessage("There are 0,40 EUR missing");
            return;
        }
        drinkMaker.process(instructions);
    }

    public void displayMessage(String message) {
        String instructions = messageAdapter.notify(message);
        drinkMaker.process(instructions);
    }
}
