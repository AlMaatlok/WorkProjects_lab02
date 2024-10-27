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

                Employment employment = new Employment(projects, staff);
                employment.assignStaffToProjects();
                employment.output();
        }
        catch (FileNotFoundException e) {
            System.out.println("Wrong arguments use -h if you need help");
        }
    }
}
