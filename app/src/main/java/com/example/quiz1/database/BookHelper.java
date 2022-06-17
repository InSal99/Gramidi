package com.example.quiz1.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quiz1.data.BookData;
import com.example.quiz1.models.Book;

import java.util.Vector;

public class BookHelper {
    private static String TABLE_NAME = "MsBook";
    private static String FIELD_ID = "Id";
    private static String FIELD_NAME= "Title";

    private static String FIELD_CATEGORY= "Category";
    private static String FIELD_WRITER= "Writer";
    private static String FIELD_PUBLISHER = "Publisher";
    private static String FIELD_PUBLISHDATE = "PublishDate";

    private static String FIELD_ISBN= "ISBN";
    private static String FIELD_HAL= "Pages";
    private static String FIELD_IMG = "ImageSource";
    private static String FIELD_DESC = "Description";



    BookData bookData;

    //create table
    public void createTableBook(SQLiteDatabase db){
        Log.wtf(" .bookhelper", "masuk createtable");
        String qCreate = "CREATE TABLE IF NOT EXISTS '"+ TABLE_NAME + "'(\n" +
                "'" + FIELD_ID + "' INTEGER NOT NULL,\n"+
                "'" + FIELD_NAME + "' TEXT PRIMARY KEY, \n"+
                "'" + FIELD_CATEGORY + "' TEXT NOT NULL,\n"+
                "'" + FIELD_WRITER + "' TEXT NOT NULL,\n"+
                "'" + FIELD_PUBLISHER + "' TEXT NOT NULL,\n"+
                "'" + FIELD_PUBLISHDATE + "' TEXT NOT NULL, \n" +
                "'" + FIELD_ISBN + "' TEXT NOT NULL,\n"+
                "'" + FIELD_HAL + "' TEXT NOT NULL,\n"+
                "'" + FIELD_IMG + "' TEXT NOT NULL,\n"+
                "'" + FIELD_DESC + "' TEXT NOT NULL, \n" +
                "UNIQUE('" + FIELD_ID + "'),\n" +
                "UNIQUE('" + FIELD_ISBN + "')" +
                ");";

        //excecute query -> jalanin kuerinya
        db.execSQL(qCreate);
        Log.wtf(" .bookhelper", "end createtable");
    }

    //insert to database
    public void insertBookList(Context context, Vector<Book> books){
        SqlHelper sqlHelper = new SqlHelper(context);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        Log.wtf(" .bookhelper", "masuk insert, arr size = "+ books.size());

        try {
            String query;
            try{
                query = "SELECT * FROM "+ TABLE_NAME;
                db.execSQL(query);
            }catch (SQLException e){
                createTableBook(db);
            }

            for (Book book : books) {
                Log.wtf(" .bookhelper", "inloop");
                query =
                        "INSERT OR IGNORE INTO " + TABLE_NAME + " (" +
                                FIELD_ID + ", " + FIELD_NAME + ", " + FIELD_CATEGORY + ", " +
                                FIELD_WRITER + ", " + FIELD_PUBLISHER + ", " + FIELD_PUBLISHDATE + ", " +
                                FIELD_ISBN + ", " + FIELD_HAL + ", " + FIELD_IMG + ", " + FIELD_DESC +")\n" +
                                "VALUES ( " + book.getId() + ", " +
                                "'" + book.getName() + "', " +
                                "'" + book.getCategory() + "', " +
                                "'" + book.getWritter() + "', " +
                                "'" + book.getPublisher() + "', " +
                                "'" + book.getPublishDate() + "', " +
                                "'" + book.getIsbn() + "', " +
                                "'" + book.getHal() + "', " +
                                "'" + book.getImg() + "', " +
                                "'" + book.getDesc() + "')\n";

                db.execSQL(query);
                Log.wtf(" .bookhelper", "loop");
            }
            db.close();
            Log.wtf(" .bookhelper", "end createtable");

        }catch (SQLException e){
            Log.wtf(" .bookhelper", "kena catch");

        }
    }

    public int isBookDataExist(Context context){
        SqlHelper sqlHelper = new SqlHelper(context);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
//        Log.wtf(" .bookhelper", "masuk insert, arr size = "+ bookhelper.size());

        String query = "SELECT COUNT (*) FROM "+ TABLE_NAME;
        Cursor cursor = null;
        int count = -1;
        try{
            cursor = db.rawQuery(query, null);
            if(cursor.moveToNext()){
                count = cursor.getInt(0);
            }
        }catch (SQLException e){
            createTableBook(db);
        }
        cursor.close();
        return count;
    }

    //delete books
    public void deleteAllData(Context context){
    SqlHelper sqlHelper = new SqlHelper(context);
    SQLiteDatabase db = sqlHelper.getWritableDatabase();

    String query =
            "DELETE FROM "+ TABLE_NAME ;
    try {
        db.execSQL(query);
    }catch (SQLException e){
    }

    db.close();
}



}
