package org.estacionaai.view.dialogs;

import org.estacionaai.model.VO.VagaVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarVagasDialog extends JDialog {
    private JTextField txtDescricao;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private JCheckBox chkOcupada;
    private boolean atualizado;
    private VagaVO vaga;

    public AdicionarVagasDialog(Frame owner) {
        super(owner, "Adicionar Vaga", true);
        this.vaga = new VagaVO();

        initComponents();

        setLayout(new BorderLayout());
        add(criarPainelCampos(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    private void initComponents() {
        txtDescricao = new JTextField(10);
        chkOcupada = new JCheckBox("Ocupada");
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
                cancelar();
            }
        });
    }

    private JPanel criarPainelCampos() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Descrição:"));
        panel.add(txtDescricao);
        panel.add(new JLabel("Ocupada:"));
        panel.add(chkOcupada);
        return panel;
    }

    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(btnSalvar);
        panel.add(btnCancelar);
        return panel;
    }

    private void salvar() {
        try {
            vaga.setDescricao(txtDescricao.getText());
            vaga.setOcupada(chkOcupada.isSelected());
            atualizado = true;
            setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar a vaga", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelar() {
        atualizado = false;
        setVisible(false);
    }

    public boolean isAtualizado() {
        return atualizado;
    }

    public VagaVO getVaga() {
        return vaga;
    }
}
