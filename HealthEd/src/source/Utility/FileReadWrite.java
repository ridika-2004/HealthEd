package source.Utility;
import java.io.*;
import java.nio.file.*;

public class FileReadWrite implements IFileReadWrite{
    @Override
    public Object readFile(String filePath, boolean splitIntoLines) {
        try {
            String fileContent = Files.readString(Path.of(filePath)).trim();
            return splitIntoLines ? fileContent.split("\n") : fileContent;
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean searchFromFile(String filePath, String searchTerm) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    if (part.trim().equalsIgnoreCase(searchTerm.trim())) {
                        return true;
                    }
                }
            }
        } catch (IOException e) { System.out.println("File is empty"); }
        
        return false;
    }

    @Override
    public void writeToFile(String filePath, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + e.getMessage(), e);
        }
    }
    @Override
    public void appendToFile(String filePath, String data) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error appending to file: " + e.getMessage(), e);
        }
    }
}
