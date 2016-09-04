package coffeemachine.sales;

import coffeemachine.domain.DrinkType;
import coffeemachine.domain.Money;

import java.util.HashMap;
import java.util.Map;

public class SalesReport
{
    private Map<DrinkType, Integer> drinksSold;
    private Money salesAmount;

    public SalesReport() {
        this.drinksSold = new HashMap<DrinkType, Integer>();
        this.salesAmount = new Money(0);
    }

    public SalesReport(Map<DrinkType, Integer> drinksSold, Money salesAmount) {
        this.drinksSold = drinksSold;
        this.salesAmount = salesAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalesReport that = (SalesReport) o;

        if (drinksSold != null ? !drinksSold.equals(that.drinksSold) : that.drinksSold != null) return false;
        return salesAmount != null ? salesAmount.equals(that.salesAmount) : that.salesAmount == null;

    }

    @Override
    public int hashCode() {
        int result = drinksSold != null ? drinksSold.hashCode() : 0;
        result = 31 * result + (salesAmount != null ? salesAmount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SalesReport{" +
                "drinksSold=" + drinksSold +
                ", salesAmount=" + salesAmount +
                '}';
    }
}
