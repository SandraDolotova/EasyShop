package EasyShopperMart;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class Panel {

    private JFrame dialogFrame;
    public String checkOwnerName = "";
    public String checkOwnerPass = "";
    public String checkCustomerName = "";
    public String checkCustomerPass = "";
    public Panel() throws SQLException, ClassNotFoundException {
        this.checkOwnerName = DBMapper.checkOwnerName();
        this.checkOwnerPass = DBMapper.checkOwnerPass();
    }

    Connection connection = DBConnection.DBConnection();

    public void showWelcomePanel() {
        JPanel welcomePanel = new JPanel();
        JButton select = new JButton("SELECT");

        JComboBox<UserType> selectUserTypeDrop = new JComboBox<>();
        selectUserTypeDrop.setModel(new DefaultComboBoxModel<>(UserType.values()));
        selectUserTypeDrop.getSelectedObjects();

        welcomePanel.add(new JLabel("Welcome to the EasyShop!\n Choose your User type: "));
        welcomePanel.add(selectUserTypeDrop);
        welcomePanel.add(select);

        JFrame frame1 = new JFrame("EasyShop");
        frame1.pack();
        frame1.setContentPane(welcomePanel);
        frame1.setSize(500, 500);
        frame1.setVisible(true);

        ActionListener actionListenerSelect = e -> {
            if (Objects.equals(selectUserTypeDrop.getSelectedItem(), UserType.NEW)) {
                showCreateAccountPanel();
            } else {
                showLoginPanel();
            }
            //frame1.setVisible(false);
        };

        select.addActionListener(actionListenerSelect);

    }


        public void showCreateAccountPanel () {
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

            JFrame frame3 = new JFrame("EasyShop - Create new account");
            frame3.pack();
            frame3.setContentPane(createAccountPanel);
            frame3.setSize(500, 500);
            frame3.setVisible(true);
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
                        System.out.println("New customer has been added to the DB");
                        connection.close();
                    } catch (ClassNotFoundException | SQLException classNotFoundException) {
                        System.out.println("Error! User was not added");
                        classNotFoundException.printStackTrace();
                    }
                } else {
                    try {
                        String sql = "INSERT INTO sales_representatives (user_name, user_phone, user_email, user_type, user_password) values (?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = Objects.requireNonNull(DBConnection.DBConnection()).prepareStatement(sql);
                        preparedStatement.setString(1, newUserName);
                        preparedStatement.setString(2, newUserPhone);
                        preparedStatement.setString(3, newUserEmail);
                        preparedStatement.setString(4, newUserType);
                        preparedStatement.setString(5, newUserPassword);
                        preparedStatement.execute();
                        System.out.println("New sales representative has been added to the DB");
                        connection.close();
                    } catch (ClassNotFoundException | SQLException classNotFoundException) {
                        System.out.println("Error! User was not added");
                        classNotFoundException.printStackTrace();
                    }
                }
                showWelcomePanel();

            };
            save.addActionListener(actionListenerSave);

        }

        public void showLoginPanel () {
            JPanel loginPanel = new JPanel();

            JTextField loginField = new JTextField(10);
            JTextField passField = new JTextField(10);

            JButton enter = new JButton("ENTER");

            loginPanel.add(new JLabel("Login: "));
            loginPanel.add(loginField);
            loginPanel.add(new JLabel("Password: "));
            loginPanel.add(passField);
            loginPanel.add(enter);

            JFrame frame2 = new JFrame("EasyShop - LogIn");
            frame2.pack();
            frame2.setContentPane(loginPanel);
            frame2.setSize(500, 500);
            frame2.setVisible(true);

            ActionListener actionListenerEnter = e -> {

                String ownerName = loginField.getText();
                String ownerPass = passField.getText();

                String customerName = loginField.getText();
                String customerPass = loginField.getText();

                if (!checkOwnerName.equalsIgnoreCase(ownerName)) {
                    JOptionPane.showMessageDialog(dialogFrame, "Incorrect user name. Try again", "warning", JOptionPane.WARNING_MESSAGE);    ///dialog show
                }else if (checkOwnerName.equalsIgnoreCase(ownerName) && checkOwnerPass.equals(ownerPass)) {
                    System.out.println("NEXT STEP");
                    // show owner menu
                }else {
                    JOptionPane.showMessageDialog(dialogFrame, "Incorrect input", "warning", JOptionPane.WARNING_MESSAGE);
                    showWelcomePanel();
                    // show owner menu
                    System.out.println(" OK ");
                }
            };
            enter.addActionListener(actionListenerEnter);
        }

    }


