import java.util.*;

public class Employment {
    private ArrayList<Projects> projects;
    private ArrayList<Staff> employees;

    public Employment(ArrayList<Projects> projects, ArrayList<Staff> employees) {
        this.projects = projects;
        this.employees = employees;
    }

    public void EmployeeToProject() {
        for (Projects project : projects) {
            for (String required : project.getRequiredQualifications()) {
                boolean positionOccupied = false;

                for (Staff employee : employees) {
                    if (employee.getOpenQualifications().contains(required)) {
                        if (project.occupyPosition(required, employee.getName())) {
                            if (employee.hire(required)) {
                                positionOccupied = true;
                                break;
                            }
                        }
                    }
                }

                if (!positionOccupied) {
                    for (Staff employee : employees) {
                        if (Staff.isSpecialQualification(required) && employee.getOpenQualifications().contains(required)) {
                            if (project.occupyPosition(required, employee.getName())) {
                                if (employee.hire(required)) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void output() {
        for (Projects project : projects) {
            System.out.println("Projekt: " + project.getName());
            System.out.println("Obsadzone stanowiska: " + project.getOccupiedPositions());
            System.out.println("Liczba wolnych miejsc: " + project.getVacantCount());
            System.out.println();
        }
    }
}