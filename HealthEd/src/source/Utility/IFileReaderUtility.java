package source.Utility;

public interface IFileReaderUtility {
    String readFile(String filePath);
    boolean searchFromFile(String filePath, String searchTerm);
}
