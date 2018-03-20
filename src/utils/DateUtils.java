package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Util file for date operations
 *
 * @author Theodora Bendlin
 */
public class DateUtils {

    public static final String DATE_FORMAT = "MM/dd/yyyy";

    /**
     * Parses a date from an input string
     * @param stringDate (String) string containing parseble date
     * @return the date contained in the string, or null if a parsing error occurs
     */
    public static Date parseDateFromString (String stringDate) {
        Date date = null;

        if (stringDate == null) {
            return date;
        }

        try {
            DateFormat format = new SimpleDateFormat(DATE_FORMAT);
            date = format.parse(stringDate);
        } catch (ParseException ex) {
            System.err.println("Unable to parse date: " + stringDate);
        }

        return date;
    }

    public static String convertDateToString (Date date) {
        String dateString = "";

        if (date == null) {
            return dateString;
        }

        try {
            DateFormat format = new SimpleDateFormat(DATE_FORMAT);
            dateString = format.format(date);
        } catch (Exception ex) {
            System.err.println("Unable to convert date to string");
        }
        return dateString;
    }
}
