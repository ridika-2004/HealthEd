package source.User;

import source.Utility.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Organizer extends AbsOrganizer {

    @Override
    public void addResources(String filePath, IFileReadWrite fileWriter) {
        Scanner scanner = new Scanner(System.in);
        String authorName = getInput("Enter Author Name: ", scanner);
        String topic = getInput("Enter Topic: ", scanner);
        String educationalResources = ResourceCollector.collectResources(scanner);

        String workshopDetails = authorName + "," + topic + "," + educationalResources;
        fileWriter.appendToFile(filePath, workshopDetails);
        System.out.println("Resources added successfully!");
    }

    @Override
    public void approveAttendees(String filePath, IFileReadWrite fileReader, String writeFile, IFileReadWrite fileWriter) {
        String[] attendees = (String[]) fileReader.readFile(filePath, true); // Read and split into lines
        if (validateAndDisplayList("attendees", attendees)) {
            if (getConfirmation("Approve them?")) {
                fileWriter.appendToFile(writeFile, String.join(",", attendees));
                System.out.println("All attendees approved and written to file.");
            } else {
                System.out.println("No attendees approved.");
            }
        }
    }

    @Override
    public void displayAttendees(String filePath, IFileReadWrite fileReader) {
        String[] attendees = (String[]) fileReader.readFile(filePath, true); // Read and split into lines
        validateAndDisplayList("attendees", attendees);
    }

    public void deleteAttendeesIfWorkshopPassed(String workshopFilePath, String attendeesFilePath, IFileReadWrite fileReader, IFileManipulator fileManipulator) {
        String workshopDateTime = getFirstWorkshopDateTime(workshopFilePath, fileReader);
        if (workshopDateTime != null && hasDateTimePassed(workshopDateTime)) {
            String[] attendees = (String[]) fileReader.readFile(attendeesFilePath, true); // Read and split into lines
            if (attendees.length > 0) {
                fileManipulator.deleteFromFile(attendeesFilePath, attendees[0]);
            }
        }
    }

    private String getFirstWorkshopDateTime(String filePath, IFileReadWrite fileReader) {
        String[] workshopDetails = (String[]) fileReader.readFile(filePath, true); // Read and split into lines
        if (workshopDetails.length > 0) {
            String[] firstWorkshopDetails = workshopDetails[0].split(",");
            return firstWorkshopDetails[1] + " " + firstWorkshopDetails[2];
        }
        return null;
    }

    private boolean hasDateTimePassed(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date parsedDateTime = dateFormat.parse(dateTime);
            return parsedDateTime.before(new Date());
        } catch (ParseException e) {
            System.out.println("Error parsing date/time.");
            return false;
        }
    }

    private boolean validateAndDisplayList(String itemType, String[] items) {
        if (items.length == 0) {
            System.out.println("No " + itemType + " found.");
            return false;
        }
        System.out.println("List of " + itemType + ":");
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i].split(",")[0]);
        }
        return true;
    }

    private boolean getConfirmation(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt + " (yes/no): ");
        return "yes".equalsIgnoreCase(scanner.nextLine().trim());
    }

    private String getInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}