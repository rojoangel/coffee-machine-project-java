package coffeemachine.domain;

public class OrderableDrink
{
    private DrinkType drinkType;
    private Money price;

    public OrderableDrink(DrinkType drinkType, Money price) {
        this.drinkType = drinkType;
        this.price = price;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public Money getPrice() {
        return price;
    }
}
