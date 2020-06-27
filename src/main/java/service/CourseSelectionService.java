package service;

import domain.CourseSelection;

import java.util.List;

public interface CourseSelectionService {
    boolean insert(CourseSelection courseSelection);

    boolean delete(String studentId, String courseNum);

    boolean update(CourseSelection courseSelection);

    CourseSelection findByStudentIdAndCourseId(String studentId, String courseNum);

    List<CourseSelection> findByStudentId(String studentId);

    List<CourseSelection> findByStudentName(String studentName);

    List<CourseSelection> findByCourseId(String courseId);

    List<CourseSelection> findByCourseName(String courseName);


}