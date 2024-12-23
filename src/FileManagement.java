import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class FileManagement {
    private String filePath;

    public FileManagement(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Projects> getProjects() throws FileNotFoundException {
        ArrayList<Projects> outputProjects = new ArrayList<>();
        File inputFile = new File(this.filePath);
        Scanner fileScanner = new Scanner(inputFile);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().toUpperCase();
            if (line.startsWith("P") && line.charAt(1) != 'R') {
                String projectName = line.substring(0, line.indexOf(":"));
                String[] projectDetails = line.split(": ")[1].split(" ");

                Projects newProject = new Projects(projectName, projectDetails);
                outputProjects.add(newProject);
            }
        }

        return outputProjects;
    }

    public ArrayList<Staff> getStaff() throws FileNotFoundException {
        ArrayList<Staff> outputStaff = new ArrayList<>();
        File inputFile = new File(this.filePath);
        Scanner fileScanner = new Scanner(inputFile);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().toUpperCase();
            if (line.startsWith("R")) {
                String staffName = line.substring(0, line.indexOf(":"));
                String[] staffQualifications = line.split(": ")[1].split(" ");

                Staff newStaff = new Staff(staffName, staffQualifications);
                outputStaff.add(newStaff);
            }
        }

        return outputStaff;
    }
}