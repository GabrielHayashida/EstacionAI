package org.estacionaai.view;

import org.estacionaai.controller.ReservaController;
import org.estacionaai.controller.VagaController;
import org.estacionaai.controller.VeiculoController;
import org.estacionaai.model.DTO.ClienteDTO;
import org.estacionaai.model.DTO.VagaDTO;
import org.estacionaai.model.DTO.VeiculoDTO;
import org.estacionaai.model.VO.ClienteVO;
import org.estacionaai.model.VO.ReservaVO;
import org.estacionaai.model.VO.VagaVO;
import org.estacionaai.view.dialogs.AdicionarReservaDialog;
import org.estacionaai.view.dialogs.AdicionarVagasDialog;
import org.estacionaai.view.dialogs.EditarReservaDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TabelaReservaVisao extends JInternalFrame {
    private JTable tabela;
    private JTextField txtSearch;
    private JButton buttonEditReserva;
    private JButton buttonDelReserva;
    private JButton buttonAddReserva;
    private ReservaController controller;
    private ArrayList<ReservaVO> reservas;

    public TabelaReservaVisao(ReservaController controller) {
        super("Gerenciamento de Reservas", true, true, false, true);
        setSize(800, 600);
        setLayout(new BorderLayout());
        this.controller = controller;

        initComponents();
        criarTabelaReserva();
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

        buttonEditReserva = new JButton("Editar");
        buttonDelReserva = new JButton("Deletar");
        buttonAddReserva = new JButton("Adicionar");

        topPanel.add(buttonEditReserva);
        topPanel.add(buttonDelReserva);
        topPanel.add(buttonAddReserva);

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

        buttonEditReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarReserva();
            }
        });

        buttonDelReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarReserva();
            }
        });
        buttonAddReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarReserva();
            }
        });
    }


    private void criarTabelaReserva() {
        String[] colunas = {"ID Reserva", "Placa Veículo", "ID Vaga", "Data Entrada", "Data Saída"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        tabela.setModel(modelo);
    }

    private void atualizaTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);

        String pesquisa = txtSearch.getText();
        reservas = controller.getReservas(pesquisa);

        for (ReservaVO reserva : reservas) {
            Object[] linha = {
                    reserva.getId(),
                    reserva.getPlaca_veiculo(),
                    reserva.getId_vaga(),
                    reserva.getData_entrada(),
                    reserva.getData_saida()
            };
            modelo.addRow(linha);
        }
    }



        private void adicionarReserva() {
            VeiculoDTO veiculoDTO = new VeiculoDTO();
            VagaDTO vagaDTO = new VagaDTO(); // Supondo que você tenha uma classe VagaDTO
            VeiculoController veiculoController = new VeiculoController(veiculoDTO);
            VagaController vagaController = new VagaController(vagaDTO); // Instanciar VagaController

            AdicionarReservaDialog dialog = new AdicionarReservaDialog((Frame) SwingUtilities.getWindowAncestor(this), veiculoController, vagaController);
            dialog.setVisible(true);

            if (dialog.isAtualizado()) {
                ReservaVO novaReserva = dialog.getReserva();
                if (controller.insertReserva(novaReserva)) {
                    JOptionPane.showMessageDialog(this, "Reserva adicionada com sucesso.");
                    atualizaTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao adicionar reserva.");
                }
            }
        }

    private void editarReserva() {
        if (tabela.getSelectedRowCount() == 1) {
            int selectedRow = tabela.getSelectedRow();
            int reservaId = (Integer) tabela.getValueAt(selectedRow, 0);
            ReservaVO reserva = controller.getReservaById(reservaId);

            if (reserva != null) {
                EditarReservaDialog dialog = new EditarReservaDialog((Frame) SwingUtilities.getWindowAncestor(this), reserva);
                dialog.setVisible(true);

                if (dialog.isAtualizado()) {
                    if (controller.updateReserva(reserva)) {
                        JOptionPane.showMessageDialog(this, "Reserva atualizada com sucesso.");
                        atualizaTabela();
                    } else {
                        JOptionPane.showMessageDialog(this, "Falha ao atualizar reserva.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Reserva não encontrada.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma reserva para editar.");
        }
    }

    private void deletarReserva() {

    }
}
