package pl.martyna.lotto.exceptions;

/**
 * Signals an inappropriate value of settings
 * @author Martyna Drabinska
 * @version 1.0
 */
public class IllegalValueException extends Exception {

    /** Default constructor */
    public IllegalValueException(){
        super();
    }

    /**
     * Returns detailed exception message
     * @return detailed exception message
     */
    @Override
    public String getMessage(){
        return "Illegal value of settings";
    }
}
