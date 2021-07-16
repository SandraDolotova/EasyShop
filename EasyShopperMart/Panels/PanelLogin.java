package EasyShopperMart.Panels;

import EasyShopperMart.DB.DBMapper;
import EasyShopperMart.UserType;
import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelLogin {

    private static JPanel loginPanel;
    private static JFrame dialogFrame;
    public static String checkOwnerName = "";
    public static String checkOwnerPass = "";
    public static String checkCustomerName = "";
    public static String checkCustomerPass = "";
    public static String checkSalesRepresentativeName = "";
    public static String checkSalesRepresentativePass = "";


    public static void showLoginPanel(Object userType) {

        loginPanel = new JPanel();
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
                checkOwnerName = DBMapper.checkOwnerName();
                checkOwnerPass = DBMapper.checkOwnerPass();
                checkCustomerName = DBMapper.checkCustomerName();
                checkCustomerPass = DBMapper.checkCustomerPass();
                checkSalesRepresentativeName = DBMapper.checkSalesRepresentativeName();
                checkSalesRepresentativeName = DBMapper.checkSalesRepresentativePass();
            } catch (Exception exp) {
                exp.printStackTrace();
            }

            if (userType == UserType.OWNER) {

                String ownerName = loginField.getText();
                String ownerPass = passField.getText();

                if (!checkOwnerName.equalsIgnoreCase(ownerName)) {
                    showMessageWarning();
                } else if (checkOwnerName.equalsIgnoreCase(ownerName) && checkOwnerPass.equals(ownerPass)) {
                    frame.setVisible(false);
                    PanelOwnerMenu.setOwnerFrame();
                } else {
                    showMessageNegative();
                }
            }

            if (userType == UserType.CUSTOMER) {

                String customerName = loginField.getText();
                String customerPass = passField.getText();

                if (!checkCustomerName.equalsIgnoreCase(customerName)) {
                    showMessageWarning();
                } else if (checkCustomerName.equalsIgnoreCase(customerName) && checkCustomerPass.equals(customerPass)) {
                    frame.setVisible(false);
                    PanelStore.setStoreFrame();
                } else {
                    showMessageNegative();
                }
            }

            if (userType == UserType.SALESREPRESENTATIVE) {

                String salesRepresentativeName = loginField.getText();
                String salesRepresentativePass = passField.getText();

                if (!checkSalesRepresentativeName.equalsIgnoreCase(salesRepresentativeName)) {
                    showMessageWarning();
                } else if (checkSalesRepresentativeName.equalsIgnoreCase(salesRepresentativeName) && checkSalesRepresentativePass.equals(salesRepresentativePass)) {
                    frame.setVisible(false);
                    System.out.println("OK_ sales panel");
                    PanelSalesRepresentative.setRepresentativeFrame();
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
