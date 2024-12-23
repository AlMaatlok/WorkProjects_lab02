import java.util.*;

public class BestSolution {
    public static Employment generateNewArrangement(ArrayList<Projects> projects, ArrayList<Staff> staffList) {

        ArrayList<Projects> copiedProjects = new ArrayList<>();
        for (Projects project : projects) {
            Projects newProject = new Projects(project.getName(),
                    project.getRequiredQualifications().toArray(new String[0]));
            copiedProjects.add(newProject);
        }

        ArrayList<Projects> newProjectArrangement = new ArrayList<>(copiedProjects);
        Collections.shuffle(newProjectArrangement);

        ArrayList<Staff> copiedStaff = new ArrayList<>();
        for (Staff staff : staffList) {
            copiedStaff.add(new Staff(staff.getName(),
                    staff.getQualifications().toArray(new String[0])));
        }

        ArrayList<Staff> newStaffArrangement = new ArrayList<>(copiedStaff);
        Collections.shuffle(newStaffArrangement);

        Employment newEmployment = new Employment(newProjectArrangement, newStaffArrangement);
        newEmployment.assignStaffToProjects();
        return newEmployment;
    }

    public static Employment optimizeAssignment(ArrayList<Projects> projects, ArrayList<Staff> staffList) {
        Employment bestSolution = generateNewArrangement(projects, staffList);
        int bestScore = bestSolution.calculateEfficiency();

        for (int i = 0; i < 1000; i++) {
            Employment newSolution = generateNewArrangement(projects, staffList);
            int newScore = newSolution.calculateEfficiency();

            if (newScore > bestScore) {
                bestSolution = newSolution;
                bestScore = newScore;
            }
        }

        return bestSolution;
    }
}