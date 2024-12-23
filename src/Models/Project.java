package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Project {
    private String name;
    private ArrayList<String> requiredQualifications = new ArrayList<>();
    private Map<String, ArrayList<String>> occupiedPositions = new HashMap<>();

    public Project(String name, String[] qualifications) {
        this.name = name;
        requiredQualifications.addAll(Arrays.asList(qualifications));
    }

    public void occupyPosition(String qualification, Employee staff) {
        if (isQualificationRequired(qualification) && staff.hire(qualification)) {
            occupiedPositions.computeIfAbsent(qualification, k -> new ArrayList<>()).add(staff.getName());
            requiredQualifications.remove(qualification);
        }
    }

    public boolean isQualificationRequired(String qualification) {
        return requiredQualifications.contains(qualification);
    }

    public int getOccupiedCount() {
        return occupiedPositions.size();
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getRequiredQualifications() {
        return requiredQualifications;
    }

    public Map<String, ArrayList<String>> getOccupiedPositions() {
        return occupiedPositions;
    }
}