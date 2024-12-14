package source.Utility;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateValidator {

    public static boolean isValidDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static String getValidDate(Scanner scanner) {
        System.out.print("Enter Workshop Date (yyyy-MM-dd): ");
        String date = scanner.nextLine().trim();

        while (!isValidDate(date, "yyyy-MM-dd")) {
            System.out.print("Invalid date format! Enter again (yyyy-MM-dd): ");
            date = scanner.nextLine().trim();
        }

        return date;
    }
}