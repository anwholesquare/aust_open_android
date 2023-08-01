package com.khandokeranan.austopen;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Functions {

    public static String getCurrentDateTimeString() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy hh:mm a", Locale.getDefault());
            Date date = new Date();
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
