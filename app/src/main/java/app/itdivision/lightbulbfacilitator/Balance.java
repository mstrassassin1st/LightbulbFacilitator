package app.itdivision.lightbulbfacilitator;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.itdivision.lightbulbfacilitator.Adapter.BalanceAdapter;
import app.itdivision.lightbulbfacilitator.Database.DatabaseAccess;
import app.itdivision.lightbulbfacilitator.Instance.ActiveIdPassing;
import app.itdivision.lightbulbfacilitator.Model.Course;

public class Balance extends AppCompatActivity {

    BottomNavigationView navigation;
    android.support.v7.widget.Toolbar toolbar;
    TextView balance;
    TextView accnum;
    Button btnReset;
    RecyclerView recycler_balance;
    List<Course> courseList;
    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(Balance.this);


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
                    Intent accountIntent = new Intent(Balance.this, Account.class);
                    startActivity(accountIntent);
                    finish();
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
        ActiveIdPassing activeIdPassing = ActiveIdPassing.getInstance();
        final int id = activeIdPassing.getActiveId();

        balance = (TextView) findViewById(R.id.currBalance);
        accnum = (TextView)findViewById(R.id.accNumber);

        databaseAccess.open();
        int fa_balance = databaseAccess.getBalance(id);
        String finalBalance = "IDR " + Integer.toString(fa_balance);
        balance.setText(finalBalance);
        String fa_accNum = "Your Bank Account Number: " + databaseAccess.getAccNum(id);
        accnum.setText(fa_accNum);
        databaseAccess.close();

        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseAccess.open();
                databaseAccess.resetBalance(id);
                databaseAccess.close();
                String reset = "IDR 0";
                balance.setText(reset);
            }
        });

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_balance);

        databaseAccess.open();
        courseList = new ArrayList<>();
        Cursor cursor = databaseAccess.getCourses(id);
        while(cursor.moveToNext()){
            byte[] imgByte = cursor.getBlob(4);
            Bitmap cover = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
            courseList.add(new Course(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cover));
        }
        cursor.close();
        databaseAccess.close();

        recycler_balance = (RecyclerView)findViewById(R.id.recycler_balance);
        recycler_balance.setLayoutManager(new LinearLayoutManager(this));
        BalanceAdapter adapter = new BalanceAdapter(this, courseList);
        recycler_balance.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.recreate();
    }
}
