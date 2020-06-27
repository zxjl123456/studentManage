package dao;

import domain.CourseSelection;
import util.DB;
import util.ResultToObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 选课表数据访问层
 */
public class CourseSelectionDao {
    //增删改查

    /**
     * 增加一条选课记录
     *
     * @param courseSelection 选课记录
     * @return 是否添加成功
     */
    public boolean insert(CourseSelection courseSelection) {
        boolean flag = false;
        Connection conn = DB.getConn();
        String sql = "insert into courseselection (studentId,courseNum,grade)values(?,?,?)";
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        try {
            preparedStatement.setString(1, courseSelection.getStudentId());
            preparedStatement.setString(2, courseSelection.getCourseNum());
            preparedStatement.setFloat(3, courseSelection.getGrade());
            if (preparedStatement.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据课程号和学号删除一条选课记录
     *
     * @param studentId 学号
     * @param courseNum 课程号
     * @return 是否删除成功
     */
    public boolean delete(String studentId, String courseNum) {
        boolean flag = false;
        Connection connection = DB.getConn();
        String sql = "delete from courseselection where studentId = ? and courseNum = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, courseNum);
            if (preparedStatement.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条选课记录
     *
     * @param courseSelection 修改的选课记录
     * @return 是否成功修改
     */
    public boolean update(CourseSelection courseSelection) {
        boolean flag = false;
        Connection connection = DB.getConn();
        String sql = "update courseselection set grade = ? where studentId = ? and courseNum = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setFloat(1, courseSelection.getGrade());
            preparedStatement.setString(2, courseSelection.getStudentId());
            preparedStatement.setString(3, courseSelection.getCourseNum());
            if (preparedStatement.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 根据学号查询选课记录
     *
     * @param studentId 学生学号
     * @return 选课记录
     */
    public List<CourseSelection> findByStudentId(String studentId) {
        List<CourseSelection> courseSelectionList = null;
        Connection connection = DB.getConn();
        String sql = "select studentId , courseNum , grade from courseselection where studentId = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            courseSelectionList = ResultToObject.getDataByResultSet(resultSet, CourseSelection.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseSelectionList;
    }

    /**
     * 根据选课号查询选课记录
     *
     * @param courseNum 课程号
     * @return 选课记录
     */
    public List<CourseSelection> findByCourseNum(String courseNum) {
        List<CourseSelection> courseSelectionList = null;
        Connection connection = DB.getConn();
        String sql = "select studentId , courseNum , grade from courseselection where courseNum = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, courseNum);
            ResultSet resultSet = preparedStatement.executeQuery();
            courseSelectionList = ResultToObject.getDataByResultSet(resultSet, CourseSelection.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseSelectionList;
    }

    /**
     * 通学生学号和课程号查询选课记录
     *
     * @param studentId 学号
     * @param courseId  课程号
     * @return 查询记录
     */
    public CourseSelection findByStudentIdAndCourseId(String studentId, String courseId) {
        List<CourseSelection> courseSelectionList = null;
        Connection conn = DB.getConn();
        String sql = "select studentId , courseNum , grade from courseselection where courseNum = ? and studentId = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        try {
            preparedStatement.setString(1, courseId);
            preparedStatement.setString(2, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            courseSelectionList = ResultToObject.getDataByResultSet(resultSet, CourseSelection.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (courseSelectionList.size() > 0) {
            return courseSelectionList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 按学生姓名模糊查询
     *
     * @param studentName 学生姓名
     * @return
     */
    public List<CourseSelection> findByStudentName(String studentName) {
        List<CourseSelection> courseSelectionList = null;
        Connection conn = DB.getConn();
        String sql = "select cs.studentId , cs.courseNum , cs.grade from courseselection cs,student s where cs.studentId = s.studentId and s.studentName like ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        try {
            preparedStatement.setString(1, studentName);
            ResultSet resultSet = preparedStatement.executeQuery();
            courseSelectionList = ResultToObject.getDataByResultSet(resultSet, CourseSelection.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseSelectionList;
    }

    /**
     * 根据课程名模糊查找
     *
     * @param courseName 课程名
     * @return 记录
     */
    public List<CourseSelection> findByCourseName(String courseName) {
        List<CourseSelection> courseSelectionList = null;
        Connection conn = DB.getConn();
        String sql = "select cs.studentId , cs.courseNum , cs.grade from courseselection cs,course s where cs.courseNum = s.courseNum and s.courseName like ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        try {
            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();
            courseSelectionList = ResultToObject.getDataByResultSet(resultSet, CourseSelection.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseSelectionList;
    }

}