package optis.guideme.domain.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("ERROR: " + message);
    }
}
