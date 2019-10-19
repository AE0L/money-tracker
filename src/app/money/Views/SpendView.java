package app.money.Views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

public class SpendView extends JPanel implements View {

  private static final long serialVersionUID = 1L;

  private final String[] categories = {"Food", "Transportation", "Entertainment", "Health",
      "Clothing", "Utilities", "Personal", "Education"};

  private JPanel categPanel;
  private JCheckBox[] categChecks;
  private JLabel balanceLabel;
  private JLabel balanceValue;
  private JLabel spendAmntLabel;
  private JLabel spendTypeLabel;
  private JTextField spendAmntField;
  private JPanel btnCont;
  private JButton spendBtn;
  private JButton cancelBtn;
  private GridBagConstraints cons;

  public SpendView() {
    this.setLayout(new GridBagLayout());
    this.setBorder(new EmptyBorder(10, 10, 10, 10));
    this.setName("Spend");
    this.initComponents();
  }

  private void initComponents() {
    cons = new GridBagConstraints();
    balanceLabel = new JLabel("Balance");
    balanceValue = new JLabel();
    spendAmntLabel = new JLabel("Amount");
    spendTypeLabel = new JLabel("Category");
    spendAmntField = new JTextField();
    btnCont = new JPanel(new GridLayout(1, 2, 10, 0));
    spendBtn = new JButton("Spend");
    cancelBtn = new JButton("Cancel");
    categPanel = new JPanel(new GridLayout(this.categories.length / 2, 2));
    categChecks = new JCheckBox[categories.length];

    for (int i = 0; i < this.categories.length; i++) {
      categChecks[i] = new JCheckBox(categories[i], false);
      categPanel.add(categChecks[i]);
    }

    // Colors
    spendBtn.setBackground(MaterialColors.INDIGO_700);
    spendBtn.setForeground(MaterialColors.WHITE);

    // Hover
    MaterialUIMovement.add(spendBtn, MaterialColors.INDIGO_900, 5, 1000 / 60);
    MaterialUIMovement.add(cancelBtn, MaterialColors.GRAY_400, 5, 1000 / 60);

    btnCont.add(cancelBtn);
    btnCont.add(spendBtn);

    // Layout
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 1.0;
    cons.weighty = 1.0;
    cons.fill = GridBagConstraints.HORIZONTAL;

    this.add(balanceLabel, setGrid(0, 0, GridBagConstraints.LINE_START));
    this.add(balanceValue, setGrid(0, 1, GridBagConstraints.LINE_START));
    this.add(spendAmntLabel, setGrid(1, 0, GridBagConstraints.FIRST_LINE_START));
    this.add(spendAmntField, setGrid(1, 1, GridBagConstraints.LINE_START));
    this.add(spendTypeLabel, setGrid(2, 0, GridBagConstraints.FIRST_LINE_START));
    this.add(categPanel, setGrid(2, 1, GridBagConstraints.FIRST_LINE_START));

    cons.gridwidth = 2;
    cons.weighty = 0;
    this.add(btnCont, setGrid(3, 0, GridBagConstraints.PAGE_END));
  }

  private GridBagConstraints setGrid(int row, int col, int anchor) {
    cons.gridx = col;
    cons.gridy = row;
    cons.anchor = anchor;

    return cons;
  }

  public JCheckBox[] getCategChecks() {
    return categChecks;
  }

  public JTextField getSpendAmntField() {
    return spendAmntField;
  }

  public JButton getSpendBtn() {
    return spendBtn;
  }

  public JButton getCancelBtn() {
    return cancelBtn;
  }

  public String getBalanceValue() {
    return balanceValue.getText();
  }

  public void setBalanceValue(double value) {
    balanceValue.setText("Php " + value);
  }

}
