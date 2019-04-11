package pl.martyna.lotto.exceptions;

/**
 * Signals an inappropriate value of settings
 * @author Martyna Drabinska
 */
public class IllegalValueException extends Exception {

    public IllegalValueException(){
        super();
    }

    @Override
    public String getMessage(){
        return "Illegal value of settings";
    }
}
