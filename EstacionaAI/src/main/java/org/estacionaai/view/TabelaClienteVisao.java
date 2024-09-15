package org.estacionaai.view;

import org.estacionaai.controller.ClienteController;
import org.estacionaai.model.DTO.ClienteDTO;
import org.estacionaai.model.VO.ClienteVO;
import org.estacionaai.view.dialogs.EditarClienteDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class TabelaClienteVisao extends JInternalFrame {

    private JTable tabela;
    private JTextField txtSearch;
    private JButton buttonEditCliente;
    private JButton buttonDelCliente;
    private ClienteController controller;
    private List<ClienteVO> clientes;

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
        JPanel panel = new JPanel(new BorderLayout());

        // Painel superior com campos de pesquisa e botões
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtSearch = new JTextField(20);
        buttonEditCliente = new JButton("Editar");
        buttonDelCliente = new JButton("Deletar");

        topPanel.add(new JLabel("Pesquisar:"));
        topPanel.add(txtSearch);
        topPanel.add(buttonEditCliente);
        topPanel.add(buttonDelCliente);
        panel.add(topPanel, BorderLayout.NORTH);

        // Tabela
        JScrollPane scrollPane = new JScrollPane();
        tabela = new JTable();
        scrollPane.setViewportView(tabela);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);

        // Eventos
        configurarEventos();
    }

    private void configurarEventos() {
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                atualizaTabela();
            }
        });

        buttonEditCliente.addActionListener(this::editarCliente);
        buttonDelCliente.addActionListener(this::deletarCliente);
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

    private void editarCliente(ActionEvent e) {
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

    private void deletarCliente(ActionEvent e) {
        if (tabela.getSelectedRowCount() == 1) {
            int selectedRow = tabela.getSelectedRow();
            ClienteVO cliente = clientes.get(selectedRow);
            int resposta = JOptionPane.showConfirmDialog(this, "Você tem certeza que deseja excluir o cliente " + cliente.getNome() + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                controller.deletarCliente(cliente.getId());
                atualizaTabela();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
        }
    }

    public static void main(String[] args) {
        // Configuração do look and feel (opcional)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Criação da interface gráfica
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistema de Gerenciamento de Clientes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Instancia o ClienteDTO e o ClienteController
            ClienteDTO clienteDTO = new ClienteDTO();
            ClienteController clienteController = new ClienteController(clienteDTO);

            // Adiciona a tela de visualização de clientes ao JFrame
            TabelaClienteVisao tabelaClienteVisao = new TabelaClienteVisao(clienteController);
            frame.add(tabelaClienteVisao);
            frame.setVisible(true);
        });
    }
}
