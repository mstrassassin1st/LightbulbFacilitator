package app.itdivision.lightbulbfacilitator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText courseName;
    Spinner category;
    EditText courseDesc;
    EditText coursePrice;
    Button btnAddM;
    Button btnLaunch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        courseName = (EditText) findViewById(R.id.etCourseName);
        category = (Spinner) findViewById(R.id.category_spinner);
        courseDesc = (EditText) findViewById(R.id.etCourseDesc);
        coursePrice = (EditText) findViewById(R.id.etCoursePrice);
        btnAddM = (Button) findViewById(R.id.btn_add_module);
        btnLaunch = (Button) findViewById(R.id.btn_launch);

        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.selection_courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        category.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
