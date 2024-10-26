import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //String filePath = "C:\\Users\\HP\\IdeaProjects\\WorkProjects_lab02\\src\\plik.txt";

        if (args.length >= 2) {
            System.out.println("Too many arguments");
            System.exit(-1);
        }

        if (args[0].equals("-h")) {
            System.out.println("Usage: java -jar main.jar <Project and staff .txt file>");
            System.exit(0);
        }

        try {
            FileManagement Fm = new FileManagement(args[0]);
                ArrayList<Staff> staff = Fm.getStaff();
                ArrayList<Projects> projects = Fm.getProjects();

                ArrayList<Employment> employments = new ArrayList<>();
        }
        catch (FileNotFoundException e) {
            System.out.println("Wrong arguments use -h if you need help");
        }

        //FileManagement fileManagement = new FileManagement(filePath);
        //ArrayList<Projects> projects = fileManagement.getProjects();
        //ArrayList<Staff> staff = fileManagement.getStaff();
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
}
