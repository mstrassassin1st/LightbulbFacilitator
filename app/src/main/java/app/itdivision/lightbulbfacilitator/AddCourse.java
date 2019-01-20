package app.itdivision.lightbulbfacilitator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import app.itdivision.lightbulbfacilitator.Database.DatabaseAccess;
import app.itdivision.lightbulbfacilitator.Instance.ActiveIdPassing;
import app.itdivision.lightbulbfacilitator.Serial.SerialBlob;

public class AddCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText courseName;
    Spinner category;
    EditText courseDesc;
    EditText coursePrice;
    ImageView imagefrGallery;
    Button getImage;
    Button btnAddM;
    Button btnLaunch;
    Bitmap Image = null;
    int coursecat = 0;
    String cat = "";
    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
    ActiveIdPassing activeIdPassing = ActiveIdPassing.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        courseName = (EditText) findViewById(R.id.etCourseName);
        category = (Spinner) findViewById(R.id.category_spinner);
        courseDesc = (EditText) findViewById(R.id.etCourseDesc);
        coursePrice = (EditText) findViewById(R.id.etCoursePrice);
        imagefrGallery = (ImageView) findViewById(R.id.img_fromGallery);
        getImage = (Button) findViewById(R.id.btn_getImage);
        btnAddM = (Button) findViewById(R.id.btn_add_module);
        btnLaunch = (Button) findViewById(R.id.btn_launch);

        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.selection_courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        category.setOnItemSelectedListener(this);

        getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromAlbum();
            }
        });

        btnAddM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm = courseName.getText().toString();
                String desc = courseDesc.getText().toString();
                int price = Integer.parseInt(coursePrice.getText().toString());
                int currFID = activeIdPassing.getActiveId();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                Image.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                byte[] bArray = bos.toByteArray();
                Blob image = null;
                try {
                    image = new SerialBlob(bArray);
                } catch (SQLException e) {
                    Toast.makeText(AddCourse.this, "Error! " + e.toString(), Toast.LENGTH_LONG).show();
                }
                Date x = Calendar.getInstance().getTime();
                SimpleDateFormat postFormater = new SimpleDateFormat("dd MMM yyyy");
                String finalDate = postFormater.format(x);

                if(nm.equals("") || desc.equals("")|| coursecat == 0 || price == 0 || Image == null){
                    Toast.makeText(AddCourse.this, "Failed to add Course, recheck data", Toast.LENGTH_LONG).show();
                }else {
                    databaseAccess.open();
                    databaseAccess.addCourse(currFID, coursecat, nm, desc, price, 1, finalDate, image);
                    activeIdPassing.setActiveCourseName(nm);
                    activeIdPassing.setActiveCourseCategory(cat);
                    databaseAccess.close();
                    Intent intent = new Intent(AddCourse.this, UpdateCourse.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        btnLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm = courseName.getText().toString();
                String desc = courseDesc.getText().toString();
                int price = Integer.parseInt(coursePrice.getText().toString());
                int currFID = activeIdPassing.getActiveId();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                Image.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                byte[] bArray = bos.toByteArray();
                Blob image = null;
                try {
                    image = new SerialBlob(bArray);
                } catch (SQLException e) {
                    Toast.makeText(AddCourse.this, "Error! " + e.toString(), Toast.LENGTH_LONG).show();
                }
                Date x = Calendar.getInstance().getTime();
                SimpleDateFormat postFormater = new SimpleDateFormat("dd MMM yyyy");
                String finalDate = postFormater.format(x);

                if(nm.equals("") || desc.equals("")|| coursecat == 0 || price == 0 || Image == null){
                    Toast.makeText(AddCourse.this, "Failed to add Course, recheck data", Toast.LENGTH_LONG).show();
                }else {
                    databaseAccess.open();
                    databaseAccess.addCourse(currFID, coursecat, nm, desc, price, 1, finalDate, image);
                    activeIdPassing.setActiveCourseName(nm);
                    activeIdPassing.setActiveCourseCategory(cat);
                    databaseAccess.close();
                    finish();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if(text.equals("Select Category..")){
            coursecat = 0;
        } else if(text.equals("Information Technology")){
            coursecat = 1;
            cat = text;
        }else if(text.equals("Art and Design")){
            coursecat = 2;
            cat = text;
        }else if(text.equals("Physics")){
            coursecat = 4;
            cat = text;
        }else if(text.equals("Music")){
            coursecat = 5;
            cat = text;
        }else if(text.equals("English")){
            coursecat = 6;
            cat = text;
        }else if(text.equals("Business")){
            coursecat = 3;
            cat = text;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void getImageFromAlbum(){
        try{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 7777);
        }catch (Exception e){
            Toast.makeText(this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            try{
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                Image = selectedImage;
                imagefrGallery.setImageBitmap(selectedImage);
            }catch (FileNotFoundException e){
                e.printStackTrace();
                Toast.makeText(this, "Error occured: "+ e.toString(), Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "No image picked", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
