package exception;

public class MinCapacityReachedException extends RuntimeException{
    public MinCapacityReachedException(String message) {
        super(message);
    }
}
