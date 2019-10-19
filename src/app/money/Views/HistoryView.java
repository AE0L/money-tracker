package app.money.Views;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

public class HistoryView extends JPanel implements View {

  private static final long serialVersionUID = 1L;

  private JScrollPane tableCont;
  private JTable table;
  private JButton returnBtn;
  private GridBagConstraints cons;

  public HistoryView() {
    this.setLayout(new GridBagLayout());
    this.setBorder(new EmptyBorder(7, 7, 7, 7));
    this.setName("History");

    initComponents();
  }

  private void initComponents() {
    cons = new GridBagConstraints();
    cons.weightx = 1;
    cons.weighty = 1;
    returnBtn = new JButton("Return");
  }

  public void setTable(DefaultTableModel model) {
    table = new JTable(model) {
      private static final long serialVersionUID = 1L;

      @Override
      public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
        Component c = super.prepareRenderer(renderer, row, col);

        if (!isRowSelected(row)) {
          int modelRow = convertRowIndexToModel(row);
          String type = (String) getModel().getValueAt(modelRow, 0);

          if ("Spend".equals(type))
            c.setBackground(new Color(255, 50, 50, 70));
          else
            c.setBackground(new Color(50, 255, 50, 70));
        }

        return c;
      }
    };

    tableCont = new JScrollPane(table);
    table.setFillsViewportHeight(true);

    // Colors
    returnBtn.setBackground(MaterialColors.INDIGO_700);
    returnBtn.setForeground(MaterialColors.WHITE);
    MaterialUIMovement.add(returnBtn, MaterialColors.INDIGO_900, 5, 1000 / 60);

    // Layout
    cons.gridy = 0;
    cons.gridx = 0;
    cons.anchor = GridBagConstraints.PAGE_START;
    cons.fill = GridBagConstraints.BOTH;
    this.add(tableCont, cons);

    cons.gridy = 1;
    cons.gridx = 0;
    cons.weighty = 0;
    cons.anchor = GridBagConstraints.PAGE_END;
    cons.fill = GridBagConstraints.HORIZONTAL;
    cons.insets = new Insets(10, 0, 0, 0);
    this.add(returnBtn, cons);
  }

  public JButton getReturnBtn() {
    return returnBtn;
  }

}
