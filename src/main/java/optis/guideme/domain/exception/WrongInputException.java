package optis.guideme.domain.exception;

public class WrongInputException extends RuntimeException {
    public WrongInputException(String message) {
        super("Wrong Input Error: " + message);
    }
}
