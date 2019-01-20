package app.itdivision.lightbulbfacilitator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import app.itdivision.lightbulbfacilitator.Database.DatabaseAccess;
import app.itdivision.lightbulbfacilitator.Instance.ActiveIdPassing;

public class Login extends AppCompatActivity {

    EditText fa_email;
    EditText fa_password;
    Button fa_btnLogin;
    Button fa_btn_forgot_password;
    Button fa_btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fa_email = (EditText) findViewById(R.id.fa_email);
        fa_password = (EditText) findViewById(R.id.fa_password);
        fa_btnLogin = (Button) findViewById(R.id.fa_btnLogin);
        fa_btnRegister = (Button) findViewById(R.id.fa_btnRegister);

        fa_btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = fa_email.getText().toString();
                String pw = fa_password.getText().toString();
                if(uname.equals("") || pw.equals("")){
                    Toast.makeText(Login.this,"Username or password is empty!", Toast.LENGTH_SHORT).show();
                }else{
                    showToast();
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();
                    int id = databaseAccess.getLogin(uname, pw);
                    if(id > 0){
                        ActiveIdPassing activeIdPassing = ActiveIdPassing.getInstance();
                        activeIdPassing.setActiveId(id);
                        databaseAccess.setHasSignedIn(id);
                        Intent intent = new Intent(Login.this, Courses.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Login failed, Check email and password!", Toast.LENGTH_LONG).show();
                        fa_email.setText("");
                        fa_password.setText("");
                    }
                    databaseAccess.close();
                    finish();
                }
            }
        });
        fa_btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    public void showToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));
        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);

        toastImage.setImageResource(R.drawable.ic_check_black_24dp);
        toastText.setText("Successfully Logged In!");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
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
