package coffeemachine.sales;

import coffeemachine.SalesReporter;
import coffeemachine.domain.DrinkType;
import coffeemachine.domain.Money;
import coffeemachine.domain.Order;

import java.util.HashMap;
import java.util.Map;

public class InMemorySalesReporter implements SalesReporter
{
    private Map<DrinkType, Integer> drinksSold;
    private Money salesAmount;

    public InMemorySalesReporter() {
        this.drinksSold = new HashMap<DrinkType, Integer>();
        this.salesAmount = new Money(0);
    }

    public void addSale(Order order) {
        if (!this.drinksSold.containsKey(order.getDrinkType())) {
            this.drinksSold.put(order.getDrinkType(), 0);
        }

        this.drinksSold.put(
                order.getDrinkType(),
                this.drinksSold.get(order.getDrinkType()) + 1
        );

        this.salesAmount = new Money(
                this.salesAmount.getCents() + order.getDrink().getPrice().getCents()
        );
    }

    public SalesReport report() {
        return new SalesReport(this.drinksSold, this.salesAmount);
    }
}
