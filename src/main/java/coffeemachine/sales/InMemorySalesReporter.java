package coffeemachine.sales;

import coffeemachine.SalesReportWriter;
import coffeemachine.SalesReporter;
import coffeemachine.domain.DrinkType;
import coffeemachine.domain.Money;
import coffeemachine.domain.Order;

import java.util.HashMap;
import java.util.Map;

public class InMemorySalesReporter implements SalesReporter
{
    private SalesReportWriter salesReportWriter;
    private Map<DrinkType, Integer> drinksSold;
    private Money salesAmount;


    public InMemorySalesReporter(SalesReportWriter salesReportWriter) {
        this.salesReportWriter = salesReportWriter;
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

    public void report() {
        salesReportWriter.write(
                new SalesReport(this.drinksSold, this.salesAmount)
        );
    }
}
