package servlet;

import dao.CourseDao;
import domain.Course;
import model.ClassData;
import model.CourseData;
import service.ClassDataService;
import service.CourseDataService;
import util.ExportExcel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 导出成Excel
 */
@WebServlet(name = "printfToExcel", urlPatterns = "/printfToExcel")
public class PrintToExcel extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("printfType");
        String message = request.getParameter("meaage");
        String path = null;
        if (type.equals("class")) {
            path = saveClass(message);
        } else if (type.equals("courseId")) {
            path = saveCourse(message);
        }
        download(path, response);
        displaced(path);
    }

    /**
     * 销毁临时文件
     *
     * @param path 路径
     */
    private void displaced(String path) {
        File f_d = new File(path);
        if (f_d.exists() && f_d.isFile()) {
            f_d.delete();
        }
    }

    private String saveCourse(String message) {
        // 导出选修message的课程成绩
        ExportExcel<CourseData> ex = new ExportExcel<CourseData>();
        //得到课程名
        CourseDao courseDao = new CourseDao();
        Course course = courseDao.selectByCourseId(message);
        ex.setTitle(course.getCourseName() + "成绩表");
        String[] headers =
                {"学号", "姓名", "成绩"};
        CourseDataService courseDataService = new CourseDataService();
        List<CourseData> courseDataList = courseDataService.getCourseDataList(message);
        String path = System.getProperty("user.dir") + "//temp";
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        } else {
            if (!f.isDirectory()) {
                f.mkdir();
            } else {
                System.out.println("f存在");
            }

        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
        path = path + "//" + course.getCourseName() + "成绩表" + simpleDateFormat.format(new Date()) + ".xls";
        try {
            OutputStream out = new FileOutputStream(path);
            ex.exportExcel(headers, courseDataList, out);
            out.close();
            System.out.println("excel导出成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    private String saveClass(String message) {
        // 导出message 班级成员的成绩表
        ExportExcel<ClassData> ex = new ExportExcel<ClassData>();

        ex.setTitle(message + "班成绩表");
        String[] headers =
                {"学号", "姓名", "总成绩", "平均分"};
        //得到数据集合
        ClassDataService classDataService = new ClassDataService();
        List<ClassData> classDataList = classDataService.getClassData(message);
        String path = System.getProperty("user.dir") + "//temp";
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        } else {
            if (!f.isDirectory()) {
                f.mkdir();
            } else {
                System.out.println("f存在");
            }

        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh:mm");
        path = path + "//" + message + "班成绩表" + simpleDateFormat.format(new Date()) + ".xls";
        try {
            OutputStream out = new FileOutputStream(path);
            ex.exportExcel(headers, classDataList, out);
            out.close();
            System.out.println("excel导出成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


    private void download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(
                    response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
