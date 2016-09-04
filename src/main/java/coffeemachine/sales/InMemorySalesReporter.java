package coffeemachine.sales;

import coffeemachine.SalesReporter;
import coffeemachine.domain.Order;

public class InMemorySalesReporter implements SalesReporter
{
    public void addSale(Order order) {

    }

    public SalesReport report() {
        return new SalesReport();
    }
}
