package EasyShopperMart.Panels;

import EasyShopperMart.UserType;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelWelcome {

    public static void showPanelWelcome() {

        JPanel welcomePanel = new JPanel();

        JButton select = new JButton("SELECT");

        JComboBox<UserType> selectUserTypeDrop = new JComboBox<>();
        selectUserTypeDrop.setModel(new DefaultComboBoxModel<>(UserType.values()));
        selectUserTypeDrop.getSelectedObjects();

        welcomePanel.add(new JLabel("Welcome to the EasyShop!\n Choose your User type: "));
        welcomePanel.add(selectUserTypeDrop);
        welcomePanel.add(select);

        JFrame frame = new JFrame("EasyShop - Welcome");
        frame.pack();
        frame.setContentPane(welcomePanel);
        frame.setSize(500, 500);
        frame.setVisible(true);

        ActionListener actionListenerSelect = e -> {
            if (selectUserTypeDrop.getSelectedItem() == UserType.NEW) {
                PanelCreateAccount.showCreateAccount();
            } else {
                PanelLogin.showLoginPanel(selectUserTypeDrop.getSelectedItem());
            }
            frame.setVisible(false);
        };
        select.addActionListener(actionListenerSelect);
    }
}
