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
        int score = 0;
        for (Project project : projects) {
            score += project.getOccupiedCount();
            score -= project.getRequiredQualifications().size(); // Kary za wakaty
        }
        return score;
    }

    public void output() {
        for (Project project : projects) {
            System.out.println("Models.Project: " + project.getName());
            if (project.getOccupiedPositions().isEmpty()) {
                System.out.println("  No staff assigned yet.");
            } else {
                for (Map.Entry<String, ArrayList<String>> entry : project.getOccupiedPositions().entrySet()) {
                    System.out.println("  - Position: " + entry.getKey() + ", Employees: " + entry.getValue());
                }
            }
        }
    }
}