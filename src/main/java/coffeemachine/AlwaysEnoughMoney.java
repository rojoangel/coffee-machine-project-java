package coffeemachine;

public class AlwaysEnoughMoney implements MoneyChecker
{
    public MoneyDifference getMoneyDifference(Order order) {
        return new MoneyDifference(0, "EUR");
    }
}
