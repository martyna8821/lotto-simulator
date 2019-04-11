package pl.martyna.lotto.dto;

import javax.validation.constraints.Min;

/**
 * DTO for sending data from form
 * @author Martyna Drabinska
 * @version 0.1
 */

public class Settings  {

    @Min(1)
    private int min;

    @Min(2)
    private int max;

    @Min(1)
    private int quantity;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Settings(){

    }


}
