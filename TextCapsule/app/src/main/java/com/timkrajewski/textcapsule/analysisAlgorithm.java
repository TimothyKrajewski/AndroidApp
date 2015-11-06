// Name: Tim Krajewski
// Course: CSC 415
// Semester: Fall 2015
// Instructor: Dr. Pulimood
// Project name: Text Capsule
// Description: My project creates and store event objects. It then takes those event objects  and
// stores them in date order(earliest date first) in a list and on the home screen in a listview object
// the user can create and store as many event objects as they want. from the home screen the user can
// chose send the message as sms-s
// Filename: analysisAlgorithm.java
// Description:Contains: an algorithm to find the key words
// Purpose: suggested a message based on users input
// Last modified on: 11/5/15
package com.timkrajewski.textcapsule;

import android.widget.Toast;
import java.util.Scanner;

/**
 * Created by TimKrajewski on 11/3/15.
 */
public class analysisAlgorithm
{

    private static String[] keyWords = {"birthday", "Birthday", "Anniversary", "anniversary", "party", "Party"};
    private static String[] suggestMessage = {"Happy Birthday!", "Happy Birthday!", "Happy Anniversary!"
            , "Happy Anniversary!", "Can't wait to see you at the party", "Can't wait to see you at the party"};
    private static int i = 0;
    public static String msg = "Shoot.. no key word found!";


    //-----------------------------------------------------------------------------------------
//
//  Function: analyze()
//
//    Parameters:
//    input String; title of the event
//
//    Pre-condition:  title must be assigned to event
//    Post-condition: a suggested is message is returned if and only if a key word is found
//-----------------------------------------------------------------------------------------
    public static String analyze(String titl)
    {
        msg="Shoot.. no key word found!";

        for(int i = 0; i < keyWords.length; i++)
        {
            if(titl.contains(keyWords[i]))
            {
                msg = suggestMessage[i];
                break;
            }
        }
        return msg;
    }

    //-----------------------------------------------------------------------------------------
//
//  Function: getMessage()
//
//    Parameters:
//
//
//    Pre-condition:  method must be called
//    Post-condition: message is given
//-----------------------------------------------------------------------------------------
    public static String getMsg() {
        return msg;
    }
}
