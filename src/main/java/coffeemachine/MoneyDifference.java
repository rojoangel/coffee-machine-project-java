package coffeemachine;

public class MoneyDifference
{
    private final int cents;
    private final String currency;

    public MoneyDifference(int cents, String currency) {
        this.cents = cents;
        this.currency = currency;
    }

    public int getCents() {
        return cents;
    }

    public String getCurrency() {
        return currency;
    }
}
