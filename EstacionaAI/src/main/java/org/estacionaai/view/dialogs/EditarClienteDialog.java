package org.estacionaai.view.dialogs;

import org.estacionaai.controller.ClienteController;
import org.estacionaai.model.VO.ClienteVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarClienteDialog extends JDialog {
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTextField txtSenha;
    private JTextField txtEndereco;
    private JCheckBox chkAdmin;
    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private ClienteController controller;
    private ClienteVO cliente;
    private boolean updated = false;

    public EditarClienteDialog(Frame owner, ClienteController controller, ClienteVO cliente) {
        super(owner, "Editar Cliente", true);
        this.controller = controller;
        this.cliente = cliente;

        initComponents();
        setLayout(new BorderLayout());
        add(criarPainelCampos(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        txtNome = new JTextField(cliente.getNome());
        txtTelefone = new JTextField(cliente.getTelefone());
        txtEmail = new JTextField(cliente.getEmail());
        txtSenha = new JTextField(cliente.getSenha());
        txtEndereco = new JTextField(cliente.getEndereco());
        chkAdmin = new JCheckBox("Administrador", cliente.isAdmin());

        buttonSalvar = new JButton("Salvar");
        buttonCancelar = new JButton("Cancelar");

        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCliente();
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private JPanel criarPainelCampos() {
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("Telefone:"));
        panel.add(txtTelefone);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Senha:"));
        panel.add(txtSenha);
        panel.add(new JLabel("Endereço:"));
        panel.add(txtEndereco);
        panel.add(new JLabel("Administrador:"));
        panel.add(chkAdmin);
        return panel;
    }

    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(buttonSalvar);
        panel.add(buttonCancelar);
        return panel;
    }

    private void salvarCliente() {
        cliente.setNome(txtNome.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setSenha(txtSenha.getText());
        cliente.setEndereco(txtEndereco.getText());
        cliente.setAdmin(chkAdmin.isSelected());

        if (controller.atualizarCliente(cliente)) {
            updated = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isUpdated() {
        return updated;
    }
}
