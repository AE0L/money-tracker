package app.money.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database class for querying and updating.
 *
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl
 */
public class Database implements AutoCloseable {

  public static Database INSTANCE = null;
  private Connection con;
  private Statement stm;
  private ResultSet res;

  private Database(String name) throws ClassNotFoundException, SQLException {
    // Register UCanAccess Driver
    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    con = DriverManager.getConnection("jdbc:ucanaccess://" + name);
  }

  public ResultSet query(String query) {
    try {
      stm = con.createStatement();
      res = stm.executeQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    return res;
  }

  public void update(String query) {
    try {
      stm = con.createStatement();
      stm.executeUpdate(query);
    } catch (SQLException e) {
      e.printStackTrace();
      return;
    }
  }

  public static Database getInstance() {
    return INSTANCE;
  }

  public static void prepareDatabase(String name) throws Exception {
    INSTANCE = new Database(name);
  }

  /* Compatibility with try-with-resources */
  @Override
  public void close() throws Exception {
    try {
      if (null != stm)
        stm.close();
      if (null != res)
        res.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
