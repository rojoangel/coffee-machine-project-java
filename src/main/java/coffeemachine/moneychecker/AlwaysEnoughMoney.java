package coffeemachine.moneychecker;

import coffeemachine.MoneyChecker;
import coffeemachine.domain.Money;
import coffeemachine.domain.Order;

public class AlwaysEnoughMoney implements MoneyChecker
{
    public Money getDifference(Order order) {
        return new Money(0);
    }
}
