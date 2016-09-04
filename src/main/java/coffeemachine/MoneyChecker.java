package coffeemachine;

import coffeemachine.domain.MoneyDifference;
import coffeemachine.domain.Order;

public interface MoneyChecker
{
    MoneyDifference getMoneyDifference(Order order);
}
