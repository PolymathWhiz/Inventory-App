package com.timbau3285.myinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "user";
    private static final String COL1 = "ID";
    private static final String COL2 = "email";
    private static final String COL3 = "full_name";
    private static final String COL4 = "password";

    public DBHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" + COL1
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL2
                + " TEXT NOT NULL," + COL3
                + " TEXT," + COL4
                + " TEXT NOT NULL)";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addSignupData(String fname, String password, String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String query = "select  * from " + TABLE_NAME;
        String checkEmail = "select email from " + TABLE_NAME + " WHERE email = '" + email + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Cursor c =  sqLiteDatabase.rawQuery(checkEmail, null);
        int count = cursor.getCount() + 1;

        if (!(c.getCount() > 0)){
            contentValues.put(COL1, count);
            contentValues.put(COL2, email);
            contentValues.put(COL3, fname);
            contentValues.put(COL4, password);
            long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean checkLogin(String email, String password) {
        boolean isPresent = false;

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL2 + ", " + COL4 + " FROM " + TABLE_NAME + " WHERE "+ COL2 + " = '" + email + "' AND " + COL4 + " = '" + password + "'";

        Cursor c = db.rawQuery(query, null);

        if (c.getCount() >= 1) {
            isPresent = true;
            Log.d("login", "authenticated");
        } else {
            isPresent = false;
            Log.d("login", "didn't authenticate");
        }
        return isPresent;
    }

}
