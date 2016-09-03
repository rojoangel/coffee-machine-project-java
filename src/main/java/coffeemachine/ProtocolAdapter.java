package coffeemachine;

public class ProtocolAdapter {

    public String adapt(Order order) {
        return extractDrink(order) + ":" + extractSugar(order) + ":" + extractStick(order);
    }

    private String extractDrink(Order order) {
        switch (order.getDrinkType()) {
            case TEA:
                return "T";
            case COFFEE:
                return "C";
            case HOT_CHOCOLATE:
                return "H";
            default:
                return "";
        }
    }

    private String extractSugar(Order order) {
        if (order.getSugarQuantity() == 0) {
            return "";
        }

        return String.valueOf(order.getSugarQuantity());
    }

    private String extractStick(Order order) {
        if (order.getSugarQuantity() == 0) {
            return "";
        }

        return "0";
    }
}