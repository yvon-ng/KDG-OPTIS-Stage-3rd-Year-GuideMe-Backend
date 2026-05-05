package optis.guideme.domain.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super("ERROR Forbidden: " + message);
    }
}
