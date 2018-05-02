package com.timbau3285.myinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
//        String createTable = "CREATE TABLE " + TABLE_NAME + "(id integer primary key," +  COL2 + " text, " + COL3 + " ,text, " + COL4 + " text)";

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
        String query = "select  * from " + TABLE_NAME ;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int count = cursor.getCount() + 1;

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
    }

//    public Cursor getData(){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        String query = "SELECT " + COL2 + " FROM " + TABLE_NAME;
//        Cursor data = sqLiteDatabase.rawQuery(query,null);
//        return data;
//    }
//
//    public long getUserCount() {
//        return getData().getCount();
//    }

//    public int getTotal(){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME;
//        Cursor data = sqLiteDatabase.rawQuery(query,null);
//        return data.getCount();
//    }

//    public List<Weights> getWeights(){
//        List<Weights> WD = new ArrayList<>();
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        String q = "SELECT weight FROM weight_history WHERE 1 ORDER BY ID ASC;";
//
//        Cursor c = db.rawQuery(q, null);
//        if(c.getCount() == 0){
//            return null;
//        }
//        Weights Wdata = null;
//        if (c.moveToFirst()) {
//            do {
//                Wdata = new Weights();
//                int weigthx = c.getInt(c.getColumnIndex("weight"));
//
//
//                Wdata.setWeight(weigthx);
//
//                WD.add(Wdata);
//
//            } while (c.moveToNext());
//        }
//        db.close();
//
//        return WD;
//    }
//
//    public class Weights {
//        public int weight;
//
//        public void setWeight(int weight){
//            this.weight = weight;
//        }
//
//        public int getWeight(){
//            return weight;
//        }
//    }

}
