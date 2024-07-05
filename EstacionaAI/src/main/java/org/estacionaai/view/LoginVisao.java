package org.estacionaai.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class LoginVisao {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton cmdLogin;

    public LoginVisao() {
        initUI();
    }

    private void initUI() {
        JFrame frame = new JFrame("Login EstacionAI"); // Criando o JFrame para a aplicação
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new MigLayout("fill, insets 20", "[center]", "[center]"));
        frame.getContentPane().add(panel);

        // Components initialization
        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        cmdLogin = new JButton("Login");

        // Styling using FlatLaf properties
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite Seu email");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite sua senha");

        cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0");





        // Adding components to the panel
        panel.add(new JLabel("Bem vindo ao EstacionAI"), "span, wrap, align center");
        panel.setPreferredSize(new Dimension(400,300));
        panel.add(new JLabel("Email:"), "split 1, span");
        panel.add(txtUsername, "wrap");
        panel.add(new JLabel("Senha:"), "split 1, span");
        panel.add(txtPassword, "wrap");
        panel.add(new JLabel("Não Possui conta? Cadastre-se"), "span, wrap, align center");
        panel.add(cmdLogin, "span, align center");

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            // Define FlatLaf como o Look and Feel
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            new LoginVisao();
        });
    }
}
