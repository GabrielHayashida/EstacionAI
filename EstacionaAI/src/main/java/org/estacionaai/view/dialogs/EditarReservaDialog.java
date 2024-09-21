package org.estacionaai.view.dialogs;

import org.estacionaai.model.VO.ReservaVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarReservaDialog extends JDialog {
    private JTextField txtPlacaVeiculo;
    private JTextField txtIdVaga;
    private JTextField txtDataEntrada;
    private JTextField txtDataSaida;

    private JButton btnSalvar;
    private JButton btnCancelar;
    private boolean atualizado;

    public EditarReservaDialog(Frame owner, ReservaVO reserva) {
        super(owner, "Editar Reserva", true);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Placa do Veículo:"));
        txtPlacaVeiculo = new JTextField(reserva.getVeiculoPlaca());
        add(txtPlacaVeiculo);

        add(new JLabel("ID da Vaga:"));
        txtIdVaga = new JTextField(String.valueOf(reserva.getVagaId()));
        add(txtIdVaga);

        add(new JLabel("Data de Entrada:"));
        txtDataEntrada = new JTextField(reserva.getDataHoraEntrada().toString());
        add(txtDataEntrada);

        add(new JLabel("Data de Saída:"));
        txtDataSaida = new JTextField(reserva.getDataHoraSaida() != null ? reserva.getDataHoraSaida().toString() : "");
        add(txtDataSaida);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserva.setVeiculoPlaca(txtPlacaVeiculo.getText());
                reserva.setVagaId(Integer.parseInt(txtIdVaga.getText()));
                reserva.setDataHoraEntrada(java.time.LocalDateTime.parse(txtDataEntrada.getText()));
                reserva.setDataHoraSaida(txtDataSaida.getText().isEmpty() ? null : java.time.LocalDateTime.parse(txtDataSaida.getText()));
                atualizado = true;
                setVisible(false);
            }
        });
        add(btnSalvar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizado = false;
                setVisible(false);
            }
        });
        add(btnCancelar);

        pack();
        setLocationRelativeTo(owner);
    }

    public boolean isAtualizado() {
        return atualizado;
    }
}
