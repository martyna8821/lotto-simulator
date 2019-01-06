package pl.martyna.lotto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.martyna.lotto.dao.DrawDao;
import pl.martyna.lotto.dao.ResultDao;
import pl.martyna.lotto.model.Draw;
import pl.martyna.lotto.model.Result;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Provides methods responsible for exchanging data with database
 * @author Martyna Drabinska
 * @version 0.1
 */
@Repository
public class DrawServiceImp  implements DrawService{

    /** DAO for draw_table */
   private final DrawDao drawDao;
    /** DAO for results table */
    private final ResultDao resultDao;

    /**
     * Default constructor
     * @param drawDao DAO for draw_table
     * @param resultDao DAO for results
     */
    @Autowired
    DrawServiceImp(DrawDao drawDao, ResultDao resultDao){
        this.drawDao = drawDao;
        this.resultDao = resultDao;
    }

    /**
     * Returns list of sets with history of drawings
     * @return list of sets with history of drawings
     */
    @Override
    @Transactional
    public List<Set<Integer>> getHistory(){

        List<Draw> drawList = drawDao.getHistory();
        List<Set<Integer>> resultList = new LinkedList<>();
        for(Draw draw: drawList){
            Set<Integer> results = new HashSet<>(0);
            for(Result result: draw.getResults()){
                results.add(result.getNumber());
            }
            resultList.add(results);
        }

        return resultList;
        // return Collections.unmodifiableList(history);
    }


    /**
     * Saves one set of results
     * @param numbers set of results
     */
    @Override
    @Transactional
    public void saveDraw(Set<Integer> numbers){
        Draw draw = new Draw();
        Set<Result> results = new HashSet<>(0);
        for(Integer i: numbers ){
            Result result = new Result(i, draw);
            results.add(result);
            resultDao.saveResult(result);
        }

        draw.setResults(results);
        drawDao.saveDraw(draw);


    }
}
