package services;


import dao.UserDao;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    public List<User> getAll(){
        return userDao.getAll();
    }

    public void createUser(User user){
        userDao.createUser(user);
    }
}
