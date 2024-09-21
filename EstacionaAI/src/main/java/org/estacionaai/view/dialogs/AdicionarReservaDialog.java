package org.estacionaai.view.dialogs;

import org.estacionaai.model.VO.ReservaVO;
import org.estacionaai.model.VO.VeiculoVO;
import org.estacionaai.controller.VeiculoController;
import org.estacionaai.controller.VagaController;
import org.estacionaai.model.VO.VagaVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import com.toedter.calendar.JDateChooser;

public class AdicionarReservaDialog extends JDialog {
    private JComboBox<VeiculoVO> cmbVeiculos;
    private JComboBox<VagaVO> cmbVagas;
    private JDateChooser dateChooserEntrada;
    private JDateChooser dateChooserSaida;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private boolean atualizado;
    private ReservaVO reserva;

    private VeiculoController veiculoController;
    private VagaController vagaController;

    public AdicionarReservaDialog(Frame owner, VeiculoController veiculoController, VagaController vagaController) {
        super(owner, "Adicionar Reserva", true);
        this.veiculoController = veiculoController;
        this.vagaController = vagaController;
        this.reserva = new ReservaVO();

        initComponents();
        carregarVeiculos();
        carregarVagas();

        setLayout(new BorderLayout());
        add(criarPainelCampos(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        cmbVeiculos = new JComboBox<>();
        cmbVagas = new JComboBox<>();
        dateChooserEntrada = new JDateChooser();
        dateChooserSaida = new JDateChooser();
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(this::salvar);
        btnCancelar.addActionListener(e -> cancel());
    }

    private JPanel criarPainelCampos() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Veículo:"));
        panel.add(cmbVeiculos);
        panel.add(new JLabel("ID da Vaga:"));
        panel.add(cmbVagas);
        panel.add(new JLabel("Data de Entrada:"));
        panel.add(dateChooserEntrada);
        panel.add(new JLabel("Data de Saída:"));
        panel.add(dateChooserSaida);
        return panel;
    }

    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(btnSalvar);
        panel.add(btnCancelar);
        return panel;
    }

    private void salvar(ActionEvent e) {
        try {
            VeiculoVO veiculoSelecionado = (VeiculoVO) cmbVeiculos.getSelectedItem();
            VagaVO vagaSelecionada = (VagaVO) cmbVagas.getSelectedItem();

            if (veiculoSelecionado == null || vagaSelecionada == null) {
                throw new IllegalArgumentException("Veículo ou vaga não podem estar vazios.");
            }

            reserva.setVeiculoPlaca(veiculoSelecionado.getPlaca());
            reserva.setVagaId(vagaSelecionada.getId());

            if (dateChooserEntrada.getDate() != null && dateChooserSaida.getDate() != null) {
                LocalDateTime dataEntrada = LocalDateTime.ofInstant(dateChooserEntrada.getDate().toInstant(), ZoneId.systemDefault());
                LocalDateTime dataSaida = LocalDateTime.ofInstant(dateChooserSaida.getDate().toInstant(), ZoneId.systemDefault());
                reserva.setDataHoraEntrada(dataEntrada);
                reserva.setDataHoraSaida(dataSaida);
            } else {
                throw new IllegalArgumentException("Data de entrada e/ou saída não podem ser nulas.");
            }

            atualizado = true;
            setVisible(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar reserva: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancel() {
        atualizado = false;
        setVisible(false);
    }

    private void carregarVeiculos() {
        ArrayList<VeiculoVO> veiculos = (ArrayList<VeiculoVO>) veiculoController.getVeiculos("");
        DefaultComboBoxModel<VeiculoVO> modelo = new DefaultComboBoxModel<>();
        for (VeiculoVO veiculo : veiculos) {
            modelo.addElement(veiculo);
        }
        cmbVeiculos.setModel(modelo);
    }

    private void carregarVagas() {
        ArrayList<VagaVO> vagas = vagaController.getVagas("");
        DefaultComboBoxModel<VagaVO> modelo = new DefaultComboBoxModel<>();
        for (VagaVO vaga : vagas) {
            modelo.addElement(vaga);
        }
        cmbVagas.setModel(modelo);
    }

    public boolean isAtualizado() {
        return atualizado;
    }

    public ReservaVO getReserva() {
        return reserva;
    }
}
