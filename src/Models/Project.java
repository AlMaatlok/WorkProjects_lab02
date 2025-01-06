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
        // Sprawdzamy, czy kwalifikacja jest wymagana i czy pracownik może zostać zatrudniony na danej roli
        if (isQualificationRequired(qualification) && staff.hire(qualification)) {
            occupiedPositions.computeIfAbsent(qualification, k -> new ArrayList<>()).add(staff.getName());
            // Usuwamy wymaganą kwalifikację z listy, gdy jest przypisana do projektu
            requiredQualifications.remove(qualification);
        }
    }

    public boolean isQualificationRequired(String qualification) {
        return requiredQualifications.contains(qualification);
    }

    public int getOccupiedCount() {
        return occupiedPositions.values().stream().mapToInt(ArrayList::size).sum(); // Liczymy całkowitą liczbę przypisanych pracowników
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getRequiredQualifications() {
        return new ArrayList<>(requiredQualifications); // Zwracamy kopię listy
    }

    public Map<String, ArrayList<String>> getOccupiedPositions() {
        return new HashMap<>(occupiedPositions); // Zwracamy kopię mapy
    }

    // Opcjonalna metoda do zwolnienia pracownika z pozycji w projekcie
    public void releasePosition(String qualification, Employee staff) {
        if (occupiedPositions.containsKey(qualification)) {
            ArrayList<String> staffList = occupiedPositions.get(qualification);
            staffList.remove(staff.getName());
            if (staffList.isEmpty()) {
                occupiedPositions.remove(qualification);
            }
            // Ponownie dodajemy kwalifikację do listy wymaganych
            requiredQualifications.add(qualification);
        }
    }

    // Sprawdzamy, czy dany pracownik jest przypisany do projektu na tej roli
    public boolean isEmployeeAssignedToPosition(String qualification, Employee staff) {
        return occupiedPositions.containsKey(qualification) && occupiedPositions.get(qualification).contains(staff.getName());
    }

    // Nowa funkcja: sprawdza, czy pozycja jest już zajęta
    public boolean isPositionOccupied(String qualification) {
        return occupiedPositions.containsKey(qualification) && !occupiedPositions.get(qualification).isEmpty();
    }
}
