package com.example.quiz1.data;

import android.content.Context;
import android.util.Log;

import com.example.quiz1.database.SqlHelper;
import com.example.quiz1.database.UserHelper;
import com.example.quiz1.models.User;

import java.util.Vector;

public class UserData {
    private static Vector<User> vectUser = new Vector<>();
    private static User loggedIn = null;

    private static UserHelper userHelper = new UserHelper();
    private static int loggedInPosition = -1; //position in recent array


    public static void loadDataFromDatabase(Context context){
        Log.wtf("userdata", "load data - masok");
        vectUser = userHelper.getAllUsers(context);
        Log.wtf("userdata", "load data - selesai");
    }

    public static void insertNewUser(Context context, User user){
        userHelper.insertUser(context, user);
    }

    public static void changeUsername(Context context, String newUsername){
        vectUser.get(loggedInPosition).setUsername(newUsername);
        loggedIn.setUsername(newUsername);
        userHelper.updateUsername(context, loggedIn);
    }
    public static void deleteAccount(Context context){
        userHelper.deleteData(context, loggedIn);
        vectUser.remove(loggedInPosition);
        loggedIn = null;
    }


    public static Vector<User> getVectUser() {
        return vectUser;
    }

    public static void setVectUser(Vector<User> vectUser) {
        UserData.vectUser = vectUser;
    }

    public static User getLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(User loggedIn) {
        UserData.loggedIn = loggedIn;
    }

    public static int getLoggedInPosition() {
        return loggedInPosition;
    }

    public static void setLoggedInPosition(int pos) {
        loggedInPosition = pos;
    }
}
