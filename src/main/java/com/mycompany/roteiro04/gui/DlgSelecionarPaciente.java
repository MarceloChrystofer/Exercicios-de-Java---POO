package com.mycompany.roteiro04.gui;

import com.mycompany.roteiro04.gerenciador.GerenciadorPaciente;
import com.mycompany.roteiro04.model.Consulta;
import com.mycompany.roteiro04.model.Paciente;
import javax.swing.*;
import java.awt.*;

public class DlgSelecionarPaciente extends JDialog {

    private JTextField edtBusca;
    private JButton    btnBuscar;
    private JTextArea  edtListaPacientes;
    private JTextArea  edtPacienteEscolhido;
    private JButton    btnAddPaciente;

    private Consulta consulta;

    public DlgSelecionarPaciente(Frame parent, boolean modal, Consulta consulta) {
        super(parent, modal);
        this.consulta = consulta;
        initComponents();
        carregarTodosPacientes();
    }

    private void initComponents() {
        setTitle("Escolha um Paciente");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel lblTitulo = new JLabel("Escolha um paciente", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        edtBusca  = new JTextField(12);
        btnBuscar = new JButton("Buscar");

        JPanel pnlBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlBusca.add(new JLabel("CPF:"));
        pnlBusca.add(edtBusca);
        pnlBusca.add(btnBuscar);

        edtListaPacientes = new JTextArea();
        edtListaPacientes.setEditable(false);
        JScrollPane scrollLista = new JScrollPane(edtListaPacientes);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Pacientes"));
        scrollLista.setPreferredSize(new Dimension(300, 300));

        edtPacienteEscolhido = new JTextArea();
        edtPacienteEscolhido.setEditable(false);
        JScrollPane scrollEscolhido = new JScrollPane(edtPacienteEscolhido);
        scrollEscolhido.setBorder(BorderFactory.createTitledBorder("Paciente Escolhido"));
        scrollEscolhido.setPreferredSize(new Dimension(280, 300));

        btnAddPaciente = new JButton("Add Paciente");
        btnAddPaciente.setBackground(new Color(0, 180, 0));
        btnAddPaciente.setForeground(Color.WHITE);
        btnAddPaciente.setPreferredSize(new Dimension(200, 35));

        JPanel pnlEsquerda = new JPanel(new BorderLayout(5, 5));
        pnlEsquerda.add(pnlBusca, BorderLayout.NORTH);
        pnlEsquerda.add(scrollLista, BorderLayout.CENTER);

        JPanel pnlDireita = new JPanel(new BorderLayout(5, 5));
        pnlDireita.add(scrollEscolhido, BorderLayout.CENTER);
        pnlDireita.add(btnAddPaciente, BorderLayout.SOUTH);

        JPanel pnlConteudo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        pnlConteudo.add(pnlEsquerda);
        pnlConteudo.add(pnlDireita);

        setLayout(new BorderLayout(5, 5));
        add(lblTitulo, BorderLayout.NORTH);
        add(pnlConteudo, BorderLayout.CENTER);

        btnBuscar.addActionListener(evt -> btnBuscarActionPerformed());
        btnAddPaciente.addActionListener(evt -> btnAddPacienteActionPerformed());
    }

    private void btnBuscarActionPerformed() {
        String cpf = edtBusca.getText().trim();
        Paciente p = GerenciadorPaciente.buscarPorCpf(cpf);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Nenhum paciente encontrado com este CPF.");
            edtPacienteEscolhido.setText("");
        } else {
            edtPacienteEscolhido.setText(p.toString());
        }
    }

    private void btnAddPacienteActionPerformed() {
        String cpf = edtBusca.getText().trim();
        Paciente p = GerenciadorPaciente.buscarPorCpf(cpf);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Busque um paciente valido antes de adicionar.");
            return;
        }
        consulta.setPaciente(p);
        JOptionPane.showMessageDialog(this, "Paciente " + p.getNome() + " adicionado a consulta!");
        dispose();
    }

    private void carregarTodosPacientes() {
        StringBuilder sb = new StringBuilder();
        for (Paciente p : GerenciadorPaciente.getLista()) {
            sb.append(p.toString());
        }
        edtListaPacientes.setText(sb.toString());
    }
}
