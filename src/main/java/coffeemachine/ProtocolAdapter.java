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
        return instructionsBuilder.append("::").toString();
    }
}