package org.estacionaai.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class LoginVisao extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton cmdLogin;

    public LoginVisao() {
        super("Login EstacionAI");
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        try {

            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new MigLayout("fill, insets 20", "[center]", "[center]"));
        getContentPane().add(panel);

        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        cmdLogin = new JButton("Login");

        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite Seu email");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite sua senha");

        txtUsername.putClientProperty("JComponent.roundRect", true);
        txtPassword.putClientProperty("JComponent.roundRect", true);

        cmdLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]background:darken(@background,10%);"
                + "[dark]background:lighten(@background,10%);"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "arc:15;"
                + "hoverBackground:darken(@background,20%);");

        // Adding components to the panel
        panel.add(new JLabel("Bem vindo ao EstacionAI"), "span, wrap, align center");
        panel.setPreferredSize(new Dimension(400, 300));
        panel.add(new JLabel("Email:"), "split 1, span ");
        panel.add(txtUsername, "wrap");
        panel.add(new JLabel("Senha:"), "split 1, span");
        panel.add(txtPassword, "wrap");
        panel.add(new JLabel("Não Possui conta? Cadastre-se"), "span, wrap, align center");
        panel.add(cmdLogin, "span, align center");

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginVisao();
        });
    }
}
