import coffeeenum.CoffeeBeanType;
import coffeeenum.CoffeeType;
import coffeetool.CoffeeGrinder;
import coffeetool.LiquidHeater;
import tank.CoffeeTank;
import tank.MilkTank;
import tank.WaterTank;

import java.util.Map;

public class CoffeeMachine {
    private final WaterTank waterTank;
    private final MilkTank milkTank;
    private final Map<CoffeeBeanType, CoffeeTank> coffeeTanksByCoffeeType;
    private final CoffeeGrinder coffeeGrinder;
    private final LiquidHeater liquidHeater;

    /**
     * @param waterTank
     * @param milkTank
     * @param coffeeTanksByCoffeeType
     * @param coffeeGrinder
     * @param liquidHeater
     */
    public CoffeeMachine(WaterTank waterTank, MilkTank milkTank, Map<CoffeeBeanType, CoffeeTank> coffeeTanksByCoffeeType, CoffeeGrinder coffeeGrinder, LiquidHeater liquidHeater) {
        this.waterTank = waterTank;
        this.milkTank = milkTank;
        this.coffeeTanksByCoffeeType = coffeeTanksByCoffeeType;
        this.coffeeGrinder = coffeeGrinder;
        this.liquidHeater = liquidHeater;
    }

    public void refillWater(int water) {
        waterTank.addWater(water);
    }

    public void refillMilk(int milk) {
        milkTank.addMilk(milk);
    }

    public void refillCoffee(CoffeeBeanType coffeeType, int coffeeQuantity) {
        coffeeTanksByCoffeeType.get(coffeeType).addCoffee(coffeeQuantity);
    }

    public int getRemainingWater(CoffeeMachine coffeeMachine) {
        return coffeeMachine.waterTank.getAvailableWaterQuantity();
    }

    public synchronized void makeCoffee(CoffeeType coffeeType) {
        Thread coffeeThread = new Thread(() -> {
            System.out.println("Order received for " + coffeeType);
            while (waterTank.getAvailableWaterQuantity() < coffeeType.getWaterQuantity()) {
                sleep();
            }
            int water = waterTank.removeWaterFromTank(coffeeType.getWaterQuantity());

            Integer milk = null;
            if (coffeeType.getMilkQuantity().isPresent()) {
                while (milkTank.getAvailableMilkQuantity() < coffeeType.getMilkQuantity().get()) {
                    sleep();
                }
                milk = milkTank.removeMilkFromTank(coffeeType.getMilkQuantity().get());
            }

            while (coffeeTanksByCoffeeType.get(coffeeType.getCoffeeBeanType()).getCoffeeQuantity() < coffeeType.getCoffeeQuantity()) {
                sleep();
            }
            int coffee = coffeeTanksByCoffeeType.get(coffeeType.getCoffeeBeanType()).removeCoffeeFromTank(coffeeType.getCoffeeQuantity());

            int grindingCoffee = coffeeGrinder.grind(coffee, coffeeType.getCoffeeGranularity());

            int heatedWater = liquidHeater.heat(water, coffeeType.getWaterTemperature());

            int madeCoffee = grindingCoffee + heatedWater;

            if (coffeeType.getMilkTemperature().isPresent()) {
                int heatedMilk = liquidHeater.heat(milk, coffeeType.getMilkTemperature().get());

                int coffeeWithMilk = madeCoffee + heatedMilk;
                System.out.println("Coffee is ready " + coffeeWithMilk);
                System.out.println(waterTank.getAvailableWaterQuantity());
            } else {
                System.out.println("Coffee is ready " + madeCoffee);
                System.out.println("Remaining water quantity " + waterTank.getAvailableWaterQuantity());
            }
        });
        coffeeThread.start();
    }

    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
