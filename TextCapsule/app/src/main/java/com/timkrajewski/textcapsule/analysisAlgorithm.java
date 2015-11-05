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



    public static String analyze(String titl)
    {


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


    public static String getMsg() {
        return msg;
    }
}
