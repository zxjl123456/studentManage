package service.impl;

import dao.UserDao;
import domain.User;
import service.UserService;

/**
 * UserService 实现类
 */
public class UserServiceImpl implements UserService {
    public User findByUsernameAndPassword(String username, String password) {
        UserDao userDao = new UserDao();
        return userDao.findByUsernameAndPassword(username, password);
    }

    public void save(User user) {
        UserDao userDao = new UserDao();
        User user1 = userDao.findByUsername(user.getUsername());
        if (user1 != null) {
            userDao.update(user);
        } else {
            userDao.insert(user);
        }
    }
}
