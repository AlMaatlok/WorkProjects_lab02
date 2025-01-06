package Logic;

import Models.AssignmentOfEmployees;
import Models.Employee;
import Models.Project;

import java.util.*;

public class AssignmentOptimizer {
    public AssignmentOptimizer() {

    }
    public static AssignmentOfEmployees generateNewArrangement(ArrayList<Project> projects, ArrayList<Employee> staffList) {

        ArrayList<Project> copiedProjects = new ArrayList<>();
        for (Project project : projects) {
            Project newProject = new Project(project.getName(),
                    project.getRequiredQualifications().toArray(new String[0]));
            copiedProjects.add(newProject);
        }

        ArrayList<Project> newProjectArrangement = new ArrayList<>(copiedProjects);
        Collections.shuffle(newProjectArrangement);

        ArrayList<Employee> copiedStaff = new ArrayList<>();
        for (Employee staff : staffList) {
            copiedStaff.add(new Employee(staff.getName(),
                    staff.getQualifications().toArray(new String[0])));
        }

        ArrayList<Employee> newStaffArrangement = new ArrayList<>(copiedStaff);
        Collections.shuffle(newStaffArrangement);

        AssignmentOfEmployees newEmployment = new AssignmentOfEmployees(newProjectArrangement, newStaffArrangement);
        newEmployment.assignStaffToProjects();
        return newEmployment;
    }

    public static AssignmentOfEmployees optimizeAssignment(ArrayList<Project> projects, ArrayList<Employee> staffList) {
        AssignmentOfEmployees bestSolution = generateNewArrangement(projects, staffList);
        int bestScore = bestSolution.calculateEfficiency();
        int noChangeInBestScore = 0;
        final int MAX_NO_IMPROVEMENT = 50;
        final int MAX_ITERATIONS = 2000;

        for (int i = 0; i < 2000; i++) {
            AssignmentOfEmployees newSolution = generateNewArrangement(projects, staffList);
            int newScore = newSolution.calculateEfficiency();

            System.out.println("Solution number " + (i+1));
            System.out.println("Score: " + newScore);
            newSolution.output();

            if (newScore > bestScore) {
                bestSolution = newSolution;
                bestScore = newScore;
                noChangeInBestScore = 0;
            }
            else{
                noChangeInBestScore++;
            }
            if (noChangeInBestScore >= MAX_NO_IMPROVEMENT) {
                System.out.println("No improvement for " + MAX_NO_IMPROVEMENT + " consecutive iterations. Stopping early.");
                break;
            }
        }

        return bestSolution;
    }
}