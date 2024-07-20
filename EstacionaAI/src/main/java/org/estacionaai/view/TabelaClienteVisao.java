package org.estacionaai.view;

import org.estacionaai.controller.ClienteController;
import org.estacionaai.model.VO.ClienteVO;
import org.estacionaai.view.dialogs.EditarClienteDialog;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TabelaClienteVisao extends JInternalFrame {

    private JTable tabela;
    private JTextField txtSearch;

    private JButton buttonEditCliente;
    private JButton buttonDelCliente;
    private ClienteController controller;
    private ArrayList<ClienteVO> clientes;

    public TabelaClienteVisao(ClienteController controller) {
        super("Gerenciamento de Clientes", true, true, false, true);
        setSize(800, 600);
        setLayout(new BorderLayout());
        this.controller = controller;



        initComponents();
        criarTabelaCliente();
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


        buttonEditCliente = new JButton("Editar");
        buttonDelCliente = new JButton("Deletar");


        topPanel.add(buttonEditCliente);
        topPanel.add(buttonDelCliente);

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



        buttonEditCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });

        buttonDelCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarCliente();
            }
        });
    }

    private void criarTabelaCliente() {
        String[] colunas = {"ID", "Nome", "Telefone", "Email", "Endereço"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        tabela.setModel(modelo);
    }

    private void atualizaTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);


        String pesquisa = txtSearch.getText();
        clientes = controller.listarClientes(pesquisa);

        for (ClienteVO cliente : clientes) {
            Object[] linha = {
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getTelefone(),
                    cliente.getEmail(),
                    cliente.getEndereco()
            };
            modelo.addRow(linha);
        }
    }



    private void editarCliente() {
        if (tabela.getSelectedRowCount() == 1) {
            int selectedRow = tabela.getSelectedRow();
            ClienteVO cliente = clientes.get(selectedRow);

            EditarClienteDialog dialog = new EditarClienteDialog((Frame) SwingUtilities.getWindowAncestor(this), controller, cliente);
            dialog.setVisible(true);

            if (dialog.isUpdated()) {
                atualizaTabela();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.");
        }
    }

    private void deletarCliente() {
        if (tabela.getSelectedRowCount() == 1) {
            int selectedRow = tabela.getSelectedRow();
            ClienteVO cliente = clientes.get(selectedRow);
            int resposta = JOptionPane.showConfirmDialog(this, "Você tem certeza que deseja excluir o cliente " + cliente.getNome() + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                // Implementar lógica de exclusão de cliente
                controller.deletarCliente(cliente.getId());
                atualizaTabela();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
        }
    }
}
