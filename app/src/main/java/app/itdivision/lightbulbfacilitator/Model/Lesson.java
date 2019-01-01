package app.itdivision.lightbulbfacilitator.Model;

public class Lesson {
    private String lessonName;
    private String lessonDesc;
    private String lessonURL;


    public Lesson(String lessonName, String lessonDesc, String lessonURL) {
        this.lessonName = lessonName;
        this.lessonDesc = lessonDesc;
        this.lessonURL = lessonURL;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonDesc() {
        return lessonDesc;
    }

    public void setLessonDesc(String lessonDesc) {
        this.lessonDesc = lessonDesc;
    }

    public String getLessonURL() {
        return lessonURL;
    }

    public void setLessonURL(String lessonURL) {
        this.lessonURL = lessonURL;
    }


}
