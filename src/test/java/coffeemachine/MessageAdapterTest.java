package coffeemachine;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageAdapterTest {

    @Test
    public void adaptsMessage() throws Exception {
        Mockery context = new Mockery();
        final Message message = context.mock(Message.class);

        context.checking(new Expectations() {{
            oneOf(message).getText();
            will(returnValue("Hello World!"));
        }});

        MessageAdapter adapter = new MessageAdapter();
        String instructions = adapter.adapt(message);
        assertEquals("M:Hello World!", instructions);
    }
}