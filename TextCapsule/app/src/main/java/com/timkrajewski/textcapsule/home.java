// Name: Tim Krajewski
// Course: CSC 415
// Semester: Fall 2015
// Instructor: Dr. Pulimood
// Project name: Text Capsule
// Description: My project creates and store event objects. It then takes those event objects  and
// stores them in date order(earliest date first) in a list and on the home screen in a listview object
// the user can create and store as many event objects as they want. from the home screen the user can
// chose send the message as sms-s
// Filename: home.java
// Description:Contains: Instantiation of the EventList, Methods to manipulate the list of events, and
// Sms sender allowing the app to send SMS
// Purpose: To create the home class and provide links to all other activities, To set up master list
// events and a way to manipulate
// Last modified on: 11/5/15

package com.timkrajewski.textcapsule;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import java.util.ArrayList;
import java.util.List;


public class home extends AppCompatActivity {

    private static List<event> eventList = new ArrayList<>();
    ListView eventLister;
    ArrayAdapter<event> arrayAdapter;

    //-----------------------------------------------------------------------------------------
//
//  Function: onCreate()
//
//    Parameters:
//    Bundle saved; given by default
//
//    Pre-condition: Start app
//    Post-condition: Creates home screen and eventList sets listView to display eventList
//-----------------------------------------------------------------------------------------
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

    //-----------------------------------------------------------------------------------------
//
//  Function: onCreateOptionsMenu()
//
//    Parameters:
//    Menu menu ; given by default
//
//    Pre-condition: Start app
//    Post-condition: Creates options Menu
//-----------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    //-----------------------------------------------------------------------------------------
//
//  Function: onOptionsItemSelected()
//
//    Parameters:
//    MenuItem item; given by default
//
//    Pre-condition: Start app
//    Post-condition:   Handle action bar item clicks here. The action bar will
//    automatically handle clicks on the Home/Up button, so long
//    as you specify a parent activity in AndroidManifest.xml.
//-----------------------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//         Handle action bar item clicks here. The action bar will
//         automatically handle clicks on the Home/Up button, so long
//         as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    //-----------------------------------------------------------------------------------------
//
//  Function: goToNewEvent()
//
//    Parameters:
//    View v; is used to establish what needs to be displays
//
//    Pre-condition: Click the New Event icon
//    Post-condition:   Creates an intent that starts the code from the newEvent.class file
//-----------------------------------------------------------------------------------------
    public void goToNewEvent(View v)
    {
        Intent intent = new Intent(this, newEvent.class);
        startActivity(intent);
    }

    //-----------------------------------------------------------------------------------------
//
//  Function: sendSMS()
//
//    Parameters:
//    View v; is used to establish what needs to be displayed
//
//    Pre-condition: Click the send Icon
//    Post-condition: Removes the event at the top of the list and sends it to the user
//      updates the listView
//-----------------------------------------------------------------------------------------
    public void sendSMS(View v)
    {
        if(eventList.size() >  0 )
        {
            event event = eventList.remove(0);
            String message = event.getMessage();
            String phoneNumber = event.getphoneNum();
            try
            {
                //send sms message
                SmsManager.getDefault().sendTextMessage(phoneNumber, null, message , null, null);
                eventLister = (ListView) findViewById(R.id.listView);
                arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventList);
                eventLister.setAdapter(arrayAdapter);

                // confirmation message
                Toast.makeText(getBaseContext(), "SMS sent it says " + message + " and was sent to " +
                        phoneNumber, Toast.LENGTH_LONG).show();
            }
            catch (Exception e) // shows any error that can occur in dialog box
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

//************************************************************************************************//
//            all method after this methods to preform on the eventList list object               //
//************************************************************************************************//

    //-----------------------------------------------------------------------------------------
//
//  Function: add()
//
//    Parameters:
//    input e; event object of event type must be passed in
//
//    Pre-condition:eventList must have been created, this method also must be called
//    Post-condition: add the event object to the end of the list eventList
//-----------------------------------------------------------------------------------------
    protected static void add(event e)
    {
        eventList.add(e);
    }

    //-----------------------------------------------------------------------------------------
//
//  Function: size()
//
//    Parameters:
//
//
//    Pre-condition: eventList must have been created, this method also must be called
//    Post-condition: nothing changes but the method returns the amount of event objects
//      currently stored in eventList
//-----------------------------------------------------------------------------------------
    protected static int size()
    {
        return eventList.size();
    }

    //-----------------------------------------------------------------------------------------
//
//  Function: size()
//
//    Parameters:
//    input i; used to find the index at which the sortDate method is call
//
//    Pre-condition: eventList must have been created, this method also must be called
//    Post-condition: calls the method sortDate() in event returns the year month day hour min
//      all concatenated in a single doulble
//-----------------------------------------------------------------------------------------
    protected static double getDate(int i)
    {
        return eventList.get(i).sortDate();

    }

    //-----------------------------------------------------------------------------------------
//
//  Function: addAt()
//
//    Parameters
//      input i; used to find the index at which the sortDate method is call
//      input e; event object of event type must be passed in
//
//    Pre-condition: eventList must have been created, this method also must be called
//    Post-condition: eventList is populated with event object at the index i
//-----------------------------------------------------------------------------------------
    protected static void addAt(int i, event e)
    {
        eventList.add(i, e);
    }


}
