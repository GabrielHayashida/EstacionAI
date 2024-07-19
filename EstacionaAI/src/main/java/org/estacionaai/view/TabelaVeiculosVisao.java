package org.estacionaai.view;

import org.estacionaai.controller.VeiculoController;
import org.estacionaai.model.VO.VeiculoVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TabelaVeiculosVisao extends JInternalFrame {

    private JTable tabela;
    private JTextField txtSearch;
    private JButton buttonEditVeiculo;
    private JButton buttonDelVeiculo;
    private VeiculoController controller;
    private ArrayList<VeiculoVO> veiculos;

    public TabelaVeiculosVisao(VeiculoController controller) {

        // Configurações do JInternalFrame
        super("Gerenciamento de Veículos", true, true, false, true);
        setSize(600, 400);
        setLayout(new BorderLayout());
        this.controller = controller;

        initComponents();
        criarTabelaVeiculos();
        atualizaTabela();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Painel superior com campos de pesquisa e botões
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        txtSearch = new JTextField(20);
        topPanel.add(new JLabel("Pesquisar:"));
        topPanel.add(txtSearch);

        buttonEditVeiculo = new JButton("Editar");
        buttonDelVeiculo = new JButton("Deletar");

        topPanel.add(buttonEditVeiculo);
        topPanel.add(buttonDelVeiculo);

        panel.add(topPanel, BorderLayout.NORTH);

        // Tabela
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);

        tabela = new JTable();
        scrollPane.setViewportView(tabela);

        add(panel, BorderLayout.CENTER);

        // Eventos
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                atualizaTabela();
            }
        });

        buttonEditVeiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarVeiculo();
            }
        });

        buttonDelVeiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarVeiculo();
            }
        });
    }

    private void criarTabelaVeiculos() {
        String[] colunas = {"Placa", "Modelo", "Cor", "Ano", "ID Cliente"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        tabela.setModel(modelo);
    }

    private void atualizaTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);

        String pesquisa = txtSearch.getText();
        veiculos = controller.getVeiculos(pesquisa);

        for (VeiculoVO veiculo : veiculos) {
            Object[] linha = {
                    veiculo.getPlaca(),
                    veiculo.getModelo(),
                    veiculo.getCor(),
                    veiculo.getAno(),
                    veiculo.getId_cliente()
            };
            modelo.addRow(linha);
        }
    }

    private void editarVeiculo() {
        if (tabela.getSelectedRowCount() == 1) {
            int selectedRow = tabela.getSelectedRow();
            VeiculoVO veiculo = veiculos.get(selectedRow);
            JOptionPane.showMessageDialog(this, "Editar Veículo: " + veiculo.getPlaca());
            atualizaTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para editar.");
        }
    }

    private void deletarVeiculo() {
        if (tabela.getSelectedRowCount() == 1) {
            int selectedRow = tabela.getSelectedRow();
            VeiculoVO veiculo = veiculos.get(selectedRow);
            int resposta = JOptionPane.showConfirmDialog(this, "Você tem certeza que deseja excluir o veículo com placa " + veiculo.getPlaca() + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                // Implementar lógica de exclusão de veículo
                controller.deleteVeiculo(veiculo.getPlaca());
                atualizaTabela();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para excluir.");
        }
    }
}
