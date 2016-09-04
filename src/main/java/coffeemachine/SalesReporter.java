package coffeemachine;

import coffeemachine.domain.Order;

public interface SalesReporter
{
    void addSale(Order order);

    void report();
}
