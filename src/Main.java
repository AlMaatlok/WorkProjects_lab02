import Logic.AssignmentOptimizer;
import Logic.FileParser;
import Models.EmployeeAssignment;
import Models.Employee;
import Models.Project;

import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        if (args.length >= 2) {
            System.out.println("Too many arguments");
            System.exit(-1);
        }
        else if(args.length == 0){
            System.out.println("No filepath given");
        }

        if (args[0].equals("-h")) {
            System.out.println("Usage: java -jar main.jar <Project and staff .txt file>");
            System.exit(0);
        }

        try {
            FileParser fm = new FileParser(args[0]);

            ArrayList<Employee> staff = fm.getStaff();
            ArrayList<Project> projects = fm.getProjects();

            System.out.println("Optimizing staff assignment to projects...");
            EmployeeAssignment bestEmployment = AssignmentOptimizer.optimizeAssignment(projects, staff);

            System.out.println("\nBest assignment:");
            bestEmployment.output();

        }
        catch (FileNotFoundException e) {
            System.out.println("Wrong arguments use -h if you need help");
        }
    }
}
