package pl.martyna.lotto.dao;

import pl.martyna.lotto.model.Draw;
import java.util.List;

public interface DrawDao {

    List<Draw> getHistory();

    void saveDraw(Draw draw);
}
