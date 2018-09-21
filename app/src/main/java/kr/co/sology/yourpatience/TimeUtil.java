package kr.co.sology.yourpatience;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimeUtil {


    public static String getDiff(long diff) {
        long day    = 0;
        long hour   = 0;
        long minute = 0;
        long second = 0;
        if (diff > 60*60*24) {
            day  = diff/(60*60*24);
            diff = diff%(60*60*24);
        }
        if (diff > 60*60) {
            hour = diff/(60*60);
            diff = diff%(60*60);
        }
        if (diff > 60) {
            minute = diff/60;
            diff = diff%60;
        }
        second = diff;

        return Long.toString(day)+":"+Long.toString(hour)+":"+Long.toString(minute)+":"+Long.toString(second);
    }

    public static String getDateTime(long t) {
        SimpleDateFormat f= new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
        return f.format(t);
    }

}
