package app.itdivision.lightbulbfacilitator;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.util.ArrayList;
import java.util.List;

import app.itdivision.lightbulbfacilitator.Adapter.CourseAdapter;
import app.itdivision.lightbulbfacilitator.Database.DatabaseAccess;
import app.itdivision.lightbulbfacilitator.Instance.ActiveIdPassing;
import app.itdivision.lightbulbfacilitator.Model.Course;

public class Courses extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner select_category;
    RecyclerView rvCourses;
    FloatingActionButton fabAddCourse;
    List<Course> courseList;
    BottomNavigationView navigation;

    public static Activity home;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_all_courses:
                    //
                    break;
                case R.id.navigation_balance:
                    Intent balanceIntent = new Intent(Courses.this, Balance.class);
                    startActivity(balanceIntent);
                    break;
                case R.id.navigation_account:
                    Intent accountIntent = new Intent(Courses.this, Account.class);
                    startActivity(accountIntent);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        home = this;

        select_category = (Spinner) findViewById(R.id.select_category);
        rvCourses = (RecyclerView) findViewById(R.id.rv_courses);
        fabAddCourse = (FloatingActionButton) findViewById(R.id.fabAddCourse);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.selection_courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_category.setAdapter(adapter);
        select_category.setOnItemSelectedListener(this);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fabAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this, AddCourse.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        ActiveIdPassing activeIdPassing = ActiveIdPassing.getInstance();
        int idQ = activeIdPassing.getActiveId();
        if(text.equals("Select Category..")){
            courseList = new ArrayList<>();
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
            databaseAccess.open();
            Cursor cursor = databaseAccess.getCourses(idQ);
            while (cursor.moveToNext()){
                byte[] imgByte = cursor.getBlob(4);
                Bitmap cover = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                courseList.add(new Course(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cover));
            }
            cursor.close();
            databaseAccess.close();
        } else if(text.equals("Information Technology")){
            courseList = new ArrayList<>();
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
            databaseAccess.open();
            Cursor cursor = databaseAccess.getSpecificCourses(idQ,1);
            while (cursor.moveToNext()){
                byte[] imgByte = cursor.getBlob(4);
                Bitmap cover = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                courseList.add(new Course(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cover));
            }
            cursor.close();
            databaseAccess.close();
        }else if(text.equals("Art and Design")){
            courseList = new ArrayList<>();
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
            databaseAccess.open();
            Cursor cursor = databaseAccess.getSpecificCourses(idQ,2);
            while (cursor.moveToNext()){
                byte[] imgByte = cursor.getBlob(4);
                Bitmap cover = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                courseList.add(new Course(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cover ));
            }
            cursor.close();
            databaseAccess.close();
        }else if(text.equals("Physics")){
            courseList = new ArrayList<>();
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
            databaseAccess.open();
            Cursor cursor = databaseAccess.getSpecificCourses(idQ,4);
            while (cursor.moveToNext()){
                byte[] imgByte = cursor.getBlob(4);
                Bitmap cover = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                courseList.add(new Course(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cover));
            }
            cursor.close();
            databaseAccess.close();

        }else if(text.equals("Music")){
            courseList = new ArrayList<>();
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
            databaseAccess.open();
            Cursor cursor = databaseAccess.getSpecificCourses(idQ,5);
            while (cursor.moveToNext()){
                byte[] imgByte = cursor.getBlob(4);
                Bitmap cover = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                courseList.add(new Course(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cover));
            }
            cursor.close();
            databaseAccess.close();

        }else if(text.equals("English")){
            courseList = new ArrayList<>();
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
            databaseAccess.open();
            Cursor cursor = databaseAccess.getSpecificCourses(idQ,6);
            while (cursor.moveToNext()){
                byte[] imgByte = cursor.getBlob(4);
                Bitmap cover = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                courseList.add(new Course(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cover));
            }
            cursor.close();
            databaseAccess.close();

        }else if(text.equals("Business")){
            courseList = new ArrayList<>();
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
            databaseAccess.open();
            Cursor cursor = databaseAccess.getSpecificCourses(idQ,3);
            while (cursor.moveToNext()){
                byte[] imgByte = cursor.getBlob(4);
                Bitmap cover = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                courseList.add(new Course(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cover));
            }
            cursor.close();
            databaseAccess.close();
        }
        rvCourses.setLayoutManager(new GridLayoutManager(this, 2));
        CourseAdapter crsadapter = new CourseAdapter(this, courseList);
        rvCourses.setAdapter(crsadapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.recreate();
        navigation.setSelectedItemId(R.id.navigation_all_courses);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
