package pl.martyna.lotto.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.martyna.lotto.model.Result;

/**
 * Provides methods responsible for exchanging data with database
 * @author Martyna Drabinska
 * @version 0.1
 */
@Repository
public class ResultDaoImp implements ResultDao {

    /** session factory */
   private final SessionFactory sessionFactory;

    /**
     * Default constructor
     * @param sessionFactory session factory
     */
    @Autowired
    ResultDaoImp(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    /**
     * Saves result to database
     * @param result result
     */
    @Override
    public void saveResult(Result result) {
        sessionFactory.getCurrentSession().save(result);
    }
}
