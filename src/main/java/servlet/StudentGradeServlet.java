package servlet;

import domain.CourseSelection;
import model.StudentAndCourse;
import service.CourseSelectionService;
import service.CourseService;
import service.StudentAndCourseService;
import service.StudentService;
import service.impl.CourseSelectionServiceImpl;
import service.impl.StudentAndCourseServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 学生成绩管理
 */
@WebServlet(name = "studentGrade", urlPatterns = "/studentGrade")
public class StudentGradeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码
        request.setCharacterEncoding("UTF-8");
        //接受操作参数
        String type = request.getParameter("type");
        RequestDispatcher requestDispatcher = null;
        if (type.equals("search")) {
            String searchGradeMessage = request.getParameter("searchGradeMessage");
            String way = request.getParameter("searchWay");
            List<StudentAndCourse> studentAndCourseList = null;
            StudentAndCourseService studentAndCourseService = new StudentAndCourseServiceImpl();
            if (way.equals("studentId")) {
                //学号查询
                studentAndCourseList = studentAndCourseService.findByStudentId(searchGradeMessage);
            } else if (way.equals("studentName")) {
                //按姓名查询
                studentAndCourseList = studentAndCourseService.findByStudentName(searchGradeMessage);
            } else if (way.equals("courseId")) {
                //按课程号查询
                studentAndCourseList = studentAndCourseService.findByCourseId(searchGradeMessage);
            } else if (way.equals("courseName")) {
                //按课程名查询
                studentAndCourseList = studentAndCourseService.findByCourseName(searchGradeMessage);
            }
            request.setAttribute("searchGradeMessage", searchGradeMessage);
            request.setAttribute("studentAndCourseList", studentAndCourseList);
            requestDispatcher = request.getRequestDispatcher("studentCourseManager.jsp");
        } else if (type.equals("modify")) {
            //修改选课表信息
            String studentId = request.getParameter("id");
            String courseNum = request.getParameter("courseNum");
            CourseSelectionService courseSelectionService = new CourseSelectionServiceImpl();
            CourseSelection courseSelection = courseSelectionService.findByStudentIdAndCourseId(studentId, courseNum);

            requestDispatcher = request.getRequestDispatcher("modifyCourseSelection.jsp");
            request.setAttribute("courseSelection", courseSelection);
        } else if (type.equals("delete")) {
            //删除选课表信息
            String studentId = request.getParameter("id");
            String courseNum = request.getParameter("courseNum");
            CourseSelectionService courseSelectionService = new CourseSelectionServiceImpl();
            requestDispatcher = request.getRequestDispatcher("message.jsp");
            if (courseSelectionService.delete(studentId, courseNum)) {
                request.setAttribute("PromptMessage", "删除成功");
                request.setAttribute("SkipLinks", "<a href='studentCourseManager.jsp'>返回</a>");
            } else {
                request.setAttribute("PromptMessage", "<font color='red'>删除失败</font>");
                request.setAttribute("SkipLinks", "<a href='studentCourseManager.jsp'>返回</a>");
            }

        } else if (type.equals("update")) {
            //更新修改信息
            String studentId = request.getParameter("studentId");
            String courseNum = request.getParameter("courseNum");
            String grade = request.getParameter("grade");
            //封装参数
            CourseSelection courseSelection = new CourseSelection();
            courseSelection.setStudentId(studentId);
            courseSelection.setCourseNum(courseNum);
            courseSelection.setGrade(Float.parseFloat(grade));
            //转交到service
            CourseSelectionService courseSelectionService = new CourseSelectionServiceImpl();
            //页面跳转
            requestDispatcher = request.getRequestDispatcher("message.jsp");
            if (courseSelectionService.update(courseSelection)) {
                request.setAttribute("PromptMessage", "修改成功");
                request.setAttribute("SkipLinks", "<a href='studentCourseManager.jsp'>返回</a>");
            } else {
                request.setAttribute("PromptMessage", "<font color='red'>修改失败</font>");
                request.setAttribute("SkipLinks", "<a href='studentCourseManager.jsp'>返回</a>");
            }
        } else if (type.equals("add")) {
            //添加选课成绩
            //接受参数
            String studentId = request.getParameter("studentId");
            String courseNum = request.getParameter("courseNum");
            String grade = request.getParameter("grade");
            //封装参数
            CourseSelection courseSelection = new CourseSelection();
            courseSelection.setStudentId(studentId);
            courseSelection.setCourseNum(courseNum);
            courseSelection.setGrade(Float.parseFloat(grade));
            //转发到service层
            CourseSelectionService courseSelectionService = new CourseSelectionServiceImpl();
            //页面跳转
            requestDispatcher = request.getRequestDispatcher("message.jsp");
            if (courseSelectionService.insert(courseSelection)) {
                request.setAttribute("PromptMessage", "修改成功");
                request.setAttribute("SkipLinks", "<a href='studentCourseManager.jsp'>返回</a>");
            } else {
                request.setAttribute("PromptMessage", "<font color='red'>修改失败</font>");
                request.setAttribute("SkipLinks", "<a href='studentCourseManager.jsp'>返回</a>");
            }
        }
        requestDispatcher.forward(request, response);
    }
}