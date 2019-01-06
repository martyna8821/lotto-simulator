package pl.martyna.lotto.dao;

import pl.martyna.lotto.model.Result;

/**
 * Provides methods responsible for exchanging data with database
 * @author Martyna Drabinska
 * @version 0.1
 */
public interface ResultDao {

    /**
     * Saves result to database
     * @param result result
     */
    void saveResult(Result result);

}
