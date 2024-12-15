package source.Utility;

import java.time.LocalDate;
import java.util.Scanner;

public class Utility {
    
    public static String getInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static boolean getConfirmation(String prompt, Scanner scanner) {
        System.out.print(prompt + " (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    public static String[] splitString(String input, String delimiter) {
        return input.trim().isEmpty() ? new String[0] : input.split(delimiter);
    }

    public static LocalDate parseDate(String date, String format) {
        try {
            return LocalDate.parse(date, java.time.format.DateTimeFormatter.ofPattern(format));
        } catch (Exception e) {
            System.out.println("Invalid date format. Please try again.");
            return null;
        }
    }

    public static String validateNonEmpty(String input, String errorMessage) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        return input.trim();
    }

    public static void displayList(String[] items, String title) {
        System.out.println(title + ":");
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i]);
        }
    }

    public static int getUserChoice(int maxOptions, Scanner scanner) {
        System.out.print("Enter your choice (1-" + maxOptions + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        return (choice >= 1 && choice <= maxOptions) ? choice - 1 : -1;
    }

    public static boolean hasPassedDate(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    public static String joinArray(String[] array, String delimiter) {
        return String.join(delimiter, array);
    }

    public static String sanitizeInput(String input) {
        return input.replaceAll("[^a-zA-Z0-9 ]", "").trim();
    }
}
