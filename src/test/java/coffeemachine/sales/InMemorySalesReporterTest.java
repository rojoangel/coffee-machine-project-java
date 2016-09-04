package coffeemachine.sales;

import coffeemachine.SalesReportWriter;
import coffeemachine.SalesReporter;
import coffeemachine.domain.DrinkType;
import coffeemachine.domain.Money;
import coffeemachine.domain.Order;
import coffeemachine.domain.OrderableDrink;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class InMemorySalesReporterTest {
    private Mockery context;
    private SalesReportWriter salesReportWriter;

    @Before
    public void setUp() throws Exception {
        context = new Mockery();
        salesReportWriter = context.mock(SalesReportWriter.class);
    }

    @Test
    public void reportsNoSales() throws Exception {
        context.checking(new Expectations() {{
            oneOf(salesReportWriter).write(new SalesReport());
        }});

        SalesReporter salesReporter = new InMemorySalesReporter(salesReportWriter);
        salesReporter.report();
    }

    @Test
    public void reportsSales() throws Exception {

        SalesReporter salesReporter = new InMemorySalesReporter(salesReportWriter);
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
        final SalesReport expectedSalesReport = new SalesReport(drinksSold, salesAmount);

        context.checking(new Expectations() {{
            oneOf(salesReportWriter).write(expectedSalesReport);
        }});

        salesReporter.report();
    }

    @After
    public void tearDown() throws Exception {
        context.assertIsSatisfied();
    }
}