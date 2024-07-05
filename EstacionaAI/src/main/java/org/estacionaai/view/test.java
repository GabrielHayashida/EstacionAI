package org.estacionaai.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My GUI App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new MigLayout("fill"));
            panel.add(new JLabel("Name:"), "split 2, span");
            panel.add(new JTextField(20), "growx, wrap");
            panel.add(new JButton("Submit"), "span, right");

            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
