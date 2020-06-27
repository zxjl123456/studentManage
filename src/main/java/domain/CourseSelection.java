package domain;

/**
 * 选课表实体
 */
public class CourseSelection {
    //学生学号
    private String studentId;
    //课程号
    private String courseNum;
    //成绩
    private float grade;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
