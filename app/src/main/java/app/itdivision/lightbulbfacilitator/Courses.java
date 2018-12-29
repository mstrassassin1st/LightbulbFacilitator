package app.itdivision.lightbulbfacilitator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import app.itdivision.lightbulbfacilitator.Adapter.CourseAdapter;
import app.itdivision.lightbulbfacilitator.Model.Course;

public class Courses extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner select_category;
    RecyclerView rvCourses;
    FloatingActionButton fabAddCourse;
    List<Course> courseList;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_all_courses:
                    return true;
                case R.id.navigation_balance:
                    return true;
                case R.id.navigation_account:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        select_category = (Spinner) findViewById(R.id.select_category);
        rvCourses = (RecyclerView) findViewById(R.id.rv_courses);
        fabAddCourse = (FloatingActionButton) findViewById(R.id.fabAddCourse);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.selection_courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_category.setAdapter(adapter);
        select_category.setOnItemSelectedListener(this);

        courseList = new ArrayList<>();
        courseList.add(new Course("Kotlin On Android", "Information Technology", "Description", (float) 200));
        courseList.add(new Course("Teknik Menggambar Batik", "Art and Design", "Description", (float) 200));
        courseList.add(new Course("Marketing Strategy", "Business", "Description", (float) 200));
        courseList.add(new Course("E-Business", "Business", "Description", (float) 200));
        courseList.add(new Course("Data Structure in C", "Information Technology", "Description", (float) 200));

        rvCourses.setLayoutManager(new GridLayoutManager(this, 2));
        CourseAdapter crsadapter = new CourseAdapter(this, courseList);
        rvCourses.setAdapter(crsadapter);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fabAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Courses.this, "Add Course?", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if(text.equals("")){

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
