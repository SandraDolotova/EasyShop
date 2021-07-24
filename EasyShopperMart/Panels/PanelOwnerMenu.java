package EasyShopperMart.Panels;

import EasyShopperMart.DB.DBConnection;
import EasyShopperMart.Exit;
import netscape.security.UserTarget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Objects;

public class PanelOwnerMenu {

    private static JFrame dialogFrame;
    public static String[] ownerSelect = {"UPDATE PRICE", "ADD PRODUCT", "DELETE PRODUCT"};
    public static JComboBox<String> comboBox = new JComboBox<>(ownerSelect);
    public static JFrame frameOwner = new JFrame("EasyShop - Owner Menu");

    public static void setOwnerFrame() {
        frameOwner.pack();
        frameOwner.add(createOwnerPanel());
        frameOwner.setSize(500, 500);
        frameOwner.setVisible(true);
    }

    public static JPanel createOwnerPanel() {
        JPanel ownerPanel = new JPanel();
        ownerPanel.add(new JLabel("Choose one option:"));
        ownerPanel.add(JComboBox());
        ownerPanel.add(JButton(action()));
        ownerPanel.add(new JTable());
        ownerPanel.add(buttonExit());
       // ownerPanel.add(Exit.exitButton());
        return ownerPanel;
    }

    public static JComboBox<String> JComboBox() {
        comboBox.setModel(new DefaultComboBoxModel<>(ownerSelect));
        comboBox.getSelectedObjects();
        return comboBox;
    }

    public static JButton JButton(ActionListener actionListener) {
        JButton select = new JButton("SELECT");
        select.addActionListener(action());
        return select;
    }

    public static ActionListener action() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //  String comboBoxSelectedItem = Arrays.toString(comboBox.getSelectedObjects());
                if (Objects.equals(comboBox.getSelectedItem(), "ADD PRODUCT")) {

                    String productName = JOptionPane.showInputDialog("TO ADD  - Insert Product name: ");
                    String productPrice = JOptionPane.showInputDialog("Insert product price: ");
                    String productQwt = JOptionPane.showInputDialog("Insert product quantity: ");

                    Connection connection = null;
                    try {
                        connection = DBConnection.DBConnection();
                    } catch (ClassNotFoundException | SQLException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }

                    if (connection != null) {
                        try {
                            String sql = "INSERT INTO sales_products (product_name, product_price, product_quantity_stock) values (?, ?, ?)";
                            PreparedStatement preparedStatement = Objects.requireNonNull(DBConnection.DBConnection()).prepareStatement(sql);
                            preparedStatement.setString(1, productName);
                            preparedStatement.setDouble(2, Double.parseDouble(productPrice));
                            preparedStatement.setString(3, productQwt);
                            preparedStatement.execute();
                            JOptionPane.showMessageDialog(dialogFrame, "Product has been added to the list");
                            connection.close();
                        } catch (ClassNotFoundException | SQLException classNotFoundException) {
                            JOptionPane.showMessageDialog(dialogFrame, "ERROR! Product was not added");
                            classNotFoundException.printStackTrace();
                        }
                    }

                }

                if (Objects.equals(comboBox.getSelectedItem(), "DELETE PRODUCT")) {

                    String productId = JOptionPane.showInputDialog("TO DELETE - Insert Product ID: ");
                    Connection connection = null;
                    try {
                        connection = DBConnection.DBConnection();
                    } catch (ClassNotFoundException | SQLException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }

                    if (connection != null) {
                        try {
                            int productID = Integer.parseInt(productId);
                            Statement statement = connection.createStatement();
                            String sql = "DELETE from sales_products WHERE (product_id) = (' " + productID + " ')";
                            statement.executeUpdate(sql);
                            JOptionPane.showMessageDialog(dialogFrame, "Product has been REMOVED from the list");
                            connection.close();
                        } catch (SQLException exc) {
                            JOptionPane.showMessageDialog(dialogFrame, "ERROR! Product was nor removed");
                            exc.printStackTrace();
                        }
                    }

                }
                if (Objects.equals(comboBox.getSelectedItem(), "UPDATE PRICE")) {

                    String productId = JOptionPane.showInputDialog("TO UPDATE PRICE - Insert Product ID: ");
                    String changePrice = JOptionPane.showInputDialog("SET new price: ");

                    Connection connection = null;
                    try {
                        connection = DBConnection.DBConnection();
                    } catch (ClassNotFoundException | SQLException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                    if (connection != null) {
                        try {
                            int productID = Integer.parseInt(productId);
                            double newPrice = Double.parseDouble(changePrice);

                            Statement statement = connection.createStatement();
                            String sql = "UPDATE sales_products SET product_price = ' " + newPrice + " ' WHERE product_id = ' " + productID + " ' ";
                            statement.executeUpdate(sql);
                            JOptionPane.showMessageDialog(dialogFrame, "Price was changed");
                            connection.close();
                        } catch (SQLException exc) {
                            JOptionPane.showMessageDialog(dialogFrame, "ERROR! Product price was not changed");
                            exc.printStackTrace();
                        }
                    }
                }
            }
        };
        return actionListener;
    }

    private static JButton buttonExit() {
        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(actionExit());
        return exitButton;
    }

    private static ActionListener actionExit() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameOwner.setVisible(false);
                PanelWelcome.showPanelWelcome();
            }
        };
        return actionListener;
    }


}