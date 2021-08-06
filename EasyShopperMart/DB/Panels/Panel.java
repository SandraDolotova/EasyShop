package EasyShopperMart.Panels;

import java.sql.SQLException;

public class Panel {
    private static Panel panel;

   public static Panel getInstance() throws SQLException, ClassNotFoundException {
        if (panel == null) {
            panel = new Panel();
        }
        return panel;
    }
    public static void showWelcomePanel() {
        PanelWelcome.showPanelWelcome();
    }

}


