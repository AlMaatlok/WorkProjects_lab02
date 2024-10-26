import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;

public class FileManagement{
    private String filePath;
    public FileManagement(String filePath){
        this.filePath = filePath;
    }
    private void validateFile() {

        File inputFile = new File(this.filePath);
        try{
            Scanner fileScanner = new Scanner(inputFile);
            String readLine;
            List<String> parsedLine = new ArrayList<>();

        } catch(FileNotFoundException e){
            System.out.println("Cannot open input file at path " + this.filePath);
        }
    }
    public ArrayList<Projects> getProjects() throws FileNotFoundException{

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

        catch(FileNotFoundException e){
                e.printStackTrace();
            }

        return outputProjects;
    }
    public ArrayList<Staff> getStaff() throws FileNotFoundException{

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
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return outputStaff;
    }
}