package Models;

import java.util.ArrayList;
import java.util.Map;

public class Assignment {
    private ArrayList<Project> projects;
    private ArrayList<Employee> staffList;

    public Assignment(ArrayList<Project> projects, ArrayList<Employee> staffList) {
        this.projects = projects;
        this.staffList = staffList;
    }

    public void assignStaffToProjects() {
        for (Project project : projects) {
            for (Employee staff : staffList) {
                for (String qualification : staff.getOpenQualifications()) {
                    if (project.isQualificationRequired(qualification)) {
                        project.occupyPosition(qualification, staff);
                    }
                }
            }
        }
    }

    public int calculateEfficiency() {
        int score = 1000;
        for (Project project : projects) {
            int occupiedPositions = project.getOccupiedCount();
            int missingPositions = project.getRequiredQualifications().size();
            int all = occupiedPositions + missingPositions;

            if(missingPositions == 0) score += 50;

            score += 7 * (occupiedPositions / all);
            score -= 20 * (missingPositions / all);

        }
        return score;
    }

    public void output() {

        for (Project project : projects) {
            System.out.println("Project: " + project.getName());
            if (project.getOccupiedPositions().isEmpty()) {
                System.out.println("No staff assigned yet.");
            } else {
                for (Map.Entry<String, ArrayList<String>> entry : project.getOccupiedPositions().entrySet()) {
                    System.out.println("  - Position: " + entry.getKey() + ", Employees: " + entry.getValue());
                }
            }
        }
        System.out.println("=========================================================");
        System.out.println();
        System.out.println("=========================================================");
    }
}