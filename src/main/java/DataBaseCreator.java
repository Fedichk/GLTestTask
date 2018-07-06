import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCreator {

    DataBaseConnector DBConnector = new DataBaseConnector();

    public void createEmployeeTable() {

        String createEmployeeTableSQL = "CREATE TABLE IF NOT EXISTS employee ("
                + "id INT NOT NULL PRIMARY KEY, "
                + "name VARCHAR (64) NOT NULL, "
                + "contacts VARCHAR (255) NOT NULL, "
                + "role VARCHAR (10) NOT NULL"
                + ")";

        try (Connection connection = DBConnector.getDBConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createEmployeeTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createProjectTable() {

        String createProjectTableSQL = "CREATE TABLE IF NOT EXISTS project ("
                + "id INT NOT NULL PRIMARY KEY, "
                + "title VARCHAR (255) NOT NULL, "
                + "description TEXT NOT NULL, "
                + "project_start_date DATE NOT NULL, "
                + "project_end_date DATE NOT NULL"
                + ")";

        try (Connection connection = DBConnector.getDBConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createProjectTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createWorkPeriodTable() {

        String createWorkPeriodTableSQL = "CREATE TABLE IF NOT EXISTS work_period ("
                + "id INT NOT NULL PRIMARY KEY, "
                + "employee_id INT, "
                + "project_id INT, "
                + "start_date_work_on_project DATE NOT NULL, "
                + "end_date_work_on_project DATE NOT NULL, "
                + "FOREIGN KEY (employee_id) REFERENCES employee (id), "
                + "FOREIGN KEY (project_id) REFERENCES project (id)"
                + ")";

        try (Connection connection = DBConnector.getDBConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createWorkPeriodTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillInEmployeeTable() {

        String fillInEmployeeTableSQL = "INSERT IGNORE INTO employee "
                + "VALUES "
                + "(1, 'Max', 'max@gmail.com', 'PM'), "
                + "(2, 'Anna', 'anna@gmail.com', 'QA'), "
                + "(3, 'Fedir', 'fedir@gmail.com', 'DEVELOPER'), "
                + "(4, 'Serg', 'serg@gmail.com', 'QA'), "
                + "(5, 'Alex', 'alex@gmail.com', 'DEVELOPER'),"
                + "(6, 'Evgen', 'evgen@gmail.com', 'PM'),"
                + "(7, 'Pavel', 'pavel@gmail.com', 'DEVELOPER'),"
                + "(8, 'Masha', 'masha@gmail.com', 'QA'),"
                + "(9, 'Oleg', 'oleg@gmail.com', 'PM'),"
                + "(10, 'Igor', 'igor@gmail.com', 'DEVELOPER'),"
                + "(11, 'Jack', 'jack@gmail.com', 'DEVELOPER');";

        try (Connection connection = DBConnector.getDBConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(fillInEmployeeTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillInProjectTable() {

        String fillInProjectTableSQL = "INSERT IGNORE INTO project "
                + "VALUES "
                + "(1, 'Car project', 'Car board systems', '2016-01-15', '2018-07-05'), "
                + "(2, 'Medical project', 'System for hospitals', '2017-03-15', '2018-05-07'), "
                + "(3, 'Insurance project', 'Insurance companies systems', '2017-02-12', '2018-07-04');";

        try (Connection connection = DBConnector.getDBConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(fillInProjectTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillInWorkPeriodTable() {

        String fillInWorkPeriodTableSQL = "INSERT IGNORE INTO work_period "
                + "VALUES "
                + "(1, '1', '1', '2016-01-15', '2018-07-05'), "
                + "(2, '2', '1', '2016-01-15', '2017-03-14'), "
                + "(3, '2', '2', '2017-03-15', '2018-05-07'), "
                + "(4, '3', '1', '2016-01-15', '2018-07-05'), "
                + "(5, '4', '1', '2016-01-15', '2018-07-05'), "
                + "(6, '5', '1', '2016-01-15', '2018-07-05'), "
                + "(7, '6', '2', '2017-03-15', '2018-05-07'), "
                + "(8, '7', '2', '2017-03-15', '2018-05-07'), "
                + "(9, '8', '1', '2016-01-15', '2018-02-11'), "
                + "(10, '8', '3', '2017-02-12', '2018-04-20'), "
                + "(11, '8', '2', '2018-04-21', '2018-05-07'), "
                + "(12, '9', '3', '2017-02-12', '2018-07-04'), "
                + "(13, '10', '3', '2017-02-12', '2018-07-04'), "
                + "(14, '11', '3', '2017-07-18', '2018-07-04'); ";

        try (Connection connection = DBConnector.getDBConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(fillInWorkPeriodTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}