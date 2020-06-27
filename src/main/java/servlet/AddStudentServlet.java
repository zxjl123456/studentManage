package servlet;

import domain.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "addStudent", urlPatterns = "/addStudent")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码
        request.setCharacterEncoding("UTF-8");
        //接受参数
        String id = request.getParameter("addStudentId");
        String name = request.getParameter("addStudentName");
        String sex = request.getParameter("StudentSex");
        String department = request.getParameter("StudentDepartment");
        String birthday = request.getParameter("noToLayTime");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        //封装参数
        Student student = new Student();
        student.setStudentId(id);
        student.setStudentName(name);
        student.setSex(sex);
        student.setDepartment(department);
        try {
            student.setBirthday(sdf.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //移交到service
        StudentService studentService = new StudentServiceImpl();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
        if (studentService.addStudent(student)) {
            request.setAttribute("PromptMessage", "添加成功");
            request.setAttribute("SkipLinks", "<a href='studentManager.jsp'>返回</a>");
        } else {
            request.setAttribute("PromptMessage", "<font color='red'>添加失败！</font>");
            request.setAttribute("SkipLinks", "<a href='studentManager.jsp'>返回</a>");
        }
        requestDispatcher.forward(request, response);
    }
}
