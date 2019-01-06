package pl.martyna.lotto.service;

import pl.martyna.lotto.exceptions.IllegalValueException;
import java.util.Set;

/**
 * Provides methods responsible for lotto simulation
 * @author Martyna Drabinska
 * @version 0.1
 */
public interface SimulationService {

    /**
     * Changes settings of simulation if passed parameters are correct
     * @param min minimum value of result
     * @param max maximum value of result
     * @param quantity quantity of results
     * @throws IllegalValueException  if for passed arguments model can't carry out a simulation
     */
    void changeSettings(int min, int max, int quantity) throws IllegalValueException;

    /**
     * Carries out a server drawing simulation and returns set representing its results
     * @return unmodifiable set of drawn results
     */
    Set<Integer> randomResults();

}