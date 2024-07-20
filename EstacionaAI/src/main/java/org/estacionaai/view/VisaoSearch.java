package org.estacionaai.view;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisaoSearch  extends JInternalFrame {
    private JTable tabela;
    private JButton editCliente = new JButton();
    private JButton rmvCliente = new JButton();

    public VisaoSearch() {

    }

    private void editarCliente() {
    }

    private void criarTabela() {
        String[] colunas = {"id", "nome", "telefone", "email", "endereco"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modelo);


        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
            JFrame frame = new JFrame("Testando VisaoSearch");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane);

            VisaoSearch visaoSearch = new VisaoSearch();
            desktopPane.add(visaoSearch);
            visaoSearch.setVisible(true);

            frame.setVisible(true);
        });
    }
}
