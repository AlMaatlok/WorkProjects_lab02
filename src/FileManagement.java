import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;

public class FileManagement{
    private String filePath;
    public FileManagement(String filePath){
        this.filePath = filePath;
        this.validateFile();
        this.getProjects();
        this.getStaff();

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
                    if (!line.matches("P\\d+: (\\w+)( \\w+)*")) {
                        return false;
                    }
                } else if (staffSection) {
                    if (!line.matches("R\\d+: (\\w+)( \\w+)*")) {
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
    public String getProjects() {
        StringBuilder outputProjects = new StringBuilder();
        String line;
        File inputFile = new File(this.filePath);
        try {
            Scanner fileScanner = new Scanner(inputFile);


            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine().toUpperCase();
                if (line.startsWith("P") && line.charAt(1) != 'R') {
                    String projectName = line.substring(0, line.indexOf(":"));
                    String[] projectDetails = line.split(": ")[1].split(" ");

                    outputProjects.append("Project: ").append(projectName).append("\n");
                    outputProjects.append("Details: ");
                    for (String detail : projectDetails) {
                        outputProjects.append(detail).append(" ");
                    }
                    outputProjects.append("\n\n");

                }
            }
        }

        catch(IOException e){
                e.printStackTrace();
            }

        return outputProjects.toString();
    }
    public String getStaff(){
        StringBuilder outputStaff = new StringBuilder();
        String line;
        File inputFile = new File(this.filePath);

        try{
            Scanner fileScanner = new Scanner(inputFile);

            while(fileScanner.hasNextLine()){
                line = fileScanner.nextLine().toUpperCase();
                if(line.startsWith("R")){
                    String staffName = line.substring(0, line.indexOf(":"));
                    String[] staffQualifications = line.split(": ")[1].split(" ");

                    outputStaff.append("Employee: ").append(staffName).append("\n");
                    outputStaff.append("Qualifications: ");
                    for(String detail : staffQualifications){
                        outputStaff.append(detail).append(" ");
                    }
                    outputStaff.append("\n\n");
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return outputStaff.toString();
    }
}