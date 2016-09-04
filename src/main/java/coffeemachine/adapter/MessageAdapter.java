package coffeemachine.adapter;

import coffeemachine.domain.Message;

public class MessageAdapter {

    public String adapt(Message message) {
        return adapt(message.getText());
    }

    private String adapt(String message)
    {
        return "M:" + message;
    }
}