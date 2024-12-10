package source.User;
import source.Utility.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Organizer extends AbsOrganizer {

    @Override
    public void addResources(String filePath, IFileWriteUtility fileWriteUtility) {
        Scanner scanner = new Scanner(System.in);
        String authorName = getInput("Enter Author Name: ", scanner);
        String topic = getInput("Enter Topic: ", scanner);
        String educationalResources = ResourceCollector.collectResources(new Scanner(System.in));
        String workshopDetails = authorName + "," + topic + "," + educationalResources;
        fileWriteUtility.appendToFile(filePath, workshopDetails);
        System.out.println("Educational resources added successfully!");
    }

    @Override
    public void approveAttendees(String filePath, IFileReaderUtility fileReaderUtility, String writeFile, IFileWriteUtility fileWriteUtility) {
        String[] attendees = readFileAndSplit(filePath, fileReaderUtility);
        if (attendees.length == 0) {
            System.out.println("No attendees to approve.");
            return;
        }
        System.out.println("List of Attendees:");
        for (int i = 0; i < attendees.length; i++) {
            System.out.println((i + 1) + ". " + attendees[i].split(",")[0]);
        }
        System.out.print("Approve them? :");
        Scanner scanner = new Scanner(System.in);
        if ("yes".equals(scanner.nextLine().trim().toLowerCase())) {
            fileWriteUtility.appendToFile(writeFile, String.join(",", attendees));
            System.out.println("All attendees approved and written to file.");
        } else {
            System.out.println("No attendees approved.");
        }
    }

    public void deleteFirstAttendeeIfWorkshopPassed(String workshopFilePath, String attendeesFilePath, IFileReaderUtility fileReaderUtility, IFileManipulator fileManipulator) {
        String[] workshopDetails = readFileAndSplit(workshopFilePath, fileReaderUtility);
        if (workshopDetails.length == 0) return;

        String[] firstWorkshopDetails = workshopDetails[0].split(",");
        String workshopDate = firstWorkshopDetails[1];
        String workshopTime = firstWorkshopDetails[2];
        String workshopDateTime = workshopDate + " " + workshopTime;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date workshopDateTimeParsed = dateFormat.parse(workshopDateTime);
            Date currentDateTime = new Date();

            if (workshopDateTimeParsed.before(currentDateTime)) {
                String[] attendees = readFileAndSplit(attendeesFilePath, fileReaderUtility);
                if (attendees.length > 0) {
                    String firstAttendee = attendees[0];
                    fileManipulator.deleteFromFile(attendeesFilePath, firstAttendee);
                }
            }
        } catch (ParseException e) {
            System.out.println("Error parsing date/time.");
        }
    }

    private String getInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private String[] readFileAndSplit(String filePath, IFileReaderUtility fileReaderUtility) {
        String fileContent = fileReaderUtility.readFile(filePath);
        return fileContent.split("\n");
    }
}