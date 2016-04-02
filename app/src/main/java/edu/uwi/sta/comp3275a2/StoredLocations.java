package edu.uwi.sta.comp3275a2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import edu.uwi.sta.comp3275a2.Models.DBHelper;
import edu.uwi.sta.comp3275a2.Models.LocationAdapter;
import edu.uwi.sta.comp3275a2.Models.locations;

public class StoredLocations extends AppCompatActivity {
    List<locations> locationList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stored_locations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // TodoDatabaseHandler is a SQLiteOpenHelper class connecting to SQLite
        final ListView listView = (ListView) findViewById(R.id.location_lv);
        DBHelper help = new DBHelper(StoredLocations.this);
        locationList = help.getLocations();
        LocationAdapter adapter = new LocationAdapter(StoredLocations.this, locationList);
        listView.setAdapter(adapter);

        //looc.changeCursor(cursor);
    }

}
