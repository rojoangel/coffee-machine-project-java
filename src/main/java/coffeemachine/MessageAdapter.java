package coffeemachine;

public class MessageAdapter {

    public String adapt(String message)
    {
        return "M:" + message;
    }

    public String adapt(Message message) {
        return adapt(message.getText());
    }
}