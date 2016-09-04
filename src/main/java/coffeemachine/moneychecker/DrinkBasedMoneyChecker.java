package coffeemachine.moneychecker;

import coffeemachine.MoneyChecker;
import coffeemachine.ports.MoneyReader;
import coffeemachine.domain.Money;
import coffeemachine.domain.Order;

public class DrinkBasedMoneyChecker implements MoneyChecker
{
    private MoneyReader moneyReader;

    public DrinkBasedMoneyChecker(MoneyReader moneyReader) {

        this.moneyReader = moneyReader;
    }

    public Money getDifference(Order order) {
        return new Money(
                moneyReader.readMoney().getCents() - order.getDrink().getPrice().getCents()
        );
    }
}
