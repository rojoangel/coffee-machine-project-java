package coffeemachine.sales;

import coffeemachine.SalesReporter;
import coffeemachine.domain.DrinkType;
import coffeemachine.domain.Money;
import coffeemachine.domain.Order;
import coffeemachine.domain.OrderableDrink;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class InMemorySalesReporterTest
{
    @Test
    public void reportsNoSales() throws Exception {
        SalesReporter salesReporter = new InMemorySalesReporter();
        SalesReport report = salesReporter.report();
        assertEquals(new SalesReport(), report);
    }

    @Test
    public void reportsSales() throws Exception {
        SalesReporter salesReporter = new InMemorySalesReporter();

        salesReporter.addSale(
                new Order(new OrderableDrink(DrinkType.ORANGE_JUICE, new Money(99))));
        salesReporter.addSale(
                new Order(new OrderableDrink(DrinkType.COFFEE, new Money(23))));
        salesReporter.addSale(
                new Order(new OrderableDrink(DrinkType.COFFEE, new Money(23))));

        Map<DrinkType, Integer> drinksSold = new HashMap<DrinkType, Integer>();
        drinksSold.put(DrinkType.ORANGE_JUICE, 1);
        drinksSold.put(DrinkType.COFFEE, 2);

        Money salesAmount = new Money(99 + 23 + 23);

        SalesReport expectedSalesReport = new SalesReport(drinksSold, salesAmount);
        SalesReport report = salesReporter.report();
        assertEquals(expectedSalesReport, report);

    }
}