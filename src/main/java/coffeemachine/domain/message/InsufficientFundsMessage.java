package coffeemachine.domain.message;

import coffeemachine.domain.Money;
import coffeemachine.domain.Message;

public class InsufficientFundsMessage implements Message
{
    private Money difference;

    public InsufficientFundsMessage(Money difference) {
        this.difference = difference;
    }

    public String getText() {
        return "There are " + difference.format() + " missing";
    }
}
