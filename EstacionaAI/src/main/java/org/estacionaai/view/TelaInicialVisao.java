package org.estacionaai.view;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicialVisao extends JPanel{
    private JTable tabela;

    public TelaInicialVisao() {
        initComponents();
    }

    private void initComponents() {
        // Dados da tabela
        Object[][] data = {
                {"Nome 1", "Sobrenome 1", "Email 1"},
                {"Nome 2", "Sobrenome 2", "Email 2"},
                {"Nome 3", "Sobrenome 3", "Email 3"},
                {"Nome 4", "Sobrenome 4", "Email 4"}
        };

        // Cabeçalho da tabela
        String[] colunas = {"Nome", "Sobrenome", "Email"};

        // Modelo da tabela
        DefaultTableModel model = new DefaultTableModel(data, colunas);

        // Criando a tabela com o modelo
        tabela = new JTable(model);
        tabela.setPreferredScrollableViewportSize(new Dimension(400, 200));
        tabela.setFillsViewportHeight(true);

        // Adicionando a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Adicionando o JScrollPane ao layout da JPanel
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        try {
            // Define FlatLaf como o Look and Feel
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Criar uma instância de TelaInicialVisao
        TelaInicialVisao tela = new TelaInicialVisao();

        // Criar um JFrame para exibir a tela
        JFrame frame = new JFrame("Tela Inicial com JTable");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(tela);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela
        frame.setVisible(true);
    }
}
