package coffeemachine.adapter;

import coffeemachine.domain.Order;

public class OrderAdapter {

    public String adapt(Order order) {
        return extractDrink(order) + extractExtraHot(order) +
                ":" + extractSugar(order) +
                ":" + extractStick(order);
    }

    private String extractExtraHot(Order order) {
        if (!order.isExtraHot()) {
            return "";
        }
        return "h";
    }

    private String extractDrink(Order order) {
        switch (order.getDrinkType()) {
            case TEA:
                return "T";
            case COFFEE:
                return "C";
            case HOT_CHOCOLATE:
                return "H";
            case ORANGE_JUICE:
                return "O";
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