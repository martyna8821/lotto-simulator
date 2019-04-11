package pl.martyna.lotto.service;

import org.springframework.stereotype.Service;
import pl.martyna.lotto.dto.Settings;
import pl.martyna.lotto.exceptions.IllegalValueException;
import java.util.*;

@Service
public class SimulationServiceImp implements SimulationService {

    private int min = 1;
    private int max = 49;
    private int quantity = 5;

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

    @Override
    public Set<Integer> randomResults(){

        Set<Integer> results = new TreeSet<>();
        Random randomGenerator = new Random();
        while(results.size() < quantity){
            results.add(randomGenerator.nextInt(max-min)+min);
        }
        return Collections.unmodifiableSet(results);
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getQuantity() {
        return quantity;
    }

}

