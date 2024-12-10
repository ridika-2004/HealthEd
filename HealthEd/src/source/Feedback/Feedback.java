package source.Feedback;
import source.Utility.*;
import java.util.Scanner;

public class Feedback {

    public void addFeedback(String filepath, IFileWriteUtility fileWriteUtility) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter your feedback: ");
        String feedback = scanner.nextLine().trim();
        String feedbackData = name + ": " + feedback;
        fileWriteUtility.appendToFile(filepath, feedbackData);
        System.out.println("Thank you for your feedback!");
    }

    public void displayFeedback(String filepath, IFileReaderUtility fileReaderUtility) {
        String feedbackData = fileReaderUtility.readFile(filepath);
        if (feedbackData.isEmpty()) {
            System.out.println("No feedbacks available.");
            return;
        }
        String[] feedbacks = feedbackData.split("\n");
        for (int i = 0; i < feedbacks.length; i++) {
            System.out.println((i + 1) + ". " + feedbacks[i]);
        }
    }
}