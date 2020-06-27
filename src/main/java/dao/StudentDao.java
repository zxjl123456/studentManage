package dao;

import domain.Student;
import util.DB;
import util.ResultToObject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生数据访问层
 */
public class StudentDao {

    /**
     * 添加一个学生记录
     *
     * @param student 学生对象
     * @return 添加的学生
     */
    public Student insert(Student student) {
        Connection conn = DB.getConn();
        String sql = "insert into student (studentId,studentName,department,sex,birthday) values(?,?,?,?,?)";
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        boolean flag = false;
        try {
            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getStudentName());
            preparedStatement.setString(3, student.getDepartment());
            preparedStatement.setString(4, student.getSex());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
            preparedStatement.setString(5, sdf.format(student.getBirthday()));
            int num = preparedStatement.executeUpdate();
            if (num >= 0) flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (flag) {
            return student;
        } else {
            return null;
        }
    }

    /**
     * 删除一条学生记录
     *
     * @param studentId 学生学号
     * @return 影响的记录条数
     */
    public boolean delete(String studentId) {
        int num = 0;
        Connection connection = DB.getConn();
        String sql = "delete from  student where studentId = ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, studentId);
            num = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num > 0;
    }

    /**
     * 修改一条学生记录
     *
     * @param student 学生对象
     * @return 修改后的学生对象
     */
    public Student update(Student student) {
        Connection conn = DB.getConn();
        String sql = "update student set studentName = ? ,department = ? , sex = ? ,birthday = ? where studentId = ?";
        int num = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
        try {
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setString(2, student.getDepartment());
            preparedStatement.setString(3, student.getSex());
            preparedStatement.setString(4, simpleDateFormat.format(student.getBirthday()));
            preparedStatement.setString(5, student.getStudentId());
            num = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (num > 0) {
            return student;
        } else {
            return null;
        }
    }

    /**
     * 通过学号查询学生记录
     *
     * @param studentId 学号
     * @return 查询到的学生记录
     */
    public Student selectByStudentId(String studentId) {
        Student student = null;
        Connection connection = DB.getConn();
        String sql = "select studentId,studentName,department,sex,birthday from student where studentId =  ?";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {
            preparedStatement.setString(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> studentList = ResultToObject.getDataByResultSet(resultSet, Student.class);
            student = studentList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    /**
     * 通过学生姓名模糊查询
     *
     * @param name 学生姓名
     * @return 学生记录列表
     */
    public List<Student> selectByName(String name) {
        List<Student> studentList = null;
        Connection connection = DB.getConn();
        String sql = "select studentId,studentName,department,sex,birthday where studentName like %?%";
        PreparedStatement preparedStatement = DB.getPreparedStatement(connection, sql);
        try {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            studentList = ResultToObject.getDataByResultSet(resultSet, Student.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

}
