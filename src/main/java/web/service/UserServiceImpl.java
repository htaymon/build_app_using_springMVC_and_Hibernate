package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public void saveUser(User user) {
        userDao.save(user);
    }

    public User getUser(Long id) {
        return userDao.findById(id);
    }

    public void deleteUser(Long id) {
        userDao.delete(id);
    }
}

