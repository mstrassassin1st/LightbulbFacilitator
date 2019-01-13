package app.itdivision.lightbulbfacilitator.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    private Context sharedContext = null;

    private DatabaseAccess(Context context){
        try{
            sharedContext = context.createPackageContext("app.itdivision.lightbulb", Context.CONTEXT_INCLUDE_CODE);
            if (sharedContext == null) {
                this.openHelper = new DatabaseHelper(context);
            }else{
                this.openHelper = new DatabaseHelper(sharedContext);
            }
        }catch (Exception e){
            Toast.makeText(context, "Failed getting shared Database", Toast.LENGTH_LONG).show();
            return;
        }

    }

    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){ this.db = openHelper.getWritableDatabase(); }
    public void close(){
        if(db != null){
            this.db.close();
        }
    }

    //Query hasSignedIn
    public int getHasSignedIn(){
        Cursor cursor = db.rawQuery("SELECT FacilitatorID from UserSignedIn", null);
        int id = 0;
        if(cursor.moveToFirst()){
            id = cursor.getInt(cursor.getColumnIndex("FacilitatorID"));
        }
        return id;
    }

    //Query to fill hasSignedIn
    public void setHasSignedIn(int id){
        db.execSQL("update UserSignedIn set FacilitatorID = '"+ id +"'");
    }
    public void doLogout(){
        db.execSQL("UPDATE UserSignedIn SET FacilitatorID = 0");
    }

    //Query for Login
    public int getLogin(String email, String password){
        Cursor cursor = db.rawQuery("select FacilitatorID from MsFacilitator where FacilitatorEmail = '"+ email +"' AND FacilitatorPassword = '"+ password +"'", null);
        int id = 0;
        if(cursor.moveToFirst()){
            id = cursor.getInt(cursor.getColumnIndex("FacilitatorID"));
        }
        return id;
    }

    //Query for Register
    public void getRegistered (String username, String email, String password, String BankAcc ,String dateJoined){
        db.execSQL("insert into MsFacilitator(FacilitatorName, FacilitatorEmail, FacilitatorPassword, FacilitatorHint, FacilitatorBalance, FacilitatorBankAccount, FacilitatorDateJoined)\n" +
                "values ('"+ username +"','"+ email + "','" + password + "','"+ password +"','0', '"+ BankAcc +"','"+ dateJoined +"')");
    }

    //Query for Courses
    public Cursor getCourses(int id){
        Cursor courses = db.rawQuery("select CourseName, CourseTypeName, CourseDescription, CoursePrice, CourseImage from MsCourse A JOIN MsCourseType B ON A.CourseTypeID = B.CourseTypeID where FacilitatorID = '"+ id +"'", null);
        return courses;
    }
    public Cursor getSpecificCourses(int id, int category){
        Cursor courses = db.rawQuery("select CourseName, CourseTypeName, CourseDescription, CoursePrice, CourseImage from MsCourse A JOIN MsCourseType B ON A.CourseTypeID = B.CourseTypeID where FacilitatorID = '"+ id +"' AND A.CourseTypeID = '"+ category +"'", null);
        return courses;
    }

    //Query for balance
    public int getBalance(int id){
        Cursor cursor = db.rawQuery("select FacilitatorBalance from MsFacilitator where FacilitatorID = '"+ id +"'", null);
        int balance = 0;
        if(cursor.moveToFirst()){
            balance = cursor.getInt(cursor.getColumnIndex("FacilitatorBalance"));
        }
        return balance;
    }

    public String getAccNum(int id){
        Cursor cursor = db.rawQuery("select FacilitatorBankAccount from MsFacilitator where FacilitatorID = '"+ id +"'", null);
        String accNum = "";
        if(cursor.moveToFirst()){
            accNum = cursor.getString(cursor.getColumnIndex("FacilitatorBankAccount"));
        }
        return accNum;
    }

    public void resetBalance(int id){
        db.execSQL("update MsFacilitator set FacilitatorBalance = 0 where FacilitatorID = '"+ id +"'");
    }

    //Query for Account Setting
    public String getDateJoined(int FacilitatorID){
        Cursor cursor = db.rawQuery("select FacilitatorDateJoined from MsFacilitator where FacilitatorID = '" + FacilitatorID +"'", null);
        String dateJoined = " ";
        if(cursor.moveToFirst()){
            dateJoined = cursor.getString(0);
        }
        return dateJoined;
    }

    public String getOldPassword(int FacilitatorID){
        Cursor cursor = db.rawQuery("select FacilitatorPassword from MsFacilitator where FacilitatorID = '" + FacilitatorID +"'", null);
        String passw = " ";
        if(cursor.moveToFirst()){
            passw = cursor.getString(0);
        }
        return passw;
    }

    public void changeEmail(String email, int id){
        db.execSQL("update MsFacilitator set FacilitatorEmail = '" + email +"' where FacilitatorID = '"+ id +"'");
    }

    public void changeUsername(String username, int id){
        db.execSQL("update MsFacilitator set FacilitatorName = '" + username +"' where FacilitatorID = '"+ id +"'");
    }

    public void changePassword(String password, int id){
        db.execSQL("update MsFacilitator set FacilitatorPassword = '" + password +"' where FacilitatorID = '"+ id +"'");
    }
    public void changeBankAccountNumber(String bankacc, int id){
        db.execSQL("update MsFacilitator set FacilitatorBankAccount = '" + bankacc +"' where FacilitatorID = '"+ id +"'");
    }

    public Cursor getFacilitatorData(int id){
        Cursor cursor = db.rawQuery("select FacilitatorName, FacilitatorEmail from MsFacilitator where FacilitatorID LIKE '" + id + "'", null);
        return cursor;
    }
}
