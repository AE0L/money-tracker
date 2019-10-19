package app.money.Views;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

public class IndexView extends JPanel implements View {

  private static final long serialVersionUID = 1L;

  private JButton spendBtn;
  private JButton balanceBtn;
  private JButton historyBtn;
  private JLabel welcome;

  public IndexView() {
    this.setLayout(new GridLayout(4, 1, 0, 10));
    this.setBorder(new EmptyBorder(10, 10, 10, 10));
    this.setName("Index");
    this.initComponents();
  }

  private void initComponents() {
    welcome = new JLabel();
    spendBtn = new JButton("Spend");
    balanceBtn = new JButton("Balance");
    historyBtn = new JButton("History");

    // Colors
    spendBtn.setBackground(MaterialColors.INDIGO_700);
    spendBtn.setForeground(MaterialColors.WHITE);

    balanceBtn.setBackground(MaterialColors.INDIGO_700);
    balanceBtn.setForeground(MaterialColors.WHITE);

    historyBtn.setBackground(MaterialColors.INDIGO_700);
    historyBtn.setForeground(MaterialColors.WHITE);

    // Hover
    MaterialUIMovement.add(spendBtn, MaterialColors.INDIGO_900, 5, 1000 / 60);
    MaterialUIMovement.add(balanceBtn, MaterialColors.INDIGO_900, 5, 1000 / 60);
    MaterialUIMovement.add(historyBtn, MaterialColors.INDIGO_900, 5, 1000 / 60);

    this.add(welcome);
    this.add(spendBtn);
    this.add(balanceBtn);
    this.add(historyBtn);
  }

  public JButton getSpendBtn() {
    return spendBtn;
  }

  public JButton getBalanceBtn() {
    return balanceBtn;
  }

  public JButton getHistoryBtn() {
    return historyBtn;
  }

  public JLabel getWelcome() {
    return welcome;
  }

}
