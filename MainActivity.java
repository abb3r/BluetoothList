package com.example.bluetoothlist;

import java.util.ArrayList;
import java.util.Set;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	
	private int REQUEST_EN_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
    	
        CheckBox ch1 = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox ch2 = (CheckBox) findViewById(R.id.checkBox2);
        		
        ch1.setText("Server");
        ch2.setText("Client");
        
             
        
        BluetoothAdapter myBlue = BluetoothAdapter.getDefaultAdapter();
        Button btn = (Button) findViewById(R.id.button1);
        
       if(!myBlue.isDiscovering()) {
    	   
    	   btn.setText("Not Discoverable");
    	   
       }
       
       if(myBlue.isDiscovering())
       {
    	   
    	   btn.setText("Discoverable");
    	   
       }
 
       
       btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			Intent makeDiscoverable = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			makeDiscoverable.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			
			startActivity(makeDiscoverable);
			// TODO Auto-generated method stub
			
		}
	});
    
    
   /* if (myBlue == null){
    	
    	Toast.makeText(getApplicationContext(), "This device does not support Bluetooth", Toast.LENGTH_SHORT).show();
    	
    	       	
    } */
    
    
    if (!myBlue.isEnabled()){
    	
    	Intent enBlue = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
    	
    	startActivityForResult(enBlue,REQUEST_EN_BT);
    	
    	
    	
    }
    
    
    ArrayList<String> Devices = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,Devices);

    Set<BluetoothDevice> pairedDevices = myBlue.getBondedDevices();    
    if (pairedDevices.size() > 0) {
    	
    	Toast.makeText(getApplicationContext(), "Device Detected", Toast.LENGTH_SHORT).show();
    	
    	for (BluetoothDevice bluetoothDevice : pairedDevices) {
			

    	Devices.add(bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress());
    		
		}
  
    	setListAdapter(arrayAdapter);
    	}
    
    
    
    
    ch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean state) {
		
			if (state==true)
			{
				
				}
			
		}
	});
  
      }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	if (requestCode==REQUEST_EN_BT && requestCode == RESULT_OK)
    	{
  
    	}
    }
    
  }



