package com.timkrajewski.textcapsule;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.View;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import java.util.Calendar;


/**
 * Created by TimKrajewski on 11/1/15.
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
    private String suggestMessage;
    private event e;
    private boolean keyWordFound = false;
    private boolean dateIsSet = false;
    private boolean timeIsSet = false;
    private boolean phoneNumIsSet = false;
    private boolean titleIsSet = false;
    private boolean messageIsSet = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);
    }

    public void setDate(View v) {
        if (dateIsSet != false) {
            Calendar cal = Calendar.getInstance();
            dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    EditText setDate = (EditText) findViewById(R.id.editSetDate);
                    setYear = year;
                    setMonth = monthOfYear;
                    setDay = dayOfMonth;
                    event.setEventDate(setYear, setMonth, setDay);
                    setDate.setText(event.eventDateToString());
                    setDate.setVisibility(View.VISIBLE);
                    setDate.setFocusable(false);
                }
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            dateDialog.show();
            dateIsSet = true;
        }

        if (dateIsSet == false) {
            Calendar cal = Calendar.getInstance();
            dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    EditText setDate = (EditText) findViewById(R.id.editSetDate);
                    setYear = year;
                    setMonth = monthOfYear;
                    setDay = dayOfMonth;
                    event.setEventDate(setYear, setMonth, setDay);
                    setDate.setText(event.eventDateToString());
                    setDate.setVisibility(View.VISIBLE);
                    setDate.setFocusable(false);
                }
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            dateDialog.show();
            dateIsSet = true;
        }

    }


    public void setTime(View v) {
        if (timeIsSet != false)
        {
            Calendar cal = Calendar.getInstance();
            timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener()
            {
                @Override
                public void onTimeSet(TimePicker view, int hour, int minute)
                {
                    EditText setDate = (EditText) findViewById(R.id.editSetTime);
                    setMin = minute;
                    setHour = hour;
                    event.setEventTime(setHour, setMin);
                    setDate.setText(event.eventTimeToString());
                    setDate.setVisibility(View.VISIBLE);
                    setDate.setFocusable(false);
                }

            }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), false);
            timeDialog.show();
            timeIsSet = true;
        }

        if (timeIsSet == false)
        {
            Calendar cal = Calendar.getInstance();
            timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener()
            {
                @Override
                public void onTimeSet(TimePicker view, int hour, int minute)
                {
                    EditText setDate = (EditText) findViewById(R.id.editSetTime);
                    setMin = minute;
                    setHour = hour;
                    event.setEventTime(setHour, setMin);
                    setDate.setText(event.eventTimeToString());
                    setDate.setVisibility(View.VISIBLE);
                    setDate.setFocusable(false);
                }

            }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), false);
            timeDialog.show();
            timeIsSet = true;
        }

    }

    public void saveData(View v)
    {
        if (timeIsSet == true && dateIsSet== true)
        {

            EditText title = (EditText) findViewById(R.id.editEventTitle);
            EditText phone = (EditText) findViewById(R.id.editPhoneNum);
            EditText message = (EditText) findViewById(R.id.editCustomMsg);
            EditText suggestMsg = (EditText) findViewById(R.id.editSugMsg);

            if(phone.getText().toString().equals("Title of the Event") == false) // this if statements ensures that the title is set at least once
            {
                setTitle = title.getText().toString();
                event.setTitle(title.getText().toString());
                keyWordFound = analysisAlgorithm.analyze(setTitle);

                titleIsSet = true;
            }
            else
                Toast.makeText(getBaseContext(), "Let's name the event (Title event)", Toast.LENGTH_SHORT).show();
            // this if statements allows the user to set title as many times as wanted
            // after the first time
            if(titleIsSet == true)
            {

                setTitle = title.getText().toString();
                event.setTitle(setTitle);
                keyWordFound = analysisAlgorithm.analyze(setTitle);
            }

            if(phone.getText().toString().equals("Phone Number") == false) // this if statements ensures that the phone number is set at least once
            {
                setPhoneNumber = phone.getText().toString();
                event.setPhoneNumber(setPhoneNumber);
                phoneNumIsSet = true;
            }
            else
                Toast.makeText(getBaseContext(), "Let's send this radical message to someone (Pick A Phone Number)", Toast.LENGTH_SHORT).show();
            // this if statements allows the user to set phone Number as many times as wanted
            // after the first time

            if(phoneNumIsSet == true)
            {
                setPhoneNumber = phone.getText().toString();
                event.setPhoneNumber(setPhoneNumber);
            }


            if(message.getText().toString().equals("Type Custom Message") == false) // this if statements ensures that the message is set at least once
            {
                setMessage = message.getText().toString();
                event.setMessage(setMessage);
                messageIsSet = true;
            }
            else
                Toast.makeText(getBaseContext(), "I'm sure who your texting wants something read (Pick Message)", Toast.LENGTH_SHORT).show();
            // this if statements allows the user to set message as many times as wanted
            // after the first time
            if(messageIsSet == true)
            {
                setMessage = message.getText().toString();
                event.setMessage(setMessage);
            }


            if(keyWordFound == true)
            {
                suggestMessage = analysisAlgorithm.suggestMessage();
                suggestMsg.setText(suggestMessage);
                suggestMsg.setVisibility(View.VISIBLE);
                suggestMsg.setFocusable(false);

            }

            if(messageIsSet == true && phoneNumIsSet == true && titleIsSet == true)
            {
                Toast.makeText(getBaseContext(), event.suceesMessage() , Toast.LENGTH_LONG).show();
            }
        }
        else
            Toast.makeText(getBaseContext(), "Why don't we pick a time and date first", Toast.LENGTH_SHORT).show();
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



    public void goToPreviewEvent(View v)
    {
        Intent intent = new Intent(this, previewEvent.class);

        if (timeIsSet == true && dateIsSet== true)
        {
            if(phoneNumIsSet == true)
            {
                if(titleIsSet == true)
                {
                    if(messageIsSet)
                    {
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getBaseContext(), "My dad always said if you don't have anything " +
                                "nice to say don't say anything at all... Do you not have a anything nice to say (Set message)", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getBaseContext(), "We should give this a name! (Set title) ", Toast.LENGTH_LONG).show();

            }
               else
                Toast.makeText(getBaseContext(), "This is a cool message! Too bad no one is gonna get it (Pick a phone number)", Toast.LENGTH_LONG).show();

        }
        else
            Toast.makeText(getBaseContext(), "Why don't we pick a time and date first", Toast.LENGTH_LONG).show();


    }





    public void goToHome(View v)
    {

        Intent intent = new Intent(getApplicationContext(), home.class);
        event e = new event(setTitle, setYear, setMonth, setDay, setHour, setMin, setPhoneNumber, setMessage);
        home.addEvent(e);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Text has been scheduled", Toast.LENGTH_SHORT).show();
    }



}
