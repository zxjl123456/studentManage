package servlet;

import domain.Course;
import service.CourseService;
import service.impl.CourseServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "courseServlet", urlPatterns = "/courseServlet")
public class CourseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("courseManager.jsp");
        if (type.equals("search")) {
            String searchMassage = request.getParameter("searchMessage");
            String way = request.getParameter("searchWay");
            List<Course> courseList = null;
            if (way.equals("id")) {
                //按课程号查询
                CourseService courseService = new CourseServiceImpl();
                courseList = new ArrayList<Course>();
                courseList.add(courseService.findByCourseNum(searchMassage));
            } else if (way.equals("name")) {
                //按课程名查询
                CourseService courseService = new CourseServiceImpl();
                courseList = courseService.findByCourseName(searchMassage);
            }
            request.setAttribute("searchCourseMessage",searchMassage);
            request.setAttribute("courseList", courseList);
        } else if (type.equals("delete")) {
            String courseNum = request.getParameter("id");
            CourseService courseService = new CourseServiceImpl();
            requestDispatcher = request.getRequestDispatcher("message.jsp");
            if (courseService.deleteCourse(courseNum)) {
                request.setAttribute("PromptMessage", "删除成功");
                request.setAttribute("SkipLinks", "<a href='courseManager.jsp'>返回</a>");
            } else {
                request.setAttribute("PromptMessage", "<font color='red'>删除失败</font>");
                request.setAttribute("SkipLinks", "<a href='courseManager.jsp'>返回</a>");
            }
        } else if (type.equals("modify")) {
            String courseNum = request.getParameter("id");
            CourseService courseService = new CourseServiceImpl();
            Course course = courseService.findByCourseNum(courseNum);
            requestDispatcher = request.getRequestDispatcher("modifyCourse.jsp");
            request.setAttribute("course", course);
        } else if (type.equals("update")) {
            //接受参数
            String courseNum = request.getParameter("courseNum");
            String courseName = request.getParameter("courseName");
            String grade = request.getParameter("grade");
            //封装参数
            Course course = new Course();
            course.setCourseNum(courseNum);
            course.setCourseName(courseName);
            course.setGrade(Float.parseFloat(grade));
            //转交到service
            CourseService courseService = new CourseServiceImpl();
            //页面跳转
            requestDispatcher = request.getRequestDispatcher("message.jsp");
            if (courseService.modifyCourse(course) != null) {
                request.setAttribute("PromptMessage", "修改成功");
                request.setAttribute("SkipLinks", "<a href='courseManager.jsp'>返回</a>");
            } else {
                request.setAttribute("PromptMessage", "<font color='red'>修改失败</font>");
                request.setAttribute("SkipLinks", "<a href='courseManager.jsp'>返回</a>");
            }
        } else if (type.equals("add")) {
            //接受参数
            String courseNum = request.getParameter("courseNum");
            String courseName = request.getParameter("courseName");
            String grade = request.getParameter("grade");
            //封装参数
            Course course = new Course();
            course.setCourseNum(courseNum);
            course.setCourseName(courseName);
            course.setGrade(Float.parseFloat(grade));
            //转交到service
            CourseService courseService = new CourseServiceImpl();
            //页面跳转
            requestDispatcher = request.getRequestDispatcher("message.jsp");
            if (courseService.addCourse(course)) {
                request.setAttribute("PromptMessage", "添加成功");
                request.setAttribute("SkipLinks", "<a href='courseManager.jsp'>返回</a>");
            } else {
                request.setAttribute("PromptMessage", "<font color='red'>添加失败</font>");
                request.setAttribute("SkipLinks", "<a href='courseManager.jsp'>返回</a>");
            }
        }
        requestDispatcher.forward(request, response);
    }
}
