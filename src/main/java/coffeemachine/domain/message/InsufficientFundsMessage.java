package coffeemachine.domain.message;

import coffeemachine.domain.MoneyDifference;
import coffeemachine.domain.Message;

public class InsufficientFundsMessage implements Message
{
    private MoneyDifference difference;

    public InsufficientFundsMessage(MoneyDifference difference) {
        this.difference = difference;
    }

    public String getText() {
        return "There are " + difference.format() + " missing";
    }
}
