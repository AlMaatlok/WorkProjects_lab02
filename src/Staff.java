import java.util.*;

public class Staff {
    private ArrayList<String> qualifications;
    private String name;
    private int NumOfAssignedProjects;
    private String assignedProjects;
    private String projectThatStaffWorksOn;
    public static enum SpecialQualifications{
        QM, PM
    }

    public Staff(String name, String[] givenQualifications){
        this.name = name;
        this.qualifications = new ArrayList<>();

        for(String qualification : givenQualifications)
            qualifications.add(qualification);

        NumOfAssignedProjects = 0;
    }

    public boolean hire(String Q, Staff staff){
        boolean isSpecial = isSpecialQualification(Q);

        if (isSpecial && staff.getNumOfAssignedProjects() < 2) {
            staff.setNumOfAssignedProjects(staff.getNumOfAssignedProjects()+1);
            return true;
        }

        else if (!isSpecial && staff.getNumOfAssignedProjects() == 0) {
            staff.setNumOfAssignedProjects(staff.getNumOfAssignedProjects()+1);
            return true;
        }
        return false;
    }

    public static boolean isSpecialQualification(String name){
        for(SpecialQualifications  q : SpecialQualifications.values()){
            if(name.equals(q.name()))
                return true;
        }
        return false;
    }
    public void setNumOfAssignedProjects(int numOfAssignedProjects){
        NumOfAssignedProjects = numOfAssignedProjects;
    }
    public ArrayList<String> getOpenQualifications(){
        return new ArrayList<>(qualifications);
    }
    public String getName(){
        return this.name;
    }
    public ArrayList<String> getQualifications(){
        return this.qualifications;
    }

    public int getNumOfAssignedProjects() {
        return NumOfAssignedProjects;
    }
}
