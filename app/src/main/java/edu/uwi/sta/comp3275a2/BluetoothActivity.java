package edu.uwi.sta.comp3275a2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class BluetoothActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;

    ListView listDevicesFound;
    Button btnScanDevice;

    TextView stateBluetooth;
    BluetoothAdapter bluetoothAdapter;

    ArrayAdapter<String> btArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        btnScanDevice = (Button)findViewById(R.id.scandevice);

        stateBluetooth = (TextView)findViewById(R.id.bluetoothstate);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        listDevicesFound = (ListView)findViewById(R.id.devicesfound);
        btArrayAdapter = new ArrayAdapter<String>(BluetoothActivity.this, android.R.layout.simple_list_item_1);
        listDevicesFound.setAdapter(btArrayAdapter);

        CheckBlueToothState();

        btnScanDevice.setOnClickListener(btnScanDeviceOnClickListener);

        registerReceiver(ActionFoundReceiver,
                new IntentFilter(BluetoothDevice.ACTION_FOUND));

    }// end OnCreate


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(ActionFoundReceiver);
    }

    public void CheckBlueToothState(){
        if (bluetoothAdapter == null){
            String str= "Bluetooth NOT support";
            stateBluetooth.setText(str);
        }else{
            if (bluetoothAdapter.isEnabled()){
                if(bluetoothAdapter.isDiscovering()){
                    String str1= "Bluetooth is currently in device discovery process.";

                    stateBluetooth.setText(str1);
                }else{
                    String str2= "Bluetooth is Enabled.";

                    stateBluetooth.setText(str2);
                    btnScanDevice.setEnabled(true);
                }
            }else{
                String str3= "Bluetooth is NOT Enabled!";

                stateBluetooth.setText(str3);
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    private Button.OnClickListener btnScanDeviceOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            btArrayAdapter.clear();
            bluetoothAdapter.startDiscovery();
        }};

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if(requestCode == REQUEST_ENABLE_BT){
            CheckBlueToothState();
        }
    }

    private final BroadcastReceiver ActionFoundReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                btArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                btArrayAdapter.notifyDataSetChanged();
            }
        }};
}