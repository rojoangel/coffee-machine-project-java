package coffeemachine;

public class Order
{
    private String drinkType;
    private int sugarQuantity;

    public Order(String drinkType) {

        this.drinkType = drinkType;
    }

    public void addSugar() {
        this.sugarQuantity ++;
    }
}
