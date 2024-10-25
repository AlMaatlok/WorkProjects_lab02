import java.util.List;

public class Employment {
    private List<Projects> projects;
    private List<Staff> staff;

    public Employment(List<Projects> projects, List<Staff> staff) {
        this.projects = projects;
        this.staff = staff;
    }

    public void assignStaffToProjects() {
        for (Projects project : projects) {
            for (String requiredQualification : project.getRequiredQualifications()) {
                for (Staff employee : staff) {

                    if (employee.getQualifications().containsKey(requiredQualification) &&
                            employee.getQualifications().get(requiredQualification) > 0) {


                        if (project.occupyPosition(requiredQualification, employee.getName())) {

                            employee.hire(requiredQualification);
                            System.out.println(employee.getName() + " przypisany do projektu " + project.getName() + " z kwalifikacją " + requiredQualification);

                            break;
                        }
                    }
                }
            }
        }
    }
}