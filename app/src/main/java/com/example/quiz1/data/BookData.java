package com.example.quiz1.data;

import android.content.Context;
import android.util.Log;

import com.example.quiz1.database.BookHelper;
import com.example.quiz1.models.Book;

import java.util.Vector;

public class BookData {
    private static Vector<Book> vectBook = new Vector<>();
    private static BookHelper bookHelper = new BookHelper();
    private static boolean loadStatus = false;

    public void loadDataToDatabase(Context context, Vector<Book> v){
        Log.wtf(" .bookdata", "masuk loadtodatabase");

       vectBook = v;
        bookHelper.insertBookList(context, v);

        Log.wtf(" .bookdata", "end loadtodatabase, array size = "+ vectBook.size());
    }

    public void deleteAllDataFromDatabase(Context context){
        Log.wtf(" .bookdata", "masuk deleteAllDataFromDatabase");

        bookHelper.deleteAllData(context);

        Log.wtf(" .bookdata", "end deleteAllDataFromDatabase");
    }

    public int checkDatabase(Context context){
        return bookHelper.isBookDataExist(context);
    }

    public static Vector<Book> getVectBook() {
        return vectBook;
    }

    public void setVectFurniture(Vector<Book> vect) {
        vectBook = vect;
    }

    public static boolean getLoadStatus() {
        return loadStatus;
    }
    public static void setLoadStatus(boolean status) { loadStatus = status;
    }
}
