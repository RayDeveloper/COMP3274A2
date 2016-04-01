package edu.uwi.sta.comp3275a2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    TextView title, tv, tv1, tv2;
    RelativeLayout layout;
//anna test comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
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


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //get the accelerometer sensor
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //get layout
        layout = (RelativeLayout) findViewById(R.id.relative);
        //get textviews
        //title = (TextView) findViewById(R.id.name);
        tv = (TextView) findViewById(R.id.xval);
        tv1 = (TextView) findViewById(R.id.yval);
        tv2 = (TextView) findViewById(R.id.zval);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        //display values using TextView
        //title.setText(R.string.app_name);
        tv.setText("X axis" + "\t\t" + x);
        tv1.setText("Y axis" + "\t\t" + y);
        tv2.setText("Z axis" +"\t\t" +z);

    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mSensorManager.unregisterListener(this);
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
