package com.timkrajewski.textcapsule;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;

public class sendMessage extends Activity {
    Button fireAway;
    String message;



    public void setMessage(String message)
    {
        this.message = message;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_home);
//        //fireAway = (Button) findViewById(R.id.fireAway);
//        fireAway.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                sendSMS("5554", "message");
//            }
//        });
//    }

    private void sendSMS(String phoneNumber, String message)
    {
        android.telephony.SmsManager fireAway = android.telephony.SmsManager.getDefault();
        fireAway.sendTextMessage(phoneNumber, null, message, null, null);
    }

}
