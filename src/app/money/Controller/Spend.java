package app.money.Controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import app.money.Models.UserModel;
import app.money.Views.IndexView;
import app.money.Views.SpendView;
import app.money.Views.View;

/**
 * Controller for the Spend Page.
 *
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl
 */
public class Spend implements ActionListener, Controller {

  private UserModel model;
  private SpendView view;
  private JFrame frame;

  public Spend(UserModel model, SpendView view, JFrame frame) {
    this.model = model;
    this.view = view;
    this.frame = frame;

    initView();
  }

  private void initView() {
    frame.setTitle("Spend | Money Tracker");

    view.setBalanceValue(model.getBalance());
    view.getCancelBtn().addActionListener(this);
    view.getSpendBtn().addActionListener(this);
  }

  private void reset(double amount) {
    view.setBalanceValue(amount);
    view.getSpendAmntField().setText("");

    for (JCheckBox checkbox : view.getCategChecks()) {
      checkbox.setSelected(false);
    }
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
      frame.getContentPane().add((JPanel) new Index(model, new IndexView(), this.frame).getView());
      frame.getContentPane().setPreferredSize(new Dimension(300, 200));
      frame.pack();
      frame.setLocationRelativeTo(null);
    } else if ("Spend".equals(action)) {
      if (model.getBalance() > 0) {
        String text = view.getSpendAmntField().getText();

        if (!"".equals(text)) {
          try {
            double amount = Double.parseDouble(text);

            if (amount > 0) {
              ArrayList<String> categories = new ArrayList<>();

              for (JCheckBox checkbox : view.getCategChecks()) {
                if (checkbox.isSelected()) {
                  categories.add(checkbox.getText());
                }
              }

              double balance = model.update("Spend", amount, categories);

              JOptionPane.showMessageDialog(frame, "Spend recorded!\n\nNew balance: " + balance,
                  "Spend successful!", JOptionPane.INFORMATION_MESSAGE);
              reset(balance);
            } else {
              JOptionPane.showMessageDialog(frame, "Amount can't be negative!", "Invalid Input",
                  JOptionPane.WARNING_MESSAGE);
              reset(model.getBalance());
            }
          } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Input contains invalid characters!",
                "Invalid Input", JOptionPane.WARNING_MESSAGE);
            reset(model.getBalance());
          }
        } else {
          JOptionPane.showMessageDialog(frame, "Amount can't be empty!", "Invalid Input",
              JOptionPane.WARNING_MESSAGE);
        }
      } else {
        JOptionPane.showMessageDialog(frame, "You don't have enough balance!",
            "Insufficient Balance", JOptionPane.WARNING_MESSAGE);
        reset(0);
      }
    }
  }

}
