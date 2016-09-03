package coffeemachine;

public class ProtocolAdapter {

    public String adapt(Order order) {
        String instructions = "H::";
        if (DrinkType.COFFEE.equals(order.getDrinkType())) {
            instructions = "C:2:0";
        }
        if (DrinkType.TEA.equals(order.getDrinkType())) {
            instructions = "T:1:0";
        }
        return instructions;
    }
}