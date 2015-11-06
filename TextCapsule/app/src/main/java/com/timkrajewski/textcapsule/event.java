// Name: Tim Krajewski
// Course: CSC 415
// Semester: Fall 2015
// Instructor: Dr. Pulimood
// Project name: Text Capsule
// Description: My project creates and store event objects. It then takes those event objects  and
// stores them in date order(earliest date first) in a list and on the home screen in a listview object
// the user can create and store as many event objects as they want. from the home screen the user can
// chose send the message as sms-s
// Filename: event.java
// Description:Contains: methods to construct edit event objects
// Purpose: to create and edit event objects
// Last modified on: 11/5/15
package com.timkrajewski.textcapsule;



/**
 * Created by TimKrajewski on 11/1/15.
 */
public class event {
    private String name;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;
    private String message;
    private String AmorPm = "am";
    private String phoneNum;
    private String title;


    //-----------------------------------------------------------------------------------------
//
//  Function: onCreate()
//
//    Parameters:
//    input String: title of object
//    input int; year of event
//    input int; month of event
//    input int; day of event
//    input int; hour of event
//    input int; minute of event
//    input String; phonenumber the message is going to be sent to
//    input String; message that is going to be sent
//
//
//
//    Pre-condition: inputs must be correct type method must be called
//    Post-condition: event object is created
//-----------------------------------------------------------------------------------------
    public event(String titl, int yr, int mnth, int dy, int hr, int mn, String phNm, String msg) {
        title = titl;
        year = yr;
        month = mnth;
        day = dy;
        hour = hr;
        min = mn;
        phoneNum = phNm;
        message = msg;

    }



    //-----------------------------------------------------------------------------------------
//
//  Function: setName()
//
//    Parameters:
//    input String; name of event
//
//    Pre-condition:  inputs must be correct type method must be called
//    Post-condition: name is set
//-----------------------------------------------------------------------------------------
    public void setName(String nm) {
        name = nm;
    }

    //*******************************************************************************************
    //                      To Strings for  of event objects printing                          //
    //*******************************************************************************************

    //-----------------------------------------------------------------------------------------
//
//  Function: eventTimeToSting()
//
//    Parameters:
//       input int; hour of event
//       input int; minute of event
//
//
//
//    Pre-condition:  Pre-condition: inputs must be correct type method must be called
//    Post-condition: the time of the event is spit out in an easy way for the user to
//      understand
//-----------------------------------------------------------------------------------------
    public static String eventTimeToString(int hr, int mn) {
        String AmorPm = "am";

        if (hr > 11) {
            AmorPm = "pm";
        }
        if (mn < 10) {
            return hr + ":" + "0" + mn + AmorPm;
        } else
            return hr + ":" + mn + AmorPm;
    }

    //-----------------------------------------------------------------------------------------
//
//  Function: eventDateToSting()
//
//    Parameters:
//       input int; year of event
//       input int; month of event
//       input int; day of event
//
//
//    Pre-condition:  Pre-condition: inputs must be correct type method must be called
//    Post-condition: the date of the event is spit out in an easy way for the user to
//      understand
//-----------------------------------------------------------------------------------------
    public static String eventDateToString(int yr, int mnth, int dy) {

        String dayName = "th";
        String monthName;
        switch (mnth) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
            default:
                monthName = "Invalid month";
                break;
        }


        if (dy == 1)
            dayName = "st";
        if (dy == 2)
            dayName = "nd";
        if (dy == 3)
            dayName = "rd";

        return monthName + " " + dy + dayName + " " + yr;
    }

    //-----------------------------------------------------------------------------------------
//
//  Function: getphoneNum()
//
//    Parameters:
//
//
//    Pre-condition:  method must be called
//    Post-condition: phone number is given
//-----------------------------------------------------------------------------------------
    public String getphoneNum() {
        return phoneNum;
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
    public String getMessage() {
        return message;
    }




    //-----------------------------------------------------------------------------------------
//
//  Function: toString()
//
//    Parameters:
//
//
//    Pre-condition:  method must be called
//    Post-condition: event object is converted to string
//-----------------------------------------------------------------------------------------
    public String toString() {
        return "Sending " + title + " on: " + eventDateToString(year, month, day) + " at " + eventTimeToString(hour, min) + " sending to "
                + phoneNum + " and it says " + "\"" + message + "\"";
    }

    //-----------------------------------------------------------------------------------------
//
//  Function: toString()
//
//    Parameters:
//
//
//    Pre-condition:  method must be called
//    Post-condition: the time of the event is concatenated into one double in
//      year month day hour min;
//-----------------------------------------------------------------------------------------
    public double sortDate() {
        String str = "";
        String mon, d, hr, mn;
        mon = "" + month;
        d = "" + day;
        hr = "" + hour;
        mn = "" + min;


        if (month < 10) {
            mon = "0" + month;
        }
        if (day < 10) {
            d = "0" + day;
        }
        if(hour < 10)
        {
            hr = "0" + hour;
        }
        if(min < 10)
        {
            mn = "0" + min;
        }
        str += year + mon + d + hr + mn;
        return Double.parseDouble(str);

    }

}
