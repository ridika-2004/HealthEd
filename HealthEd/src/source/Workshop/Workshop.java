package source.Workshop;

import java.util.ArrayList;
import java.util.List;

public class Workshop implements IWorkshop {

    // Setters for the workshop details
    public void setTitle(String title) {
        this.title = title;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    // Methods to manage attendees
    @Override
    public void addParticipants(String participantName) {
        if (!attendees.contains(participantName)) {
            attendees.add(participantName);
            System.out.println(participantName + " has been added to the workshop.");
        } else {
            System.out.println(participantName + " is already attending this workshop.");
        }
    }

    @Override
    public void displayParticipants() {
        if (attendees.isEmpty()) {
            System.out.println("No participants have registered for this workshop yet.");
        } else {
            System.out.println("Participants in the workshop:");
            for (String participant : attendees) {
                System.out.println(participant);
            }
        }
    }

    @Override
    public void removeParticipants(String participantName) {
        if (attendees.contains(participantName)) {
            attendees.remove(participantName);
            System.out.println(participantName + " has been removed from the workshop.");
        } else {
            System.out.println(participantName + " is not registered for this workshop.");
        }
    }

    // Methods to manage educational resources
    @Override
    public void addEducationalResources(String resource) {
        if (!educationalResources.contains(resource)) {
            educationalResources.add(resource);
            System.out.println("Resource: " + resource + " has been added.");
        } else {
            System.out.println("Resource already exists.");
        }
    }

    @Override
    public void displayEducationalResources() {
        if (educationalResources.isEmpty()) {
            System.out.println("No educational resources have been added.");
        } else {
            System.out.println("Educational Resources for the workshop:");
            for (String resource : educationalResources) {
                System.out.println(resource);
            }
        }
    }

    // Save the workshop details to a file using FileManager
    @Override
    public void saveToFile() {
        StringBuilder content = new StringBuilder();

        // Save workshop details
        content.append(title).append(",").append(topic).append(",").append(date).append(",").append(speaker).append("\n");

        // Save participants in CSV format
        for (String participant : attendees) {
            content.append("Participant,").append(participant).append("\n");
        }

        // Save educational resources in CSV format
        for (String resource : educationalResources) {
            content.append("Resource,").append(resource).append("\n");
        }

        // Write to file using FileManager's write method
        fileManager.write(FILE_NAME, content.toString());
        System.out.println("Workshop details saved to " + FILE_NAME);
    }

    // Load the workshop details from the file using FileManager
    @Override
    public void loadFromFile() {
        // Read the file content using FileManager's read method
        String fileContent = fileManager.read(FILE_NAME);

        if (fileContent == null || fileContent.isEmpty()) {
            System.out.println("No workshop data found in file.");
            return;
        }

        String[] lines = fileContent.split("\n");
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length == 4) {
                // This is the workshop info
                title = fields[0];
                topic = fields[1];
                date = fields[2];
                speaker = fields[3];
            } else if (fields.length == 2) {
                // This is a participant or resource
                String type = fields[0];
                String value = fields[1];
                if (type.equals("Participant")) {
                    attendees.add(value);
                } else if (type.equals("Resource")) {
                    educationalResources.add(value);
                }
            }
        }

        System.out.println("Workshop details loaded from " + FILE_NAME);
    }
}