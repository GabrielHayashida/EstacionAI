package org.estacionaai.view;

import org.estacionaai.controller.ClienteController;
import org.estacionaai.controller.VeiculoController;
import org.estacionaai.model.DTO.ClienteDTO;
import org.estacionaai.model.VO.VagaVO;
import org.estacionaai.model.VO.VeiculoVO;
import org.estacionaai.view.dialogs.AdicionarVagasDialog;
import org.estacionaai.view.dialogs.AdicionarVeiculoDialog;
import org.estacionaai.view.dialogs.EditarVeiculoDialog;

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
    private JButton buttonAddVeiculo;
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
        buttonAddVeiculo = new JButton("Adicionar");

        topPanel.add(buttonEditVeiculo);
        topPanel.add(buttonDelVeiculo);
        topPanel.add(buttonAddVeiculo);

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
        buttonAddVeiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarVeiculo();
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
    private void adicionarVeiculo() {
        ClienteDTO clienteDTO = new ClienteDTO();  // Instancia corretamente o ClienteDTO
        ClienteController clienteController = new ClienteController(clienteDTO);  // Cria o ClienteController
        AdicionarVeiculoDialog dialog = new AdicionarVeiculoDialog((Frame) SwingUtilities.getWindowAncestor(this), clienteController);
        dialog.setVisible(true);

        if (dialog.isAtualizado()) {
            VeiculoVO novoVeiculo = dialog.getVeiculo();
            if (controller.insertVeiculo(novoVeiculo)) {
                JOptionPane.showMessageDialog(this, "Veículo adicionado com sucesso.");
                atualizaTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao adicionar veículo.");
            }
        }
    }
    private void editarVeiculo() {
        if (tabela.getSelectedRowCount() == 1) {
            int selectedRow = tabela.getSelectedRow();
            VeiculoVO veiculo = veiculos.get(selectedRow);

            EditarVeiculoDialog dialog = new EditarVeiculoDialog((Frame) SwingUtilities.getWindowAncestor(this), controller, veiculo);
            dialog.setVisible(true);

            if (dialog.isUpdated()) {
                atualizaTabela();
            }
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

                controller.deleteVeiculo(veiculo.getPlaca());
                atualizaTabela();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um veículo para excluir.");
        }
    }
}
