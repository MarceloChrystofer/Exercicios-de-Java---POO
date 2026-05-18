package com.mycompany.roteiro04.gui;

import com.mycompany.roteiro04.gerenciador.GerenciadorMedico;
import com.mycompany.roteiro04.model.Consulta;
import com.mycompany.roteiro04.model.Medico;
import javax.swing.*;
import java.awt.*;

public class DlgSelecionarMedico extends JDialog {

    private JTextField edtBusca;
    private JButton    btnBuscar;
    private JTextArea  edtListaMedicos;
    private JTextArea  edtMedicoEscolhido;
    private JButton    btnAddMedico;

    private Consulta consulta;

    public DlgSelecionarMedico(Frame parent, boolean modal, Consulta consulta) {
        super(parent, modal);
        this.consulta = consulta;
        initComponents();
        carregarTodosMedicos();
    }

    private void initComponents() {
        setTitle("Escolha um Medico");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel lblTitulo = new JLabel("Escolha um medico", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        edtBusca  = new JTextField(12);
        btnBuscar = new JButton("Buscar");

        JPanel pnlBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlBusca.add(new JLabel("CRM:"));
        pnlBusca.add(edtBusca);
        pnlBusca.add(btnBuscar);

        edtListaMedicos = new JTextArea();
        edtListaMedicos.setEditable(false);
        JScrollPane scrollLista = new JScrollPane(edtListaMedicos);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Medicos"));
        scrollLista.setPreferredSize(new Dimension(300, 300));

        edtMedicoEscolhido = new JTextArea();
        edtMedicoEscolhido.setEditable(false);
        JScrollPane scrollEscolhido = new JScrollPane(edtMedicoEscolhido);
        scrollEscolhido.setBorder(BorderFactory.createTitledBorder("Medico Escolhido"));
        scrollEscolhido.setPreferredSize(new Dimension(280, 300));

        btnAddMedico = new JButton("Add Medico");
        btnAddMedico.setBackground(new Color(0, 180, 0));
        btnAddMedico.setForeground(Color.WHITE);
        btnAddMedico.setPreferredSize(new Dimension(200, 35));

        JPanel pnlEsquerda = new JPanel(new BorderLayout(5, 5));
        pnlEsquerda.add(pnlBusca, BorderLayout.NORTH);
        pnlEsquerda.add(scrollLista, BorderLayout.CENTER);

        JPanel pnlDireita = new JPanel(new BorderLayout(5, 5));
        pnlDireita.add(scrollEscolhido, BorderLayout.CENTER);
        pnlDireita.add(btnAddMedico, BorderLayout.SOUTH);

        JPanel pnlConteudo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        pnlConteudo.add(pnlEsquerda);
        pnlConteudo.add(pnlDireita);

        setLayout(new BorderLayout(5, 5));
        add(lblTitulo, BorderLayout.NORTH);
        add(pnlConteudo, BorderLayout.CENTER);

        btnBuscar.addActionListener(evt -> btnBuscarActionPerformed());
        btnAddMedico.addActionListener(evt -> btnAddMedicoActionPerformed());
    }

    private void btnBuscarActionPerformed() {
        String crm = edtBusca.getText().trim();
        Medico m = GerenciadorMedico.buscarPorCrm(crm);
        if (m == null) {
            JOptionPane.showMessageDialog(this, "Nenhum medico encontrado com este CRM.");
            edtMedicoEscolhido.setText("");
        } else {
            edtMedicoEscolhido.setText(m.toString());
        }
    }

    private void btnAddMedicoActionPerformed() {
        String crm = edtBusca.getText().trim();
        Medico m = GerenciadorMedico.buscarPorCrm(crm);
        if (m == null) {
            JOptionPane.showMessageDialog(this, "Busque um medico valido antes de adicionar.");
            return;
        }
        consulta.setMedico(m);
        JOptionPane.showMessageDialog(this, "Medico " + m.getNome() + " adicionado a consulta!");
        dispose();
    }

    private void carregarTodosMedicos() {
        StringBuilder sb = new StringBuilder();
        for (Medico m : GerenciadorMedico.getLista()) {
            sb.append(m.toString());
        }
        edtListaMedicos.setText(sb.toString());
    }
}
