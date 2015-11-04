package com.timkrajewski.textcapsule;

/**
 * Created by TimKrajewski on 11/1/15.
 */
public class event {
    private static String name;
    private static int year;
    private static int month;
    private static int day;
    private static int hour;
    private static int min;
    private static String message;
    private static String AmorPm = "am";
    private static String phoneNum;
    private static String title;




    /**Makes an event with name year month day hour min */
    public event(String titl, int yr, int mnth, int dy, int hr, int mn, String phNm, String msg)
    {
        title = titl;
        year = yr;
        month = mnth;
        day = dy;
        hour = hr;
        min = mn;
        phoneNum = phNm;
        message = msg;

    }

    public static void setPhoneNumber(String phoneNumber) {
        event.phoneNum = phoneNumber;
    }

    public static void setMessage(String msg) {
        message = msg;
    }

    public static void setEventDate(int yr, int mnth, int dy)
    {
        year = yr;
        month = mnth+1;
        day = dy;
    }

    public static void setEventTime(int setHour, int setMin)
    {
        hour = setHour;
        min = setMin;

        if(hour > 12)
        {
            hour = hour%12;
            AmorPm = "pm";
        }
        if( hour == 0)
        {
            hour = 12;
            AmorPm = "am";
        }

    }

    public static void setName(String nm) {
        name = nm;
    }

    public static void setTitle(String titl) {
        title = titl;
    }



    // To strings for printing
    public static String eventTimeToString()
    {
        if(min < 10)
        {
            return hour + ":" + "0" + min + AmorPm;
        }
        else
            return hour + ":" + min + AmorPm;
    }


    public static String eventDateToString() {


        String dayName = "th";
        String monthName;
        switch (month) {
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


        if(day== 1)
            dayName = "st";
        if(day == 2)
            dayName = "nd";
        if(day == 3)
            dayName = "rd";

        return monthName + " " + day + dayName + " " + year;
    }


    public static String nameTostring() {
        return name;
    }

    public static String getphoneNum() {
        return phoneNum;
    }

    public static String getTitle() {return title;}

    public static String getMessage() {
        return message;
    }

    public static String suceesMessage()
    {
        return "Sending " + title + " on: " + eventDateToString() + " at " + eventTimeToString() + " sending to "
                + phoneNum + " and it says " + "\"" + message + "\"";
    }
}
