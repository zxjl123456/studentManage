package servlet;

import domain.Student;
import util.DB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "searchStudentMessage", urlPatterns = "/searchStudentMessage")
public class SearchStudentMessage extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String way = request.getParameter("searchWay");
        String searchMessage = request.getParameter("searchMessage");
        System.out.println(way);
        request.setAttribute("searchMessage",searchMessage);
        List<Student> studentList = null;
        if (way.equals("id")) {
            //按学号查询
            studentList = new ArrayList<Student>();
            Connection conn = DB.getConn();
            String sql = "select studentId,studentName,sex,birthday,department from student where studentId = ?";
            PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
            try {
                preparedStatement.setString(1, searchMessage);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setStudentId(resultSet.getString("studentId"));
                    student.setStudentName(resultSet.getString("studentName"));
                    student.setSex(resultSet.getString("sex"));
                    student.setBirthday(resultSet.getDate("birthday"));
                    student.setDepartment(resultSet.getString("department"));
                    studentList.add(student);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            //按名字模糊查询
            //按学号查询
            studentList = new ArrayList<Student>();
            Connection conn = DB.getConn();
            String sql = "select studentId,studentName,sex,birthday,department from student where studentName like ?";
            PreparedStatement preparedStatement = DB.getPreparedStatement(conn, sql);
            try {
                preparedStatement.setString(1, "%" + searchMessage + "%");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setStudentId(resultSet.getString("studentId"));
                    student.setStudentName(resultSet.getString("studentName"));
                    student.setSex(resultSet.getString("sex"));
                    student.setBirthday(resultSet.getDate("birthday"));
                    student.setDepartment(resultSet.getString("department"));
                    studentList.add(student);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("studentList", studentList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("studentManager.jsp");
        requestDispatcher.forward(request, response);
    }
}
