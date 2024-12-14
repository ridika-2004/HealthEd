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
        String[] workshops = (String[]) fileReaderUtility.readFile(filePath, true);
        for (int i = 0; i < workshops.length; i++) {
            String[] workshopParts = workshops[i].split(",");
            if (workshopParts[0].equals(workshopName)) {
                workshops[i] = getUpdatedWorkshopDetails(workshopParts);
                fileWriteUtility.writeToFile(filePath, String.join("\n", workshops));
                System.out.println("Workshop details updated successfully!");
                return;
            }
        }
        System.out.println("Workshop not found with the name: " + workshopName);
    }

    public void deleteWorkshop(String workshopName, String filePath, IFileReadWrite fileReaderUtility, IFileManipulator fileManipulator) {
        String[] workshops = (String[]) fileReaderUtility.readFile(filePath, true);
        for (String workshop : workshops) {
            String[] workshopParts = workshop.split(",");
            if (workshopParts[0].equals(workshopName)) {
                fileManipulator.deleteFromFile(filePath, workshop);
                System.out.println("Workshop deleted successfully!");
                return;
            }
        }
        System.out.println("Workshop not found with the name: " + workshopName);
    }

    private String getInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private String getUpdatedWorkshopDetails(String[] workshopParts) {
        Scanner scanner = new Scanner(System.in);
        String newWorkshopName = getInput("Enter Workshop Name : ", scanner);
        String newWorkshopDate = DateValidator.getValidDate(scanner);
        String newSpeaker = getInput("Enter Speaker : ", scanner);

        return (newWorkshopName.isEmpty() ? workshopParts[0] : newWorkshopName) + "," + (newWorkshopDate.isEmpty() ? workshopParts[1] : newWorkshopDate) + "," + (newSpeaker.isEmpty() ? workshopParts[2] : newSpeaker);
    }
}

