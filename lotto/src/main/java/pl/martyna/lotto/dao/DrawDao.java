package pl.martyna.lotto.dao;

import pl.martyna.lotto.model.Draw;
import java.util.List;

/**
 * Provides methods responsible for exchanging data with database
 * @author Martyna Drabinska
 * @version 0.1
 */
public interface DrawDao {

    /**
     * Returns list with all draws from database
     * @return list of draws
     */
    List<Draw> getHistory();

    /**
     * Saves one draw to database
     * @param draw draw to save
     */
    void saveDraw(Draw draw);
}
