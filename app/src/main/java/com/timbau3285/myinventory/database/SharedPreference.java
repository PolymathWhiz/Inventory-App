package com.timbau3285.myinventory.database;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    private static final String PREF_NAME = "UserInfo";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_FULL_NAME = "full_name";
    private static final String USER_LOGGEDIN = "loggedin";

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;

    public SharedPreference(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setEmail(String email){
        editor.putString(USER_EMAIL, email);
        editor.apply();
    }

    public void setFullName(String fullName){
        editor.putString(USER_FULL_NAME, fullName);
        editor.apply();
    }

    public void setPassword(String password){
        editor.putString(USER_PASSWORD, password);
        editor.apply();
    }

    public void setState(boolean loggedin){
        editor.putBoolean(USER_LOGGEDIN, loggedin);
        editor.apply();
    }

    public void clearUserPreference(){
        editor.clear();
        editor.commit();
    }

    // getting data from the shared preference

    public String getUserEmail() {
        return prefs.getString(USER_EMAIL, "");
    }
    public boolean getState() {
        return prefs.getBoolean(USER_LOGGEDIN, false);
    }
    public String getUserFullName() { return prefs.getString(USER_FULL_NAME, ""); }

}
