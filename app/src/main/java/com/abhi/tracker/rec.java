package com.abhi.tracker;

import com.google.android.gms.drive.widget.DataBufferAdapter;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class rec extends BroadcastReceiver
{    
	@Override
	public void onReceive(Context context, Intent intent) 
	{        
		//---get the SMS message that was received---
		Bundle bundle = intent.getExtras();        
		SmsMessage[] msgs = null;
		String str="";   
		String Tel=""; 
		if (bundle != null)
		{
			//---retrieve the SMS message received---
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			for (int i=0; i<msgs.length; i++){
				msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
				if (i==0) {
					//---get the sender address/phone number--
					Tel = msgs[i].getOriginatingAddress();
					} 
				//---get the message body---
				str += msgs[i].getMessageBody().toString();                	
			}
/* Checks if User is tracker*/
			if (str.startsWith("T!")) {            	

                String[] parts = str.split(":");
                String ms1=parts[0];
                String ms=parts[1];
                String[] tr=ms1.split("!");
                String t=tr[1];
                parts = ms.split("@");
                String phone=parts[0];
                String locs=parts[1];
                parts = locs.split(",");
                String lat=parts[0];
                String lon=parts[1];
                SharedPreferences pref = context.getSharedPreferences("TrackVal", Context.MODE_PRIVATE); 
                Editor edit=pref.edit();
                edit.putString("Value",t);
                edit.putString("Phone",Tel);   
                edit.commit();
                Intent in=new Intent(context,map.class);	
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("phone",""+t+""+phone);
                in.putExtra("latitude",lat);
                in.putExtra("longitude",lon);
    			context.startActivity(in);
            }
            this.abortBroadcast();
			}
			
			}
		}                         
	

