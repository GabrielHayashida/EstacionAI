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
    private JTextField txtEndereco;
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
        // Initialize fields with existing cliente data
        txtNome = new JTextField(cliente.getNome());
        txtTelefone = new JTextField(cliente.getTelefone());
        txtEmail = new JTextField(cliente.getEmail());
        txtEndereco = new JTextField(cliente.getEndereco());

        buttonSalvar = new JButton("Salvar");
        buttonCancelar = new JButton("Cancelar");

        // Add action listeners for buttons
        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCliente();
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close dialog without saving
            }
        });
    }

    private JPanel criarPainelCampos() {
        JPanel panel = new JPanel(new GridLayout(5, 2)); // Alterado para 5 linhas
        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);
        panel.add(new JLabel("Telefone:"));
        panel.add(txtTelefone);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Endereço:"));
        panel.add(txtEndereco);
        return panel;
    }

    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(buttonSalvar);
        panel.add(buttonCancelar);
        return panel;
    }

    private void salvarCliente() {
        // Update cliente with the data from fields
        cliente.setNome(txtNome.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setEndereco(txtEndereco.getText());

        // Try to save the updated cliente
        if (controller.updateCliente(cliente)) { // Ensure method name is correct
            updated = true;
            dispose(); // Close dialog after successful update
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isUpdated() {
        return updated; // Return whether the cliente was updated
    }
}
