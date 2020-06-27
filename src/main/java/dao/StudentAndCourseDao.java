package dao;

import model.StudentAndCourse;
import util.DB;
import util.ResultToObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * StudentAndCourse Dao 层
 */
public class StudentAndCourseDao {

    /**
     * 通过学号查询视图记录
     *
     * @param studentId 学号
     * @return 记录
     */
    public List<StudentAndCourse> findByStudentId(String studentId) {
        Connection connection = DB.getConn();
        String sql = "select * from studentandcourse where studentId = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<StudentAndCourse> studentAndCourseList = ResultToObject.getDataByResultSet(resultSet, StudentAndCourse.class);
        return studentAndCourseList;
    }

    /**
     * 通过学生姓名查询视图记录
     *
     * @param studentName 学生姓名
     * @return 记录
     */
    public List<StudentAndCourse> findByStudentName(String studentName) {
        Connection connection = DB.getConn();
        String sql = "select * from studentandcourse where studentName like ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, studentName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<StudentAndCourse> studentAndCourseList = ResultToObject.getDataByResultSet(resultSet, StudentAndCourse.class);
        return studentAndCourseList;
    }

    /**
     * 通过课程号查询记录
     *
     * @param courseId 课程号
     * @return 记录
     */
    public List<StudentAndCourse> findByCourseId(String courseId) {
        Connection connection = DB.getConn();
        String sql = "select * from studentandcourse where courseNum = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, courseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<StudentAndCourse> studentAndCourseList = ResultToObject.getDataByResultSet(resultSet, StudentAndCourse.class);
        return studentAndCourseList;
    }

    /**
     * 通过课程名模糊查询
     *
     * @param courseName 课程名
     * @return 记录
     */
    public List<StudentAndCourse> findByCourseName(String courseName) {
        Connection connection = DB.getConn();
        String sql = "select * from studentandcourse where courseName like ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, courseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<StudentAndCourse> studentAndCourseList = ResultToObject.getDataByResultSet(resultSet, StudentAndCourse.class);
        return studentAndCourseList;
    }

    public StudentAndCourse findByStudentIdAndCourseId(String studentId, String courseId) {
        Connection connection = DB.getConn();
        String sql = "select * from studentandcourse where studentId = ? and courseNum = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, courseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<StudentAndCourse> studentAndCourseList = ResultToObject.getDataByResultSet(resultSet, StudentAndCourse.class);
        if (studentAndCourseList.size() > 0) {
            return studentAndCourseList.get(0);
        } else {
            return null;
        }
    }
}
