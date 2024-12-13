package source.User;
import java.time.LocalDate;
import java.util.*;
import source.Utility.*;

public class User {
    private String name, email;
    private static final Scanner scanner = new Scanner(System.in);

    public void login() {
        System.out.print("Enter your name: ");
        name = scanner.nextLine().trim();
        System.out.print("Enter your email: ");
        email = scanner.nextLine().trim();
    }

    public String getName() {return name;}
    public String getEmail() {return email;}

    public void registerForWorkshop(IFileReadWrite fileWrite, String filepath, String workshopName) {
        fileWrite.appendToFile(filepath, workshopName + name);
    }

    public void viewWorkshops(IFileReadWrite fileRead, String filepath, boolean upcoming) {
        String[] workshops = readFileAndSplit(fileRead, filepath);
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

    public void viewResources(IFileReadWrite fileRead, String filepath) {
        String[] lines = readFileAndSplit(fileRead, filepath);
        int selectedIndex = displayHeadlines(lines);
        if (selectedIndex != -1) {
            parseAndDisplayResource(lines, selectedIndex);
        } else {
            System.out.println("Invalid index entered. Try again.");
        }
    }

    private String[] readFileAndSplit(IFileReadWrite fileRead, String filepath) {
        String fileContent = fileRead.readFile(filepath);
        return fileContent.split("\n");
    }

    private int displayHeadlines(String[] lines) {
        System.out.println("Resources:");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].split(",")[0];
            System.out.println((i + 1) + ". " + line);
        }
        return getUserInputForIndex(lines.length);
    }

    private int getUserInputForIndex(int maxIndex) {
        System.out.print("Enter the index of the headline you want to view: ");
        int index = scanner.nextInt();
        return (index >= 1 && index <= maxIndex) ? index - 1 : -1;
    }

    private void parseAndDisplayResource(String[] lines, int index) {
        String selectedLine = lines[index];
        System.out.println("Selected Headline: " + selectedLine.split(",")[0]);
        String[] parts = selectedLine.split(",");
        System.out.println("Details:");
        for (int i = 1; i < parts.length; i++) {
            System.out.println(i + ". " + parts[i]);
        }
    }

    public void displayProfile() {
        System.out.println("Name: " + this.name);
        System.out.println("Email: " + this.email);
    }
}