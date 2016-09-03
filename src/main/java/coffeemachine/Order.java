package coffeemachine;

public class Order
{
    private DrinkType drinkType;
    private int sugarQuantity;

    public Order(DrinkType drinkType) {

        this.drinkType = drinkType;
    }

    public void addSugar() {
        this.sugarQuantity ++;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }
}
