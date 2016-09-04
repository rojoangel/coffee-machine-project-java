package coffeemachine.sales;

import coffeemachine.SalesReporter;
import org.junit.Test;

import static org.junit.Assert.*;

public class InMemorySalesReporterTest
{
    @Test
    public void reportsNoSales() throws Exception {
        SalesReporter salesReporter = new InMemorySalesReporter();
        SalesReport report = salesReporter.report();
        assertEquals(new SalesReport(), report);
    }
}