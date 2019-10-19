package app.money.Controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import app.money.Models.HistoryModel;
import app.money.Models.Model;
import app.money.Models.UserModel;
import app.money.Views.HistoryView;
import app.money.Views.IndexView;
import app.money.Views.View;

/**
 * Controller for the History page.
 *
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl
 */
public class History implements ActionListener, Controller {

  private HistoryModel model;
  private HistoryView view;
  private JFrame frame;

  public History(Model model, View view, JFrame frame) {
    this.model = (HistoryModel) model;
    this.view = (HistoryView) view;
    this.frame = frame;

    initView();
  }

  private void initView() {
    frame.setTitle("History | Money Tracker");
    view.setTable(model.getTableModel());
    view.getReturnBtn().addActionListener(this);
  }

  @Override
  public View getView() {
    return this.view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    frame.getContentPane().removeAll();

    try {
      frame.getContentPane()
          .add((JPanel) new Index(new UserModel(), new IndexView(), frame).getView());
    } catch (Exception ignore) {
    }

    frame.getContentPane().setPreferredSize(new Dimension(300, 200));
    frame.pack();
    frame.setLocationRelativeTo(null);
  }

}
