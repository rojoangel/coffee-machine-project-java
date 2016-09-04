package coffeemachine;

import coffeemachine.adapter.MessageAdapter;
import coffeemachine.adapter.OrderAdapter;
import coffeemachine.domain.MoneyDifference;
import coffeemachine.domain.Order;
import coffeemachine.domain.message.InsufficientFundsMessage;

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
            displayInsufficientFundsMessage(difference);
            return;
        }
        drinkMaker.process(instructions);
    }

    private void displayInsufficientFundsMessage(MoneyDifference difference) {
        InsufficientFundsMessage message = new InsufficientFundsMessage(difference);
        String instructions = messageAdapter.adapt(message);
        drinkMaker.process(instructions);
    }
}
