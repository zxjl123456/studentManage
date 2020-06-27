package dao;

import domain.Course;
import domain.Student;
import model.StudentAndCourse;
import util.DB;
import util.ResultToObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程数据访问层
 */
public class CourseDao {

    /**
     * 添加一条课程记录
     *
     * @param course 课程
     * @return 添加的课程信息
     */
    public Course insert(Course course) {
        Connection conn = DB.getConn();
        String sql = "insert into course (courseNum,courseName,grade) values(?,?,?)";
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        boolean flag = false;
        try {
            preparedStatement.setString(1, course.getCourseNum());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setFloat(3, course.getGrade());
            int num = preparedStatement.executeUpdate();
            if (num >= 0) flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (flag) {
            return course;
        } else {
            return null;
        }
    }

    /**
     * 删除一条课程记录
     *
     * @param courseNum 课程号
     * @return 是否删除成功
     */
    public boolean delete(String courseNum) {
        int num = 0;
        Connection connection = DB.getConn();
        String sql = "delete from  course where courseNum = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, courseNum);
            num = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num > 0;
    }

    /**
     * 修改一条课程记录
     *
     * @param course 待修改的课程记录
     * @return 修改的课程记录
     */
    public Course update(Course course) {
        Connection conn = DB.getConn();
        String sql = "update course set courseName = ? , grade = ?  where courseNum = ?";
        int num = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        try {
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setFloat(2, course.getGrade());
            preparedStatement.setString(3, course.getCourseNum());
            num = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (num > 0) {
            return course;
        } else {
            return null;
        }
    }

    /**
     * 通过课程号查询课程记录
     *
     * @param courseNum 课程号
     * @return 查询到的课程记录
     */
    public Course selectByCourseId(String courseNum) {
        Connection connection = DB.getConn();
        String sql = "select courseNum,courseName,grade from course where courseNum =  ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, courseNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Course> courseList = ResultToObject.getDataByResultSet(resultSet, Course.class);
        if (courseList.size() > 0) {
            return courseList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 通过课程名模糊查询
     *
     * @param name 课程名
     * @return 课程记录列表
     */
    public List<Course> selectByName(String name) {
        List<Course> courseList = null;
        Connection connection = DB.getConn();
        String sql = "select courseNum,courseName,grade from course where courseName like ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {

            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            courseList = ResultToObject.getDataByResultSet(resultSet, Course.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    /**
     * 根据课程名模糊查询
     *
     * @param courseName 课程名
     * @return 查询记录
     */
    public List<Course> selectByCourseName(String courseName) {
        List<Course> courseList = null;
        Connection connection = DB.getConn();
        String sql = "select courseNum,courseName,grade from course  where courseName like ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, "%" + courseName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            courseList = ResultToObject.getDataByResultSet(resultSet, Course.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

}
