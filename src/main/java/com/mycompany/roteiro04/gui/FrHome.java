package com.mycompany.roteiro04.gui;

import com.mycompany.roteiro04.persistencia.SerializacaoCSVConsulta;
import com.mycompany.roteiro04.persistencia.SerializacaoCSVMedico;
import com.mycompany.roteiro04.persistencia.SerializacaoCSVPaciente;
import javax.swing.*;
import java.awt.*;

public class FrHome extends JFrame {

    private JButton btnPacientes;
    private JButton btnMedicos;
    private JButton btnAgendamentos;

    public FrHome() {
        initComponents();
        SerializacaoCSVPaciente.carregar();
        SerializacaoCSVMedico.carregar();
        SerializacaoCSVConsulta.carregar();
    }

    private void initComponents() {
        setTitle("Sistema de Agendamento de Consultas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel lblTitulo = new JLabel("Clinica Saude Total", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        btnPacientes    = new JButton("Pacientes");
        btnMedicos      = new JButton("Medicos");
        btnAgendamentos = new JButton("Agendamentos");

        btnPacientes.setPreferredSize(new Dimension(160, 45));
        btnMedicos.setPreferredSize(new Dimension(160, 45));
        btnAgendamentos.setPreferredSize(new Dimension(160, 45));

        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        pnlBotoes.add(btnPacientes);
        pnlBotoes.add(btnMedicos);
        pnlBotoes.add(btnAgendamentos);

        setLayout(new BorderLayout(10, 10));
        add(lblTitulo, BorderLayout.NORTH);
        add(pnlBotoes, BorderLayout.CENTER);

        btnPacientes.addActionListener(evt -> btnPacientesActionPerformed());
        btnMedicos.addActionListener(evt -> btnMedicosActionPerformed());
        btnAgendamentos.addActionListener(evt -> btnAgendamentosActionPerformed());
    }

    private void btnPacientesActionPerformed() {
        FrCadPaciente cadPaciente = new FrCadPaciente();
        cadPaciente.setVisible(true);
    }

    private void btnMedicosActionPerformed() {
        FrCadMedico cadMedico = new FrCadMedico();
        cadMedico.setVisible(true);
    }

    private void btnAgendamentosActionPerformed() {
        DlgAgendamento telaAgendamento = new DlgAgendamento(this, true);
        telaAgendamento.setVisible(true);
    }
}
