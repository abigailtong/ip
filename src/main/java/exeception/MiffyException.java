package exeception;

/**
 * Exception handler for invalid input from the user.
 */
public class MiffyException extends Exception {
    public MiffyException(String description) {
        super(description);
    }
}
