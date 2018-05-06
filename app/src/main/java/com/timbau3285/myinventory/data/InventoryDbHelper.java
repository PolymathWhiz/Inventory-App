package com.timbau3285.myinventory.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Polygod
 */
public class InventoryDbHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "inventory.db";
    public final static int DB_VERSION = 1;
    public final static String LOG_TAG = InventoryDbHelper.class.getCanonicalName();

    public InventoryDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(com.timbau3285.myinventory.data.StockContract.StockEntry.CREATE_TABLE_STOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertItem(com.timbau3285.myinventory.data.StockItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_NAME, item.getProductName());
        values.put(com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_PRICE, item.getPrice());
        values.put(com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_QUANTITY, item.getQuantity());
        values.put(com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_SUPPLIER_NAME, item.getSupplierName());
        values.put(com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_SUPPLIER_PHONE, item.getSupplierPhone());
        values.put(com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_SUPPLIER_EMAIL, item.getSupplierEmail());
        values.put(com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_IMAGE, item.getImage());
        long id = db.insert(com.timbau3285.myinventory.data.StockContract.StockEntry.TABLE_NAME, null, values);
    }

    public Cursor readStock() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                com.timbau3285.myinventory.data.StockContract.StockEntry._ID,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_NAME,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_PRICE,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_QUANTITY,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_SUPPLIER_NAME,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_SUPPLIER_PHONE,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_SUPPLIER_EMAIL,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_IMAGE
        };
        Cursor cursor = db.query(
                com.timbau3285.myinventory.data.StockContract.StockEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor readItem(long itemId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                com.timbau3285.myinventory.data.StockContract.StockEntry._ID,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_NAME,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_PRICE,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_QUANTITY,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_SUPPLIER_NAME,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_SUPPLIER_PHONE,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_SUPPLIER_EMAIL,
                com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_IMAGE
        };
        String selection = com.timbau3285.myinventory.data.StockContract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };

        Cursor cursor = db.query(
                com.timbau3285.myinventory.data.StockContract.StockEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return cursor;
    }

    public void updateItem(long currentItemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_QUANTITY, quantity);
        String selection = com.timbau3285.myinventory.data.StockContract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(currentItemId) };
        db.update(com.timbau3285.myinventory.data.StockContract.StockEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }

    public void sellOneItem(long itemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        int newQuantity = 0;
        if (quantity > 0) {
            newQuantity = quantity -1;
        }
        ContentValues values = new ContentValues();
        values.put(com.timbau3285.myinventory.data.StockContract.StockEntry.COLUMN_QUANTITY, newQuantity);
        String selection = com.timbau3285.myinventory.data.StockContract.StockEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };
        db.update(com.timbau3285.myinventory.data.StockContract.StockEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }
}
