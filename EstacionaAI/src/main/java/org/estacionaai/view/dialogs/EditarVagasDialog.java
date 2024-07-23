package org.estacionaai.view.dialogs;

import org.estacionaai.model.VO.VagaVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarVagasDialog extends JDialog {
    private JTextField txtNumero;
    private JTextField txtSetor;
    private JTextField txtTipo;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private JCheckBox chkOcuped;
    private boolean atualizado;
    private VagaVO vaga;

    public EditarVagasDialog(Frame owner, VagaVO vaga) {
        super(owner, "Editar Vaga", true);
        this.vaga = vaga;

        initComponents();
        preencherCampos();

        setLayout(new BorderLayout());
        add(criarPainelCampos(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        txtNumero = new JTextField(10);
        txtSetor = new JTextField(10);
        txtTipo = new JTextField(10);
        chkOcuped = new JCheckBox("Ocupada", vaga.getOcupada());
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

    private JPanel criarPainelCampos() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Número:"));
        panel.add(txtNumero);
        panel.add(new JLabel("Setor:"));
        panel.add(txtSetor);
        panel.add(new JLabel("Tipo:"));
        panel.add(txtTipo);
        panel.add(new JLabel("Ocupada:"));
        panel.add(chkOcuped);
        return panel;
    }

    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(btnSalvar);
        panel.add(btnCancelar);
        return panel;
    }

    private void preencherCampos() {
        txtNumero.setText(String.valueOf(vaga.getNumero()));
        txtSetor.setText(vaga.getSetor());
        txtTipo.setText(vaga.getTipo());
    }

    private void salvar() {
        try {
            vaga.setNumero(Integer.parseInt(txtNumero.getText()));
            vaga.setSetor(txtSetor.getText());
            vaga.setTipo(txtTipo.getText());
            vaga.setOcupada(chkOcuped.isSelected());
            atualizado = true;
            setVisible(false);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número inválido", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancel() {
        atualizado = false;
        setVisible(false);
    }

    public boolean isAtualizado() {
        return atualizado;
    }
}
