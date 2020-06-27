package service.impl;

import dao.StudentDao;
import domain.Student;
import service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    public Student findByStudentId(String studentId) {
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.selectByStudentId(studentId);
        return student;
    }

    public List<Student> findByStudentName(String studentName) {
        StudentDao studentDao = new StudentDao();
        return studentDao.selectByName(studentName);
    }

    public boolean addStudent(Student student) {
        boolean flag = false;
        StudentDao studentDao = new StudentDao();
        if (studentDao.insert(student) != null) {
            flag = true;
        }
        return flag;
    }

    public boolean deleteStudent(String studentId) {
        boolean flag = false;
        StudentDao studentDao = new StudentDao();
        if (studentDao.delete(studentId)) {
            flag = true;
        }
        return flag;
    }

    public Student modifyStudent(Student student) {
        StudentDao studentDao = new StudentDao();
        return studentDao.update(student);
    }
}
