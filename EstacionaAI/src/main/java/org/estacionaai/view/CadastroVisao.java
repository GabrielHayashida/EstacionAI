package org.estacionaai.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import net.miginfocom.swing.MigLayout;
import org.estacionaai.controller.ClienteController;
import org.estacionaai.model.DTO.ClienteDTO;
import org.estacionaai.model.VO.ClienteVO;
import org.estacionaai.utils.CriptografiaSenha;

import javax.swing.*;
import java.awt.*;

public class CadastroVisao extends JFrame {
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JPasswordField txtSenha;

    private JTextField txtEndereco;
    private JButton cmdCadastrar;
    private ClienteController clienteController;

    public CadastroVisao(ClienteController clienteController) {
        super("Cadastro EstacionAI");
        this.clienteController = clienteController;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new MigLayout("fill, insets 20", "[center]", "[center]"));
        getContentPane().add(panel);

        txtNome = new JTextField(20);
        txtTelefone = new JTextField(20);
        txtEmail = new JTextField(20);
        txtSenha = new JPasswordField(20);

        txtEndereco = new JTextField(20);
        cmdCadastrar = new JButton("Cadastrar");

        txtNome.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite seu nome");
        txtTelefone.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite seu telefone");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite seu email");
        txtSenha.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite sua senha");
        txtEndereco.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite seu endereço");

        txtNome.putClientProperty("JComponent.roundRect", true);
        txtTelefone.putClientProperty("JComponent.roundRect", true);
        txtEmail.putClientProperty("JComponent.roundRect", true);
        txtSenha.putClientProperty("JComponent.roundRect", true);
        txtEndereco.putClientProperty("JComponent.roundRect", true);

        cmdCadastrar.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]background:darken(@background,10%);"
                + "[dark]background:lighten(@background,10%);"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "arc:15;"
                + "hoverBackground:darken(@background,20%);");

        // Adding components to the panel
        panel.add(new JLabel("Cadastro EstacionAI"), "span, wrap, align center");
        panel.setPreferredSize(new Dimension(400, 400));
        panel.add(new JLabel("Nome:"), "split 1, span ");
        panel.add(txtNome, "wrap");
        panel.add(new JLabel("Telefone:"), "split 1, span");
        panel.add(txtTelefone, "wrap");
        panel.add(new JLabel("Email:"), "split 1, span");
        panel.add(txtEmail, "wrap");
        panel.add(new JLabel("Senha:"), "split 1, span");
        panel.add(txtSenha, "wrap");
        panel.add(new JLabel("Endereço:"), "split 1, span");
        panel.add(txtEndereco, "wrap");
        panel.add(cmdCadastrar, "span, align center");

        cmdCadastrar.addActionListener(e -> cadastrarCliente());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cadastrarCliente() {
        String nome = txtNome.getText();
        String telefone = txtTelefone.getText();
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        String endereco = txtEndereco.getText();

        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || senha.isEmpty() || endereco.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        ClienteVO clienteVO = new ClienteVO();
        clienteVO.setNome(nome);
        clienteVO.setTelefone(telefone);
        clienteVO.setEmail(email);
        clienteVO.setSenha(CriptografiaSenha.criptografar(senha));

        clienteVO.setEndereco(endereco);

        if (clienteController.inserirCliente(clienteVO)) {
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao realizar o cadastro. Tente novamente.");
        }
    }

    public static void main(String[] args) {
        ClienteDTO clienteDTO = new ClienteDTO();
        ClienteController clienteController = new ClienteController(clienteDTO);
        SwingUtilities.invokeLater(() -> new CadastroVisao(clienteController));
    }
}
