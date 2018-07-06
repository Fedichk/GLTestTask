public class Application {

    public static void main(String[] args) {

        DataBaseCreator DBCreator = new DataBaseCreator();
        DBCreator.createEmployeeTable();
        DBCreator.createProjectTable();
        DBCreator.createWorkPeriodTable();
        DBCreator.fillInEmployeeTable();
        DBCreator.fillInProjectTable();
        DBCreator.fillInWorkPeriodTable();

    }
}