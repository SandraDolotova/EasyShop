package EasyShopperMart.Panels;

import EasyShopperMart.Product.ProductType;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelStore {

    public static JComboBox<ProductType> comboBox = new JComboBox<>();
    public static JTextField money = new JTextField(10);


    public static void setStoreFrame() {
        JFrame frame = new JFrame("EasyShop - Store");
        frame.pack();
        frame.add(createStorePanel());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public static JPanel createStorePanel() {
        JPanel storePanel = new JPanel();
        storePanel.add(new JLabel("Enter your budget"));
        storePanel.add(JTextField());// customer enters budget
        storePanel.add(new JLabel("Choose product category:"));
        storePanel.add(JComboBox(choice));
        //storePanel.add(); show Product list from owner
        // storePanel.add();


        storePanel.add(JButton()); // Button to finish -> pay
        return storePanel;
    }

    public static JComboBox<ProductType> JComboBox(ActionListener choice) {
        comboBox.setModel(new DefaultComboBoxModel<>(ProductType.values()));
        comboBox.getSelectedObjects();
        comboBox.addActionListener(PanelStore.choice);
        return comboBox;
    }

    public static JButton JButton() {
        JButton finish = new JButton("FINISH");
        finish.addActionListener(action());
        return finish;
    }

    public static JTextField JTextField() {
        money.getText();
        return money;
    }

    private static ActionListener choice = e -> {
       Object x = comboBox.getSelectedItem();
        if(x == ProductType.FRUITS){
            System.out.println("show fruits");
        }else if(x== ProductType.VEGETABLES) {
            System.out.println("show vegetables");
        }else{
            System.out.println("doesn't work ");
        }
    };


    private static ActionListener action() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Double x = Double.parseDouble(money.getText());
                //proceed with payment
            }
        };
        return null;


    }

}
