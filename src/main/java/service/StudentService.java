package service;

import domain.Student;

import java.util.List;

/**
 * 学生服务类
 */
public interface StudentService {

    /**
     * 通过学号查询学生信息
     *
     * @param studentId 学生学号
     * @return 查询的学生
     */
    Student findByStudentId(String studentId);

    /**
     * 根据学生姓名模糊查询学生信息
     *
     * @param studentName 学生姓名
     * @return 学生列表
     */
    List<Student> findByStudentName(String studentName);

    /**
     * 添加一条学生记录
     * @param student 添加的学生
     * @return 是否添加成功
     */
    boolean addStudent(Student student);

    /**
     * 根据学号删除学生记录
     *
     * @param studentId 学生学号
     */
    boolean deleteStudent(String studentId);

    /**
     * 修改学生记录
     *
     * @param student 修改的学生记录
     * @return 修改后的学生记录
     */
    Student modifyStudent(Student student);
}
