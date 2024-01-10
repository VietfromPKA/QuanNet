package tinhNang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThoiGian {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static String getCurrentTime() {
        return sdf.format(new Date());
    }

    public static Date parseTime(String time) throws ParseException {
        return sdf.parse(time);
    }

    public static long tinhKhoangThoiGian(Date startTime, Date endTime) {
        return endTime.getTime() - startTime.getTime();
    }
}
