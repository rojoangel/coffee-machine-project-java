package coffeemachine.domain;

public class Order
{
    private OrderableDrink drink;
    private int sugarQuantity;

    public Order(OrderableDrink drink) {
        this.drink = drink;
    }

    public void addSugar() {
        this.sugarQuantity ++;
    }

    public OrderableDrink getDrink() {
        return drink;
    }

    public DrinkType getDrinkType() {
        return drink.getDrinkType();
    }

    public int getSugarQuantity() {
        return sugarQuantity;
    }
}
