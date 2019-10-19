package app.money.Controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import app.money.Models.HistoryModel;
import app.money.Models.UserModel;
import app.money.Views.BalanceView;
import app.money.Views.HistoryView;
import app.money.Views.IndexView;
import app.money.Views.SpendView;
import app.money.Views.View;

/**
 * Controller for the Index (Home) page.
 *
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl
 */
public class Index implements ActionListener, Controller {

  private UserModel model;
  private IndexView view;
  private JFrame frame;

  public Index(UserModel model, IndexView view, JFrame frame) {
    this.model = model;
    this.view = view;
    this.frame = frame;

    initView();
  }

  private void initView() {
    frame.setTitle("Main Menu | Money Tracker");

    view.getWelcome().setText("Welcome back " + model.getName() + "!");

    view.getSpendBtn().addActionListener(this);
    view.getBalanceBtn().addActionListener(this);
    view.getHistoryBtn().addActionListener(this);
  }

  @Override
  public View getView() {
    return this.view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand();
    Controller page = null;

    // Clear frame
    frame.getContentPane().removeAll();

    // Then add the correct page (JPanel)
    if ("Spend".equals(action)) {
      page = new Spend(model, new SpendView(), frame);

      frame.getContentPane().add((JPanel) page.getView());
      frame.getContentPane().setPreferredSize(new Dimension(400, 275));
    } else if ("Balance".equals(action)) {
      page = new Balance(model, new BalanceView(), frame);

      frame.getContentPane().add((JPanel) page.getView());
      frame.getContentPane().setPreferredSize(new Dimension(400, 125));
    } else if ("History".equals(action)) {
      page = new History(new HistoryModel(), new HistoryView(), frame);

      frame.getContentPane().add((JPanel) page.getView());
      frame.getContentPane().setPreferredSize(new Dimension(750, 500));
    }

    frame.pack();
    frame.setLocationRelativeTo(null);
  }

}
