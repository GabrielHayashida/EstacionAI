package org.estacionaai.view.dialogs;

import org.estacionaai.controller.VeiculoController;
import org.estacionaai.model.VO.VeiculoVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarVeiculoDialog extends JDialog {
    private JTextField txtPlaca;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextField txtCor;
    private JTextField txtClienteId;
    private JCheckBox chkAcesso;
    private JButton buttonSalvar;
    private JButton buttonCancelar;
    private VeiculoController controller;
    private VeiculoVO veiculo;
    private boolean updated = false;

    public EditarVeiculoDialog(Frame owner, VeiculoController controller, VeiculoVO veiculo) {
        super(owner, "Editar Veículo", true);
        this.controller = controller;
        this.veiculo = veiculo;

        initComponents();
        setLayout(new BorderLayout());
        add(criarPainelCampos(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        txtPlaca = new JTextField(veiculo.getPlaca());
        txtPlaca.setEnabled(false); // Placa não pode ser alterada
        txtMarca = new JTextField(veiculo.getMarca());
        txtModelo = new JTextField(veiculo.getModelo());
        txtCor = new JTextField(veiculo.getCor());
        txtClienteId = new JTextField(String.valueOf(veiculo.getClienteId()));
        chkAcesso = new JCheckBox("Acesso Permitido", veiculo.isAcesso());

        buttonSalvar = new JButton("Salvar");
        buttonCancelar = new JButton("Cancelar");

        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarVeiculo();
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
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Placa:"));
        panel.add(txtPlaca);
        panel.add(new JLabel("Marca:"));
        panel.add(txtMarca);
        panel.add(new JLabel("Modelo:"));
        panel.add(txtModelo);
        panel.add(new JLabel("Cor:"));
        panel.add(txtCor);
        panel.add(new JLabel("ID Cliente:"));
        panel.add(txtClienteId);
        panel.add(new JLabel("Acesso:"));
        panel.add(chkAcesso);
        return panel;
    }

    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(buttonSalvar);
        panel.add(buttonCancelar);
        return panel;
    }

    private void salvarVeiculo() {
        try {
            veiculo.setMarca(txtMarca.getText());
            veiculo.setModelo(txtModelo.getText());
            veiculo.setCor(txtCor.getText());
            veiculo.setClienteId(Integer.parseInt(txtClienteId.getText()));
            veiculo.setAcesso(chkAcesso.isSelected());

            if (controller.updateVeiculo(veiculo)) {
                updated = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar veículo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID Cliente inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isUpdated() {
        return updated;
    }
}
