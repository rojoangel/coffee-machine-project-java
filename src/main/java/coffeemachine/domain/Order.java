package coffeemachine.domain;

public class Order
{
    private OrderableDrink drink;
    private int sugarQuantity;
    private boolean extraHot;

    public Order(OrderableDrink drink) {
        this.drink = drink;
        this.sugarQuantity = 0;
        this.extraHot = false;
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

    public void setExtraHot() {
        this.extraHot = true;
    }

    public boolean isExtraHot() {
        return extraHot;
    }
}
