package dao;

import model.Todo;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoDao {
    private final SessionFactory sessionFactory;

    public TodoDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public List<Todo> getAll(){
        List<Todo> todos = this.sessionFactory
                .getCurrentSession()
                .createQuery("FROM Todo ",Todo.class)
                .getResultList();
        return todos==null? new ArrayList<Todo>() : todos;
    }

    public void save(Todo todo){
        this.sessionFactory.getCurrentSession().save(todo);
    }

    public Todo getOne(int id){
        return this.sessionFactory.getCurrentSession().get(Todo.class,id);
    }

    public void update(Todo todo){
        this.sessionFactory.getCurrentSession().update(todo);
    }

    public void deleteOne(int id){
        Todo todo = getOne(id);
        this.sessionFactory.getCurrentSession().delete(todo);
    }
    public void deleteOne(Todo todo){
        this.sessionFactory.getCurrentSession().delete(todo);
    }

}
