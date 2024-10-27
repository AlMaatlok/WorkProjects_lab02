import java.util.*;

public class Staff {
    private ArrayList<String> qualifications;
    private String name;
    private int NumOfAssignedProjects;
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

    public boolean hire(String Q){
        boolean isSpecial = isSpecialQualification(Q);
        // Umożliwienie zatrudnienia na podstawie kwalifikacji
        if (isSpecial) {
            if (NumOfAssignedProjects < 2) {
                NumOfAssignedProjects++;
                return true;
            }
        } else {  // Dla pozostałych kwalifikacji
            if (NumOfAssignedProjects == 0) {
                NumOfAssignedProjects++;
                return true;
            }
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

    public ArrayList<String> getOpenQualifications(){
        ArrayList<String> output = new ArrayList<>();
        for(String qualification : this.qualifications){
                output.add(qualification);
        }
        return output;
    }

    /*public void leaveWork(String qualification){
        this.qualifications.put(qualification, this.qualifications.get(qualification ) + 1);
    }
*/
    public String getName(){
        return this.name;
    }
    public ArrayList<String> getQualifications(){
        return this.qualifications;
    }
}
