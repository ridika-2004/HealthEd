package source.User;

import source.Utility.*;

public abstract class AbsOrganizer extends User {

    public abstract void addResources(String filePath, IFileWriteUtility fileWriteUtility);

    public abstract void approveAttendees(String filePath, IFileReaderUtility fileReaderUtility, String writeFile, IFileWriteUtility fileWriteUtility);
}
