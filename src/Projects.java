import java.util.*;
public class Projects {
   private String name;
   private ArrayList<String> requiredQualifications = new ArrayList<>();

   private ArrayList<String> occupiedPositions = new ArrayList<>();

   public Projects(String name, String[] qualifications){
       this.name = name;
       for(String qualification : qualifications)
           requiredQualifications.add(qualification);

   }
   public boolean occupyPosition(String qualification, String NameOfEmployee){
      if(isQualificationRequired(qualification) && !occupiedPositions.contains(NameOfEmployee)){
          occupiedPositions.add(NameOfEmployee);
          return true;
      }
      return false;
   }

   public boolean isQualificationRequired(String qualification){
       return requiredQualifications.contains(qualification);
   }
   public int getOccupiedCount(){
       return occupiedPositions.size();
   }
   public int getVacantCount(){
       return requiredQualifications.size()-getOccupiedCount();
   }
  /* public float getOccupancyPercantage(){
       return (float) getOccupiedCount() /requiredQualifications.size();
   }*/
   public String getName(){
       return name;
   }
   public ArrayList<String> getRequiredQualifications(){
       return requiredQualifications;
   }
   public ArrayList<String> getOccupiedPositions(){
       return occupiedPositions;
   }


}
