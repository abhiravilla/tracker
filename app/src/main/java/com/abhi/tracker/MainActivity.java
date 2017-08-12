package com.abhi.tracker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button b1,b2;
	   SharedPreferences pref;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.trackmain);
        pref = getApplicationContext().getSharedPreferences("TrackVal", MODE_PRIVATE); 
        Editor ed=pref.edit();
     
        b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new OnClickListener() {
        	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			stop();
		}
        });
        b1 = (Button) findViewById(R.id.button1); 
        b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
            map();	
			}

			
		});
       }
	    void map(){
	    	Intent 	id = new Intent(this,map.class);
	    	id.putExtra("phone","null");
	       	startActivity(id);
	   	    }
	    void stop(){
	    	Editor ed;
	    	String val=pref.getString("Value",null);
	    	if(val != null){
	        SmsManager smsManager = SmsManager.getDefault();
            String sm="S:"+ val;
	        smsManager.sendTextMessage(pref.getString("Phone",null), null,sm, null, null);
	        ed=pref.edit();
	        ed.remove("value");
	    }
	    	else{
	    		Toast.makeText(getApplicationContext(), "You have not received any message",Toast.LENGTH_SHORT).show();
	    	}
	    }
}

