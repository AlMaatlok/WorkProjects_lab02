import java.util.*;
public class Projects {
   private String name;
   private ArrayList<String> requiredQualifications = new ArrayList<>();

   private Map<String, ArrayList> occupiedPositions = new HashMap<>();

   public Projects(String name, String[] qualifications){
       this.name = name;
       for(String qualification : qualifications)
           requiredQualifications.add(qualification);

   }
   public void occupyPosition(String qualification, Staff staff){
      if(isQualificationRequired(qualification) && staff.hire(qualification,staff)){
          if(occupiedPositions.containsKey(qualification)){
              occupiedPositions.get(qualification).add(staff.getName());
          }
          else{
              occupiedPositions.put(qualification, new ArrayList());
              occupiedPositions.get(qualification).add(staff.getName());
          }
          requiredQualifications.remove(qualification);
      }
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
   public float getOccupancyPercantage(){
       return (float) getOccupiedCount() /requiredQualifications.size();
   }
   public String getName(){
       return name;
   }
   public ArrayList<String> getRequiredQualifications(){
       return requiredQualifications;
   }
   public Map<String, ArrayList> getOccupiedPositions(){
       return occupiedPositions;
   }
}