// Name: Tim Krajewski
// Course: CSC 415
// Semester: Fall 2015
// Instructor: Dr. Pulimood
// Project name: Text Capsule
// Description: My project creates and store event objects. It then takes those event objects  and
// stores them in date order(earliest date first) in a list and on the home screen in a listview object
// the user can create and store as many event objects as they want. from the home screen the user can
// chose send the message as sms-s
// Filename: newEvent.java
// Description:Contains: the creation of the newEvent activity screen, temp vairables to pass into event,
// a way to go back to the home screen
// Purpose: To create the newEvent activity, gives the user a way to input all the data needed to create
// a new event, creates new events to store in eventList, uses an algorithm to interpurt the users input and
// suggest a message based on it
// Last modified on: 11/5/15
package com.timkrajewski.textcapsule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;


/**
 * Created by TimKrajewski on 11/1/15.
 *
 * newEvent is in charge of the New Activity Screen and creating
 * newEvents to pass back to he master list
 */
public class newEvent extends Activity {

    private DatePickerDialog dateDialog;
    private TimePickerDialog timeDialog;
    private int setYear;
    private int setMonth;
    private int setDay;
    private int setHour;
    private int setMin;
    private String setTitle;
    private String setPhoneNumber;
    private String setMessage;
    private String suggestMessage = analysisAlgorithm.getMsg();
    private boolean runAnalysisHappen = false;

    //-----------------------------------------------------------------------------------------
//
//  Function: onCreate()
//
//    Parameters:
//    Bundle saved; given by default
//
//    Pre-condition: Start activity
//    Post-condition: Creates newEvent screen
//-----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);
    }

    //-----------------------------------------------------------------------------------------
//
//  Function: setDate()
//
//    Parameters:
//    input view; view allows the method to called by onClick
//
//    Pre-condition: Set Date Button is pressed
//    Post-condition: temp variables for year month day are set by user
//-----------------------------------------------------------------------------------------
    public void setDate(View v) {


        Calendar cal = Calendar.getInstance();
        dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                EditText setDate = (EditText) findViewById(R.id.editSetDate);
                setYear = year;
                setMonth = monthOfYear + 1;
                setDay = dayOfMonth;
                setDate.setText(event.eventDateToString(year, setMonth, dayOfMonth));
                setDate.setVisibility(View.VISIBLE);
                setDate.setFocusable(false);
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dateDialog.show();

    }

    //-----------------------------------------------------------------------------------------
//
//  Function: setTime()
//
//    Parameters:
//    input view; view allows the method to called by onClick
//
//    Pre-condition: Set Time Button is pressed
//    Post-condition: temp variables for hour min are set by user
//-----------------------------------------------------------------------------------------
    public void setTime(View v) {

        Calendar cal = Calendar.getInstance();
        timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                EditText setTime = (EditText) findViewById(R.id.editSetTime);
                setMin = minute;
                setHour = hour;
                setTime.setText(event.eventTimeToString(hour, minute));
                setTime.setVisibility(View.VISIBLE);
                setTime.setFocusable(false);
            }

        }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), false);
        timeDialog.show();
    }

    //-----------------------------------------------------------------------------------------
