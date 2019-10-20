package app.money.Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Access UserData table in the database.
 *
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl
 */
public class UserModel implements Model {

  private User user;
  private HistoryModel history;

  public UserModel() throws Exception {
    user = new User();
    history = new HistoryModel();
  }

  public String getName() {
    return user.getName();
  }

  public double getBalance() {
    return user.getBalance();
  }

  public double update(String type, double amount, ArrayList<String> categories) {
    double newAmount = user.getBalance();

    if ("Spend".equals(type) && newAmount > 0) {
      newAmount -= amount;
    } else if ("Balance".equals(type)) {
      newAmount += amount;
    }

    try (Database db = Database.getInstance()) {
      db.update("UPDATE UserData SET Balance=" + newAmount + ", lastModified=" + getCurrentDate());
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      user.update();
    } catch (Exception e) {
      e.printStackTrace();
    }

    history.update(type, amount, categories);

    return this.user.getBalance();
  }

  private static String getCurrentDate() {
    return "'" + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm").format(LocalDateTime.now()) + "'";
  }

  public static UserModel registerUser(String name) {
    try (Database db = Database.getInstance()) {
      db.update("INSERT INTO UserData (Name, Balance, lastModified) VALUES ('" + name + "', 0, "
          + getCurrentDate() + ")");

      return new UserModel();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

}
