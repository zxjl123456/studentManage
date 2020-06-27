package dao;

import domain.User;
import util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User Dao 层
 */
public class UserDao {
    /**
     * 插入一个用户
     *
     * @param user
     */
    public void insert(User user) {
        Connection conn = DB.getConn();
        String sql = "insert into adminuser (username,password) values( ?,?)";
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        try {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改用户信息
     *
     * @param user
     */
    public void update(User user) {
        Connection conn = DB.getConn();
        String sql = "update adminuser set password = ? where username = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        try {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过用户名删除用户
     *
     * @param username
     */
    public void delete(String username) {
        Connection conn = DB.getConn();
        String sql = "delete from adminuser where username = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        try {
            preparedStatement.setString(1, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return
     */
    public User findByUsername(String username) {
        Connection conn = DB.getConn();
        User user = null;
        String sql = "select username from adminuser where username = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        try {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 根据用户名和密码查询用户
     *
     * @param username 用户名
     * @param pasword  密码
     * @return
     */
    public User findByUsernameAndPassword(String username, String pasword) {
        Connection conn = DB.getConn();
        User user = null;
        String sql = "select username,password from adminuser where username = ? and password = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        try {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pasword);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
