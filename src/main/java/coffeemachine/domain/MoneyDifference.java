package coffeemachine.domain;

import java.text.NumberFormat;
import java.util.Locale;

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

    public String format() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        return numberFormat.format(Math.abs(this.getCents()) / 100.0);
    }
}
