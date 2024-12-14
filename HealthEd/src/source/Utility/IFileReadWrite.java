package source.Utility;

public interface IFileReadWrite {
    Object readFile(String filePath, boolean splitIntoLines);
    boolean searchFromFile(String filePath, String searchTerm);
    void writeToFile(String filePath, String data);
    void appendToFile(String filePath, String data);
}
