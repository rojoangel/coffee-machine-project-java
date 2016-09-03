package coffeemachine;

import org.junit.Test;

import static org.junit.Assert.*;

public class MessageAdapterTest {

    @Test
    public void adaptsMessage() throws Exception {
        MessageAdapter adapter = new MessageAdapter();
        String instructions = adapter.adapt("Hello World!");
        assertEquals("M:Hello World!", instructions);
    }
}