package com.timkrajewski.textcapsule;

import android.os.Parcel;
import android.os.Parcelable;

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


    /**
     * Makes an event with name year month day hour min
     */
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


    public void setMessage(String msg) {
        message = msg;
    }

    public void setEventDate(int yr, int mnth, int dy) {
        year = yr;
        month = mnth + 1;
        day = dy;
    }

    public void setEventTime(int setHour, int setMin) {
        hour = setHour;
        min = setMin;

        if (hour > 12) {
            hour = hour % 12;
            AmorPm = "pm";
        }
        if (hour == 0) {
            hour = 12;
            AmorPm = "am";
        }

    }

    public void setName(String nm) {
        name = nm;
    }

    public void setTitle(String titl) {
        title = titl;
    }


    // To strings for printing
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


    public String nameTostring() {
        return name;
    }

    public String getphoneNum() {
        return phoneNum;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String suceesMessage() {
        return "Sending " + title + " on: " + eventDateToString(year, month, day) + " at " + eventTimeToString(hour, min) + " sending to "
                + phoneNum + " and it says " + "\"" + message + "\"";
    }

    public String convertString() {
        return title + " " + message + " " + phoneNum + " " + " " + year + " " + month + 1 + " " + day + " " + hour
                + " " + min + " " + AmorPm;
    }

    //toStirng can not be static but has to be over written for the list view
    public String toString() {
        return "Sending " + title + " on: " + eventDateToString(year, month, day) + " at " + eventTimeToString(hour, min) + " sending to "
                + phoneNum + " and it says " + "\"" + message + "\"";
    }

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
