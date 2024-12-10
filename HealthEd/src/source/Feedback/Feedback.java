package source.Feedback;


public class Feedback {

    private static final String FILE_NAME = "feedback.txt";
    private IFileManager fileManager;

    // Constructor: Depend on IFileManager abstraction
    public Feedback(IFileManager fileManager) {
        this.fileManager = fileManager;  // Inject dependency through constructor
    }

    // Method to add feedback to the file
    public void addFeedback(String participantName, String feedback) {
        String data = participantName + ": " + feedback;
        fileManager.write(FILE_NAME, data);  // Delegate to IFileManager's write method
    }

    // Method to display feedback by reading from the file
    public void displayFeedback() {
        // Read all the content from the file
        String fileContent = fileManager.read(FILE_NAME);

        // If the file is empty, print a message and return
        if (fileContent == null || fileContent.isEmpty()) {
            System.out.println("No feedback available.");
            return;
        }

        // Split the content into lines
        String[] feedbackArray = fileContent.split("\n");

        // Loop through each feedback and print it
        System.out.println("Feedbacks:");
        for (String feedback : feedbackArray) {
            System.out.println(feedback.trim());  // Print each feedback line
        }
    }
}