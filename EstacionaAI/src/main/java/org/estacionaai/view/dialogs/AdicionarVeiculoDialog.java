package org.estacionaai.view.dialogs;

import org.estacionaai.controller.ClienteController;
import org.estacionaai.model.VO.ClienteVO;
import org.estacionaai.model.VO.VeiculoVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdicionarVeiculoDialog extends JDialog {
    private JTextField txtPlaca;
    private JTextField txtModelo;
    private JTextField txtCor;
    private JComboBox<ClienteVO> cmbClientes;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private boolean atualizado;
    private VeiculoVO veiculo;
    private ClienteController clienteController;

    public AdicionarVeiculoDialog(Frame owner, ClienteController clienteController) {
        super(owner, "Adicionar Veículo", true);
        this.veiculo = new VeiculoVO();
        this.clienteController = clienteController;

        initComponents();
        carregarClientes();

        setLayout(new BorderLayout());
        add(criarPainelCampos(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        txtPlaca = new JTextField(10);
        txtModelo = new JTextField(10);
        txtCor = new JTextField(10);
        cmbClientes = new JComboBox<>();
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

    private JPanel criarPainelCampos() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Placa:"));
        panel.add(txtPlaca);
        panel.add(new JLabel("Modelo:"));
        panel.add(txtModelo);
        panel.add(new JLabel("Cor:"));
        panel.add(txtCor);
        panel.add(new JLabel("Cliente:"));
        panel.add(cmbClientes);
        return panel;
    }

    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(btnSalvar);
        panel.add(btnCancelar);
        return panel;
    }

    private void carregarClientes() {
        ArrayList<ClienteVO> clientes = (ArrayList<ClienteVO>) clienteController.getClientes("");  // Carrega todos os clientes
        DefaultComboBoxModel<ClienteVO> modelo = new DefaultComboBoxModel<>();
        for (ClienteVO cliente : clientes) {
            modelo.addElement(cliente);
        }
        cmbClientes.setModel(modelo);
    }

    private void salvar() {
        if (txtPlaca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "A placa não pode ser vazia.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            veiculo.setPlaca(txtPlaca.getText());
            veiculo.setModelo(txtModelo.getText());
            veiculo.setCor(txtCor.getText());
            ClienteVO clienteSelecionado = (ClienteVO) cmbClientes.getSelectedItem();
            veiculo.setClienteId(clienteSelecionado != null ? clienteSelecionado.getId() : -1);
            atualizado = true;
            setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar veículo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancel() {
        atualizado = false;
        setVisible(false);
    }

    public boolean isAtualizado() {
        return atualizado;
    }

    public VeiculoVO getVeiculo() {
        return veiculo;
    }
}
