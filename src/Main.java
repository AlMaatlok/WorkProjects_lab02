import Logic.AssignmentOptimizer;
import Logic.FileParser;
import Models.Assignment;
import Models.Employee;
import Models.Project;

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
            System.out.println("Usage: java -jar main.jar <Models.Project and staff .txt file>");
            System.exit(0);
        }

        try {
            FileParser fm = new FileParser(args[0]);
            ArrayList<Employee> staff = fm.getStaff();
            ArrayList<Project> projects = fm.getProjects();

            System.out.println("Optimizing staff assignment to projects...");
            Assignment bestEmployment = AssignmentOptimizer.optimizeAssignment(projects, staff);

            System.out.println("\nBest assignment:");
            bestEmployment.output();

        }
        catch (FileNotFoundException e) {
            System.out.println("Wrong arguments use -h if you need help");
        }
    }
}
