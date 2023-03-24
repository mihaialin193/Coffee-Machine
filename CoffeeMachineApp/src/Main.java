import coffeeenum.CoffeeBeanType;
import coffeeenum.CoffeeType;
import coffeetool.CoffeeGrinder;
import coffeetool.LiquidHeater;
import tank.CoffeeTank;
import tank.MilkTank;
import tank.WaterTank;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CoffeeMachine coffeeMachine = new CoffeeMachine(
                new WaterTank(2000, 500, 2000),
                new MilkTank(2000, 500, 2000),
                Map.of(
                        CoffeeBeanType.ARABICA, new CoffeeTank(500, CoffeeBeanType.ARABICA, 500),
                        CoffeeBeanType.COLD_BREW, new CoffeeTank(500, CoffeeBeanType.COLD_BREW, 500),
                        CoffeeBeanType.ROBUSTA, new CoffeeTank(500, CoffeeBeanType.ROBUSTA, 500),
                        CoffeeBeanType.BLONDE_ROAST, new CoffeeTank(500, CoffeeBeanType.BLONDE_ROAST, 500)
                ),
                new CoffeeGrinder(2),
                new LiquidHeater(5)
        );


        for (int i = 0; i < 30; i++) {
            int randomCoffee = (int) (Math.random() * 10);
            switch (randomCoffee) {
                case 1:
                    coffeeMachine.makeCoffee(CoffeeType.AMERICANO);
                    break;
                case 2:
                    coffeeMachine.makeCoffee(CoffeeType.ESPRESSO);
                    break;
                case 3:
                    coffeeMachine.makeCoffee(CoffeeType.CAPPUCCINO);
                    break;
                default:
                    coffeeMachine.makeCoffee(CoffeeType.CORTATO);
                    break;

            }
            Thread.sleep(5000);
        }
        //TODO: random sleep
        //  Thread.sleep((long) (Math.random() * 1000));
    }
}