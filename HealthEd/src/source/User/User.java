package source.User;
import java.time.LocalDate;
import java.util.*;
import source.Utility.*;

public class User {
    private String name,email;

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        name = scanner.nextLine().trim();
        System.out.print("Enter your email: ");
        email = scanner.nextLine().trim();
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public void registerForWorkshop(IFileWriteUtility fileWriteUtility, String filepath, String workshopName) {
        fileWriteUtility.appendToFile(filepath,workshopName+name);
    }

    public void viewWorkshops(IFileReaderUtility fileReadUtility, String filepath, boolean upcoming) {
        LocalDate currentDate = LocalDate.now();
        String fileContent = fileReadUtility.readFile(filepath);
        String[] workshops = fileContent.split("\n");

        String workshopType = upcoming ? "Upcoming Workshops:" : "Past Workshops:";
        System.out.println(workshopType);

        for (String workshop : workshops) {
            String[] parts = workshop.split(" ");
            String workshopID = parts[0];
            LocalDate workshopDate = LocalDate.parse(parts[1]);

            if ((upcoming && workshopDate.isAfter(currentDate)) || (!upcoming && workshopDate.isBefore(currentDate))) {
                System.out.println(workshopID + " on " + workshopDate);
            }
        }
    }

    public void viewResources(IFileReaderUtility fileReadUtility, String filepath){

    }

    public void displayProfile() {
        System.out.println("Name: " + this.name);
        System.out.println("Email: " + this.email);
    }
}
