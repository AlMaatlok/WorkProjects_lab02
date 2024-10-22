import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileManagement{
    private String filePath;
    public FileManagement(String filePath){
        this.filePath = filePath;
    }
    public boolean validateFile() {
        // Prosta walidacja pliku
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean projectsSection = false;
            boolean staffSection = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.equals("PROJECTS")) {
                    projectsSection = true;
                    staffSection = false;
                    continue;
                } else if (line.equals("STAFF")) {
                    staffSection = true;
                    projectsSection = false;
                    continue;
                }

                // Walidacja linii projektu lub pracownika
                if (projectsSection) {
                    if (!line.matches("P\\d+: (\\w+)( \\w+)*")) {
                        return false; // Błędny format projektu
                    }
                } else if (staffSection) {
                    if (!line.matches("R\\d+: (\\w+)( \\w+)*")) {
                        return false; // Błędny format pracownika
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<Projects> getProjects() {
        List<Projects> projects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean projectsSection = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.equals("PROJECTS")) {
                    projectsSection = true;
                    continue;
                } else if (line.equals("STAFF")) {
                    projectsSection = false;
                    continue;
                }

                if (projectsSection) {
                    String[] parts = line.split(":");
                    String projectName = parts[0].trim();
                    String[] qualifications = parts[1].trim().split(" ");
                    List<String> requiredQualifications = List.of(qualifications);
                    String[] qualificationsArray = requiredQualifications.toArray(new String[0]);
                    projects.add(new Projects(projectName, qualificationsArray));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projects;
    }
    public List<Staff> getStaff() {
        List<Staff> staffList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean staffSection = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.equals("STAFF")) {
                    staffSection = true;
                    continue;
                }

                if (staffSection) {
                    String[] parts = line.split(":");
                    String staffName = parts[0].trim();
                    String[] skillsArray = parts[1].trim().split(" ");
                    List<String> staffSkills = List.of(skillsArray);
                    staffList.add(new Staff(staffName, skillsArray));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return staffList;
    }
}