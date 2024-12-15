package source.workshop;

import source.Utility.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Workshop {

    public void createWorkshop(String filePath, IFileReadWrite fileWriteUtility) {
        Scanner scanner = new Scanner(System.in);

        String workshopName = Utility.getInput("Enter Workshop Name: ", scanner);
        String workshopDate = DateValidator.getValidDate(scanner);
        String speaker = Utility.getInput("Enter Speaker Name: ", scanner);

        String workshopDetails = Utility.joinArray(new String[]{workshopName, workshopDate, speaker}, ",");
        fileWriteUtility.appendToFile(filePath, workshopDetails);

        System.out.println("Workshop created and saved successfully!");
    }

    public void editWorkshop(String workshopName, String filePath, IFileReadWrite fileWriteUtility, IFileReadWrite fileReaderUtility) {
        
        String[] workshops = (String[]) fileReaderUtility.readFile(filePath, true);

        for (int i = 0; i < workshops.length; i++) {
            String[] workshopParts = Utility.splitString(workshops[i], ",");
            if (workshopParts[0].equals(workshopName)) {
            
                workshops[i] = getUpdatedWorkshopDetails(workshopParts);

                fileWriteUtility.writeToFile(filePath, Utility.joinArray(workshops, "\n"));
                System.out.println("Workshop details updated successfully!");
                return;
            }
        }
        System.out.println("Workshop not found with the name: " + workshopName);
    }

    public void viewWorkshops(IFileReadWrite fileRead, String filepath, boolean upcoming) {
        String[] workshops = (String[]) fileRead.readFile(filepath, true);
        String workshopType = upcoming ? "Upcoming Workshops:" : "Past Workshops:";
        System.out.println(workshopType);

        LocalDate currentDate = LocalDate.now();
        for (String workshop : workshops) {
            if (isWorkshopOfType(workshop, currentDate, upcoming)) {
                displayWorkshop(workshop);
            }
        }
    }

    private boolean isWorkshopOfType(String workshop, LocalDate currentDate, boolean upcoming) {
        String[] parts = workshop.split(" ");
        LocalDate workshopDate = LocalDate.parse(parts[1]);
        return (upcoming && workshopDate.isAfter(currentDate)) || (!upcoming && workshopDate.isBefore(currentDate));
    }

    private void displayWorkshop(String workshop) {
        String[] parts = workshop.split(",");
        String workshopName = parts[0];
        LocalDate workshopDate = LocalDate.parse(parts[1]);
        System.out.println(workshopName + " on " + workshopDate);
    }

    public void deleteWorkshop(String workshopName, String filePath, IFileReadWrite fileReaderUtility, IFileManipulator fileManipulator) {

        String[] workshops = (String[]) fileReaderUtility.readFile(filePath, true);

        for (String workshop : workshops) {
            String[] workshopParts = Utility.splitString(workshop, ",");
            if (workshopParts[0].equals(workshopName)) {
                fileManipulator.deleteFromFile(filePath, workshop);
                System.out.println("Workshop deleted successfully!");
                return;
            }
        }
        System.out.println("Workshop not found with the name: " + workshopName);
    }

    private String getUpdatedWorkshopDetails(String[] workshopParts) {
        Scanner scanner = new Scanner(System.in);

        String newWorkshopName = Utility.getInput("Enter Workshop Name: ", scanner);
        String newWorkshopDate = DateValidator.getValidDate(scanner);
        String newSpeaker = Utility.getInput("Enter Speaker: ", scanner);

        return Utility.joinArray(new String[]{ newWorkshopName.isEmpty() ? workshopParts[0] : newWorkshopName, newWorkshopDate.isEmpty() ? workshopParts[1] : newWorkshopDate, newSpeaker.isEmpty() ? workshopParts[2] : newSpeaker }, ",");
    }
}