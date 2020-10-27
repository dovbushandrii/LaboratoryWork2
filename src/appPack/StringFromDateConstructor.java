package appPack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that can from LocalDateTime
 * construct String Object
 */
public class StringFromDateConstructor {

    /**
     * Returns Full Date&Time format text.
     *
     * @param time
     * @return - returns String object.
     */
    public static String dateAndTimeToString(LocalDateTime time) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(time);
    }

}
