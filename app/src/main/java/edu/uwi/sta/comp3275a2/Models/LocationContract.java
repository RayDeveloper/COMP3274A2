package edu.uwi.sta.comp3275a2.Models;

import android.provider.BaseColumns;

/**
 * Created by Raydon on 4/2/2016.
 */
public class LocationContract {
    private static final String INT_TYPE=" INTEGER ";
    private static final String TEXT_TYPE= " TEXT ";
    private static final String COMMA_SEP= " , ";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LocationEntry.TABLE_NAME + " ( " +
                    LocationEntry._ID + INT_TYPE + " PRIMARY KEY , "+
                    LocationEntry.COLUMN_NAME_long + TEXT_TYPE + COMMA_SEP +
                    LocationEntry.COLUMN_NAME_lat + TEXT_TYPE  +
                     " );";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LocationEntry.TABLE_NAME;

    public static abstract class LocationEntry implements BaseColumns {
        public static final String TABLE_NAME = "location";

        public static final String _ID = " locationID ";
        public static final String COLUMN_NAME_long = " long ";
        public static final String COLUMN_NAME_lat = " lat ";

    }





}

