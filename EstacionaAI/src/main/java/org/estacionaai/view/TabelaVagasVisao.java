package org.estacionaai.view;

import org.estacionaai.controller.VagaController;
import org.estacionaai.model.VO.VagaVO;
import org.estacionaai.view.dialogs.EditarVagasDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TabelaVagasVisao extends JInternalFrame {
    private JTable tabela;
    private JTextField txtSearch;
    private JButton buttonEditVaga;
    private JButton buttonDelVaga;
    private VagaController controller;
    private ArrayList<VagaVO> vagas;

    public TabelaVagasVisao(VagaController controller) {
        super("Gerenciamento de Vagas", true, true, false, true);
        setSize(600, 400);
        setLayout(new BorderLayout());
        this.controller = controller;

        initComponents();
        criarTabelaVaga();
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

        buttonEditVaga = new JButton("Editar");
        buttonDelVaga = new JButton("Deletar");

        topPanel.add(buttonEditVaga);
        topPanel.add(buttonDelVaga);

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

        buttonEditVaga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarVaga();
            }
        });

        buttonDelVaga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarVaga();
            }
        });
    }

    private void criarTabelaVaga() {
        String[] colunas = {"ID", "Número", "Setor", "Tipo"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        tabela.setModel(modelo);
    }

    private void atualizaTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);

        String pesquisa = txtSearch.getText();
        vagas = controller.getVagas(pesquisa);

        for (VagaVO vaga : vagas) {
            Object[] linha = {
                    vaga.getId(),
                    vaga.getNumero(),
                    vaga.getSetor(),
                    vaga.getTipo()
            };
            modelo.addRow(linha);
        }
    }

    private void editarVaga() {
        if (tabela.getSelectedRowCount() == 1) {
            int selectedRow = tabela.getSelectedRow();
            VagaVO vaga = vagas.get(selectedRow);

            EditarVagasDialog dialog = new EditarVagasDialog((Frame) SwingUtilities.getWindowAncestor(this), vaga);
            dialog.setVisible(true);

            if (dialog.isAtualizado()) {
                if (controller.updateVaga(vaga)) {
                    JOptionPane.showMessageDialog(this, "Vaga atualizada com sucesso.");
                    atualizaTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao atualizar vaga.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma vaga para editar.");
        }
    }


    private void deletarVaga() {
        if (tabela.getSelectedRowCount() == 1) {
            int selectedRow = tabela.getSelectedRow();
            VagaVO vaga = vagas.get(selectedRow);
            int resposta = JOptionPane.showConfirmDialog(this, "Você tem certeza que deseja excluir a vaga " + vaga.getNumero() + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                // Implementar lógica de exclusão de vaga
                controller.deleteVaga(String.valueOf(vaga.getId()));
                atualizaTabela();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma vaga para excluir.");
        }
    }
}
