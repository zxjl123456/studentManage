package service;

import domain.Course;
import domain.Student;

import java.util.List;

public interface CourseService {

    /**
     * 通过课程号查询课程记录
     * @param courseNum 课程号
     * @return 课程记录
     */
    Course findByCourseNum(String courseNum);

    /**
     * 通过课程名迷糊查找
     * @param courseName 课程名
     * @return 查找得到的记录
     */
    List<Course> findByCourseName(String courseName);

    /**
     * 添加一条课程记录
     * @param course 课程记录
     * @return 是否添加成功
     */
    boolean addCourse(Course course);

    /**
     * 通过课程号删除一条记录
     * @param courseNum 课程号
     * @return 是否删除成功
     */
    boolean deleteCourse(String courseNum);

    /**
     * 修改一条课程记录
     * @param course 课程记录
     * @return 修改的课程记录
     */
    Course modifyCourse(Course course);

}
