package coffeemachine;

import coffeemachine.adapter.MessageAdapter;
import coffeemachine.adapter.OrderAdapter;
import coffeemachine.domain.Money;
import coffeemachine.domain.Order;
import coffeemachine.domain.message.InsufficientFundsMessage;

public class CoffeeMachine
{
    private final DrinkMaker drinkMaker;
    private final OrderReader orderReader;
    private final MoneyChecker moneyChecker;
    private final OrderAdapter orderAdapter;
    private final MessageAdapter messageAdapter;
    private final SalesReporter salesReporter;

    public CoffeeMachine(
            DrinkMaker drinkMaker,
            OrderReader orderReader,
            MoneyChecker moneyChecker,
            SalesReporter salesReporter
    ) {
        this.drinkMaker = drinkMaker;
        this.orderReader = orderReader;
        this.moneyChecker = moneyChecker;
        this.salesReporter = salesReporter;
        this.orderAdapter = new OrderAdapter();
        this.messageAdapter = new MessageAdapter();
    }

    public void serveOrder() {
        Order order = orderReader.readInput();
        String instructions = orderAdapter.adapt(order);
        Money difference = moneyChecker.getDifference(order);
        if (difference.getCents() < 0) {
            displayInsufficientFundsMessage(difference);
            return;
        }
        salesReporter.addSale(order);
        drinkMaker.process(instructions);
    }

    private void displayInsufficientFundsMessage(Money difference) {
        InsufficientFundsMessage message = new InsufficientFundsMessage(difference);
        String instructions = messageAdapter.adapt(message);
        drinkMaker.process(instructions);
    }

    public void reportSales() {
        salesReporter.report();
    }
}
