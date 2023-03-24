package tank;

import coffeeenum.CoffeeBeanType;
import exception.MaxCapacityReachedException;
import exception.MinCapacityReachedException;

public class CoffeeTank {
    private final int maxCapacity;
    private final CoffeeBeanType coffeeBeanType;
    private int coffeeQuantity;

    public CoffeeTank(int maxCapacity, CoffeeBeanType coffeeBeanType, int coffeeQuantity) {
        //TODO: validate input parameters

        if(maxCapacity<0){
            throw new IllegalArgumentException("Value must be higher than 0");
        }

        if(coffeeQuantity> maxCapacity || coffeeQuantity<0){
            throw new IllegalArgumentException("Value is not correct");
        }
        this.maxCapacity = maxCapacity;
        this.coffeeBeanType = coffeeBeanType;
        this.coffeeQuantity = coffeeQuantity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public CoffeeBeanType getCoffeeType() {
        return coffeeBeanType;
    }

    public int getCoffeeQuantity() {
        return coffeeQuantity;
    }

    public void addCoffee(int coffee){
        if(coffee < 0){
            throw new IllegalArgumentException("Invalid value of coffee : negative value");
        }
        if(coffeeQuantity + coffee > maxCapacity){
            throw new MaxCapacityReachedException("Water maximum capacity reached");
        }
        coffeeQuantity = coffeeQuantity + coffee;
    }
    public int removeCoffeeFromTank(int coffee){
        if(coffee < 0){
            throw new IllegalArgumentException("Invalid value of coffee : negative value");
        }
        if(coffeeQuantity - coffee < 0){
            throw new MinCapacityReachedException("Water tank is empty");
        }
        coffeeQuantity = coffeeQuantity - coffee;
        return coffee;
    }

    @Override
    public String toString() {
        return "CoffeeTank{" +
                "maxCapacity=" + maxCapacity +
                ", coffeeType=" + coffeeBeanType +
                ", coffeeQuantity=" + coffeeQuantity +
                '}';
    }
}
