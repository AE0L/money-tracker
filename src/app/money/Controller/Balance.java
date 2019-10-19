package app.money.Controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import app.money.Models.Model;
import app.money.Models.UserModel;
import app.money.Views.BalanceView;
import app.money.Views.IndexView;
import app.money.Views.View;

/**
 * Controller for the Balance page.
 *
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl
 */
public class Balance implements ActionListener, Controller {

  private UserModel model;
  private BalanceView view;
  private JFrame frame;

  public Balance(Model model, View view, JFrame frame) {
    this.model = (UserModel) model;
    this.view = (BalanceView) view;
    this.frame = frame;

    initView();
  }

  private void initView() {
    frame.setTitle("Balance | Money Tracker");
    view.setBalanceValue(model.getBalance());
    view.getCancelBtn().addActionListener(this);
    view.getAddBtn().addActionListener(this);
  }

  private void reset(double amount) {
    view.setBalanceValue(amount);
    view.getAddBalanceField().setText("");
  }

  @Override
  public View getView() {
    return this.view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand();

    if ("Cancel".equals(action)) {
      frame.getContentPane().removeAll();
      frame.getContentPane().add((JPanel) new Index(model, new IndexView(), frame).getView());
      frame.getContentPane().setPreferredSize(new Dimension(300, 200));
      frame.pack();
      frame.setLocationRelativeTo(null);
    } else if ("Add balance".equals(action)) {
      String text = view.getAddBalanceField().getText();

      if (!"".equals(text)) {
        double amount;

        try {
          amount = Double.parseDouble(view.getAddBalanceField().getText());

          if (amount > 0) {
            double balance = model.update("Balance", amount, null);

            JOptionPane.showMessageDialog(frame, "Balance recorded!\n\nNew balance: " + balance);
            reset(balance);
          } else {
            JOptionPane.showMessageDialog(frame, "Amount can't be negative!", "Invalid input",
                JOptionPane.WARNING_MESSAGE);
            reset(model.getBalance());
          }
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(frame, "Input contains invalid characters!",
              "Invalid input", JOptionPane.WARNING_MESSAGE);
          reset(model.getBalance());
        }
      } else {
        JOptionPane.showMessageDialog(frame, "Amount can't be empty!", "Invalid input",
            JOptionPane.WARNING_MESSAGE);
      }
    }
  }

}
