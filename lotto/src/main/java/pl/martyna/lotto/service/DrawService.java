package pl.martyna.lotto.service;

import java.util.List;
import java.util.Set;

/**
 * Provides methods responsible for exchanging data with database
 * @author Martyna Drabinska
 * @version 0.1
 */
public interface DrawService {

    /**
     * Returns list of sets with history of drawings
     * @return list of sets with history of drawings
     */
    List<Set<Integer>> getHistory();

    /**
     * Saves one set of results
     * @param numbers set of results
     */
    void saveDraw(Set<Integer> numbers);
}
