package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AssignmentOfEmployees {
    private ArrayList<Project> projects;
    private ArrayList<Employee> staffList;

    public AssignmentOfEmployees(ArrayList<Project> projects, ArrayList<Employee> staffList) {
        this.projects = projects;
        this.staffList = staffList;
    }

    public void assignStaffToProjects() {
        for (Project project : projects) {
            for (Employee staff : staffList) {
                for (String qualification : staff.getOpenQualifications()) {
                    if (project.isQualificationRequired(qualification) && !project.isPositionOccupied(qualification)) {
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

            if (missingPositions == 0) score += 50;

            score += 7 * (occupiedPositions / all);
            score -= 20 * (missingPositions / all);

            //sprawdza czy kwalfikacja jest special
            for (Map.Entry<String, ArrayList<String>> entry : project.getOccupiedPositions().entrySet()) {
                ArrayList<String> assignedStaff = entry.getValue();
                for (String staffName : assignedStaff) {
                    Employee staff = getStaffByName(staffName);
                    if (staff != null) {
                        for (String qualification : staff.getQualifications()) {
                            if (staff.isSpecialQualification(qualification)) {

                                score += 30;
                            }
                        }
                    }
                }
            }

            if (!project.getOccupiedPositions().containsKey("QA")) {
                score -= 40;
            }
            if (!project.getOccupiedPositions().containsKey("PM")) {
                score -= 40;
            }
        }
        return score;
    }

    private Employee getStaffByName(String name) {
        Map<String, Employee> staffMap = createStaffMap();
        return staffMap.get(name);
    }

    private Map<String, Employee> createStaffMap() {
        Map<String, Employee> staffMap = new HashMap<>();
        for (Employee e : staffList) {
            staffMap.put(e.getName(), e);
        }
        return staffMap;
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
