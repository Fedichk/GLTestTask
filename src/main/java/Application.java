public class Application {

    public static void main(String[] args) {

        DataBaseCreator DBCreator = new DataBaseCreator();
        DBCreator.createEmployeeTable();
        DBCreator.createProjectTable();
        DBCreator.createWorkPeriodTable();
        DBCreator.fillInEmployeeTable();
        DBCreator.fillInProjectTable();
        DBCreator.fillInWorkPeriodTable();

        DataBaseExecutor DBExecutor = new DataBaseExecutor();
        System.out.println("List of PMs:");
        DBExecutor.getPMS().forEach(projectManager -> System.out.println(projectManager.getName() +
                " " + projectManager.getContacts()));
        System.out.println();
        System.out.println("List of QAs:");
        DBExecutor.getQAS().forEach(testEngineer -> System.out.println(testEngineer.getName() +
                " " + testEngineer.getContacts()));
    }
}