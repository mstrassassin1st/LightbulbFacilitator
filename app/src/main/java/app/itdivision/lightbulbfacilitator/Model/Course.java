package app.itdivision.lightbulbfacilitator.Model;

public class Course {
    private String CourseName;
    private String CourseCategory;
    private String CourseDesc;
    private float CoursePrice;

    public Course(String courseName, String courseCategory, String courseDesc, float coursePrice) {
        CourseName = courseName;
        CourseCategory = courseCategory;
        CourseDesc = courseDesc;
        CoursePrice = coursePrice;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseCategory() {
        return CourseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        CourseCategory = courseCategory;
    }

    public String getCourseDesc() {
        return CourseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        CourseDesc = courseDesc;
    }

    public float getCoursePrice() {
        return CoursePrice;
    }

    public void setCoursePrice(float coursePrice) {
        CoursePrice = coursePrice;
    }
}
