package edu.uwi.sta.comp3275a2;

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
import android.widget.TextView;
import android.widget.Toast;

public class ProximitySensor extends AppCompatActivity implements SensorEventListener {
    TextView proxText;
    SensorManager sm;
    Sensor proxSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);
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

        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        proxSensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        proxText=(TextView)findViewById(R.id.proximityTextView);

        sm.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] == 0) {
                //near
               proxText.setText(String.valueOf(event.values[0]));

                Toast.makeText(getApplicationContext(), "near", Toast.LENGTH_SHORT).show();
            } else {
                //far
                proxText.setText(String.valueOf(event.values[0]));

                Toast.makeText(getApplicationContext(), "far", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (proxSensor != null) {
            sm.unregisterListener(this);
        }

    }



    @Override
    protected void onPause(){

        sm.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sm.unregisterListener(this);
    }

//    @Override
//    public void onSensorChanged(SensorEvent event){
//        proxText.setText(String.valueOf(event.values[0]));
//    }

}
