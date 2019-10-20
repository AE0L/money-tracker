package app.money.Controller;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import app.money.Models.Database;
import app.money.Models.UserModel;
import app.money.Views.IndexView;
import app.money.Views.RootView;

/**
 * Initializes the program and redirects to the index (Home) page.
 *
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl
 */
public class Root {

  private RootView view;
  private Index index;

  public Root(RootView view) {
    this.view = view;

    try {
      Database.prepareDatabase("MoneyDB.Accdb");
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      this.index = new Index(new UserModel(), new IndexView(), view.getFrame());
    } catch (Exception ignore) {
      // Show register form if no user was found.
      showRegisterForm();
    } finally {
      initView();
    }
  }

  private void showRegisterForm() {
    String name = "";

    do {
      name = (String) JOptionPane.showInputDialog(view.getFrame(), "Enter name: ", "New User",
          JOptionPane.PLAIN_MESSAGE, null, null, null);

      if (null == name) {
        System.exit(0);
      } else if ("".equals(name)) {
        JOptionPane.showMessageDialog(view.getFrame(), "Please enter a name!", "Invalid name",
            JOptionPane.WARNING_MESSAGE);
      }

    } while ("".equals(name));

    this.index = new Index(UserModel.registerUser(name), new IndexView(), view.getFrame());
  }

  private void initView() {
    view.getRoot().add((JPanel) index.getView());
  }

  public void start() {
    view.getFrame().pack();
    view.getFrame().setLocationRelativeTo(null);
    view.getFrame().setVisible(true);
  }

}
