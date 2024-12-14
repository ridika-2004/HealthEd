package source;

import source.workshop.Workshop;
import source.Feedback.Feedback;
import source.Utility.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IFileReadWrite fileReadWrite = new FileReadWrite(); // Assuming you have implemented IFileReadWrite
        IFileManipulator fileManipulator = new FileManipulator(); // Assuming you have implemented this as well
        Workshop workshop = new Workshop();
        Feedback feedback = new Feedback();

        Scanner scanner = new Scanner(System.in);
        String filePath = "src\\source\\files\\workshop.txt"; // Replace with an actual file path in your system
        
        while (true) {
            System.out.println("\n--- Workshop Management System ---");
            System.out.println("1. Create Workshop");
            System.out.println("2. Edit Workshop");
            System.out.println("3. Delete Workshop");
            System.out.println("4. Give Feedback");
            System.out.println("5. Display Feedback");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1 -> {
                    workshop.createWorkshop(filePath, fileReadWrite);
                }
                case 2 -> {
                    System.out.print("Enter the name of the workshop to edit: ");
                    String workshopName = scanner.nextLine();
                    workshop.editWorkshop(workshopName, filePath, fileReadWrite, fileReadWrite);
                }
                case 3 -> {
                    System.out.print("Enter the name of the workshop to delete: ");
                    String workshopName = scanner.nextLine();
                    workshop.deleteWorkshop(workshopName, filePath, fileReadWrite, fileManipulator);
                }
                case 4 -> {
                    feedback.addFeedback("src\\source\\files\\feedback.txt", fileReadWrite);
                }
                case 5 -> {
                    feedback.displayFeedback("src\\source\\files\\feedback.txt", fileReadWrite);
                }
                case 6 -> {
                    System.out.println("Exiting Workshop Management System. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
