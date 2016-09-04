package coffeemachine.domain;

import coffeemachine.domain.factory.OrderableDrinkFactory;

public class Order
{
    private DrinkType drinkType;
    private OrderableDrink drink;
    private int sugarQuantity;

    public Order(DrinkType drinkType) {

        this.drinkType = drinkType;
        this.drink = OrderableDrinkFactory.fromDrinkType(drinkType);
    }

    public void addSugar() {
        this.sugarQuantity ++;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public OrderableDrink getDrink() {
        return drink;
    }
    public int getSugarQuantity() {
        return sugarQuantity;
    }
}
