package servlet;

import model.ClassData;
import model.CourseData;
import service.ClassDataService;
import service.CourseDataService;
import service.StudentAndCourseService;
import service.impl.StudentAndCourseServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "dataStatistics", urlPatterns = "/dataStatistics")
public class DataStatisticsServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码
        request.setCharacterEncoding("UTF-8");
        //跳转页面
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("dataStatistics.jsp");
        //接受操作参数
        String type = request.getParameter("type");
        if (type.equals("search")) {
            String searchdataStatisticsMessage = request.getParameter("searchdataStatisticsMessage");
            String way = request.getParameter("searchWay");
            StudentAndCourseService studentAndCourseService = new StudentAndCourseServiceImpl();
            if (way.equals("class")) {
                //班级查询
                ClassDataService classDataService = new ClassDataService();
                List<ClassData> classDataList = classDataService.getClassData(searchdataStatisticsMessage);
                request.setAttribute("classDataList", classDataList);
                request.setAttribute("courseDataList", null);
            } else if (way.equals("courseId")) {
                //按课程号查询
                CourseDataService courseDataService = new CourseDataService();
                List<CourseData> courseDataList = courseDataService.getCourseDataList(searchdataStatisticsMessage);
                request.setAttribute("classDataList", null);
                request.setAttribute("courseDataList", courseDataList);
            }

            request.setAttribute("searchdataStatisticsMessage", searchdataStatisticsMessage);
            request.setAttribute("way", way);
        }
        requestDispatcher.forward(request, response);
    }
}
