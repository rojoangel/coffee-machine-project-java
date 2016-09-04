package coffeemachine;

import coffeemachine.adapter.MessageAdapter;
import coffeemachine.adapter.OrderAdapter;
import coffeemachine.domain.Money;
import coffeemachine.domain.Order;
import coffeemachine.domain.message.InsufficientFundsMessage;
import coffeemachine.ports.BeverageQuantityChecker;
import coffeemachine.ports.DrinkMaker;
import coffeemachine.ports.EmailNotifier;
import coffeemachine.ports.OrderReader;

public class CoffeeMachine
{
    private final DrinkMaker drinkMaker;
    private final OrderReader orderReader;
    private final MoneyChecker moneyChecker;
    private final OrderAdapter orderAdapter;
    private final MessageAdapter messageAdapter;
    private final SalesReporter salesReporter;
    private final BeverageQuantityChecker beverageQuantityChecker;
    private final EmailNotifier emailNotifier;

    public CoffeeMachine(
            DrinkMaker drinkMaker,
            OrderReader orderReader,
            MoneyChecker moneyChecker,
            SalesReporter salesReporter,
            BeverageQuantityChecker beverageQuantityChecker,
            EmailNotifier emailNotifier
    ) {
        this.drinkMaker = drinkMaker;
        this.orderReader = orderReader;
        this.moneyChecker = moneyChecker;
        this.salesReporter = salesReporter;
        this.beverageQuantityChecker = beverageQuantityChecker;
        this.emailNotifier = emailNotifier;
        this.orderAdapter = new OrderAdapter();
        this.messageAdapter = new MessageAdapter();
    }

    public void serveOrder() {
        Order order = orderReader.readInput();
        Money difference = moneyChecker.getDifference(order);
        if (difference.getCents() < 0) {
            displayInsufficientFundsMessage(difference);
            return;
        }
        if(beverageQuantityChecker.isEmpty(order.getDrinkType().toString())) {
            emailNotifier.notifyMissingDrink(order.getDrinkType().toString());
            return;
        }
        salesReporter.addSale(order);
        String instructions = orderAdapter.adapt(order);
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
