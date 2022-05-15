package com.rxjava.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataUtil {
    public static String getNowDate(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(Calendar.getInstance().getTime());
    }
}
