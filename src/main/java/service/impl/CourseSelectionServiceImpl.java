package service.impl;

import dao.CourseSelectionDao;
import domain.CourseSelection;
import service.CourseSelectionService;

import java.util.List;

/**
 * 选课 服务实现类
 */
public class CourseSelectionServiceImpl implements CourseSelectionService {
    @Override
    public boolean insert(CourseSelection courseSelection) {
        CourseSelectionDao courseSelectionDao = new CourseSelectionDao();
        return courseSelectionDao.insert(courseSelection);
    }

    @Override
    public boolean delete(String studentId, String courseNum) {
        CourseSelectionDao courseSelectionDao = new CourseSelectionDao();
        return courseSelectionDao.delete(studentId, courseNum);
    }

    @Override
    public boolean update(CourseSelection courseSelection) {
        CourseSelectionDao courseSelectionDao = new CourseSelectionDao();
        return courseSelectionDao.update(courseSelection);
    }

    @Override
    public CourseSelection findByStudentIdAndCourseId(String studentId, String courseNum) {
        CourseSelectionDao courseSelectionDao = new CourseSelectionDao();
        return courseSelectionDao.findByStudentIdAndCourseId(studentId, courseNum);
    }

    @Override
    public List<CourseSelection> findByStudentId(String studentId) {
        CourseSelectionDao courseSelectionDao = new CourseSelectionDao();
        return courseSelectionDao.findByStudentId(studentId);
    }

    @Override
    public List<CourseSelection> findByStudentName(String studentName) {
        return null;
    }

    @Override
    public List<CourseSelection> findByCourseId(String courseId) {
        CourseSelectionDao courseSelectionDao = new CourseSelectionDao();
        return courseSelectionDao.findByCourseNum(courseId);
    }

    @Override
    public List<CourseSelection> findByCourseName(String courseName) {
        return null;
    }
}
