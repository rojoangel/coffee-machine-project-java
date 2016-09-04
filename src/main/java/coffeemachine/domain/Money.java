package coffeemachine.domain;

import java.text.NumberFormat;
import java.util.Locale;

public class Money
{
    private final int cents;

    public Money(int cents) {
        this.cents = cents;
    }

    public int getCents() {
        return cents;
    }

    public String format() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        return numberFormat.format(Math.abs(this.getCents()) / 100.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        return cents == money.cents;

    }

    @Override
    public int hashCode() {
        return cents;
    }

    @Override
    public String toString() {
        return "Money{" +
                "cents=" + cents +
                '}';
    }
}
