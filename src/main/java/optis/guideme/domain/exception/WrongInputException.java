package optis.guideme.domain.exception;

public class WrongInputException extends RuntimeException {
    public WrongInputException(String message) {
        super("ERROR Wrong Input: " + message);
    }
}
