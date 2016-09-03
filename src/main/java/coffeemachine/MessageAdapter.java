package coffeemachine;

public class MessageAdapter {

    String notify(String message)
    {
        return "M:" + message;
    }
}