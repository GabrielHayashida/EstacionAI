package org.estacionaai.view;

import com.formdev.flatlaf.FlatDarkLaf;
import org.estacionaai.controller.VeiculoController;
import org.estacionaai.model.DAO.VeiculoDAO;
import org.estacionaai.model.VO.VeiculoVO;
import org.estacionaai.view.TabelaVeiculosVisao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


public class TelaInicialVisao extends JPanel{
    private JTable tabela;
    private VeiculoController controller;
    public TelaInicialVisao() {
        // Define o layout da tela inicial
        setLayout(new BorderLayout());

        // Cria a barra de menu com os menus Vagas e Usuário
        JMenuBar menuBar = criarBarraMenu();
        add(menuBar, BorderLayout.NORTH);

        // Cria a tabela de veículos
        criarTabelaVeiculos();
    }private JMenuBar criarBarraMenu() {
        // Cria o menu Vagas
        JMenu menuVagas = new JMenu("Vagas");
        JMenuItem vagasDisponiveis = new JMenuItem("Vagas Disponíveis");
        JMenuItem vagasOcupadas = new JMenuItem("Vagas Ocupadas");
        menuVagas.add(vagasDisponiveis);
        menuVagas.add(vagasOcupadas);

        // Cria o menu Usuário
        JMenu menuUsuario = new JMenu("Usuário");
        JMenuItem editarConta = new JMenuItem("Editar Conta");
        JMenuItem configuracoes = new JMenuItem("Configurações");
        JMenuItem sair = new JMenuItem("Sair");
        menuUsuario.add(editarConta);
        menuUsuario.add(configuracoes);
        menuUsuario.addSeparator();
        menuUsuario.add(sair);

        // Cria a barra de menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuVagas);
        menuBar.add(menuUsuario);

        return menuBar;
    }

    private void criarTabelaVeiculos() {
        // Dados da tabela
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        this.controller = new VeiculoController(veiculoDAO);
        ArrayList<VeiculoVO> veiculos = controller.getVeiculos();

        // Cabeçalho da tabela
        String[] colunas = {"Placa", "Modelo", "Cor", "Vaga Ocupada"};

        // Modelo da tabela
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        // Preenche o modelo com os dados dos veículos
        for (VeiculoVO veiculo : veiculos) {
            Object[] rowData = {veiculo.getPlaca(), veiculo.getModelo(), veiculo.getCor(), veiculo.isVaga()};
            model.addRow(rowData);
        }

        // Criando a tabela com o modelo
        tabela = new JTable(model);
        tabela.setPreferredScrollableViewportSize(new Dimension(400, 200));
        tabela.setFillsViewportHeight(true);

        // Adicionando a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabela);

        // Adicionando o JScrollPane ao layout da JPanel
        add(scrollPane, BorderLayout.CENTER);
    }

        public static void main (String[]args){
            SwingUtilities.invokeLater(() -> {
                try {
                    // Define FlatLaf como o Look and Feel
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }

                // Cria um VeiculoDAO para passar para a TelaInicialVisao
                VeiculoDAO veiculoDAO = new VeiculoDAO();

                // Cria um JFrame para exibir a tela
                JFrame frame = new JFrame("Tela Inicial com JTable");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Instancia a tela inicial
                TelaInicialVisao telaInicial = new TelaInicialVisao();

                // Adiciona a tela inicial ao frame
                frame.getContentPane().add(telaInicial);

                frame.pack();
                frame.setLocationRelativeTo(null); // Centraliza a janela na tela
                frame.setVisible(true);
            });

        }
    }

