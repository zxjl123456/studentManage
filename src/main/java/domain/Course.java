package domain;

/**
 * 课程实体
 */
public class Course {
    //课程编号
    private String courseNum;
    //课程名
    private String courseName;
    //学分
    private float grade;

    @Override
    public String toString() {
        return "Course{" +
                "courseNum='" + courseNum + '\'' +
                ", courseName='" + courseName + '\'' +
                ", grade=" + grade +
                '}';
    }

    public Course() {
    }

    public Course(String courseNum, String courseName, float grade) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.grade = grade;
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
