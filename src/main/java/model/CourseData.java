package model;

/**
 * 按课程数据统计
 */
public class CourseData {
    //学号
    private String studentId;
    //姓名
    private String studentName;
    //成绩
    private float grade;

    @Override
    public String toString() {
        return "CourseData{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", grade=" + grade +
                '}';
    }

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

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
