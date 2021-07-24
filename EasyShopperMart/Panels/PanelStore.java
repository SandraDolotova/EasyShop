package EasyShopperMart.Panels;

import EasyShopperMart.DB.DBConnection;
import EasyShopperMart.DB.DBMapper;
import EasyShopperMart.Type.ProductType;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PanelStore {

    private static JFrame dialogFrame;
    public static JComboBox<ProductType> comboBox = new JComboBox<>();
    public static JTextField money = new JTextField(10);
    public static JFrame frameStore = new JFrame("EasyShop - Store");

    public static String productName;
    public static Double productPrice;
    public static int productID;

    public static void setStoreFrame() throws SQLException, ClassNotFoundException {

        frameStore.pack();
        frameStore.add(createStorePanel());
        frameStore.setSize(500, 500);
        frameStore.setVisible(true);
    }

    public static JPanel createStorePanel() throws SQLException, ClassNotFoundException {
        JPanel storePanel = new JPanel();
        storePanel.add(new JLabel("Enter your budget"));
        storePanel.add(JTextField());// customer enters budget
        // storePanel.add(new JLabel("Choose product category:"));
        // storePanel.add(JComboBox(choice));
        storePanel.add(new JLabel("Product LIST: "));
        storePanel.add(new JTextArea(DBMapper.showProductList()));
        storePanel.add(JButton());// Button to select-> products -> JDIALOG
        storePanel.add(buttonPay());
        storePanel.add(buttonExit());//button to exit and clear shopping cart table
        return storePanel;
    }

    public static JButton JButton() {
        JButton select = new JButton("SELECT PRODUCTS");
        select.addActionListener(action());
        return select;
    }
    public static JButton buttonPay() {
        JButton pay = new JButton("PAY");
        pay.addActionListener(actionPay());
        return pay;
    }
    public static JButton buttonExit() {
        JButton exit = new JButton("EXIT");
        exit.addActionListener(actionExit());
        return exit;
    }
    public static JTextField JTextField() {
        money.getText();
        return money;
        // Double x = Double.parseDouble(money.getText());
    }

    public static ActionListener action() {
        ActionListener actionListener = e -> {
            int productID = getProductIdFromCustomer();
            int productQwt = getProductQwtFromCustomer();
            // double budget = Double.parseDouble(JTextField().getText());
            //  double totalProductPrice = getTotalProductPrice();

            try {
                productName = DBMapper.showProductNameByID(productID);
                productPrice = DBMapper.showProductPriceByID(productID);
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }

            Connection connection = null;
            try {
                connection = DBConnection.DBConnection();
            } catch (ClassNotFoundException | SQLException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            if (connection != null) {
                try {
                    String sql = "INSERT INTO shopping_cart (product_id, product_name, product_price_vat, product_quantity)" +
                            " values (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql);
                    preparedStatement.setInt(1, productID);
                    preparedStatement.setString(2, productName);
                    preparedStatement.setDouble(3, productPrice);
                    preparedStatement.setInt(4, productQwt);

                    preparedStatement.execute();
                    JOptionPane.showMessageDialog(dialogFrame, "Product has been added to the Shopping cart");

                } catch (SQLException | ClassNotFoundException exception) {
                    JOptionPane.showMessageDialog(dialogFrame, "Shopping cart ERROR", "warning", JOptionPane.WARNING_MESSAGE);
                    exception.printStackTrace();
                }
            }

        };
        return actionListener;
    }

    private static ActionListener actionPay() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double budget = Double.parseDouble(JTextField().getText());
                Connection connection = null;
                try {
                    connection = DBConnection.DBConnection();
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                if (connection != null) {
                    try {
                        String sql2 = "INSERT INTO pay (total_sum, customer_budget ) VALUES ((SELECT SUM(total_price) FROM shopping_cart), ?) ";
                        PreparedStatement preparedStatement1 = DBConnection.DBConnection().prepareStatement(sql2);
                        preparedStatement1.setDouble(1, budget);
                        preparedStatement1.execute();

                        PanelPurchase.setPurchaseFrame();

                    } catch (SQLException | ClassNotFoundException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        };
        return actionListener;
    }

    public static ActionListener actionExit() {
        ActionListener actionListener = e -> {
                    clearShoppingCart();
                    frameStore.setVisible(false);
                    PanelWelcome.showPanelWelcome();
        };
        return actionListener;
    }

    public static int getProductIdFromCustomer() {
        String productID = JOptionPane.showInputDialog("Add products to shopping cart: Insert Product id: ");
        return Integer.parseInt(productID);
    }

    public static int getProductQwtFromCustomer() {
        String productQwt = JOptionPane.showInputDialog("Insert product quantity: ");
        return Integer.parseInt(productQwt);
    }

    public static void clearShoppingCart() {
        Connection connection = null;
        try {
            connection = DBConnection.DBConnection();
        } catch (ClassNotFoundException | SQLException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        if (connection != null) {
            try {
                String sql1 = "TRUNCATE TABLE shopping_cart";
                PreparedStatement preparedStatement1 = DBConnection.DBConnection().prepareStatement(sql1);
                preparedStatement1.execute();
                JOptionPane.showMessageDialog(dialogFrame, "Shopping cart is empty");
                String sql2 = "TRUNCATE TABLE pay";
                PreparedStatement preparedStatement2 = DBConnection.DBConnection().prepareStatement(sql2);
                preparedStatement2.execute();

                frameStore.setVisible(false);
                PanelWelcome.showPanelWelcome();

            } catch (SQLException | ClassNotFoundException exception) {
                JOptionPane.showMessageDialog(dialogFrame, "Shopping cart ERROR", "warning", JOptionPane.WARNING_MESSAGE);
                exception.printStackTrace();
            }
        }
    }





}
 /*  public static JComboBox<ProductType> JComboBox(ActionListener choice) {
        comboBox.setModel(new DefaultComboBoxModel<>(ProductType.values()));
        comboBox.getSelectedObjects();
        comboBox.addActionListener(PanelStore.choice);
        return comboBox;
    }*/
 /* private static ActionListener choice = e -> {
        Object x = comboBox.getSelectedItem();
        if (x == ProductType.FRUITS) {
            System.out.println("show fruits");
        } else if (x == ProductType.VEGETABLES) {
            System.out.println("show vegetables");
        } else {
            System.out.println("doesn't work ");
        }
    };*/