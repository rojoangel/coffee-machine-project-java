package coffeemachine.moneychecker;

import coffeemachine.MoneyChecker;
import coffeemachine.MoneyReader;
import coffeemachine.domain.Money;
import coffeemachine.domain.Order;

public class DrinkTypeMoneyChecker implements MoneyChecker
{
    private MoneyReader moneyReader;

    public DrinkTypeMoneyChecker(MoneyReader moneyReader) {

        this.moneyReader = moneyReader;
    }

    public Money getDifference(Order order) {
        return new Money(
                moneyReader.readMoney().getCents() - order.getDrink().getPrice().getCents()
        );
    }
}
