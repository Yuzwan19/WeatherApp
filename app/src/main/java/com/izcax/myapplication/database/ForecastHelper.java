package com.izcax.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.izcax.myapplication.model.db.ForecastDb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Izcax on 1/9/18.
 */

public class ForecastHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "forecastManager";

    // Contacts table name
    private static final String TABLE_FORECAST = "forecast";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_TEMP_MAX = "temp_max";
    private static final String KEY_TEMP_MIN = "temp_min";
    private static final String KEY_DESC = "desc";
    private static final String KEY_IMG_URL = "img_url";

    public ForecastHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_FORECAST + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DATE + " TEXT,"
                + KEY_TEMP_MAX + " TEXT,"
                + KEY_TEMP_MIN + " TEXT,"
                + KEY_DESC + " TEXT,"
                + KEY_IMG_URL + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORECAST);

        // Create tables again
        onCreate(db);

    }

   public void addForecast(ForecastDb forecastDb) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, forecastDb.getDate());
        values.put(KEY_TEMP_MAX, forecastDb.getTempMax());
        values.put(KEY_TEMP_MIN, forecastDb.getTempMin());
        values.put(KEY_DESC, forecastDb.getDesc());
        values.put(KEY_IMG_URL, forecastDb.getImgUrl());

        // Inserting Row
        db.insert(TABLE_FORECAST, null, values);
        db.close(); // Closing database connection
    }

    public List<ForecastDb> getAllForecast() {
        List<ForecastDb> contactList = new ArrayList<ForecastDb>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FORECAST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ForecastDb contact = new ForecastDb();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setDate(cursor.getString(1));
                contact.setTempMax(cursor.getString(2));
                contact.setTempMin(cursor.getString(3));
                contact.setDesc(cursor.getString(4));
                contact.setImgUrl(cursor.getString(5));

                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public void removeAll(String tableName) {
        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
        SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete(tableName, null, null);
    }
}
