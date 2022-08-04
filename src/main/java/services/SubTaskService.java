package services;

import dao.SubTaskDao;
import model.SubTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubTaskService {
    private final SubTaskDao subTaskDao;

    public SubTaskService(SubTaskDao subTaskDao){
        this.subTaskDao = subTaskDao;
    }

    public SubTask getOne(int id){return this.subTaskDao.getOne(id);}

    public void update(SubTask subTask){this.subTaskDao.update(subTask);}

    public void deleteOne(int id){this.subTaskDao.deleteOne(id);}

    public void create(SubTask subTask){this.subTaskDao.save(subTask);}
}
