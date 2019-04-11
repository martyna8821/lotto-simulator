package pl.martyna.lotto.service;

import pl.martyna.lotto.exceptions.IllegalValueException;
import java.util.Set;

public interface SimulationService {

    void changeSettings(int min, int max, int quantity) throws IllegalValueException;
    Set<Integer> randomResults();
}
