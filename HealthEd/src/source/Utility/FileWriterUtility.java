package source.Utility;

import java.io.*;

public class FileWriterUtility implements IFileWriteUtility{
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
