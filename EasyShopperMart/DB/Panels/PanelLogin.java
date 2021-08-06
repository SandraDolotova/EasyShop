package EasyShopperMart.Panels;

import EasyShopperMart.DB.DBMapper;
import EasyShopperMart.Type.UserType;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Map;

public class PanelLogin {
    private static JFrame dialogFrame;
    public static Map<String, String> checkOwner;
    public static Map<String, String> checkCustomer;
    public static Map<String, String> checkSalesRepresentative;


    public static void showLoginPanel(Object userType) {

        JPanel loginPanel = new JPanel();
        JTextField loginField = new JTextField(10);
        JTextField passField = new JTextField(10);
        JButton enter = new JButton("ENTER");

        loginPanel.add(new JLabel("Login: "));
        loginPanel.add(loginField);
        loginPanel.add(new JLabel("Password: "));
        loginPanel.add(passField);
        loginPanel.add(enter);

        JFrame frame = new JFrame("EasyShop - LogIn");
        frame.pack();
        frame.setContentPane(loginPanel);
        frame.setSize(500, 500);
        frame.setVisible(true);

        ActionListener actionListenerEnter = e -> {
            try {
                checkOwner = DBMapper.checkOwner();
                checkCustomer = DBMapper.checkCustomer();
                checkSalesRepresentative = DBMapper.checkSalesRepresentative();
            } catch (Exception exp) {
                exp.printStackTrace();
            }

            if (userType == UserType.OWNER) {

                String ownerName = loginField.getText();
                String ownerPass = passField.getText();

                if (!(checkOwner.containsKey(ownerName))) {
                    showMessageWarning();
                } else if (checkOwner.containsKey(ownerName) && checkOwner.containsValue(ownerPass)) {
                    frame.setVisible(false);
                    PanelOwnerMenu.setOwnerFrame();
                } else {
                    showMessageNegative();
                }
            }

            if (userType == UserType.CUSTOMER) {

                String customerName = loginField.getText();
                String customerPass = passField.getText();

                if (!checkCustomer.containsKey(customerName)) {
                    showMessageWarning();
                } else if (checkCustomer.containsKey(customerName) && checkCustomer.containsValue(customerPass)) {
                    frame.setVisible(false);
                    try {
                        PanelStore.setStoreFrame();
                    } catch (SQLException | ClassNotFoundException exception) {
                        exception.printStackTrace();
                    }
                } else {
                    showMessageNegative();
                }
            }

            if (userType == UserType.SALESREPRESENTATIVE) {

                String salesRepresentativeName = loginField.getText();
                String salesRepresentativePass = passField.getText();

                if (!checkSalesRepresentative.containsKey(salesRepresentativeName)) {
                    showMessageWarning();
                } else if (checkSalesRepresentative.containsKey(salesRepresentativeName) && checkSalesRepresentative.containsValue(salesRepresentativePass)) {
                    frame.setVisible(false);
                    try {
                        PanelSalesRepresentative.setRepresentativeFrame();
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    showMessageNegative();
                }
            }
        };
        enter.addActionListener(actionListenerEnter);
    }

    public static void showMessageWarning() {
        JOptionPane.showMessageDialog(dialogFrame, "Incorrect user name. Try again", "warning", JOptionPane.WARNING_MESSAGE);
    }

    public static void showMessageNegative() {
        JOptionPane.showMessageDialog(dialogFrame, "Incorrect input", "warning", JOptionPane.WARNING_MESSAGE);
        PanelWelcome.showPanelWelcome();
    }

}
