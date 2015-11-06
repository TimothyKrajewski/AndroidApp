package com.timkrajewski.textcapsule;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class home extends AppCompatActivity {

    private static List<event> eventList = new ArrayList<>();
    ListView eventLister;
    ArrayAdapter<event> arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        eventLister = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventList);
        eventLister.setAdapter(arrayAdapter);

        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.
        FacebookSdk.sdkInitialize(getApplicationContext());
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
        startActivity(intent);
    }

    public void sendSMS(View v)
    {

        if(eventList.size() >  0 )
        {
            event event = eventList.remove(0);
            String message = event.getMessage();
            String phoneNumber = event.getphoneNum();

            try
            {

                SmsManager.getDefault().sendTextMessage(phoneNumber, null, message , null, null);
                eventLister = (ListView) findViewById(R.id.listView);
                arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventList);
                eventLister.setAdapter(arrayAdapter);

                // confirmation message
                Toast.makeText(getBaseContext(), "SMS sent it says " + message + " and was sent to " +
                        phoneNumber, Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                AlertDialog.Builder alertDialogBuilder = new
                        AlertDialog.Builder(this);
                AlertDialog dialog = alertDialogBuilder.create();


                dialog.setMessage(e.getMessage());


                dialog.show();
            }

        }
        else
        {
            Toast.makeText(getBaseContext(),  "No SMS to send ", Toast.LENGTH_LONG).show();
        }


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


    //methods to preform on the eventList list object

    protected static void add(event e)
    {
        eventList.add(e);
    }

    protected static int size()
    {
        return eventList.size();
    }

    protected static double getDate(int i)
    {
        return eventList.get(i).sortDate();

    }

    protected static void addAt(int i, event e)
    {
        eventList.add(i, e);
    }


}
