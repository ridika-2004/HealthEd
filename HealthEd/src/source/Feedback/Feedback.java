package source.Feedback;
import source.Utility.*;
import java.util.Scanner;

public class Feedback {

    public void addFeedback(String filepath, IFileReadWrite fileWriteUtility) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter your feedback: ");
        String feedback = scanner.nextLine().trim();
        String feedbackData = name + ", " + feedback;
        fileWriteUtility.appendToFile(filepath, feedbackData);
        System.out.println("Thank you for your feedback!");
    }

    public void displayFeedback(String filepath, IFileReadWrite fileReaderUtility) {
        String[] feedbackData = (String[]) fileReaderUtility.readFile(filepath, true);
        if (feedbackData == null || feedbackData.length == 0) {
            System.out.println("No feedbacks available.");
            return;
        }
        for (int i = 0; i < feedbackData.length; i++) {
            System.out.println((i + 1) + ". " + feedbackData[i]);
        }
    }
    
}