package edu.uwi.sta.comp3275a2;

import android.content.res.Resources;
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

public class Gyroscope extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor sensorGyroscope;
    private TextView textView, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

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

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        sensorGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensorGyroscope, SensorManager.SENSOR_DELAY_FASTEST);
    }

    protected void onDestroy(){
        sensorManager.unregisterListener(this);
        super.onDestroy();

    }

    protected void onStop(){
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){
        //do nothing
    }

    public void onSensorChanged(SensorEvent event){
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE){
            return;
        }

        //textView.setText("X : " + event.values[0] + "\n" + "Y : " + event.values[1]
        //   + "\n" + "Z : " + event.values[2] + "\n");

        double X= event.values[0];
        double Y= event.values[1];
        double Z= event.values[2];

        Resources res = getResources();

        String coordinatesX= (String.format(res.getString(R.string.x), X));
        String coordinatesY= (String.format(res.getString(R.string.y), Y));
        String coordinatesZ= (String.format(res.getString(R.string.z), Z));

        textView.setText(coordinatesX);
        textView2.setText(coordinatesY);
        textView3.setText(coordinatesZ);


    }
}

