package EasyShopperMart.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PanelOwnerMenu {

    public static String[] ownerSelect = {"PROFIT/ LOSS", "ADD/ DELETE"};
    public static JComboBox<String> comboBox = new JComboBox<>(ownerSelect);

    public static void setOwnerFrame() {
        JFrame frame = new JFrame("EasyShop - Owner Menu");
        frame.pack();
        frame.add(createOwnerPanel());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public static JPanel createOwnerPanel() {
        JPanel ownerPanel = new JPanel();
        ownerPanel.add(new JLabel("Choose one option:"));
        ownerPanel.add(JComboBox());
        ownerPanel.add(JButton(action()));

        // ownerPanel.setLayout(new GridLayout(6, 3)); maybe
        return ownerPanel;
    }

    public static JComboBox<String> JComboBox() {
        comboBox.setModel(new DefaultComboBoxModel<>(ownerSelect));
        comboBox.getSelectedObjects();
        return comboBox;
    }

    public static JButton JButton(ActionListener action) {
        JButton select = new JButton("SELECT");
        select.addActionListener(action());
        return select;
    }

    public static ActionListener action() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String x = (String) comboBox.getSelectedItem();
                switch (Objects.requireNonNull(x)) {
                    case "PROFIT/ LOSS TABLE":
                        System.out.println("PROFIT/ LOSS");    // all was OK and working
                        break;
                    case "ADD/ DELETE TABLE":
                        System.out.println("ADD PRODUCT");    // all was OK and working
                        break;
                }
            }
        };
        return actionListener;
    }
}