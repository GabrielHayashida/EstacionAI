package org.estacionaai.view;

import com.formdev.flatlaf.FlatDarkLaf;
import org.estacionaai.controller.VeiculoController;
import org.estacionaai.model.DAO.VeiculoDAO;
import org.estacionaai.model.VO.VeiculoVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TabelaVeiculosVisao extends JPanel{
        private JTable tabela;
        private VeiculoController controller;
        private VeiculoDAO veiculoDAO;



    public TabelaVeiculosVisao() {

            this.veiculoDAO = new VeiculoDAO();
            this.controller = new VeiculoController(veiculoDAO);
            initComponents();
        }

        private void initComponents() {
            // Dados da tabela
            ArrayList<VeiculoVO> veiculos = controller.getVeiculos();

            // Cabeçalho da tabela
            String[] colunas = {"Placa", "Modelo", "Cor", "Vaga Ocupada"};

            // Modelo da tabela
            DefaultTableModel model = new DefaultTableModel(colunas, 0);

            // Preenche o modelo com os dados dos veículos
            for (VeiculoVO veiculo : veiculos) {
                Object[] rowData = {veiculo.getPlaca(), veiculo.getModelo(), veiculo.getCor(),veiculo.isVaga()};
                model.addRow(rowData);
            }

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

            // Criar uma instância de TelaCarrosVisao
            TabelaVeiculosVisao tela = new TabelaVeiculosVisao();

            // Criar um JFrame para exibir a tela
            JFrame frame = new JFrame("Tela Inicial com JTable");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(tela);
            frame.pack();
            frame.setLocationRelativeTo(null); // Centraliza a janela na tela
            frame.setVisible(true);
        }
}
