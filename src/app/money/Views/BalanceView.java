package app.money.Views;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

public class BalanceView extends JPanel implements View {

  private static final long serialVersionUID = 1L;

  private JLabel balanceLabel;
  private JLabel balanceValue;
  private JLabel addBalanceLabel;
  private JTextField addBalanceField;
  private JButton cancelBtn;
  private JButton addBtn;

  public BalanceView() {
    this.setLayout(new GridLayout(3, 2, 5, 7));
    this.setBorder(new EmptyBorder(10, 10, 10, 10));
    this.setName("Balance");
    this.initComponents();
  }

  private void initComponents() {
    balanceLabel = new JLabel("Balance");
    balanceValue = new JLabel();
    addBalanceLabel = new JLabel("Add balance");
    addBalanceField = new JTextField();
    cancelBtn = new JButton("Cancel");
    addBtn = new JButton("Add balance");

    // Colors
    addBtn.setBackground(MaterialColors.INDIGO_700);
    addBtn.setForeground(MaterialColors.WHITE);
    MaterialUIMovement.add(addBtn, MaterialColors.INDIGO_900, 5, 1000 / 60);
    MaterialUIMovement.add(cancelBtn, MaterialColors.GRAY_400, 5, 1000 / 60);

    this.add(balanceLabel);
    this.add(balanceValue);
    this.add(addBalanceLabel);
    this.add(addBalanceField);
    this.add(cancelBtn);
    this.add(addBtn);
  }

  public JTextField getAddBalanceField() {
    return addBalanceField;
  }

  public JButton getCancelBtn() {
    return cancelBtn;
  }

  public JButton getAddBtn() {
    return addBtn;
  }

  public void setBalanceValue(double amount) {
    this.balanceValue.setText("Php " + amount);
  }

}
