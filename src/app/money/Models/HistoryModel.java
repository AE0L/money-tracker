package app.money.Models;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 * Access the History table in database.
 *
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl
 */
public class HistoryModel implements Model {

  private final String DATABASE = "MoneyDB.Accdb";

  public void update(String type, Object value, ArrayList<String> categories) {
    String details = "";
    String date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm").format(LocalDateTime.now());

    if (null != categories) {
      for (int i = 0; i < categories.size(); i++) {
        if (i != categories.size() - 1) {
          details = details.concat(categories.get(i) + ",");
        } else {
          details = details.concat(categories.get(i));
        }
      }
    }

    try (Database db = new Database(DATABASE)) {
      db.update("INSERT INTO History (ActivityType, ActivityValue, ActivityDetails, ActivityDate) "
          + "VALUES ('" + type + "','Php " + value.toString() + "','" + details + "','" + date
          + "')");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Object[][] getData() {
    ArrayList<Object[]> data = new ArrayList<>();

    try (Database db = new Database(DATABASE)) {
      Object[] record;

      ResultSet res = db.query("SELECT * FROM History");

      while (res.next()) {
        record = new Object[4];

        record[0] = res.getString("ActivityType");
        record[1] = res.getString("ActivityValue");
        record[2] = res.getString("ActivityDetails");
        record[3] = res.getString("ActivityDate");

        data.add(record);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    Object[][] finalData = new Object[data.size()][4];

    for (int i = 0; i < data.size(); i++) {
      for (int j = 0; j < 4; j++) {
        finalData[i][j] = data.get(i)[j];
      }
    }

    return finalData;
  }

  public Object[] getColumns() {
    Object[] columns = {"Type", "Value", "Details", "Date"};

    return columns;
  }

  public DefaultTableModel getTableModel() {
    return new DefaultTableModel(getData(), getColumns()) {
      private static final long serialVersionUID = 1L;

      @Override
      public Class<? extends Object> getColumnClass(int column) {
        return getValueAt(0, column).getClass();
      }

      @Override
      public boolean isCellEditable(int row, int col) {
        return false;
      }
    };
  }

}
