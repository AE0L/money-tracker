package app.money;

import app.money.Controller.Root;
import app.money.Views.RootView;

/**
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl
 */
public class Main {

  public static void main(String[] args) {
    Root app = new Root(new RootView());
    app.start();
  }

}
