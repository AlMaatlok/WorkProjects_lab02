import java.util.*;


public class Main {
    public static void main(String[] args) {
        String filePath = "src/plik.txt";

        FileManagement fileManagement = new FileManagement(filePath);
        //ArrayList<Projects> projects = fileManagement.getProjects();
        //ArrayList<Staff> staff = fileManagement.getStaff();

        if (fileManagement.validateFile()) {
            ArrayList<Projects> projects = fileManagement.getProjects();
            ArrayList<Staff> staff = fileManagement.getStaff();

            Employment manager = new Employment(projects, staff);

            manager.EmployeeToProject();

            manager.output();
        } else {
            System.out.println("Plik z danymi jest niepoprawny.");
        }
    }

        /*for (Projects project : projects) {
            System.out.println("Projekt: " + project.getName());
            System.out.println("Potrzebne kwalifikacje: ");
            for (String qualification : project.getRequiredQualifications()) {
                System.out.print(qualification + " ");
            }
            System.out.println("");
        }
        ArrayList<Staff> staffList = fileManagement.getStaff();

        for (Staff employee : staffList) {
            String qualifications = String.join(" ", employee.getQualifications().keySet());
            System.out.println("Employee: " + employee.getName() + " Qualifications: " + qualifications);
        }*/
}
