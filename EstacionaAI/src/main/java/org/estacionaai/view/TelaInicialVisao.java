import com.formdev.flatlaf.FlatDarkLaf;
import org.estacionaai.model.DAO.VeiculoDAO;
import org.estacionaai.view.TabelaVeiculosVisao;
import javax.swing.*;

public class TelaInicialVisao extends JPanel {
    private TabelaVeiculosVisao tabelaVeiculos;

    public TelaInicialVisao(VeiculoDAO veiculoDAO) {
        initComponents(veiculoDAO);
    }

    private void initComponents(VeiculoDAO veiculoDAO) {
        try {
            // Define FlatLaf como o Look and Feel
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Instancia a tabela de veículos
        tabelaVeiculos = new TabelaVeiculosVisao();

        // Adiciona a tabela de veículos ao painel principal
        add(tabelaVeiculos);
    }

    public static void main(String[] args) {
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
            TelaInicialVisao telaInicial = new TelaInicialVisao(veiculoDAO);

            // Adiciona a tela inicial ao frame
            frame.getContentPane().add(telaInicial);

            frame.pack();
            frame.setLocationRelativeTo(null); // Centraliza a janela na tela
            frame.setVisible(true);
        });
    }
}
