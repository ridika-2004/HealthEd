package source.Utility;

public interface IFileWriteUtility {
    void writeToFile(String filePath, String data);
    void appendToFile(String filePath, String data);
}
