package org.estacionaai.view;

import org.estacionaai.controller.VeiculoController;
import org.estacionaai.model.DAO.VeiculoDAO;
import org.estacionaai.model.VO.VeiculoVO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TabelaVeiculosVisao extends JInternalFrame {
    private VeiculoController controller;
    private VeiculoDAO veiculoDAO;
    private ArrayList<VeiculoVO> veiculos;
    private JTable tabela;

    public TabelaVeiculosVisao() {
        this.veiculoDAO = new VeiculoDAO();
        this.controller = new VeiculoController(veiculoDAO);
        inicializarComponentes();
    }

    private void inicializarComponentes() {


        veiculos = controller.getVeiculos();

        String[] colunas = {"Placa", "Modelo", "Cor", "Vaga Ocupada"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (VeiculoVO veiculo : veiculos) {
            Object[] rowData = {veiculo.getPlaca(), veiculo.getModelo(), veiculo.getCor(), veiculo.isVaga()};
            model.addRow(rowData);
        }

        tabela = new JTable(model);
        tabela.setPreferredScrollableViewportSize(new Dimension(400, 200));
        tabela.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tabela);


        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TabelaVeiculosVisao frame = new TabelaVeiculosVisao();
            frame.setVisible(true);
        });
    }
}
