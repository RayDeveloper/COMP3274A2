package edu.uwi.sta.comp3275a2.Models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raydon on 4/2/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    Context context;
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "locations.db";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocationContract.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(LocationContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }



    public List<locations> getLocations(){
        final SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT long,lat FROM location ; " ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<locations> checkList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                locations loc= new locations();
                loc.setLong(cursor.getString(0));
                loc.setLat(cursor.getString(1));
                checkList.add(loc);

            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return checkList;

    }
}