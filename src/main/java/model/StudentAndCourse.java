package model;

/**
 * 学生课程中间实体
 */
public class StudentAndCourse {
    //学生
    //学生学号
    private String studentId;
    //学生姓名
    private String studentName;
    //课程
    //课程编号
    private String courseNum;
    //课程名
    private String courseName;
    //选课
    private float grade;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
