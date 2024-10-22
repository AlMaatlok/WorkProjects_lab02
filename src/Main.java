import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "path_to_your_file.txt"; // Uzupełnij rzeczywistą ścieżką
        FileManagement fileManagement = new FileManagement(filePath);

        if (fileManagement.validateFile()) {
            List<Projects> projects = fileManagement.getProjects();
            List<Staff> staff = fileManagement.getStaff();

            Employment employment = new Employment(projects, staff);
            employment.assignStaffToProjects();
        } else {
            System.out.println("Walidacja pliku nie powiodła się.");
        }
    }
}