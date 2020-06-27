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

@WebServlet(name = "studentOperation", urlPatterns = "/studentOperation")
public class studentOperation extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码
        request.setCharacterEncoding("UTF-8");
        //接受操作参数
        String type = request.getParameter("type");
        RequestDispatcher requestDispatcher = null;
        if (type.equals("modify")) {
            System.out.println("修改");
            String studentId = request.getParameter("id");
            StudentService studentService = new StudentServiceImpl();
            Student student = studentService.findByStudentId(studentId);
            requestDispatcher = request.getRequestDispatcher("modifyStudent.jsp");
            request.setAttribute("student", student);
        } else if (type.equals("delete")) {
            System.out.println("删除");
            String studentId = request.getParameter("id");
            StudentService studentService = new StudentServiceImpl();
            requestDispatcher = request.getRequestDispatcher("message.jsp");
            if(studentService.deleteStudent(studentId)){
                request.setAttribute("PromptMessage", "删除成功");
                request.setAttribute("SkipLinks", "<a href='studentManager.jsp'>返回</a>");
            }else{
                request.setAttribute("PromptMessage", "<font color='red'>删除失败</font>");
                request.setAttribute("SkipLinks", "<a href='studentManager.jsp'>返回</a>");
            }

        } else if (type.equals("update")) {
            //接收参数
            String studentId = request.getParameter("addStudentId");
            String studentName = request.getParameter("addStudentName");
            String sex = request.getParameter("StudentSex");
            String department = request.getParameter("StudentDepartment");
            String birthday = request.getParameter("noToLayTime");
            //日期转换
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //封装参数
            Student student = new Student();
            student.setStudentId(studentId);
            student.setStudentName(studentName);
            student.setSex(sex);
            student.setDepartment(department);
            try {
                student.setBirthday(simpleDateFormat.parse(birthday));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //转交到service
            StudentService studentService = new StudentServiceImpl();
            //页面跳转
            requestDispatcher = request.getRequestDispatcher("message.jsp");
            if (studentService.modifyStudent(student) != null) {
                request.setAttribute("PromptMessage", "修改成功");
                request.setAttribute("SkipLinks", "<a href='studentManager.jsp'>返回</a>");
            } else {
                request.setAttribute("PromptMessage", "<font color='red'>修改失败</font>");
                request.setAttribute("SkipLinks", "<a href='studentManager.jsp'>返回</a>");
            }
        }
        requestDispatcher.forward(request, response);
    }
}