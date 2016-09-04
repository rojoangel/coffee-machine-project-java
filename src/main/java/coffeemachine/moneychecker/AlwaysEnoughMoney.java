package coffeemachine.moneychecker;

import coffeemachine.MoneyChecker;
import coffeemachine.domain.MoneyDifference;
import coffeemachine.domain.Order;

public class AlwaysEnoughMoney implements MoneyChecker
{
    public MoneyDifference getDifference(Order order) {
        return new MoneyDifference(0);
    }
}
