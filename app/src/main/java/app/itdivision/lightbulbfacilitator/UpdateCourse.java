package app.itdivision.lightbulbfacilitator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.itdivision.lightbulbfacilitator.Adapter.LessonAdapter;
import app.itdivision.lightbulbfacilitator.Model.Lesson;

public class UpdateCourse extends AppCompatActivity {

    EditText moduleLesson;
    EditText moduleName;
    EditText moduleURL;
    TextView courseName;
    TextView courseType;
    Button btnDone;
    RecyclerView rvLessonUpdater;

    List<Lesson> lessonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        moduleLesson = (EditText) findViewById(R.id.etModule);
        moduleName = (EditText) findViewById(R.id.etModuleName);
        moduleURL = (EditText) findViewById(R.id.etModuleURL);
        courseName = (TextView) findViewById(R.id.tv_courseName);
        courseType = (TextView) findViewById(R.id.tv_courseType);
        btnDone = (Button) findViewById(R.id.btn_done);
        rvLessonUpdater = (RecyclerView) findViewById(R.id.rv_update);

        lessonList = new ArrayList<>();
        lessonList.add(new Lesson("Lesson 1", "Lesson Description", "http://url.url/url"));
        lessonList.add(new Lesson("Lesson 2", "Lesson Description", "http://url.url/url"));
        lessonList.add(new Lesson("Lesson 3", "Lesson Description", "http://url.url/url"));
        lessonList.add(new Lesson("Lesson 4", "Lesson Description", "http://url.url/url"));
        lessonList.add(new Lesson("Lesson 5", "Lesson Description", "http://url.url/url"));
        lessonList.add(new Lesson("Lesson 6", "Lesson Description", "http://url.url/url"));
        lessonList.add(new Lesson("Lesson 7", "Lesson Description", "http://url.url/url"));
        lessonList.add(new Lesson("Lesson 8", "Lesson Description", "http://url.url/url"));
        lessonList.add(new Lesson("Lesson 9", "Lesson Description", "http://url.url/url"));

        rvLessonUpdater.setLayoutManager(new LinearLayoutManager(this));
        LessonAdapter lessonAdapter = new LessonAdapter(this, lessonList);
        rvLessonUpdater.setAdapter(lessonAdapter);
    }
}
