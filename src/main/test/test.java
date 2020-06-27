import dao.CourseDao;
import domain.Course;
import domain.Student;
import domain.User;
import model.ClassData;
import model.CourseData;
import service.ClassDataService;
import service.CourseDataService;
import util.DB;
import util.ExportExcel;
import util.ResultToObject;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {


    public static void main(String[] args) {
        // 测试学生
        ExportExcel<Course> ex = new ExportExcel<Course>();
        ex.setTitle("选课表");
        String[] headers =
                {"课程编号", "课程名", "学分"};
        List<Course> dataset = new ArrayList<Course>();
        dataset.add(new Course("1001", "张三", 20));
        dataset.add(new Course("1002", "张三", 21));
        dataset.add(new Course("1003", "张三", 22));
        String path = System.getProperty("user.dir") + "/temp";
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

        try {
            OutputStream out = new FileOutputStream(path + "/a.xls");
            ex.exportExcel(headers, dataset, out);
            out.close();
            System.out.println("excel导出成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f_d = new File(path + "/a.xls");
        if (f_d.exists() && f_d.isFile()) {
            f_d.delete();
        }


    }


}
