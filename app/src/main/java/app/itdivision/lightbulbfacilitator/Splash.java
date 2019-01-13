package app.itdivision.lightbulbfacilitator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import app.itdivision.lightbulbfacilitator.Database.DatabaseAccess;
import app.itdivision.lightbulbfacilitator.Instance.ActiveIdPassing;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 1.5s = 1500ms
                SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                boolean firstStart = prefs.getBoolean("firstStart", true);

                if(firstStart){
                    Intent intent = new Intent(Splash.this, AppIntroActivity.class);
                    startActivity(intent);
                }
                else{
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();
                    int id = databaseAccess.getHasSignedIn();
                    if(id > 0){
                        ActiveIdPassing activeIdPassing = ActiveIdPassing.getInstance();
                        activeIdPassing.setActiveId(id);
                        Intent intent = new Intent(Splash.this,Courses.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(Splash.this, Login.class);
                        startActivity(intent);
                    }
                    databaseAccess.close();
                    finish();
                }
            }
        }, 1500);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
