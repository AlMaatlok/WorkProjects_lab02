import java.util.ArrayList;
import java.util.Arrays;

class Staff {
    private ArrayList<String> qualifications;
    private String name;
    private int numOfAssignedProjects;

    private enum SpecialQualifications {
        QA, PM
    }

    public Staff(String name, String[] givenQualifications) {
        this.name = name;
        this.qualifications = new ArrayList<>(Arrays.asList(givenQualifications));
        this.numOfAssignedProjects = 0;
    }

    public boolean hire(String qualification) {
        boolean isSpecial = isSpecialQualification(qualification);

        if (isSpecial && numOfAssignedProjects < 2) {
            numOfAssignedProjects++;
            return true;
        } else if (!isSpecial && numOfAssignedProjects == 0) {
            numOfAssignedProjects++;
            return true;
        }

        return false;
    }

    private static boolean isSpecialQualification(String qualification) {
        for (SpecialQualifications q : SpecialQualifications.values()) {
            if (q.name().equals(qualification)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getOpenQualifications() {
        return new ArrayList<>(qualifications);
    }
    public ArrayList<String> getQualifications(){
        return this.qualifications;
    }
}
