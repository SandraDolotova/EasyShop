package EasyShopperMart.Panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PanelSalesRepresentative {

    static String[] representativeSelect = {"ADD/ DELETE PRODUCTS", "WAREHOUSE STATUS"};

    public static void setRepresentativeFrame() {
        JFrame frame = new JFrame("EasyShop - Sales Representative Menu");
        frame.pack();
        frame.add(createRepresentativePanel());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
    public static JPanel createRepresentativePanel() {
        JPanel representativePanel = new JPanel();
        representativePanel.add(new JLabel("Choose one option:"));
        representativePanel.add(JComboBox());
        representativePanel.add(JButton());
        return representativePanel;
    }
    public static JComboBox JComboBox() {
        JComboBox comboBox = new JComboBox(representativeSelect);
        comboBox.getSelectedObjects();
        return comboBox;
    }
    public static JButton JButton() {
        JButton select = new JButton("SELECT");
        //select.addActionListener();
        return select;
    }


}

