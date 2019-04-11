package pl.martyna.lotto.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.martyna.lotto.model.Draw;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DrawDaoImp implements DrawDao {

    private final SessionFactory sessionFactory;

    @Autowired
    DrawDaoImp(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Draw> getHistory() {
       Query query = sessionFactory.getCurrentSession().createQuery("FROM draw");
        return  query.getResultList();
    }

    @Override
    public void saveDraw(Draw draw) {
        sessionFactory.getCurrentSession().save(draw);
    }

}
