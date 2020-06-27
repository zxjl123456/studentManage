package service;

import domain.User;

/**
 * 用户服务类
 */
public interface UserService {
    /**
     * 通过用户名和密码查找用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * 保存用户
     *
     * @param user
     */
    void save(User user);
}
