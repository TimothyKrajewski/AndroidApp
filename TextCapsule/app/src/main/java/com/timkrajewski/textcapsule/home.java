package com.timkrajewski.textcapsule;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.facebook.FacebookSdk;



public class home extends AppCompatActivity {

    private static List<event> eventList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        FacebookSdk.sdkInitialize(getApplicationContext());
        // Initialize the SDK before executing any other operations,
        // especially, if you're using Facebook UI elements.
        setContentView(R.layout.activity_home);

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

    public void goToSendMessage(View v)
    {
        Intent intent = new Intent(this, sendMessage.class);
        startActivity(intent);
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




}
