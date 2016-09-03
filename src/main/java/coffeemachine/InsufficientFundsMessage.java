package coffeemachine;

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
