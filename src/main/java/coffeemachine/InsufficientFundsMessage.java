package coffeemachine;

public class InsufficientFundsMessage implements Message
{
    private MoneyDifference difference;

    public InsufficientFundsMessage(MoneyDifference difference) {
        this.difference = difference;
    }

    public String getText() {
        return "There are 0,40 EUR missing";
    }
}
