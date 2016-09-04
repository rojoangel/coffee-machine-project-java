package coffeemachine;

import coffeemachine.domain.Money;
import coffeemachine.domain.Order;

public interface MoneyChecker
{
    Money getDifference(Order order);
}
