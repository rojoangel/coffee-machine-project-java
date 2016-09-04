package coffeemachine;

import coffeemachine.domain.MoneyDifference;
import coffeemachine.domain.Order;

public interface MoneyChecker
{
    MoneyDifference getDifference(Order order);
}
