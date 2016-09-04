package coffeemachine;

import coffeemachine.domain.Order;
import coffeemachine.sales.SalesReport;

public interface SalesReporter
{
    void addSale(Order order);

    SalesReport report();
}
