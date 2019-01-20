package app.itdivision.lightbulbfacilitator;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.itdivision.lightbulbfacilitator.Adapter.LessonAdapter;
import app.itdivision.lightbulbfacilitator.Database.DatabaseAccess;
import app.itdivision.lightbulbfacilitator.Instance.ActiveIdPassing;
import app.itdivision.lightbulbfacilitator.Model.Lesson;

public class UpdateCourse extends AppCompatActivity {

    EditText moduleLesson;
    EditText moduleName;
    EditText moduleURL;
    TextView courseName;
    TextView courseType;
    Button btnDone;
    RecyclerView rvLessonUpdater;
    int currModule = 0;
    boolean isEdit = false;

    List<Lesson> lessonList;

    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(UpdateCourse.this);

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

        final ActiveIdPassing activeIdPassing = ActiveIdPassing.getInstance();
        courseName.setText(activeIdPassing.getActiveCourseName());
        courseType.setText(activeIdPassing.getActiveCourseCategory());

        databaseAccess.open();
        final int find = databaseAccess.getCourseID(activeIdPassing.getActiveCourseName(), activeIdPassing.getActiveCourseCategory());
        lessonList = new ArrayList<>();
        Cursor cursor = databaseAccess.getLesson(find);
        while(cursor.moveToNext()){
            lessonList.add(new Lesson(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }

        rvLessonUpdater.setLayoutManager(new LinearLayoutManager(this));
        LessonAdapter lessonAdapter = new LessonAdapter(this, lessonList);
        rvLessonUpdater.setAdapter(lessonAdapter);
        lessonAdapter.setOnBtnEditLessonClickListener(new LessonAdapter.OnBtnEditLessonClickListener() {
            @Override
            public void onBtnEditLessonClicked(String LessonName, String LessonDesc) {
                databaseAccess.open();
                String URL = databaseAccess.getLessonURL(find, LessonName, LessonDesc);
                currModule = databaseAccess.getLessonID(find, LessonName, LessonDesc);
                databaseAccess.close();
                String fixedURL = "https://youtu.be/"+URL;
                moduleName.setText(LessonDesc);
                moduleLesson.setText(LessonName);
                moduleURL.setText(fixedURL);
                isEdit = true;
            }
        });
        lessonAdapter.setOnBtnDeleteLessonClickListener(new LessonAdapter.OnBtnDeleteLessonClickListener() {
            @Override
            public void onBtnDeleteLessonClicked(String LessonName, String LessonDesc) {
                databaseAccess.open();
                currModule = databaseAccess.getLessonID(find, LessonName, LessonDesc);
                databaseAccess.deleteModule(currModule);
                databaseAccess.close();
                Toast.makeText(UpdateCourse.this, "Module Updated!", Toast.LENGTH_LONG).show();
                onUpdate();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updateName = moduleLesson.getText().toString();
                String updateDesc = moduleName.getText().toString();
                String updateURL = moduleURL.getText().toString();
                String fixedupdateURL = updateURL.substring(updateURL.lastIndexOf('/')+1,updateURL.length());

                if(updateName.equals("") && updateDesc.equals("") && updateURL.equals("")){
                    Intent intent = new Intent(UpdateCourse.this, Courses.class);
                    startActivity(intent);
                    finish();
                }else if(updateName.equals("") || updateDesc.equals("") || updateURL.equals("")){
                    Toast.makeText(UpdateCourse.this, "All forms must be filled!", Toast.LENGTH_LONG).show();
                }else{
                    databaseAccess.open();
                    if(isEdit){
                        databaseAccess.setUpdateModule(currModule, updateName, updateDesc,fixedupdateURL);
                    }else {
                        databaseAccess.addModule(find, updateName, updateDesc, fixedupdateURL);
                    }
                    databaseAccess.close();
                    Toast.makeText(UpdateCourse.this, "Module Updated!", Toast.LENGTH_LONG).show();
                    onUpdate();
                    isEdit = false;
                }
            }
        });
    }

    public void onUpdate(){
        this.recreate();
        moduleName.setText("");
        moduleLesson.setText("");
        moduleURL.setText("");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
