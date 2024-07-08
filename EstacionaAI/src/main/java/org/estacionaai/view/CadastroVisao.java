package org.estacionaai.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class CadastroVisao {
    private JTextField txtUsername;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JButton cmdLogin;

    public CadastroVisao() {
        initUI();
    }

    private void initUI() {
        JFrame frame = new JFrame("Login EstacionAI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new MigLayout("fill, insets 20", "[center]", "[center]"));
        frame.getContentPane().add(panel);

        // Components initialization
        txtUsername = new JTextField(20);
        txtEmail = new JTextField(20);
        txtPassword = new JPasswordField(20);
        txtConfirmPassword = new JPasswordField(20);

        cmdLogin = new JButton("Login");


        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite Seu Nome");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite Seu Email");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite sua senha");
        txtConfirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Confirme Sua Senha");

        txtUsername.putClientProperty("JComponent.roundRect", true);
        txtEmail.putClientProperty("JComponent.roundRect", true);
        txtPassword.putClientProperty("JComponent.roundRect", true);
        txtConfirmPassword.putClientProperty("JComponent.roundRect", true);

        cmdLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]background:darken(@background,10%);"
                + "[dark]background:lighten(@background,10%);"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "arc:15;"
                + "hoverBackground:darken(@background,20%);");

        // Adding components to the panel
        panel.setPreferredSize(new Dimension(400, 300));
        panel.add(new JLabel("Nome:"), "split 1, span");
        panel.add(txtUsername, "wrap");
        panel.add(new JLabel("Email:"), "split 1, span");
        panel.add(txtEmail, "wrap");
        panel.add(new JLabel("Senha:"), "split 1, span");
        panel.add(txtPassword, "wrap");
        panel.add(new JLabel("Confirmar Senha:"), "split 1, span");
        panel.add(txtConfirmPassword, "wrap");

        panel.add(cmdLogin, "span, align center");

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            // Define FlatLaf como o Look and Feel
            UIManager.setLookAndFeel(new FlatMacDarkLaf());

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            new CadastroVisao();
        });
    }
}
