package source.Utility;

import java.io.*;
import java.nio.file.*;

public class FileReaderUtility implements IFileReaderUtility{
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
}
