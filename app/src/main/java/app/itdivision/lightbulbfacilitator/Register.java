package app.itdivision.lightbulbfacilitator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import app.itdivision.lightbulbfacilitator.Database.DatabaseAccess;
import app.itdivision.lightbulbfacilitator.Instance.ActiveIdPassing;
import app.itdivision.lightbulbfacilitator.Model.Course;

public class Register extends AppCompatActivity {

    EditText fa_first_name;
    EditText fa_rekeningNumb;
    EditText fa_last_name;
    EditText fa_email;
    EditText fa_password;
    EditText fa_confirmpw;
    Button fa_btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fa_first_name = (EditText) findViewById(R.id.fa_first_name);
        fa_last_name = (EditText) findViewById(R.id.fa_last_name);
        fa_email = (EditText) findViewById(R.id.fa_emailReg);
        fa_password = (EditText) findViewById(R.id.fa_passwordReg);
        fa_confirmpw = (EditText) findViewById(R.id.fa_confpasswordReg);
        fa_btnRegister = (Button)findViewById(R.id.fa_btnRegister);
        fa_rekeningNumb = (EditText) findViewById(R.id.fa_rekeningNumb);

        fa_btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fa_first = fa_first_name.getText().toString();
                String fa_last = fa_last_name.getText().toString();
                String name = fa_first + " " + fa_last;
                String fa_em = fa_email.getText().toString();
                String fa_pw = fa_password.getText().toString();
                String fa_confpw = fa_confirmpw.getText().toString();
                String fa_rek = fa_rekeningNumb.getText().toString();

                Date x = Calendar.getInstance().getTime();
                SimpleDateFormat postFormater = new SimpleDateFormat("dd MMM yyyy");
                String finalDate = postFormater.format(x);

                if(fa_first.equals("") || fa_em.equals("") || fa_pw.equals("") ||fa_rek.equals("")|| fa_confpw.equals("")){
                    Toast.makeText(Register.this, "All forms must be filled!", Toast.LENGTH_SHORT).show();
                }else if(!isValidEmail(fa_em)){
                    Toast.makeText(Register.this, "Email invalid!", Toast.LENGTH_SHORT).show();
                    fa_email.setText("");
                    fa_password.setText("");
                    fa_confirmpw.setText("");
                }else if(fa_pw.length() < 8 ) {
                    Toast.makeText(Register.this, "Password must contain at least 8 characters!", Toast.LENGTH_SHORT).show();
                    fa_password.setText("");
                    fa_confirmpw.setText("");

                }else if(fa_confpw.equals(fa_pw)) {
                    //dbQuery
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(Register.this);
                    databaseAccess.open();
                    int chck = databaseAccess.checkEmail(fa_em);
                    if(chck == 0) {
                        databaseAccess.getRegistered(name, fa_em, fa_pw, fa_rek, finalDate);
                        int id = databaseAccess.getLogin(fa_em, fa_pw);
                        if (id > 0) {
                            showToast();
                            ActiveIdPassing activeIdPassing = ActiveIdPassing.getInstance();
                            activeIdPassing.setActiveId(id);
                            databaseAccess.setHasSignedIn(id);
                            Intent intent = new Intent(Register.this, Courses.class);
                            startActivity(intent);
                            databaseAccess.close();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_LONG).show();
                            databaseAccess.close();
                        }
                    }else{
                        Toast.makeText(Register.this, "You've already registered! Try Logging in", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Toast.makeText(Register.this, "Password and confirm password do not match!", Toast.LENGTH_SHORT).show();
                    fa_password.setText("");
                    fa_confirmpw.setText("");
                }

            }
        });
    }

    public void showToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));
        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);

        toastImage.setImageResource(R.drawable.ic_check_black_24dp);
        toastText.setText("Successfully Registered!");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean shouldAllowBack(){
        return false;
    }
    public void doNothing(){
        finish();
        moveTaskToBack(true);
    }

    @Override
    public void onBackPressed() {
        if (!shouldAllowBack()) {
            doNothing();
        } else {
            super.onBackPressed();
        }
    }

}
