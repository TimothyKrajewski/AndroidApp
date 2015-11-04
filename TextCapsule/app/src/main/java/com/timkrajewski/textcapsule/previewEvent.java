package com.timkrajewski.textcapsule;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by TimKrajewski on 11/1/15.
 */
public class previewEvent extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_msg);

        EditText sendDate = (EditText) findViewById(R.id.date);
        EditText sendTime = (EditText) findViewById(R.id.time);
        EditText phNum = (EditText) findViewById(R.id.number);
        EditText title = (EditText) findViewById(R.id.title);
        EditText message = (EditText) findViewById(R.id.message);
        EditText count = (EditText) findViewById(R.id.editCount);




        sendDate.setText(event.eventDateToString());
        sendDate.setVisibility(View.VISIBLE);
        sendDate.setFocusable(false);

        sendTime.setText(event.eventTimeToString());
        sendTime.setVisibility(View.VISIBLE);
        sendTime.setFocusable(false);

        phNum.setText(event.getphoneNum());
        phNum.setVisibility(View.VISIBLE);
        phNum.setFocusable(false);

        title.setText(event.getTitle());
        title.setVisibility(View.VISIBLE);
        title.setFocusable(false);

        message.setText(event.getMessage());
        message.setVisibility(View.VISIBLE);
        message.setFocusable(false);

        count.setText(home.eventListCount());
        count.setVisibility(View.VISIBLE);
        count.setFocusable(false);

    }


    public void goToNewEvent(View v)
    {
        Intent intent = new Intent(this, newEvent.class);
        startActivity(intent);
    }


    public void goToHome(View v)
    {
        Intent intent = new Intent(getApplicationContext(), home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Text has been scheduled", Toast.LENGTH_SHORT).show();
    }
}
