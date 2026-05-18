package com.mycompany.roteiro04.gui;

import com.mycompany.roteiro04.gerenciador.GerenciadorPaciente;
import com.mycompany.roteiro04.model.Paciente;
import com.mycompany.roteiro04.persistencia.SerializacaoCSVPaciente;
import javax.swing.*;
import java.awt.*;

public class FrCadPaciente extends JFrame {

    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JButton btnExcluir;
    private JButton btnSalvar;

    private JTextField edtId;
    private JTextField edtNome;
    private JTextField edtCpf;
    private JTextField edtDataNasc;
    private JTextArea  edtHistorico;
    private JTextArea  edtLista;

    private boolean modoEdicao = false;

    public FrCadPaciente() {
        initComponents();
        atualizarLista();
    }

    private void initComponents() {
        setTitle("Cadastro de Paciente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        btnNovo     = new JButton("Novo");
        btnEditar   = new JButton("Editar");
        btnCancelar = new JButton("Cancelar");
        btnExcluir  = new JButton("Excluir");
        btnSalvar   = new JButton("Salvar");

        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pnlBotoes.add(btnNovo);
        pnlBotoes.add(btnEditar);
        pnlBotoes.add(btnCancelar);
        pnlBotoes.add(btnExcluir);
        pnlBotoes.add(btnSalvar);

        edtId        = new JTextField(5);
        edtNome      = new JTextField(20);
        edtCpf       = new JTextField(15);
        edtDataNasc  = new JTextField(10);
        edtHistorico = new JTextArea(3, 30);
        edtHistorico.setLineWrap(true);
        edtId.setEditable(false);

        JPanel pnlCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 6, 4, 6);
        gc.anchor = GridBagConstraints.WEST;

        gc.gridx = 0; gc.gridy = 0; pnlCampos.add(new JLabel("Id:"), gc);
        gc.gridx = 1;               pnlCampos.add(edtId, gc);
        gc.gridx = 0; gc.gridy = 1; pnlCampos.add(new JLabel("Nome:"), gc);
        gc.gridx = 1;               pnlCampos.add(edtNome, gc);
        gc.gridx = 0; gc.gridy = 2; pnlCampos.add(new JLabel("CPF:"), gc);
        gc.gridx = 1;               pnlCampos.add(edtCpf, gc);
        gc.gridx = 0; gc.gridy = 3; pnlCampos.add(new JLabel("Data Nasc (DD-MM-AAAA):"), gc);
        gc.gridx = 1;               pnlCampos.add(edtDataNasc, gc);
        gc.gridx = 0; gc.gridy = 4; pnlCampos.add(new JLabel("Historico Medico:"), gc);
        gc.gridx = 1;               pnlCampos.add(new JScrollPane(edtHistorico), gc);

        edtLista = new JTextArea();
        edtLista.setEditable(false);
        JScrollPane scroll = new JScrollPane(edtLista);
        scroll.setBorder(BorderFactory.createTitledBorder("Pacientes Cadastrados"));

        JPanel pnlSuperior = new JPanel(new BorderLayout());
        pnlSuperior.add(pnlBotoes, BorderLayout.NORTH);
        pnlSuperior.add(pnlCampos, BorderLayout.CENTER);

        setLayout(new BorderLayout(5, 5));
        add(pnlSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        habilitarCampos(false);

        btnNovo.addActionListener(evt -> btnNovoActionPerformed());
        btnEditar.addActionListener(evt -> btnEditarActionPerformed());
        btnCancelar.addActionListener(evt -> btnCancelarActionPerformed());
        btnExcluir.addActionListener(evt -> btnExcluirActionPerformed());
        btnSalvar.addActionListener(evt -> btnSalvarActionPerformed());
    }

    private void btnNovoActionPerformed() {
        limparCampos();
        habilitarCampos(true);
        modoEdicao = false;
        edtNome.requestFocus();
    }

    private void btnEditarActionPerformed() {
        String idStr = JOptionPane.showInputDialog(this, "Informe o ID do paciente que deseja editar:", "");
        if (idStr == null) return;
        int id = Integer.parseInt(idStr.trim());
        Paciente p = GerenciadorPaciente.buscarPorId(id);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Nao existe paciente com este ID.");
            return;
        }
        edtId.setText(String.valueOf(p.getId()));
        edtNome.setText(p.getNome());
        edtCpf.setText(p.getCpf());
        edtDataNasc.setText(p.getDataNascimento());
        edtHistorico.setText(p.getHistoricoMedico());
        habilitarCampos(true);
        modoEdicao = true;
    }

    private void btnCancelarActionPerformed() {
        limparCampos();
        habilitarCampos(false);
        modoEdicao = false;
    }

    private void btnExcluirActionPerformed() {
        String idStr = JOptionPane.showInputDialog(this, "Informe o ID do paciente que deseja excluir:", "");
        if (idStr == null) return;
        int id = Integer.parseInt(idStr.trim());
        Paciente p = GerenciadorPaciente.buscarPorId(id);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Nao existe paciente com este ID.");
            return;
        }
        int resp = JOptionPane.showConfirmDialog(this,
                "Deseja realmente excluir o paciente " + p.getNome() + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            GerenciadorPaciente.remover(p);
            SerializacaoCSVPaciente.salvar();
            JOptionPane.showMessageDialog(this, "Paciente excluido com sucesso!");
            atualizarLista();
        }
    }

    private void btnSalvarActionPerformed() {
        if (edtNome.getText().isEmpty() || edtCpf.getText().isEmpty() || edtDataNasc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatorios.");
            return;
        }
        Paciente p = new Paciente();
        p.setNome(edtNome.getText().trim());
        p.setCpf(edtCpf.getText().trim());
        p.setDataNascimento(edtDataNasc.getText().trim());
        p.setHistoricoMedico(edtHistorico.getText().trim());

        if (modoEdicao) {
            int id = Integer.parseInt(edtId.getText());
            GerenciadorPaciente.alterar(id, p);
            JOptionPane.showMessageDialog(this, "Paciente alterado com sucesso!");
        } else {
            GerenciadorPaciente.adicionar(p);
            JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso!");
        }
        SerializacaoCSVPaciente.salvar();
        limparCampos();
        habilitarCampos(false);
        modoEdicao = false;
        atualizarLista();
    }

    private void habilitarCampos(boolean habilitar) {
        edtNome.setEnabled(habilitar);
        edtCpf.setEnabled(habilitar);
        edtDataNasc.setEnabled(habilitar);
        edtHistorico.setEnabled(habilitar);
        btnSalvar.setEnabled(habilitar);
        btnCancelar.setEnabled(habilitar);
    }

    private void limparCampos() {
        edtId.setText("");
        edtNome.setText("");
        edtCpf.setText("");
        edtDataNasc.setText("");
        edtHistorico.setText("");
    }

    private void atualizarLista() {
        StringBuilder sb = new StringBuilder();
        for (Paciente p : GerenciadorPaciente.getLista()) {
            sb.append(p.toString());
        }
        edtLista.setText(sb.toString());
    }
}
