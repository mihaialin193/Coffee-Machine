package tank;

import exception.MaxCapacityReachedException;
import exception.MinCapacityReachedException;

/**
 * All levels are measured in ml.
 */
public class WaterTank {
    private final int maxCapacity;
    private final int minCapacity;
    private int waterQuantity;

    public WaterTank(int maxCapacity, int minCapacity, int waterQuantity) {
        //TODO: validate input arguments
        this.maxCapacity = maxCapacity;
        this.minCapacity = minCapacity;
        this.waterQuantity = waterQuantity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }


    public int getMinCapacity() {
        return minCapacity;
    }


    public int getAvailableWaterQuantity() {
        return waterQuantity - minCapacity;
    }

    public void addWater(int water){
        if(water < 0){
            throw new MaxCapacityReachedException("Invalid value of water : negative value");
        }
        if(waterQuantity + water > maxCapacity){
            throw new MaxCapacityReachedException("Water maximum capacity reached");
        }
        waterQuantity = waterQuantity + water;
    }

    public int removeWaterFromTank(int water){
        if(water < 0){
            throw new IllegalArgumentException("Invalid value of water : negative value");
        }
        if(waterQuantity - water < minCapacity){
            throw new MinCapacityReachedException("Water tank is empty");
        }
        waterQuantity = waterQuantity - water;
        return water;
    }
    @Override
    public String toString() {
        return "WaterTank{" +
                "maxCapacity=" + maxCapacity +
                ", minCapacity=" + minCapacity +
                ", waterQuantity=" + waterQuantity +
                '}';
    }
}
