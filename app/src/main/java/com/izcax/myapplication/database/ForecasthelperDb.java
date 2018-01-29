package com.izcax.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Izcax on 1/27/18.
 */

public class ForecasthelperDb extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ForecastManager";
    private static final String TABLE_NAME = "forecast";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_TEMP_MAX = "temp_max";
    private static final String KEY_TEMP_MIN = "temp_min";
    private static final String KEY_DESC = "desc";
    private static final String KEY_IMG_URL = "img_url";

    public ForecasthelperDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FORECAST_TABLE = "CREATE TABLE "+TABLE_NAME+"("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DATE + " TEXT,"
                + KEY_TEMP_MAX + " TEXT,"
                + KEY_TEMP_MIN + " TEXT,"
                + KEY_DESC + " TEXT,"
                + KEY_IMG_URL + " TEXT" + ")";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
