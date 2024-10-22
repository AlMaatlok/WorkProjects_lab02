import java.util.*;

public class Staff {
    private Map<String, Integer> qualifications;
    private String name;
    public static enum SpecialQualifications{
        QM, PM
    }

    public Staff(String name, String[] givenQualifications){
        this.name = name;
        this.qualifications = new HashMap<>();

        for(String qualifications : givenQualifications){

            if(this.isSpecialQualification(qualifications))
                this.qualifications.put(qualifications, 2);
            else
                this.qualifications.put(qualifications, 1);
        }

    }

    public boolean hire(String Qualification){
        if(!this.qualifications.containsKey(Qualification))
            return false;

        if(this.qualifications.get(Qualification) <= 1)
            return false;

        this.qualifications.put(Qualification, this.qualifications.get(Qualification) - 1);
        return true;
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
        for(String qualification : this.qualifications.keySet()){
            if(this.qualifications.get(qualification) > 0)
                output.add(qualification);
        }
        return output;
    }

    public void leaveWork(String qualification){
        this.qualifications.put(qualification, this.qualifications.get(qualification ) + 1);
    }

    public String getName(){
        return this.name;
    }
    public Map<String, Integer> getQualifications(){
        return this.qualifications;
    }

}
