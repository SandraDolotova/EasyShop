package EasyShopperMart.Panels;

import EasyShopperMart.DB.DBConnection;
import EasyShopperMart.DB.DBMapper;
import EasyShopperMart.Exit;
import EasyShopperMart.Type.ProductType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class PanelSalesRepresentative {

    private static JFrame dialogFrame;
    // public static String[] representativeSelect = {"ADD PRODUCTS", "DELETE PRODUCTS"};
    public static JComboBox<String> comboBox1 = new JComboBox(ProductType.values());
    // public static JComboBox<String> comboBox2 = new JComboBox<>(representativeSelect);

    public static void setRepresentativeFrame() throws SQLException, ClassNotFoundException {
        JFrame frame = new JFrame("EasyShop - Sales Representative Menu");
        frame.pack();
        frame.add(createRepresentativePanel());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public static JPanel createRepresentativePanel() throws SQLException, ClassNotFoundException {
        JPanel representativePanel = new JPanel();
        representativePanel.add(new JLabel("To add new Product - choose product type: "));
       // representativePanel.add(productTypeJComboBox());
        representativePanel.add(addButton());
        representativePanel.add(new JLabel("WAREHOUSE STATUS: "));
        representativePanel.add(new JTextArea(DBMapper.showWarehouse()));
        representativePanel.add(Exit.exitButton());
        return representativePanel;
    }

    private static JComboBox<String> productTypeJComboBox() {
        comboBox1.setModel(new DefaultComboBoxModel(ProductType.values()));
        comboBox1.getSelectedObjects();
        comboBox1.addActionListener(PanelSalesRepresentative.salesRepChoice());
        return comboBox1;
    }

    public static ActionListener salesRepChoice() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comboBox1.getSelectedItem() == ProductType.FRUITS) {
                    System.out.println("show fruits");
                } else if (comboBox1.getSelectedItem() == ProductType.VEGETABLES) {
                    System.out.println("show vegetables");
                }
            }
        };
        return actionListener;
    }
    public static JButton addButton() {
        JButton jButtonAdd = new JButton("ADD");
        jButtonAdd.addActionListener(addAction());
        return jButtonAdd;
    }

    public static ActionListener addAction() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
                    String productTYpe = Arrays.toString(comboBox1.getSelectedObjects());
                    try {
                        String sql = "INSERT INTO sales_products (product_name, product_price, product_quantity_stock, product_type) values (?, ?, ?, ?)";
                        PreparedStatement preparedStatement = Objects.requireNonNull(DBConnection.DBConnection()).prepareStatement(sql);
                        preparedStatement.setString(1, productName);
                        preparedStatement.setString(2, productPrice);
                        preparedStatement.setString(3, productQwt);
                        preparedStatement.setString(4, productTYpe);
                        preparedStatement.execute();
                        JOptionPane.showMessageDialog(dialogFrame, "Product has been added to the list");
                    } catch (ClassNotFoundException | SQLException classNotFoundException) {
                        System.out.println("Error! Product was not added");
                        classNotFoundException.printStackTrace();
                    }
                }
            }
        };
        return actionListener;
    }




}

