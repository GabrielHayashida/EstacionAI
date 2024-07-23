package org.estacionaai.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import net.miginfocom.swing.MigLayout;
import org.estacionaai.controller.ClienteController;
import org.estacionaai.model.DTO.ClienteDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginVisao extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton cmdLogin;
    private ClienteController clienteController;

    public LoginVisao(ClienteController clienteController) {
        super("Login EstacionAI");
        this.clienteController = clienteController; // Corrigido: inicializa o clienteController
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

        // Adiciona o componente ao painel
        panel.add(new JLabel("Bem-vindo ao EstacionAI"), "span, wrap, align center");
        panel.setPreferredSize(new Dimension(400, 300));
        panel.add(new JLabel("Email:"), "split 1, span ");
        panel.add(txtUsername, "wrap");
        panel.add(new JLabel("Senha:"), "split 1, span");
        panel.add(txtPassword, "wrap");

        JLabel lblRegister = new JLabel("<html>Não possui conta? <a href='#'>Cadastre-se</a></html>");
        lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirCadastro();
            }
        });

        panel.add(lblRegister, "span, wrap, align center");
        panel.add(cmdLogin, "span, align center");
        cmdLogin.addActionListener(e -> fazerLogin());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void abrirCadastro() {
        new CadastroVisao(clienteController);
    }

    private void fazerLogin() {
        String email = txtUsername.getText();
        String senha = new String(txtPassword.getPassword());

        if (clienteController.verificarCredenciais(email, senha)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");

            new TelaInicialVisao().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciais inválidas. Tente novamente.");
        }
    }

    public static void main(String[] args) {
        ClienteDTO clienteDTO = new ClienteDTO();
        ClienteController clienteController = new ClienteController(clienteDTO);
        SwingUtilities.invokeLater(() -> new LoginVisao(clienteController));
    }
}