package dao;

import model.SubTask;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SubTaskDao {
    private final SessionFactory sessionFactory;

    public SubTaskDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SubTask getOne(int id){
        return this.sessionFactory.getCurrentSession().get(SubTask.class,id);
    }

    public void update(SubTask subTask){
        this.sessionFactory.getCurrentSession().update(subTask);
    }

    public void save(SubTask subTask){
        this.sessionFactory.getCurrentSession().save(subTask);
    }

    public void deleteOne(int id){
        this.sessionFactory.getCurrentSession().delete(getOne(id));
    }

    public void deleteOne(SubTask subTask){
        this.sessionFactory.getCurrentSession().delete(subTask);
    }
}
