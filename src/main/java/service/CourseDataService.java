package service;

import model.CourseData;
import util.DB;
import util.ResultToObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseDataService {

    /**
     * 根据课程号查询选修本门课的学生考试情况
     *
     * @param courseNum 课程编号
     * @return 结果集
     */
    public List<CourseData> getCourseDataList(String courseNum) {
        List<CourseData> courseDataList = null;
        Connection connection = DB.getConn();
        String sql = "select s.studentId,studentName,grade from student s,courseselection cs where cs.studentId = s.studentId and cs.courseNum = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, courseNum);
            ResultSet resultSet = preparedStatement.executeQuery();
            courseDataList = ResultToObject.getDataByResultSet(resultSet, CourseData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseDataList;
    }
}
