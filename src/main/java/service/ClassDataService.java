package service;

import domain.CourseSelection;
import model.ClassData;
import service.impl.CourseSelectionServiceImpl;
import service.impl.StudentServiceImpl;
import util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDataService {
    /**
     * 按班级号得到选课结果集
     *
     * @param classNum 班级
     * @return 结果数据集
     */
    public List<ClassData> getClassData(String classNum) {
        //根据班级号码查到班级里的所有人的学号
        List<String> studentIdList = new ArrayList<>();
        List<ClassData> classDataArrayList = new ArrayList<>();
        Connection conn = DB.getConn();
        String sql = "select studentId from student where studentId like ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        try {
            preparedStatement.setString(1, classNum + "__");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                studentIdList.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //根据学号查询每个学号的选修课情况并统计
        CourseSelectionService courseSelectionService = new CourseSelectionServiceImpl();
        StudentService studentService = new StudentServiceImpl();
        for (String studentId : studentIdList) {
            //选修课情况
            List<CourseSelection> courseSelectionList = courseSelectionService.findByStudentId(studentId);
            int listSize = courseSelectionList.size();
            if (listSize > 0) {
                ClassData classData = new ClassData();
                //统计总成绩
                float sum = 0;
                for (CourseSelection courseSelection : courseSelectionList) {
                    sum += courseSelection.getGrade();
                }
                classData.setTotalGrade(sum);
                classData.setAverageGrade(sum / listSize);
                classData.setStudentId(studentId);
                classData.setStudentName(studentService.findByStudentId(studentId).getStudentName());
                classDataArrayList.add(classData);
            }
        }
        return classDataArrayList;
    }

}
