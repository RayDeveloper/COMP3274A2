package edu.uwi.sta.comp3275a2;

/**
 * Created by Raydon on 4/1/2016.
 */


        import android.Manifest;
        import android.app.AlertDialog;
        import android.app.Service;
        import android.content.BroadcastReceiver;
        import android.content.ContentValues;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.database.sqlite.SQLiteDatabase;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.os.Binder;
        import android.os.Bundle;
        import android.provider.Settings;
        import android.support.v4.app.ActivityCompat;
        import android.util.Log;
        import android.widget.Button;
        import android.widget.Toast;

        import edu.uwi.sta.comp3275a2.Models.DBHelper;
        import edu.uwi.sta.comp3275a2.Models.LocationContract;

public class MyReceiver extends BroadcastReceiver  {
    Context contexts;
    Intent intents;
    double latitudes;
    double longitudes;


    public MyReceiver(){}

    public MyReceiver(Context context,Intent intent,double latitude,double longitude  ) {
        contexts = context;
        intents=intent;
        latitudes=latitude;
        longitudes=longitude;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if(latitudes==0.0 ||longitudes==0.0 ){
        //do nothing
        }else {

            Toast.makeText(context, "Intent Detected.\nLatitudes:" + latitudes + "\nLongitudes :" + longitudes, Toast.LENGTH_LONG).show();
            DBHelper mDbHelper = new DBHelper(context);
            final SQLiteDatabase db = mDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(LocationContract.LocationEntry.COLUMN_NAME_long, longitudes);
            values.put(LocationContract.LocationEntry.COLUMN_NAME_lat, latitudes);
            final long newRowId = db.insert(LocationContract.LocationEntry.TABLE_NAME, null, values);
            Toast.makeText(context, "Values inserted into database", Toast.LENGTH_LONG).show();

        }
    }




}

