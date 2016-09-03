package coffeemachine;

public class ProtocolAdapter {

    public String adapt(Order order) {
        StringBuilder instructionsBuilder = new StringBuilder();
        switch (order.getDrinkType()) {
            case TEA:
                instructionsBuilder.append("T");
                break;
            case COFFEE:
                instructionsBuilder.append("C");
                break;
            case HOT_CHOCOLATE:
                instructionsBuilder.append("H");
        }
        instructionsBuilder.append(":");
        if (order.getSugarQuantity() == 0) {
            return instructionsBuilder.append(":").toString();
        }

        instructionsBuilder.append(order.getSugarQuantity());
        instructionsBuilder.append(":0");
        return instructionsBuilder.toString();
    }
}