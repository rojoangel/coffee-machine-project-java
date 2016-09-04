package coffeemachine.adapter;

import coffeemachine.domain.DrinkType;
import coffeemachine.domain.Order;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderAdapterTest {

    @Test
    public void adaptsTea() throws Exception {
        Order order = new Order(DrinkType.TEA);
        OrderAdapter adapter = new OrderAdapter();
        String instructions = adapter.adapt(order);
        assertEquals("T::", instructions);
    }

    @Test
    public void adaptsCoffee() throws Exception {
        Order order = new Order(DrinkType.COFFEE);
        OrderAdapter adapter = new OrderAdapter();
        String instructions = adapter.adapt(order);
        assertEquals("C::", instructions);
    }

    @Test
    public void adaptsHotChocolate() throws Exception {
        Order order = new Order(DrinkType.HOT_CHOCOLATE);
        OrderAdapter adapter = new OrderAdapter();
        String instructions = adapter.adapt(order);
        assertEquals("H::", instructions);
    }

    @Test
    public void adaptsHotChocolateWithOneSugar() throws Exception {
        Order order = new Order(DrinkType.HOT_CHOCOLATE);
        order.addSugar();
        OrderAdapter adapter = new OrderAdapter();
        String instructions = adapter.adapt(order);
        assertEquals("H:1:0", instructions);
    }

}