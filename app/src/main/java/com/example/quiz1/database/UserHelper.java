package com.example.quiz1.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.quiz1.data.UserData;
import com.example.quiz1.models.User;

import java.security.PrivateKey;
import java.util.Vector;

public class UserHelper {
    private static String TABLE_NAME = "MsUsers";
    private static String FIELD_ID = "Id";
    private static String FIELD_EMAIL= "Email";
    private static String FIELD_NAME= "Name";
    private static String FIELD_PHONE = "Phone";
    private static String FIELD_PASS = "Password";

    UserData userData;

    //create table
    public void createTableUser(SQLiteDatabase db){
        Log.wtf("userhelper", "masuk createtable");
        String qCreate = "CREATE TABLE IF NOT EXISTS '"+ TABLE_NAME + "'(\n" +
                "'" + FIELD_ID + "' INTEGER PRIMARY KEY,\n"+
                "'" + FIELD_EMAIL + "' TEXT NOT NULL, \n"+
                "'" + FIELD_NAME + "' TEXT NOT NULL,\n"+
                "'" + FIELD_PHONE + "' TEXT NOT NULL,\n"+
                "'" + FIELD_PASS + "' TEXT NOT NULL,\n" +
                "UNIQUE('" + FIELD_EMAIL + "'),\n"+
                "UNIQUE('" + FIELD_PASS + "')" +
                ");";

        //excecute query -> menjalankan kuerinya
        db.execSQL(qCreate);

        Log.wtf("userhelper", "end createtable");
    }

    public Vector<User> getAllUsers(Context context) {
        SqlHelper sqlHelper = new SqlHelper(context);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        Vector<User> usersData = new Vector<User>();
        Log.wtf("userhelper", "get all user start");

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = null;
        try{
            cursor = db.rawQuery(query, null);
            Log.wtf("userhelper", "test");

            int id;
            String email, name, phone, pass;

            while(cursor.moveToNext()){
                id = cursor.getInt(0);
                email = cursor.getString(1);
                name = cursor.getString(2);
                phone = cursor.getString(3);
                pass = cursor.getString(4);
                User userAdded = new User(id,email,name,phone,pass);
                userData.getVectUser().add(userAdded);
                usersData.add(userAdded);
                Log.wtf("userhelper", "loop");
            };
            while(cursor.moveToNext());

            Log.wtf("userhelper", "masuk getalluser - ada data");

        }catch (SQLException e){
            createTableUser(db);
            Log.wtf("userhelper", "masuk getalluser - kena catch");

        }

        cursor.close();
        Log.wtf("userhelper", "getalluser done");
        return usersData;
    }

    //insert to database
    public void insertUser(Context context, User user){
        SqlHelper sqlHelper = new SqlHelper(context);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        String query =
                "INSERT OR IGNORE INTO "+ TABLE_NAME +" ("+
                        FIELD_ID + ", "+ FIELD_EMAIL+ ", "+ FIELD_NAME + ", "+
                        FIELD_PHONE+ ", "+ FIELD_PASS+")\n" +
                        "VALUES ( " + user.getId() +", " +
                        "'" + user.getEmailAddress() +"', " +
                        "'" + user.getUsername() +"', " +
                        "'" + user.getPhoneNum() +"', " +
                        "'" + user.getPassword() +"')\n";
        try {
            db.execSQL(query);
            db.close();
            userData.getVectUser().add(user);
        }catch (SQLException e){
        }
    }

    //update to database
    public void updateUsername(Context context, User user){
        SqlHelper sqlHelper = new SqlHelper(context);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        String query =
                "UPDATE " + TABLE_NAME +"\n" +
                        "SET " + FIELD_NAME + " = '" + user.getUsername()+"'\n" +
                        "WHERE " + FIELD_ID + " = " + user.getId();
        db.execSQL(query);
        db.close();
    }

    //delete
    public void deleteData(Context context, User user){
        SqlHelper sqlHelper = new SqlHelper(context);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        String query =
                "DELETE FROM "+ TABLE_NAME +"\n"+
                        "WHERE " + FIELD_ID + " = " + user.getId();

        db.execSQL(query);
        db.close();
    }

}
