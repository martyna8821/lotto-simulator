package pl.martyna.lotto.dto;


import javax.validation.constraints.Min;

/**
 * DTO for sending data from form
 * @author Martyna Drabinska
 * @version 0.1
 */

public class Settings  {

    /** minimum value of drawn result */
    @Min(1)
    private int min;


    /** maximum value of drawn result */
    @Min(2)
    private int max;

    /** quantity of drawn results  */
    @Min(1)
    private int quantity;

    /**
     * default constructor
     */
    public Settings(){

    }
    /**
     * sets minimal value
     * @param min minimum value
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * sets maximum value
     * @param max maximum value
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * sets quantity
     * @param quantity quantity of drawn results
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * returns minimum value
     * @return minimum value
     */
    public int getMin() {
        return min;
    }

    /**
     * returns maximum value
     * @return maximum value
     */
    public int getMax() {
        return max;
    }

    /**
     * returns quantity of drawn results
     * @return quantity of drawn results
     */
    public int getQuantity() {
        return quantity;
    }
}
