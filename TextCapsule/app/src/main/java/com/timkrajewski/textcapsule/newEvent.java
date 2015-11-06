package com.timkrajewski.textcapsule;

import android.app.Activity;
import android.app.AlarmManager;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);
    }

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

    public void runAnalysis(View v) {

        EditText suggestMsg = (EditText) findViewById(R.id.editSugMsg);
        EditText title = (EditText) findViewById(R.id.editEventTitle);
        EditText phone = (EditText) findViewById(R.id.editPhoneNum);
        EditText message = (EditText) findViewById(R.id.editCustomMsg);
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
                    if (!message.getText().toString().equals("")) {
                        setPhoneNumber = phone.getText().toString();
                        setMessage = message.getText().toString();


                        //analyze

                        setTitle = title.getText().toString();
                        suggestMessage = analysisAlgorithm.analyze(setTitle);
                        suggestMessage = analysisAlgorithm.getMsg();
                        suggestMsg.setText(suggestMessage);
                        suggestMsg.setVisibility(View.VISIBLE);
                        suggestMsg.setFocusable(false);
                        suggestMessage = analysisAlgorithm.getMsg();
                        suggestMsg.setText(suggestMessage);
                        suggestMsg.setVisibility(View.VISIBLE);
                        suggestMsg.setFocusable(false);
                        Toast.makeText(getBaseContext(), "Analyzed...hopefully the boys in the lab found something", Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(getBaseContext(), "My dad always said if you don't have anything " +
                                "nice to say don't say anything at all... Do you not have a anything nice to say (Set message)", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getBaseContext(), "We should give this a name! (Set title) ", Toast.LENGTH_LONG).show();

            } else
                Toast.makeText(getBaseContext(), "This is a cool message! Too bad no one is gonna get it (Pick a valid 10 digit phone number)", Toast.LENGTH_LONG).show();
        } else
        Toast.makeText(getBaseContext(), "Why don't we pick a time and date first", Toast.LENGTH_LONG).show();


    runAnalysisHappen = true;
    }


    public void useSugMsg(View v)
    {
        if(suggestMessage != null)
        {
            EditText message = (EditText) findViewById(R.id.editCustomMsg);

            message.setText(suggestMessage);
            message.setVisibility(View.VISIBLE);
            message.setFocusable(false);

        }

    }

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



    public void goToHome(View v) {
        if(runAnalysisHappen) {
            event e = new event(setTitle, setYear, setMonth, setDay, setHour, setMin, setPhoneNumber, setMessage);
            //writeToFile(event.convertString());
            Toast.makeText(getBaseContext(), e.toString() , Toast.LENGTH_LONG).show();

            //this if checks if eventList is empty
            if(home.size() !=  0 )
            {
                //for loop does sort to place newly created events in the correct spot Earliest date come first
                for(int i = 0; i <= home.size() ; i++)
                {
                   if( i == home.size())
                   {
                       home.addAt(i,e);
                       break;
                   }
                    else if(home.getDate(i) > e.sortDate())
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
            startActivity(intent);
           // Toast.makeText(getBaseContext(), e.suceesMessage() , Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getBaseContext(), "Why don't we get the folks in the lab to analyze this", Toast.LENGTH_LONG).show();

        }

    }



}
