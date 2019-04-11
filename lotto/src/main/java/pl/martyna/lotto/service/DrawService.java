package pl.martyna.lotto.service;

import java.util.List;
import java.util.Set;

public interface DrawService {

    List<Set<Integer>> getHistory();
    
    void saveDraw(Set<Integer> numbers);
}
