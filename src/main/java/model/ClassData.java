package model;

/**
 * 班级数据统计
 */
public class ClassData {
    //学号
    private String studentId;
    //姓名
    private String studentName;
    //总成绩
    private float totalGrade;
    //平均分
    private float averageGrade;

    @Override
    public String toString() {
        return "ClassData{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", totalGrade=" + totalGrade +
                ", averageGrade=" + averageGrade +
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

    public float getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(float totalGrade) {
        this.totalGrade = totalGrade;
    }

    public float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }
}
