package EasyShopperMart;

import EasyShopperMart.Panels.PanelWelcome;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit {

    public static JButton exitButton() {
        JButton exit = new JButton("EXIT");
        exit.addActionListener(doExit);
        return exit;
    }

    private static ActionListener doExit = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            PanelWelcome.showPanelWelcome();
        }
    };


}
