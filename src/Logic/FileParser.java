package Logic;

import Models.Employee;
import Models.Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParser {
    private String filePath;

    public FileParser(String filePath) {
        this.filePath = filePath;
    }
    public int checkLines() throws FileNotFoundException {
        File inputFile = new File(this.filePath);
        Scanner fileScanner = new Scanner(inputFile);

        int numberOfLines = 0;

        while (fileScanner.hasNextLine()) {
            numberOfLines++;
        }
        return numberOfLines;
    }

    public ArrayList<Project> getProjects() throws FileNotFoundException {
        ArrayList<Project> outputProjects = new ArrayList<>();
        File inputFile = new File(this.filePath);
        Scanner fileScanner = new Scanner(inputFile);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().toUpperCase();
            if (line.startsWith("P") && line.charAt(1) != 'R') {
                String projectName = line.substring(0, line.indexOf(":"));
                String[] projectDetails = line.split(": ")[1].split(" ");

                Project newProject = new Project(projectName, projectDetails);
                outputProjects.add(newProject);
            }
        }

        return outputProjects;
    }

    public ArrayList<Employee> getStaff() throws FileNotFoundException {
        ArrayList<Employee> outputStaff = new ArrayList<>();
        File inputFile = new File(this.filePath);
        Scanner fileScanner = new Scanner(inputFile);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().toUpperCase();
            if (line.startsWith("R")) {
                String staffName = line.substring(0, line.indexOf(":"));
                String[] staffQualifications = line.split(": ")[1].split(" ");

                Employee newStaff = new Employee(staffName, staffQualifications);
                outputStaff.add(newStaff);
            }
        }

        return outputStaff;
    }
}