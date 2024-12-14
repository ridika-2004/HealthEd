package source.User;

import source.Utility.*;

public abstract class AbsOrganizer extends User {

    public abstract void addResources(String filePath, IFileReadWrite fileWrite);

    public abstract void approveAttendees(String filePath, IFileReadWrite fileReader, String writeFile, IFileReadWrite fileWrite);

    public abstract void displayAttendees(String filePath, IFileReadWrite fileReader);
}
