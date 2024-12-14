package source.Utility;
import java.util.Scanner;

public class ResourceCollector {

    public static String collectResources(Scanner scanner) {
        StringBuilder resources = new StringBuilder();
        int resourceCount = 1;

        while (true) {
            String resource = scanner.nextLine().trim();
            if (resource.equalsIgnoreCase("done")) {
                break;
            }
            resources.append(resourceCount).append(".").append(resource).append(",");
            resourceCount++;
        }
        if (resources.length() > 0) {
            resources.setLength(resources.length() - 1);
        }
        return resources.toString();
    }
}