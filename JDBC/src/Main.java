import java.sql.*;

public class Main {
    // Database connection details
    public static final String DB_URL = "jdbc:mysql://localhost/company";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "mysql";


    public static void main(String[] args) {
        // Database connection objects
        Connection connect = null;

        // SQL query to retrieve data from the database
        String sql = "SELECT * FROM employees";
        try {
            // Establish a database connection
            connect = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            // Create a statement for executing SQL queries

            Statement statement = connect.createStatement();

            // Execute the SQL query and get the result set
            ResultSet resultSet = statement.executeQuery(sql);

            // Iterate through the result set and print employee details
            while (resultSet.next()){
                System.out.println("ID : "+resultSet.getInt("employee_id")+
                        " NAME : "+resultSet.getString("employee_name")+
                        " POSITION : "+resultSet.getString("employee_position")+
                        " SALARY : "+resultSet.getInt("employee_salary"));
            }

            // Close the database connection
            connect.close();

        } catch (SQLException e) {
            // Handle any SQLException that might occur during database operations
            // and throw a RuntimeException with the original exception for debugging purposes
            System.out.println(e.getMessage());
        }
    }
}
