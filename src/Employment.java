import java.util.*;

public class Employment {
    private ArrayList<Projects> projects;
    private ArrayList<Staff> staffList;

    public Employment(ArrayList<Projects> projects, ArrayList<Staff> staffList) {
        this.projects = projects;
        this.staffList = staffList;
    }
    public void assignStaffToProjects() {
        for (Projects project : projects) {
            for (Staff staff : staffList) {
                for (String qualification : staff.getOpenQualifications()) {
                    if (project.isQualificationRequired(qualification)) {
                         project.occupyPosition(qualification, staff);
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
                for (Map.Entry<String, ArrayList> entry : project.getOccupiedPositions().entrySet()) {
                    System.out.println("  - Position: " + entry.getKey() + ", Employee: " + entry.getValue());
                }
            }
        }
    }
}
