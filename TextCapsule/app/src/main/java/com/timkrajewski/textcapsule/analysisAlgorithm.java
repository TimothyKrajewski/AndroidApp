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
    public static String msg;
    public static boolean result = false;


    public static boolean analyze(String titl)
    {


        while( i < keyWords.length)
        {

            if(titl.equalsIgnoreCase(keyWords[i]))
            {
                result = true;
                msg = suggestMessage[i];
            }
            i++;

        }
        return result;
    }

    public static String suggestMessage()
    {
        return msg;

    }
}
