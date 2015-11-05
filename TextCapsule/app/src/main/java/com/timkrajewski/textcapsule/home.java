package com.timkrajewski.textcapsule;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Scanner;

import com.facebook.FacebookSdk;



public class home extends AppCompatActivity {

    private static ArrayList<event> eventList = new ArrayList<event>(10);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView eventLister = (ListView)findViewById(R.id.listView);

        super.onCreate(savedInstanceState);
        //Scanner saved = new Scanner("eventList.txt");


        FacebookSdk.sdkInitialize(getApplicationContext());
        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.
        setContentView(R.layout.activity_home);
//        for(int i = 0; i < eventList.size() ; i++)
//        {
//            eventListString.add(eventList.get(i).convertString());
//            if(eventListString.get(i)!= null)
//            {
//                ArrayAdapter<String> arrayAdapter =
//                        new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventListString);
//                eventLister.setAdapter(arrayAdapter);
//            }
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void goToNewEvent(View v)
    {
        Intent intent = new Intent(this, newEvent.class);
        //intent.putExtra(String, Data)
        startActivity(intent);
    }

    public void sendSMS(View v)
    {
        if(eventList.size() > 0 ) {
            String message = "happy";
            String phoneNumber = "to be here";
            event temp = eventList.get(0);
            String[] str = temp.convertString().split(" ");
            str[1] = message;
            str[2] = phoneNumber;
            Toast.makeText(getBaseContext(), temp.convertString(), Toast.LENGTH_LONG).show();
            Toast.makeText(getBaseContext(), phoneNumber, Toast.LENGTH_LONG).show();


//        android.telephony.SmsManager fireAway = android.telephony.SmsManager.getDefault();
//        fireAway.sendTextMessage(phoneNumber, null, message, null, null);
        }
        else
        {
            Toast.makeText(getBaseContext(),  " No SMS to send ", Toast.LENGTH_LONG).show();
        }

    }

    public static String eventListCount()
    {
        int size = eventList.size();
        String strSize = String.valueOf(size);
        return strSize;
    }

    public static void addEvent(event E)
    {
        eventList.add(E);
    }


    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput("eventList.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString).append("\n");
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }



}
