package com.izcax.myapplication.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Izcax on 1/9/18.
 */

public class DatabaseContract {
    public static String TABLE_WEATHER = "weather";

    public static final class WeatherColumns implements BaseColumns {

        public static String DATE = "date";
        public static String DESCRIPTION = "description";
        public static String MIN = "min";
        public static String MAX = "max";
        public static String ICON = "icon";

    }

    public static final String AUTHORITY = "com.izcax.myapplication";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_WEATHER)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
