import java.util.ArrayList;
import java.util.Collections;


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
        newEmployment.output();

        return newEmployment;
    }

}
