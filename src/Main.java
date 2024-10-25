import java.util.*;

//import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/plik.txt";

        FileManagement fileManagement = new FileManagement(filePath);

        String projects = fileManagement.getProjects();
        System.out.println("Projects:\n" +projects);

        String staffList = fileManagement.getStaff();
        System.out.println("\nStaff:\n" +staffList);
        System.out.println("lol");

    }
}