package source.workshop;

import source.Utility.*;
import java.util.Scanner;

public class Workshop {

    public void createWorkshop(String filePath, IFileReadWrite fileWriteUtility) {
        Scanner scanner = new Scanner(System.in);
        String workshopName = getInput("Enter Workshop Name: ", scanner);
        String workshopDate = DateValidator.getValidDate(scanner);
        String speaker = getInput("Enter Speaker Name: ", scanner);
        String workshopDetails = workshopName + "," + workshopDate + "," + speaker;
        fileWriteUtility.appendToFile(filePath, workshopDetails);
        System.out.println("Workshop created and saved successfully!");
    }

    public void editWorkshop(String workshopName, String filePath, IFileReadWrite fileWriteUtility, IFileReadWrite fileReaderUtility) {
        if (fileReaderUtility.searchFromFile(filePath, workshopName)) {
            String[] workshops = readFileAndSplit(filePath, fileReaderUtility);
            for (int i = 0; i < workshops.length; i++) {
                String[] workshopParts = workshops[i].split(",");
                if (workshopParts[0].equals(workshopName)) {
                    workshops[i] = getUpdatedWorkshopDetails(workshopParts);
                    fileWriteUtility.writeToFile(filePath, String.join("\n", workshops));
                    System.out.println("Workshop details updated successfully!");
                    return;
                }
            }
        }
        System.out.println("Workshop not found with the name: " + workshopName);
    }

    public void deleteWorkshop(String workshopName, String filePath, IFileReadWrite fileReaderUtility, IFileManipulator fileManipulator) {
        if (fileReaderUtility.searchFromFile(filePath, workshopName)) {
            String workshopDetails = getWorkshopDetailsFromFile(workshopName, filePath, fileReaderUtility);
            fileManipulator.deleteFromFile(filePath, workshopDetails);
            System.out.println("Workshop deleted successfully!");
        } else {
            System.out.println("Workshop not found with the name: " + workshopName);
        }
    }
    private String getInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private String[] readFileAndSplit(String filePath, IFileReadWrite fileReaderUtility) {
        String fileContent = fileReaderUtility.readFile(filePath);
        return fileContent.split("\n");
    }

    private String getWorkshopDetailsFromFile(String workshopName, String filePath, IFileReadWrite fileReaderUtility) {
        String[] workshops = readFileAndSplit(filePath, fileReaderUtility);
        for (String workshop : workshops) {
            String[] workshopParts = workshop.split(",");
            if (workshopParts[0].equals(workshopName)) {
                return workshop;
            }
        }
        return null;
    }

    private String getUpdatedWorkshopDetails(String[] workshopParts) {
        Scanner scanner = new Scanner(System.in);
        String newWorkshopName = getInput("Enter new Workshop Name (press Enter to leave unchanged): ", scanner);
        String newWorkshopDate = getInput("Enter new Date (press Enter to leave unchanged): ", scanner);
        String newSpeaker = getInput("Enter new Speaker (press Enter to leave unchanged): ", scanner);

        return (newWorkshopName.isEmpty() ? workshopParts[0] : newWorkshopName) + "," + (newWorkshopDate.isEmpty() ? workshopParts[1] : newWorkshopDate) + "," + (newSpeaker.isEmpty() ? workshopParts[2] : newSpeaker);
    }
}
