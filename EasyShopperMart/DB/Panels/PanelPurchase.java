package EasyShopperMart.Panels;

import EasyShopperMart.DB.DBConnection;
import EasyShopperMart.DB.DBMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class PanelPurchase extends PanelStore {

    public static Double totalSum;
    public static Double change;
    private static JFrame dialogFrame;


    public static void setPurchaseFrame() throws SQLException, ClassNotFoundException {
        JFrame frameStore = new JFrame("EasyShop - Purchase");
        frameStore.pack();
        frameStore.add(createPurchasePanel());
        frameStore.setSize(500, 500);
        frameStore.setVisible(true);
    }

    public static JPanel createPurchasePanel() throws SQLException, ClassNotFoundException {
        JPanel purchasePanel = new JPanel();
        purchasePanel.add(new JLabel("Your payment details :"));
       // purchasePanel.add(new JTextArea(DBMapper.showShoppingCart()));
        purchasePanel.add(finishButton());
        purchasePanel.add(buttonExit());
        return purchasePanel;
    }


    static JButton finishButton() {
        JButton finish = new JButton("FINISH");
        finish.addActionListener(actionFinish());
        return finish;

    }

    private static ActionListener actionFinish() {
        ActionListener actionListener = e -> {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            double budget = Double.parseDouble(money.getText());
            try {
                totalSum = DBMapper.showTotalPaySum();
                change = DBMapper.showChange();
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
            if (budget >= totalSum) {
                JOptionPane.showMessageDialog(dialogFrame, "You have made purchase for the amount: " + decimalFormat.format(totalSum) + " euros." + " Your budget remainder is: " + decimalFormat.format(change) + " euros.", "", JOptionPane.PLAIN_MESSAGE);
                try {
                    changeProductQuantity();
                    PanelWelcome.showPanelWelcome();
                    frameStore.setVisible(false);
                } catch (SQLException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                }
            } else if (budget < totalSum) {
                JOptionPane.showMessageDialog(dialogFrame, "Insufficient funds.\nPlease make changes in your order details", "warning", JOptionPane.WARNING_MESSAGE);
                PanelStore.clearShoppingCart();
                try {
                    PanelStore.setStoreFrame();
                } catch (SQLException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
        };
        return actionListener;
    }

    public static void changeProductQuantity() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            connection = DBConnection.DBConnection();
        } catch (ClassNotFoundException | SQLException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        if (connection != null) {
            try {
                String sql = "UPDATE sales_products SET product_quantity_stock = product_quantity_stock - (SELECT (product_quantity) FROM shopping_cart WHERE shopping_cart.product_id = sales_products.product_id)";
                PreparedStatement preparedStatement = DBConnection.DBConnection().prepareStatement(sql);
                preparedStatement.executeUpdate();
                System.out.println("OK UPDATED QWT");
                connection.close();
            } catch (SQLException exc) {
                System.out.println("PROBLEM");
                exc.printStackTrace();
            }
        }
    }


}