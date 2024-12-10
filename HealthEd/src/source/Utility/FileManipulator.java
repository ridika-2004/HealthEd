package source.Utility;

import java.io.*;

public class FileManipulator implements IFileManipulator {

    @Override
    public void deleteFromFile(String filePath, String dataToDelete) {
        File tempFile = new File(filePath + ".tmp");
        File originalFile = new File(filePath);

        boolean isDeleted = filterAndWriteToFile(originalFile, tempFile, dataToDelete);

        if (isDeleted) {
            replaceFile(originalFile, tempFile);
        } else {
            tempFile.delete();
            System.out.println("No matching data found to delete.");
        }
    }

    private boolean filterAndWriteToFile(File originalFile, File tempFile, String dataToDelete) {
        boolean isDeleted = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.equals(dataToDelete)) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    isDeleted = true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error filtering and writing to file: " + e.getMessage(), e);
        }

        return isDeleted;
    }

    private void replaceFile(File originalFile, File tempFile) {
        if (originalFile.delete()) {
            if (!tempFile.renameTo(originalFile)) {
                throw new RuntimeException("Failed to rename temp file to original.");
            } else {
                System.out.println("File updated successfully.");
            }
        } else {
            throw new RuntimeException("Failed to delete the original file.");
        }
    }
}