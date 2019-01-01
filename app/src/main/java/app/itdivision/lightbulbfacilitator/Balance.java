package app.itdivision.lightbulbfacilitator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import app.itdivision.lightbulbfacilitator.Adapter.BalanceAdapter;
import app.itdivision.lightbulbfacilitator.Model.Course;

public class Balance extends AppCompatActivity {

    BottomNavigationView navigation;
    android.support.v7.widget.Toolbar toolbar;
    RecyclerView recycler_balance;
    List<Course> courseList;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_all_courses:
                    Intent allCoursesIntent = new Intent(Balance.this, Courses.class);
                    startActivity(allCoursesIntent);
                    finish();
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
        setContentView(R.layout.activity_balance);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_balance);

        courseList = new ArrayList<>();
        courseList.add(new Course("Kotlin On Android", "Information Technology", "Description", (float) 200));
        courseList.add(new Course("Teknik Menggambar Batik", "Art and Design", "Description", (float) 200));
        courseList.add(new Course("Marketing Strategy", "Business", "Description", (float) 200));
        courseList.add(new Course("E-Business", "Business", "Description", (float) 200));
        courseList.add(new Course("Data Structure in C", "Information Technology", "Description", (float) 200));


        recycler_balance = (RecyclerView)findViewById(R.id.recycler_balance);
        recycler_balance.setLayoutManager(new LinearLayoutManager(this));
        BalanceAdapter adapter = new BalanceAdapter(this, courseList);
        recycler_balance.setAdapter(adapter);
    }

}
