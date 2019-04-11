package pl.martyna.lotto.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.martyna.lotto.model.Result;
@Repository
public class ResultDaoImp implements ResultDao {

   private final SessionFactory sessionFactory;

    @Autowired
    ResultDaoImp(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveResult(Result result) {
        sessionFactory.getCurrentSession().save(result);
    }
}
