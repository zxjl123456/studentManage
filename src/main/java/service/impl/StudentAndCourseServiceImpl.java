package service.impl;

import dao.StudentAndCourseDao;
import model.StudentAndCourse;
import service.StudentAndCourseService;

import java.util.List;

public class StudentAndCourseServiceImpl implements StudentAndCourseService {
    public List<StudentAndCourse> findByStudentId(String studentId) {
        StudentAndCourseDao studentAndCourseDao = new StudentAndCourseDao();
        return studentAndCourseDao.findByStudentId(studentId);
    }

    public List<StudentAndCourse> findByStudentName(String studentName) {
        StudentAndCourseDao studentAndCourseDao = new StudentAndCourseDao();
        return studentAndCourseDao.findByStudentName(studentName);
    }

    public List<StudentAndCourse> findByCourseId(String courseId) {
        StudentAndCourseDao studentAndCourseDao = new StudentAndCourseDao();
        return studentAndCourseDao.findByCourseId(courseId);
    }

    public StudentAndCourse findByStudentIdAndCourseName(String studentId, String courseNum) {
        StudentAndCourseDao studentAndCourseDao = new StudentAndCourseDao();
        return studentAndCourseDao.findByStudentIdAndCourseId(studentId, courseNum);
    }

    public List<StudentAndCourse> findByCourseName(String courseName) {
        StudentAndCourseDao studentAndCourseDao = new StudentAndCourseDao();
        return studentAndCourseDao.findByCourseName(courseName);
    }
}
