package source.User;
import source.Utility.*;
import java.util.Scanner;

public class Organizer extends AbsOrganizer {

    @Override
    public void createWorkshop(String filePath, IFileWriteUtility fileWriteUtility) {
        Scanner scanner = new Scanner(System.in);
        String workshopName = getInput("Enter Workshop Name: ", scanner);
        String workshopDate = DateValidator.getValidDate(scanner);
        String speaker = getInput("Enter Speaker Name: ", scanner);

        String workshopDetails = workshopName + "," + workshopDate + "," + speaker;
        fileWriteUtility.appendToFile(filePath, workshopDetails);
        System.out.println("Workshop created and saved successfully!");
    }

    @Override
    public void editWorkshop(String workshopName, String filePath, IFileWriteUtility fileWriteUtility) {
    }

    @Override
    public void deleteWorkshop(String workshopName, String filePath, IFileWriteUtility fileWriteUtility) {
    }

    @Override
    public void addResources(String filePath, IFileWriteUtility fileWriteUtility) {
        Scanner scanner = new Scanner(System.in);
        String authorName = getInput("Enter Author Name: ", scanner);
        String topic = getInput("Enter Topic: ", scanner);

        String educationalResources = ResourceCollector.collectResources(scanner);
        String workshopDetails = authorName + "," + topic + "," + educationalResources;
        fileWriteUtility.appendToFile(filePath, workshopDetails);
        System.out.println("Educational resources added successfully!");
    }

    private String getInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}