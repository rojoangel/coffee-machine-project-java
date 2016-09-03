package coffeemachine;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProtocolAdapterTest {

    @Test
    public void adaptsTea() throws Exception {
        Order order = new Order(DrinkType.TEA);
        ProtocolAdapter adapter = new ProtocolAdapter();
        String instructions = adapter.adapt(order);
        assertEquals("T::", instructions);
    }
}