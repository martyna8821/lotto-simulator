package pl.martyna.lotto.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.martyna.lotto.model.Draw;
import javax.persistence.Query;
import java.util.List;

/**
 * Provides methods responsible for exchanging data with database
 *  @author Martyna Drabinska
 *  @version 0.1
 */
@Repository
public class DrawDaoImp implements DrawDao {

    /** session factory */
    private final SessionFactory sessionFactory;

    /**
     * Default constructor
     * @param sessionFactory session factory
     */
    @Autowired
    DrawDaoImp(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    /**
     * Returns list with all draws from database
     * @return list of draws
     */
    @Override
    public List<Draw> getHistory() {
       Query query = sessionFactory.getCurrentSession().createQuery("FROM draw");
        return  query.getResultList();
    }

    /**
     * Saves one draw to database
     * @param draw draw to save
     */
    @Override
    public void saveDraw(Draw draw) {
        sessionFactory.getCurrentSession().save(draw);
    }

}
