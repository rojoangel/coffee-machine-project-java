package coffeemachine.domain;

public class OrderableDrink
{
    public static final OrderableDrink HOT_CHOCOLATE =
            new OrderableDrink(
                    DrinkType.HOT_CHOCOLATE,
                    new Money(50));

    public static final OrderableDrink TEA =
            new OrderableDrink(
                    DrinkType.TEA,
                    new Money(40));

    public static final OrderableDrink COFFEE =
            new OrderableDrink(
                    DrinkType.COFFEE,
                    new Money(60));

    public static final OrderableDrink ORANGE_JUICE =
            new OrderableDrink(
                    DrinkType.ORANGE_JUICE,
                    new Money(60));

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
