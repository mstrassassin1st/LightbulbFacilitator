package app.itdivision.lightbulbfacilitator.Model;

import android.graphics.Bitmap;

public class Course {
    private String CourseName;
    private String CourseCategory;
    private String CourseDesc;
    private int CoursePrice;
    private Bitmap thumbnail;

    public Course(String courseName, String courseCategory, String courseDesc, int coursePrice, Bitmap thumbnail) {
        CourseName = courseName;
        CourseCategory = courseCategory;
        CourseDesc = courseDesc;
        CoursePrice = coursePrice;
        this.thumbnail = thumbnail;
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

    public int getCoursePrice() {
        return CoursePrice;
    }

    public void setCoursePrice(int coursePrice) {
        CoursePrice = coursePrice;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }
}
