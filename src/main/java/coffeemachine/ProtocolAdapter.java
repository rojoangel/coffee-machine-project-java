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
        }
        return instructionsBuilder.append("::").toString();
    }
}