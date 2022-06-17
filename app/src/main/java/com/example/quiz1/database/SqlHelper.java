package com.example.quiz1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqlHelper extends SQLiteOpenHelper {
    //buat konstanta
    public static final String dbName = "bookCaseDatabase";
    public static final int dbVersion = 2;
    public static final SQLiteDatabase.CursorFactory cFactory = null;

    //constructor
    public SqlHelper(Context context){
        super(context, dbName, cFactory, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //dipanggil sekali ketika db pertama dibuat
        //perlu create table" yg dibutuhkan di awal


        //table user
        Log.wtf("sqlhelper", "masuk sqlhelper oncreate - start");
        UserHelper userHelper = new UserHelper();
        userHelper.createTableUser(sqLiteDatabase);

        //table book
        BookHelper bookHelper = new BookHelper();
        bookHelper.createTableBook(sqLiteDatabase);

        Log.wtf("sqlhelper", "masuk sqlhelper oncreate - end");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

}