//
//  Function: runAnalysis()
//
//    Parameters:
//    input view; view allows the method to called by onClick
//
//    Pre-condition: anaylze Button is pressed, every field must have been filled in properly by the user
//    Post-condition: temp variables for title message phoneNumber are set, The users input is run
//      through the algorith and a suggested message is created
//-----------------------------------------------------------------------------------------
    public void runAnalysis(View v) {

        EditText suggestMsg = (EditText) findViewById(R.id.editSugMsg);
        EditText title = (EditText) findViewById(R.id.editEventTitle);
        EditText phone = (EditText) findViewById(R.id.editPhoneNum);
        //EditText message = (EditText) findViewById(R.id.editCustomMsg);
        EditText setTime = (EditText) findViewById(R.id.editSetTime);
        EditText setDate = (EditText) findViewById(R.id.editSetDate);
        boolean allNum;

        try {
            allNum = true;
            for(int i = 0; i < 10; i++ )
            {
                Double.parseDouble(String.valueOf(phone.getText().toString().charAt(i)));
            }

        } catch (NumberFormatException e)
        {
            allNum = false;
        }
        catch (StringIndexOutOfBoundsException e)
        {
            allNum = false;
        }

        if (!setDate.getText().toString().equals("") && !setTime.getText().toString().equals("")) {
            if (phone.getText().toString().length() == 10 && allNum)// change one back to 10
            {
                if (!title.getText().toString().equals("")) {

                        setPhoneNumber = phone.getText().toString();
                        setTitle = title.getText().toString();

                        //analyze
                        suggestMessage = analysisAlgorithm.analyze(setTitle);
                        suggestMessage = analysisAlgorithm.getMsg();
                        suggestMsg.setText(suggestMessage);
                        suggestMsg.setVisibility(View.VISIBLE);
                        suggestMsg.setFocusable(false);
                        Toast.makeText(getBaseContext(), "Analyzed...hopefully the boys in the lab found something", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getBaseContext(), "We should give this a name! (Set title) ", Toast.LENGTH_LONG).show();

            } else
                Toast.makeText(getBaseContext(), "This is a cool message! Too bad no one is gonna get it (Pick a valid 10 digit phone number)", Toast.LENGTH_LONG).show();
        } else
        Toast.makeText(getBaseContext(), "Why don't we pick a time and date first", Toast.LENGTH_LONG).show();


    runAnalysisHappen = true;
    }


    //-----------------------------------------------------------------------------------------
//
//  Function: useSugMsg()
//
//    Parameters:
//    input view; view allows the method to called by onClick
//
//    Pre-condition:  Use Suggested message button is pressed
//    Post-condition: suggested message is set to message
//-----------------------------------------------------------------------------------------
    public void useSugMsg(View v)
    {
        if(runAnalysisHappen)
        {
            if (suggestMessage != null) {
                EditText message = (EditText) findViewById(R.id.editCustomMsg);
                message.setText(suggestMessage);
                message.setVisibility(View.VISIBLE);
                suggestMessage = null;
            }
        }
        else
        {
            Toast.makeText(getBaseContext(), "Why don't we get the folks in the lab to analyze this", Toast.LENGTH_LONG)                        .show();

        }

    }

    //-----------------------------------------------------------------------------------------
//
//  Function: goToHome()
//
//    Parameters:
//    input view; view allows the method to called by onClick
//
//    Pre-condition:  All done! button is pressed all fields have been filled in correctly
//          run Anylsis has been run
//    Post-condition: Event is created, then sorted, and stored in the correct spot of eventList
//          application returns to home activity
//-----------------------------------------------------------------------------------------
    public void goToHome(View v) {
        if (runAnalysisHappen) {

            EditText title = (EditText) findViewById(R.id.editEventTitle);
            EditText phone = (EditText) findViewById(R.id.editPhoneNum);
            EditText message = (EditText) findViewById(R.id.editCustomMsg);

            if (!message.getText().toString().equals("")) {
                setTitle = title.getText().toString();
                setPhoneNumber = phone.getText().toString();
                setMessage = message.getText().toString();

                event e = new event(setTitle, setYear, setMonth, setDay, setHour, setMin, setPhoneNumber, setMessage);
                //writeToFile(event.convertString());

                //this if checks if eventList is empty
                if (home.size() != 0) {
                    //for loop does sort to place newly created events in the correct spot Earliest date come first
                    for (int i = 0; i <= home.size(); i++) {
                        if (i == home.size()) {
                            home.addAt(i, e);
                            break;
                        } else if (home.getDate(i) > e.sortDate())
                        {
                            home.addAt(i, e);
                            break;
                        }
                    }
                }
                else
                {
                    home.add(e);
                }


                Intent intent = new Intent(this, home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "Event Created", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getBaseContext(), "My dad always said if you don't have anything " +
                        "nice to say don't say anything at all... Do you not have a anything nice to say (Set message)",                                    Toast.LENGTH_LONG).show();
            }
        }
        else
        {
                Toast.makeText(getBaseContext(), "Why don't we get the folks in the lab to analyze this", Toast.LENGTH_LONG)                        .show();

        }
    }

    //*****************************************************************************************
    //          Not yet implemented as SQl data base is much better but much harder  !       //
    //*****************************************************************************************
    protected void writeToFile(String data) {


        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("save.txt", Context.MODE_PRIVATE));
            outputStreamWriter.append(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput("save.txt");

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
