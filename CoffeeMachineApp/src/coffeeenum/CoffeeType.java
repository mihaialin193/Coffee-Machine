package coffeeenum;

import java.util.Optional;

public enum CoffeeType {
    ESPRESSO(CoffeeBeanType.ARABICA, 8, 30, 85),
    CAPPUCCINO(CoffeeBeanType.BLONDE_ROAST, 6, 30, 85, 120, 85),

    AMERICANO(CoffeeBeanType.ARABICA, 4, 50, 90),

    CORTATO(CoffeeBeanType.BLONDE_ROAST, 10, 45, 95, 140, 90);


    private final CoffeeBeanType coffeeBeanType;
    private final int coffeeGranularity;
    private final int waterQuantity;
    private final int waterTemperature;
    private final Integer milkQuantity;
    private final Integer milkTemperature;

    CoffeeType(CoffeeBeanType coffeeBeanType, int coffeeGranularity, int waterQuantity, int waterTemperature) {
        this.coffeeBeanType = coffeeBeanType;
        this.coffeeGranularity = coffeeGranularity;
        this.waterQuantity = waterQuantity;
        this.waterTemperature = waterTemperature;
        this.milkQuantity = null;
        this.milkTemperature = null;
    }

    CoffeeType(CoffeeBeanType coffeeBeanType, int coffeeGranularity, int waterQuantity, int waterTemperature, Integer milkQuantity, Integer milkTemperature) {
        this.coffeeBeanType = coffeeBeanType;
        this.coffeeGranularity = coffeeGranularity;
        this.waterQuantity = waterQuantity;
        this.waterTemperature = waterTemperature;
        this.milkQuantity = milkQuantity;
        this.milkTemperature = milkTemperature;
    }

    public CoffeeBeanType getCoffeeBeanType() {
        return coffeeBeanType;
    }

    public int getCoffeeGranularity() {
        return coffeeGranularity;
    }

    public int getWaterQuantity() {
        return waterQuantity;
    }

    public int getWaterTemperature() {
        return waterTemperature;
    }

    public Optional<Integer> getMilkQuantity() {
        return Optional.ofNullable(milkQuantity);
    }

    public Optional<Integer> getMilkTemperature() {
        return Optional.ofNullable(milkTemperature);
    }

    public int getCoffeeQuantity() {
        return 8;
    }
}
