package app.money.Models;

import java.sql.ResultSet;

/**
 * Represents the User data.
 *
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl
 */
public class User {

  private String name;
  private double balance;

  public User() throws Exception {
    this.update();
  }

  public void update() throws Exception {
    try (Database db = Database.getInstance();
        ResultSet res = db.query("SELECT Name, Balance FROM UserData")) {

      res.next();

      this.name = res.getString("Name");
      this.balance = res.getDouble("Balance");
    } catch (Exception e) {
      throw new Exception();
    }
  }

  public String getName() {
    return name;
  }

  public double getBalance() {
    return balance;
  }

}
