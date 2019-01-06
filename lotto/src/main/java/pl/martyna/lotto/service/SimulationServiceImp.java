package pl.martyna.lotto.service;

import org.springframework.stereotype.Service;
import pl.martyna.lotto.exceptions.IllegalValueException;
import java.util.*;

/**
 * Responsible for simulation of drawings
 * @author Martyna Drabinska
 * @version 2.0
 */
@Service
public class SimulationServiceImp implements SimulationService {


    /** minimum value of result, default: 1 */
    private int min = 1;
    /**  maximum value of result, default: 49 */
    private int max = 49;
    /** quantity of result, default: 5 */
    private int quantity = 5;

    /** Default constructor */
    public SimulationServiceImp(){}

    /**
     * Changes settings of simulation if passed parameters are correct
     * @param min new minimal value of a result
     * @param max new maximum value of a result
     * @param quantity new quantity of results
     * @throws IllegalValueException if for passed arguments model can't carry out a simulation
     */
    @Override
    public void changeSettings(int min, int max, int quantity) throws IllegalValueException {
        if(min > max || (max - min) < quantity ){
            throw new IllegalValueException();
        }
        else{
            this.min = min;
            this.max = max;
            this.quantity = quantity;
        }
    }

    /**
     * Carries out a server drawing simulation and returns set representing its results
     * @return unmodifiable set of drawn results
     */
    @Override
    public Set<Integer> randomResults(){

        Set<Integer> results = new TreeSet<>();
        Random randomGenerator = new Random();
        while(results.size() < quantity){
            results.add(randomGenerator.nextInt(max)+min);
        }
        return Collections.unmodifiableSet(results);
    }

    /**
     * Returns minimal value of drawn result
     * @return minimum value of drawn result
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns maximum value of drawn result
     * @return maximum value of drawn result
     */
    public int getMax() {
        return max;
    }

    /**
     * Returns quantity of drawn results
     * @return quantity of drawn results
     */
    public int getQuantity() {
        return quantity;
    }

}

