package coffeemachine.ports;

import coffeemachine.domain.Order;

public interface OrderReader
{
    Order readInput();
}
