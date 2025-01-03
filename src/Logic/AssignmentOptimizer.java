package Logic;

import Models.Assignment;
import Models.Employee;
import Models.Project;

import java.util.*;

public class AssignmentOptimizer {
    public AssignmentOptimizer() {

    }
    public static Assignment generateNewArrangement(ArrayList<Project> projects, ArrayList<Employee> staffList) {

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

        Assignment newEmployment = new Assignment(newProjectArrangement, newStaffArrangement);
        newEmployment.assignStaffToProjects();
        return newEmployment;
    }

    public static Assignment optimizeAssignment(ArrayList<Project> projects, ArrayList<Employee> staffList) {
        Assignment bestSolution = generateNewArrangement(projects, staffList);
        int bestScore = bestSolution.calculateEfficiency();

        for (int i = 0; i < 2000; i++) {
            Assignment newSolution = generateNewArrangement(projects, staffList);
            int newScore = newSolution.calculateEfficiency();
            int id = i + 1;

            System.out.println("Solution number " + id);
            System.out.println("Score: " + newScore);
            newSolution.output();

            int noChangeInBestScore = 0;
            if(newScore < bestScore){
                noChangeInBestScore++;
            }
            else if (newScore > bestScore) {
                bestSolution = newSolution;
                bestScore = newScore;
                noChangeInBestScore = 0;
            }
            if(noChangeInBestScore == 5) break;
        }

        return bestSolution;
    }
}