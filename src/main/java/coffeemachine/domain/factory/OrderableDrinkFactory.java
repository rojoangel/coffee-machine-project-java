package coffeemachine.domain.factory;

import coffeemachine.domain.DrinkType;
import coffeemachine.domain.Money;
import coffeemachine.domain.OrderableDrink;

import java.util.HashMap;
import java.util.Map;

public class OrderableDrinkFactory
{
    private static Map<DrinkType, OrderableDrink> drinks = initDrinks();

    private static Map<DrinkType,OrderableDrink> initDrinks() {
        Map<DrinkType, OrderableDrink> drinks = new HashMap<DrinkType, OrderableDrink>();
        drinks.put(DrinkType.TEA, new OrderableDrink(DrinkType.TEA, new Money(40)));
        return drinks;
    }

    public static OrderableDrink fromDrinkType(DrinkType drinkType)
    {
        return drinks.get(drinkType);
    }
}
