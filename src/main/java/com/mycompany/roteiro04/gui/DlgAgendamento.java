package com.mycompany.roteiro04.gui;

import com.mycompany.roteiro04.gerenciador.GerenciadorConsulta;
import com.mycompany.roteiro04.model.Consulta;
import com.mycompany.roteiro04.persistencia.SerializacaoCSVConsulta;
import javax.swing.*;
import java.awt.*;

public class DlgAgendamento extends JDialog {

    private JButton    btnAddPaciente;
    private JButton    btnAddMedico;
    private JButton    btnCancelar;
    private JButton    btnFecharConsulta;
    private JTextField edtData;
    private JTextField edtHora;
    private JTextArea  edtConsulta;

    private Consulta novaConsulta;

    public DlgAgendamento(Frame parent, boolean modal) {
        super(parent, modal);
        this.novaConsulta = new Consulta();
        initComponents();
    }

    private void initComponents() {
        setTitle("Novo Agendamento");
        setSize(500, 580);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel lblTitulo = new JLabel("Novo Agendamento", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        btnAddPaciente    = new JButton("Add Paciente");
        btnAddMedico      = new JButton("Add Medico");
        btnCancelar       = new JButton("Cancelar");
        btnFecharConsulta = new JButton("Fechar Consulta");

        btnAddPaciente.setPreferredSize(new Dimension(150, 35));
        btnAddMedico.setPreferredSize(new Dimension(150, 35));

        JPanel pnlBotoesTopo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        pnlBotoesTopo.add(btnAddPaciente);
        pnlBotoesTopo.add(btnAddMedico);

        edtData = new JTextField(10);
        edtHora = new JTextField(8);

        JPanel pnlData = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        pnlData.add(new JLabel("Data (DD-MM-AAAA):"));
        pnlData.add(edtData);
        pnlData.add(new JLabel("Hora:"));
        pnlData.add(edtHora);

        edtConsulta = new JTextArea();
        edtConsulta.setEditable(false);
        JScrollPane scroll = new JScrollPane(edtConsulta);
        scroll.setBorder(BorderFactory.createTitledBorder("Resumo da Consulta"));

        JPanel pnlBotoesRodape = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        pnlBotoesRodape.add(btnCancelar);
        pnlBotoesRodape.add(btnFecharConsulta);

        JPanel pnlNorte = new JPanel(new BorderLayout());
        pnlNorte.add(lblTitulo, BorderLayout.NORTH);
        pnlNorte.add(pnlBotoesTopo, BorderLayout.CENTER);
        pnlNorte.add(pnlData, BorderLayout.SOUTH);

        setLayout(new BorderLayout(5, 5));
        add(pnlNorte, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(pnlBotoesRodape, BorderLayout.SOUTH);

        btnAddPaciente.addActionListener(evt -> btnAddPacienteActionPerformed());
        btnAddMedico.addActionListener(evt -> btnAddMedicoActionPerformed());
        btnCancelar.addActionListener(evt -> btnCancelarActionPerformed());
        btnFecharConsulta.addActionListener(evt -> btnFecharConsultaActionPerformed());
    }

    private void btnAddPacienteActionPerformed() {
        DlgSelecionarPaciente telaPaciente = new DlgSelecionarPaciente((Frame) getOwner(), true, novaConsulta);
        telaPaciente.setVisible(true);

        // So executado apos o DlgSelecionarPaciente ser FECHADO
        atualizarResumo();
    }

    private void btnAddMedicoActionPerformed() {
        DlgSelecionarMedico telaMedico = new DlgSelecionarMedico((Frame) getOwner(), true, novaConsulta);
        telaMedico.setVisible(true);

        // So executado apos o DlgSelecionarMedico ser FECHADO
        atualizarResumo();
    }

    private void btnCancelarActionPerformed() {
        int resposta = JOptionPane.showConfirmDialog(this,
                "Deseja realmente cancelar este agendamento?",
                "Confirmar",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (resposta == JOptionPane.OK_OPTION) {
            dispose();
        }
    }

    private void btnFecharConsultaActionPerformed() {
        if (novaConsulta.getPaciente() == null) {
            JOptionPane.showMessageDialog(this, "Voce nao selecionou o paciente.");
            return;
        }
        if (novaConsulta.getMedico() == null) {
            JOptionPane.showMessageDialog(this, "Voce nao selecionou o medico.");
            return;
        }
        if (edtData.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a data da consulta.");
            return;
        }
        if (edtHora.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o horario da consulta.");
            return;
        }
        novaConsulta.setData(edtData.getText().trim());
        novaConsulta.setHora(edtHora.getText().trim());

        GerenciadorConsulta.adicionar(novaConsulta);
        SerializacaoCSVConsulta.salvar();

        JOptionPane.showMessageDialog(this, "Consulta agendada com sucesso!");
        dispose();
    }

    private void atualizarResumo() {
        edtConsulta.setText(novaConsulta.toString());
    }
}
