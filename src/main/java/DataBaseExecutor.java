import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseExecutor {

    DataBaseConnector DBConnector = new DataBaseConnector();

    public List<Employee> getPMS() {

        String getPMS = "SELECT name, contacts "
                + "FROM employee "
                + "WHERE id IN ( "
                + "SELECT employee_id "
                + "FROM work_period wp "
                + "INNER JOIN employee em ON (em.id = wp.employee_id) "
                + "WHERE role = 'PM' AND project_id IN ( "
                + "SELECT project_id "
                + "FROM work_period wp "
                + "INNER JOIN employee em ON (em.id = wp.employee_id) "
                + "WHERE em.role = 'DEVELOPER' "
                + "GROUP BY project_id "
                + "HAVING COUNT(project_id) = ( "
                + "SELECT MAX(counted) "
                + "FROM ( "
                + "SELECT COUNT(project_id) counted "
                + "FROM work_period wp "
                + "INNER JOIN employee em ON (em.id = wp.employee_id) "
                + "WHERE em.role = 'DEVELOPER' "
                + "GROUP BY project_id) AS counts)"
                + ") "
                + ");";

        return getEmployees(getPMS);
    }

    public List<Employee> getQAS() {

        String getQAS = "SELECT name, contacts "
                + "FROM employee "
                + "WHERE id IN ( "
                + "SELECT employee_id "
                + "FROM "
                + "(SELECT employee_id, COUNT(project_id) counted "
                + "FROM work_period wp "
                + "INNER JOIN employee em ON (em.id = wp.EMPLOYEE_ID) "
                + "WHERE em.ROLE = 'QA' "
                + "GROUP BY employee_id ORDER BY counted DESC) AS qas"
                + ")";

        return getEmployees(getQAS);
    }

    private List<Employee> getEmployees(String query) {

        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DBConnector.getDBConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setName(resultSet.getString("name"));
                employee.setContacts(resultSet.getString("contacts"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}