import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;

public class FileManagement{
    private String filePath;
    public FileManagement(String filePath){
        this.filePath = filePath;
    }
    public boolean validateFile() {
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


                if (projectsSection) {
                    if (!line.matches("P\\d+:\\s+([a-zA-Z]+\\s*)+")) {
                        return false;
                    }
                } else if (staffSection) {
                    if (!line.matches("R\\d+:\\s+([a-zA-Z]+\\s*)+")) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public ArrayList<Projects> getProjects(){

        ArrayList<Projects> outputProjects = new ArrayList<>();
        String line;
        File inputFile = new File(this.filePath);

        try {
            Scanner fileScanner = new Scanner(inputFile);


            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine().toUpperCase();
                if (line.startsWith("P") && line.charAt(1) != 'R') {
                    String projectName = line.substring(0, line.indexOf(":"));
                    String[] projectDetails = line.split(": ")[1].split(" ");

                    Projects newProject = new Projects(projectName, projectDetails);
                    outputProjects.add(newProject);
                }
            }
        }

        catch(IOException e){
                e.printStackTrace();
            }

        return outputProjects;
    }
    public ArrayList<Staff> getStaff(){

        ArrayList<Staff> outputStaff = new ArrayList<>();
        String line;
        File inputFile = new File(this.filePath);

        try{
            Scanner fileScanner = new Scanner(inputFile);

            while(fileScanner.hasNextLine()){
                line = fileScanner.nextLine().toUpperCase();
                if(line.startsWith("R")){
                    String staffName = line.substring(0, line.indexOf(":"));
                    String[] staffQualifications = line.split(": ")[1].split(" ");

                    Staff newStaff = new Staff(staffName, staffQualifications);
                    outputStaff.add(newStaff);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return outputStaff;
    }
}