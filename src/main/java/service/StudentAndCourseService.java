package service;

import model.StudentAndCourse;

import java.util.List;

/**
 * StudentAndCourse视图服务层
 * 仅提供查询
 */
public interface StudentAndCourseService {
    /**
     * 根据学号查询
     *
     * @param studentId 学号
     * @return 查询结果
     */
    List<StudentAndCourse> findByStudentId(String studentId);

    /**
     * 根据学生姓名查询
     *
     * @param studentName 学生姓名
     * @return
     */
    List<StudentAndCourse> findByStudentName(String studentName);

    /**
     * 根据课程编号查询
     *
     * @param courseId 课程编号
     * @return 查询结果
     */
    List<StudentAndCourse> findByCourseId(String courseId);

    /**
     * 根据课程名查询
     *
     * @param courseName 课程名
     * @return 查询结果
     */
    List<StudentAndCourse> findByCourseName(String courseName);

    /**
     * 通过学号和课程号查询
     * @param studentId 学号
     * @param courseNum 课程号
     * @return 记录
     */
    StudentAndCourse findByStudentIdAndCourseName(String studentId, String courseNum);

}
