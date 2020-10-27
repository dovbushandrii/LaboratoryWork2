package appPack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringFromDateConstructor {
    public static String currentDateAndTimeToString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String dateAndTimeToString(LocalDateTime time) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(time);
    }

}
