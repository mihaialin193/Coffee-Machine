package tank;

import exception.MaxCapacityReachedException;
import exception.MinCapacityReachedException;

/**
 * All levels are measured in ml.
 */
public class MilkTank {
    private final int maxCapacity;
    private final int minCapacity;
    private int milkQuantity;

    public MilkTank(int maxCapacity, int minCapacity, int milkQuantity) {
        //TODO: validate input arguments

        this.maxCapacity = maxCapacity;
        this.minCapacity = minCapacity;
        this.milkQuantity = milkQuantity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }


    public int getMinCapacity() {
        return minCapacity;
    }


    public int getAvailableMilkQuantity() {
        return milkQuantity - minCapacity;
    }

    public void addMilk(int milk){
        if(milk < 0){
            throw new IllegalArgumentException("Invalid value of milk : negative value");
        }
        if(milkQuantity + milk > maxCapacity){
            throw new MaxCapacityReachedException("Milk maximum capacity reached");
        }
        milkQuantity = milkQuantity + milk;
    }

    public int removeMilkFromTank(int milk){
        if(milk < 0){
            throw new IllegalArgumentException("Invalid value of milk : negative value");
        }
        if(milkQuantity - milk < minCapacity){
            throw new MinCapacityReachedException("Milk tank is empty");
        }
        milkQuantity = milkQuantity - milk;
        return milk;
    }
    @Override
    public String toString() {
        return "MilkTank{" +
                "maxCapacity=" + maxCapacity +
                ", minCapacity=" + minCapacity +
                ", milkQuantity=" + milkQuantity +
                '}';
    }
}
