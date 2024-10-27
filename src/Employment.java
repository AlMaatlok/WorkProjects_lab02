import java.util.*;

public class Employment {
    private ArrayList<Projects> projects;
    private ArrayList<Staff> staffList;

    // Constructor to initialize projects and staff lists
    public Employment(ArrayList<Projects> projects, ArrayList<Staff> staffList) {
        this.projects = projects;
        this.staffList = staffList;
    }

    // Method to assign staff to projects based on qualifications
    public void assignStaffToProjects() {
        for (Projects project : projects) {
            for (Staff staff : staffList) {
                for (String qualification : staff.getOpenQualifications()) {
                    if (project.isQualificationRequired(qualification)) {
                        if (project.occupyPosition(qualification, staff.getName())) {
                            staff.hire(qualification);
                        }
                    }
                }
            }
        }
    }
    public void output() {
        for (Projects project : projects) {
            System.out.println("Project: " + project.getName());
            if (project.getOccupiedPositions().isEmpty()) {
                System.out.println("  No staff assigned yet.");
            } else {
                for (Map.Entry<String, String> entry : project.getOccupiedPositions().entrySet()) {
                    System.out.println("  - Position: " + entry.getKey() + ", Employee: " + entry.getValue());
                }
            }
        }
    }
}