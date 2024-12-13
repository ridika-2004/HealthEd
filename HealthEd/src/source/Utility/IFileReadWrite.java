package source.Utility;

public interface IFileReadWrite {
    String readFile(String filePath);
    boolean searchFromFile(String filePath, String searchTerm);
    void writeToFile(String filePath, String data);
    void appendToFile(String filePath, String data);
}
