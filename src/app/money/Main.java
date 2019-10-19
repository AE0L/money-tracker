package app.money;

import app.money.Controller.Root;
import app.money.Views.RootView;

public class Main {

  public static void main(String[] args) {
    // Root app = new Root();
    //
    // app.render();

    Root app = new Root(new RootView());
    app.start();
  }

}
