package app.itdivision.lightbulbfacilitator;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.itdivision.lightbulbfacilitator.Database.DatabaseAccess;
import app.itdivision.lightbulbfacilitator.Dialogs.DialogBankAccountNumber;
import app.itdivision.lightbulbfacilitator.Dialogs.DialogEmail;
import app.itdivision.lightbulbfacilitator.Dialogs.DialogPassword;
import app.itdivision.lightbulbfacilitator.Dialogs.DialogUsername;
import app.itdivision.lightbulbfacilitator.Instance.ActiveIdPassing;
import app.itdivision.lightbulbfacilitator.Model.Course;

public class Account extends AppCompatActivity implements DialogUsername.DialogUsernameListener, DialogEmail.DialogEmailListener, DialogBankAccountNumber.DialogBankAccountNumberListener {

    BottomNavigationView navigation;
    Toolbar toolbar;
    CardView card_username;
    CardView card_email;
    CardView card_password;
    CardView card_bankAcc;
    TextView tv_change_username;
    TextView tv_change_bankAccNum;
    TextView tv_change_member_since;
    TextView tv_change_email;
    Button btnLogout;
    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(Account.this);
    ActiveIdPassing activeIdPassing = ActiveIdPassing.getInstance();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_all_courses:
                    Intent allCoursesIntent = new Intent(Account.this, Courses.class);
                    startActivity(allCoursesIntent);
                    finish();
                    return true;
                case R.id.navigation_balance:
                    Intent balanceIntent = new Intent(Account.this, Balance.class);
                    startActivity(balanceIntent);
                    finish();
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
        setContentView(R.layout.activity_account);
        int id = activeIdPassing.getActiveId();

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_account);

        card_username = (CardView) findViewById(R.id.card_username);
        card_email = (CardView) findViewById(R.id.card_email);
        card_password = (CardView) findViewById(R.id.card_password);
        card_bankAcc = (CardView)findViewById(R.id.cardBankAcc);
        tv_change_username = (TextView) findViewById(R.id.tv_change_username);
        tv_change_bankAccNum = (TextView) findViewById(R.id.tv_change_accnum);
        tv_change_member_since = (TextView) findViewById(R.id.tv_change_member_since);
        tv_change_email = (TextView) findViewById(R.id.tv_change_email);
        btnLogout = (Button) findViewById(R.id.btn_logout);

        card_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUsername();
            }
        });

        card_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogEmail();
            }
        });

        card_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogPassword();
            }
        });

        card_bankAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogBankAccNum();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Courses.home.finish();
                databaseAccess.open();
                databaseAccess.doLogout();
                databaseAccess.close();
                startActivity(new Intent(Account.this, Login.class));
                finish();
            }
        });

        //content
        databaseAccess.open();
        Cursor cursor = databaseAccess.getFacilitatorData(id);
        if(cursor.moveToFirst()){
            tv_change_username.setText(cursor.getString(0));
            tv_change_email.setText(cursor.getString(1));
        }
        cursor.close();
        String DateJoined = databaseAccess.getDateJoined(id);
        tv_change_member_since.setText(DateJoined);
        String accNum = databaseAccess.getAccNum(id);
        tv_change_bankAccNum.setText(accNum);

    }

    public void openDialogUsername(){
        DialogUsername dialogUsername = new DialogUsername();
        dialogUsername.show(getSupportFragmentManager(), "Change Username");
    }
    public void openDialogEmail(){
        DialogEmail dialogEmail = new DialogEmail();
        dialogEmail.show(getSupportFragmentManager(), "Change Email");
    }
    public void openDialogPassword(){
        DialogPassword dialogPassword = new DialogPassword();
        dialogPassword.show(getSupportFragmentManager(), "Change Password");
    }

    public void openDialogBankAccNum(){
        DialogBankAccountNumber dialogBankAccountNumber = new DialogBankAccountNumber();
        dialogBankAccountNumber.show(getSupportFragmentManager(), "Change Bank Account Number");
    }

    @Override
    public void applyTextsUsername(String username) {
        tv_change_username.setText(username);
    }

    @Override
    public void applyTextsEmail(String email) {
        tv_change_email.setText(email);
    }

    @Override
    public void applyTextsBankAccountNumber(String bankAccountNumber) {
        tv_change_bankAccNum.setText(bankAccountNumber);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
