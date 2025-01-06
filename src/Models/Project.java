package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Project {
    private String name;
    private ArrayList<String> requiredQualifications;
    private Map<String, ArrayList<String>> occupiedPositions;

    public Project(String name, String[] qualifications) {
        this.name = name;
        this.requiredQualifications = new ArrayList<>(Arrays.asList(qualifications));
        this.occupiedPositions = new HashMap<>();
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
        return occupiedPositions.values().stream().mapToInt(ArrayList::size).sum();
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getRequiredQualifications() {
        return new ArrayList<>(requiredQualifications);
    }

    public Map<String, ArrayList<String>> getOccupiedPositions() {
        return new HashMap<>(occupiedPositions);
    }

    public void releasePosition(String qualification, Employee staff) {
        if (occupiedPositions.containsKey(qualification)) {
            ArrayList<String> staffList = occupiedPositions.get(qualification);
            staffList.remove(staff.getName());
            if (staffList.isEmpty()) {
                occupiedPositions.remove(qualification);
            }
            requiredQualifications.add(qualification);
        }
    }

    public boolean isEmployeeAssignedToPosition(String qualification, Employee staff) {
        return occupiedPositions.containsKey(qualification) && occupiedPositions.get(qualification).contains(staff.getName());
    }

    public boolean isPositionOccupied(String qualification) {
        return occupiedPositions.containsKey(qualification) && !occupiedPositions.get(qualification).isEmpty();
    }
}
