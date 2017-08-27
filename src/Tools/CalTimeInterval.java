package Tools;

import Subjects.LocationSubject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalTimeInterval {

    public static double getInterval( LocationSubject is, LocationSubject is2){
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(is.getDateTime());
        calendar2.setTime(is2.getDateTime());
        int hour1, minu1, sec1;
        int hour2, minu2, sec2;
        double timeDis1 = 0;
        double timeDis2 = 0;
        hour1 = calendar.get(Calendar.HOUR_OF_DAY);
        hour2 = calendar2.get(Calendar.HOUR_OF_DAY);
        minu1 = calendar.get(Calendar.MINUTE);
        minu2 = calendar2.get(Calendar.MINUTE);
        sec1 = calendar.get(Calendar.SECOND);
        sec2 = calendar2.get(Calendar.SECOND);
        timeDis1 = (hour1 * 60 * 60 + minu1 * 60 + sec1);
        timeDis2 = (hour2 * 60 * 60 + minu2 * 60 + sec2);
        return Math.abs(timeDis2 - timeDis1);
    }

    public static void main(String args[]) throws ParseException {
        LocationSubject is = new LocationSubject();
        LocationSubject is2 = new LocationSubject();
        String time1 = "20151104200000";
        String time2 = "20151104203000";
        is.setDateTime(time1);
        is2.setDateTime(time2);
        System.out.println(getInterval(is,is2));

    }
}
