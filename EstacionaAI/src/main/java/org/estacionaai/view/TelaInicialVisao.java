package org.estacionaai.view;

import com.formdev.flatlaf.FlatDarkLaf;
import org.estacionaai.controller.ClienteController;
import org.estacionaai.controller.ReservaController;
import org.estacionaai.controller.VagaController;
import org.estacionaai.controller.VeiculoController;
import org.estacionaai.model.DTO.ClienteDTO;
import org.estacionaai.model.DTO.ReservaDTO;
import org.estacionaai.model.DTO.VagaDTO;
import org.estacionaai.model.DTO.VeiculoDTO;

import javax.swing.*;
import java.awt.*;

public class TelaInicialVisao extends JFrame {

    private JDesktopPane desktopPane;

    public TelaInicialVisao() {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());


        } catch (Exception e) {
            e.printStackTrace();
        }


        setTitle("Tela Inicial");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        setJMenuBar(criarBarraMenu());


        desktopPane = new JDesktopPane();
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.add(new JLabel("Bem-vindo ao Sistema EstacionaAI!", SwingConstants.CENTER), BorderLayout.CENTER);
        painelPrincipal.add(desktopPane, BorderLayout.CENTER);
        add(painelPrincipal);


        setVisible(true);
    }

    private JMenuBar criarBarraMenu() {

        JMenu menuVagas = new JMenu("Vagas");
        JMenuItem vagasDisponiveis = new JMenuItem("Vagas Disponíveis");
        JMenuItem reservas = new JMenuItem("Reservas");
        reservas.addActionListener(e->mostrarTabelaReservas());
        vagasDisponiveis.addActionListener(e -> mostrarTabelaVagas());
        menuVagas.add(vagasDisponiveis);
        menuVagas.add(reservas);


        JMenu menuUsuario = new JMenu("Usuário");
        JMenuItem clientes = new JMenuItem("Clientes");
        JMenuItem configuracoes = new JMenuItem("Configurações");
        JMenuItem sair = new JMenuItem("Sair");
        clientes.addActionListener(e->mostrarTabelaClientes());
        menuUsuario.add(clientes);
        menuUsuario.add(configuracoes);
        menuUsuario.addSeparator();
        menuUsuario.add(sair);


        JMenu menuVeiculos = new JMenu("Veículos");
        JMenuItem mostrarVeiculos = new JMenuItem("Mostrar Veículos");
        mostrarVeiculos.addActionListener(e -> mostrarTabelaVeiculos());
        menuVeiculos.add(mostrarVeiculos);






        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuVagas);
        menuBar.add(menuUsuario);
        menuBar.add(menuVeiculos);

        return menuBar;
    }

    private void mostrarTabelaVagas() {
        VagaDTO vagaDTO = new VagaDTO();
        VagaController controller = new VagaController(vagaDTO);
        TabelaVagasVisao tabelaVagasVisao = new TabelaVagasVisao(controller);
        desktopPane.add(tabelaVagasVisao);
        tabelaVagasVisao.setVisible(true);
        Dimension desktopSize = desktopPane.getSize();
        Dimension jInternalFrameSize = tabelaVagasVisao.getSize();
        int x = (desktopSize.width - jInternalFrameSize.width) / 2;
        int y = (desktopSize.height - jInternalFrameSize.height) / 2;
        tabelaVagasVisao.setLocation(x, y);

    }

    private void mostrarTabelaClientes() {
        ClienteDTO clienteDTO = new ClienteDTO();
        ClienteController controller = new ClienteController(clienteDTO);
        TabelaClienteVisao tabelaClienteVisao = new TabelaClienteVisao(controller);
        desktopPane.add(tabelaClienteVisao);
        tabelaClienteVisao.setVisible(true);
        Dimension desktopSize = desktopPane.getSize();
        Dimension jInternalFrameSize = tabelaClienteVisao.getSize();
        int x = (desktopSize.width - jInternalFrameSize.width) / 2;
        int y = (desktopSize.height - jInternalFrameSize.height) / 2;
        tabelaClienteVisao.setLocation(x, y);
    }

    private void mostrarTabelaReservas() {
        ReservaDTO reservaDTO = new ReservaDTO();
        ReservaController controller = new ReservaController(reservaDTO);
        TabelaReservaVisao tabelaReservaVisao = new TabelaReservaVisao(controller);
        desktopPane.add(tabelaReservaVisao);
        tabelaReservaVisao.setVisible(true);
        Dimension desktopSize = desktopPane.getSize();
        Dimension jInternalFrameSize = tabelaReservaVisao.getSize();
        int x = (desktopSize.width - jInternalFrameSize.width) / 2;
        int y = (desktopSize.height - jInternalFrameSize.height) / 2;
        tabelaReservaVisao.setLocation(x, y);
    }

    private void mostrarTabelaVeiculos() {
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        VeiculoController controller = new VeiculoController(veiculoDTO);

        // Passa o controlador para a TabelaVeiculosVisao
        TabelaVeiculosVisao tabelaVeiculosVisao = new TabelaVeiculosVisao(controller);
        desktopPane.add(tabelaVeiculosVisao);
        tabelaVeiculosVisao.setVisible(true);
        Dimension desktopSize = desktopPane.getSize();
        Dimension jInternalFrameSize = tabelaVeiculosVisao.getSize();
        int x = (desktopSize.width - jInternalFrameSize.width) / 2;
        int y = (desktopSize.height - jInternalFrameSize.height) / 2;
        tabelaVeiculosVisao.setLocation(x, y);
    }

    public static void main(String[] args) {
        // Executa a interface em um thread de eventos de interface gráfica
        SwingUtilities.invokeLater(() -> new TelaInicialVisao());
    }
}
