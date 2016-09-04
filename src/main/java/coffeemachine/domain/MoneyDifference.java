package coffeemachine.domain;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyDifference
{
    private final int cents;

    public MoneyDifference(int cents) {
        this.cents = cents;
    }

    public int getCents() {
        return cents;
    }

    public String format() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        return numberFormat.format(Math.abs(this.getCents()) / 100.0);
    }
}
