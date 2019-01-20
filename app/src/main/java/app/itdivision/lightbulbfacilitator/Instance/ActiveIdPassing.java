package app.itdivision.lightbulbfacilitator.Instance;

public class ActiveIdPassing {
    private static ActiveIdPassing instance;
    private int ActiveId;
    private String ActiveCourseName;
    private String ActiveCourseCategory;

    public static ActiveIdPassing getInstance(){
        if(instance == null){
            instance = new ActiveIdPassing();
        }
        return instance;
    }

    public String getActiveCourseName() {
        return ActiveCourseName;
    }

    public void setActiveCourseName(String activeCourseName) {
        ActiveCourseName = activeCourseName;
    }

    public String getActiveCourseCategory() {
        return ActiveCourseCategory;
    }

    public void setActiveCourseCategory(String activeCourseCategory) {
        ActiveCourseCategory = activeCourseCategory;
    }

    public int getActiveId(){
        return ActiveId;
    }

    public void setActiveId(int activeId){
        ActiveId = activeId;
    }

}
