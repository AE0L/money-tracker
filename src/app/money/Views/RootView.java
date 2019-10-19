package app.money.Views;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import mdlaf.MaterialLookAndFeel;

public class RootView {

  private JFrame frame;
  private JPanel root;

  public RootView() {
    // Google Material Theme
    try {
      UIManager.setLookAndFeel(new MaterialLookAndFeel());
    } catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    } finally {
      frame = new JFrame();
      root = new JPanel(new GridLayout(1, 1));

      frame.setTitle("MoneyTracker");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(root);
      root.setPreferredSize(new Dimension(300, 200));
    }
  }

  public JFrame getFrame() {
    return this.frame;
  }

  public JPanel getRoot() {
    return this.root;
  }

}
