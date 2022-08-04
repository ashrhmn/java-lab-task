package services;


import dao.TodoDao;
import model.Todo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TodoService {
    private final TodoDao todoDao;

    public TodoService(TodoDao todoDao){
        this.todoDao = todoDao;
    }

    @Transactional(readOnly = true)
    public List<Todo> getAll(){
        return this.todoDao.getAll();
    }

    public void add(Todo todo){ this.todoDao.save(todo);}

    public Todo getOne(int id){return this.todoDao.getOne(id);}

    public void update(Todo todo){this.todoDao.update(todo);}

    public void deleteOne(int id){this.todoDao.deleteOne(id);}
    public void deleteOne(Todo todo){this.todoDao.deleteOne(todo);}
}
