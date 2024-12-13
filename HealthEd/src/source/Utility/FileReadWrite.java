package source.Utility;
import java.io.*;
import java.nio.file.*;

public class FileReadWrite implements IFileReadWrite{
    @Override
    public String readFile(String filePath) {
        try {
            return Files.readString(Path.of(filePath)).trim();
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
    }
    @Override
    public boolean searchFromFile(String filePath, String searchTerm) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchTerm)) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error searching in file: " + e.getMessage(), e);
        }
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
        try (FileWriter writer = new FileWriter(filePath, true)) { // append mode
            writer.write(data + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error appending to file: " + e.getMessage(), e);
        }
    }
}
