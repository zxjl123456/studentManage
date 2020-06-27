package service.impl;

import dao.CourseDao;
import domain.Course;
import service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    public Course findByCourseNum(String courseNum) {
        CourseDao courseDao = new CourseDao();
        return courseDao.selectByCourseId(courseNum);
    }

    public List<Course> findByCourseName(String courseName) {
        CourseDao courseDao = new CourseDao();
        return courseDao.selectByName(courseName);
    }

    public boolean addCourse(Course course) {
        boolean flag = false;
        CourseDao courseDao = new CourseDao();
        if (courseDao.insert(course) != null) {
            flag = true;
        }
        return flag;
    }

    public boolean deleteCourse(String courseNum) {
        boolean flag = false;
        CourseDao courseDao = new CourseDao();
        if (courseDao.delete(courseNum)) {
            flag = true;
        }
        return flag;
    }

    public Course modifyCourse(Course course) {
        CourseDao courseDao = new CourseDao();
        return courseDao.update(course);
    }
}