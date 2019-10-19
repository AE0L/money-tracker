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

  private Connection con;
  private Statement stm;
  private ResultSet res;

  public Database(String name) throws ClassNotFoundException, SQLException {
    // Register UCanAccess Driver
    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    con = DriverManager.getConnection("jdbc:ucanaccess://" + name);
    stm = con.createStatement();
  }

  public ResultSet query(String query) {
    try {
      res = stm.executeQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    return res;
  }

  public void update(String query) {
    try {
      stm.executeUpdate(query);
    } catch (SQLException e) {
      e.printStackTrace();
      return;
    }
  }

  /* Compatibility with try-with-resources */
  @Override
  public void close() throws Exception {
    try {
      if (null != con) {
        if (null != stm)
          stm.close();
        if (null != res)
          res.close();

        con.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
