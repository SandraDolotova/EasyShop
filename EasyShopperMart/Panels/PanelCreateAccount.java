package EasyShopperMart.Panels;

import EasyShopperMart.DB.DBConnection;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class PanelCreateAccount {
    private static Connection connection = null;
    private static JFrame dialogFrame;

    public static void showCreateAccount() {

        JPanel createAccountPanel = new JPanel();

        JTextField nameField = new JTextField(10);
        JTextField passwordField = new JTextField(10);
        JTextField phoneField = new JTextField(10);
        JTextField emailField = new JTextField(10);

        String[] userTypes = {"CUSTOMER", "SALES_REPRESENTATIVE"};
        JComboBox<String> createUserTypeBox = new JComboBox<>(userTypes);
        createUserTypeBox.getSelectedObjects();

        JButton save = new JButton("SAVE");

        createAccountPanel.add(new JLabel("Enter name: "));
        createAccountPanel.add(nameField);
        createAccountPanel.add(new JLabel("Enter password: "));
        createAccountPanel.add(passwordField);
        createAccountPanel.add(new JLabel("Enter phone: "));
        createAccountPanel.add(phoneField);
        createAccountPanel.add(new JLabel("Enter email: "));
        createAccountPanel.add(emailField);
        createAccountPanel.add(new JLabel("Choose your user type: "));
        createAccountPanel.add(createUserTypeBox);
        createAccountPanel.add(save);

        JFrame frame = new JFrame("EasyShop - Create new account");
        frame.pack();
        frame.setContentPane(createAccountPanel);
        frame.setSize(500, 500);
        frame.setVisible(true);

        ActionListener actionListenerSave = e -> {
            String newUserName = nameField.getText();
            String newUserPhone = phoneField.getText();
            String newUserEmail = emailField.getText();
            String newUserType = Arrays.toString(createUserTypeBox.getSelectedObjects());
            String newUserPassword = passwordField.getText();

            if (Objects.equals(createUserTypeBox.getSelectedItem(), "CUSTOMER")) {
                try {
                    String sql = "INSERT INTO customers (user_name, user_phone, user_email, user_type, user_password) values (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = Objects.requireNonNull(DBConnection.DBConnection()).prepareStatement(sql);
                    preparedStatement.setString(1, newUserName);
                    preparedStatement.setString(2, newUserPhone);
                    preparedStatement.setString(3, newUserEmail);
                    preparedStatement.setString(4, newUserType);
                    preparedStatement.setString(5, newUserPassword);
                    preparedStatement.execute();
                    System.out.println("New customer has been added to the EasyShopperMart.DB");
                    //connection.close();
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    System.out.println("Error! User was not added");
                    classNotFoundException.printStackTrace();
                }
                frame.setVisible(false);
            } if (Objects.equals(createUserTypeBox.getSelectedItem(), "SALES_REPRESENTATIVE")) {
                try {
                    String sql = "INSERT INTO sales_representatives (user_name, user_phone, user_email, user_type, user_password) values (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = Objects.requireNonNull(DBConnection.DBConnection()).prepareStatement(sql);
                    preparedStatement.setString(1, newUserName);
                    preparedStatement.setString(2, newUserPhone);
                    preparedStatement.setString(3, newUserEmail);
                    preparedStatement.setString(4, newUserType);
                    preparedStatement.setString(5, newUserPassword);
                    preparedStatement.execute();
                    System.out.println("New sales representative has been added to the EasyShopperMart.DB");
                    //connection.close();
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    System.out.println("Error! User was not added");
                    classNotFoundException.printStackTrace();
                }
            }
            showMessage();
        };
        save.addActionListener(actionListenerSave);
    }

    public static void showMessage() {
        JOptionPane.showMessageDialog(dialogFrame, "New user has been added. Now please login", "", JOptionPane.PLAIN_MESSAGE);
        PanelWelcome.showPanelWelcome();
    }

}