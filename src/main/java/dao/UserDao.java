package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public List<User> getAll(){
        Session session = this.sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User",User.class);
        List<User> users = query.getResultList();
        return users==null? new ArrayList<User>():users;
    }

    public void createUser(User user){
        this.sessionFactory.getCurrentSession().save(user);
    }
}
