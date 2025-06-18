package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
  public static void main(String[] args) {
    // NOTE: Connection and Statement are AutoCloseable.
    // Don't forget to close them both in order to avoid leaks.
    try (
        // create a database connection
        Connection connection = DriverManager.getConnection("jdbc:sqlite:tasks.db");
        Statement statement = connection.createStatement();) {

      statement.setQueryTimeout(30); // set timeout to 30 sec.
      statement.executeUpdate("drop table if exists tasks");
      statement.executeUpdate(
          "create table tasks (id integer, title string, description string, status string, due_date_time string)");
      statement.executeUpdate("insert into tasks values(1, 'clean dishes', 'with soap and water', 'to do','' )");
      ResultSet rs = statement.executeQuery("select * from tasks");
      while (rs.next()) {
        // read the result set
        System.out.println("name = " + rs.getString("title"));
        System.out.println("id = " + rs.getInt("status"));
      }
    } catch (SQLException e) {
      // if the error message is "out of memory",
      // it probably means no database file is found
      e.printStackTrace(System.err);
    }
  }
}