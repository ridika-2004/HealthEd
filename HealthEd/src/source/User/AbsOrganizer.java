package source.User;

import source.Utility.*;

public abstract class AbsOrganizer extends User {

    public abstract void createWorkshop(String filePath, IFileWriteUtility fileWriteUtility);

    public abstract void editWorkshop(String workshopName, String filePath, IFileWriteUtility fileWriteUtility);

    public abstract void deleteWorkshop(String workshopName, String filePath, IFileWriteUtility fileWriteUtility);

    public abstract void addResources(String filePath, IFileWriteUtility fileWriteUtility);
}
