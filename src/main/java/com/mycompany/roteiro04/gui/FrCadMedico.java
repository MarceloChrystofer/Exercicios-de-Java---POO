package com.mycompany.roteiro04.gui;

import com.mycompany.roteiro04.gerenciador.GerenciadorMedico;
import com.mycompany.roteiro04.model.Medico;
import com.mycompany.roteiro04.persistencia.SerializacaoCSVMedico;
import javax.swing.*;
import java.awt.*;

public class FrCadMedico extends JFrame {

    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JButton btnExcluir;
    private JButton btnSalvar;

    private JTextField edtId;
    private JTextField edtNome;
    private JTextField edtCrm;
    private JTextField edtEspecialidade;
    private JTextField edtHorarios;
    private JTextArea  edtLista;

    private boolean modoEdicao = false;

    public FrCadMedico() {
        initComponents();
        atualizarLista();
    }

    private void initComponents() {
        setTitle("Cadastro de Medico");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 520);
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

        edtId            = new JTextField(5);
        edtNome          = new JTextField(20);
        edtCrm           = new JTextField(15);
        edtEspecialidade = new JTextField(20);
        edtHorarios      = new JTextField(25);
        edtId.setEditable(false);

        JPanel pnlCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 6, 4, 6);
        gc.anchor = GridBagConstraints.WEST;

        gc.gridx = 0; gc.gridy = 0; pnlCampos.add(new JLabel("Id:"), gc);
        gc.gridx = 1;               pnlCampos.add(edtId, gc);
        gc.gridx = 0; gc.gridy = 1; pnlCampos.add(new JLabel("Nome:"), gc);
        gc.gridx = 1;               pnlCampos.add(edtNome, gc);
        gc.gridx = 0; gc.gridy = 2; pnlCampos.add(new JLabel("CRM:"), gc);
        gc.gridx = 1;               pnlCampos.add(edtCrm, gc);
        gc.gridx = 0; gc.gridy = 3; pnlCampos.add(new JLabel("Especialidade:"), gc);
        gc.gridx = 1;               pnlCampos.add(edtEspecialidade, gc);
        gc.gridx = 0; gc.gridy = 4; pnlCampos.add(new JLabel("Horarios Disponiveis:"), gc);
        gc.gridx = 1;               pnlCampos.add(edtHorarios, gc);

        edtLista = new JTextArea();
        edtLista.setEditable(false);
        JScrollPane scroll = new JScrollPane(edtLista);
        scroll.setBorder(BorderFactory.createTitledBorder("Medicos Cadastrados"));

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
        String idStr = JOptionPane.showInputDialog(this, "Informe o ID do medico que deseja editar:", "");
        if (idStr == null) return;
        int id = Integer.parseInt(idStr.trim());
        Medico m = GerenciadorMedico.buscarPorId(id);
        if (m == null) {
            JOptionPane.showMessageDialog(this, "Nao existe medico com este ID.");
            return;
        }
        edtId.setText(String.valueOf(m.getId()));
        edtNome.setText(m.getNome());
        edtCrm.setText(m.getCrm());
        edtEspecialidade.setText(m.getEspecialidade());
        edtHorarios.setText(m.getHorariosDisponiveis());
        habilitarCampos(true);
        modoEdicao = true;
    }

    private void btnCancelarActionPerformed() {
        limparCampos();
        habilitarCampos(false);
        modoEdicao = false;
    }

    private void btnExcluirActionPerformed() {
        String idStr = JOptionPane.showInputDialog(this, "Informe o ID do medico que deseja excluir:", "");
        if (idStr == null) return;
        int id = Integer.parseInt(idStr.trim());
        Medico m = GerenciadorMedico.buscarPorId(id);
        if (m == null) {
            JOptionPane.showMessageDialog(this, "Nao existe medico com este ID.");
            return;
        }
        int resp = JOptionPane.showConfirmDialog(this,
                "Deseja realmente excluir o medico " + m.getNome() + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            GerenciadorMedico.remover(m);
            SerializacaoCSVMedico.salvar();
            JOptionPane.showMessageDialog(this, "Medico excluido com sucesso!");
            atualizarLista();
        }
    }

    private void btnSalvarActionPerformed() {
        if (edtNome.getText().isEmpty() || edtCrm.getText().isEmpty() || edtEspecialidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatorios.");
            return;
        }
        Medico m = new Medico();
        m.setNome(edtNome.getText().trim());
        m.setCrm(edtCrm.getText().trim());
        m.setEspecialidade(edtEspecialidade.getText().trim());
        m.setHorariosDisponiveis(edtHorarios.getText().trim());

        if (modoEdicao) {
            int id = Integer.parseInt(edtId.getText());
            GerenciadorMedico.alterar(id, m);
            JOptionPane.showMessageDialog(this, "Medico alterado com sucesso!");
        } else {
            GerenciadorMedico.adicionar(m);
            JOptionPane.showMessageDialog(this, "Medico cadastrado com sucesso!");
        }
        SerializacaoCSVMedico.salvar();
        limparCampos();
        habilitarCampos(false);
        modoEdicao = false;
        atualizarLista();
    }

    private void habilitarCampos(boolean habilitar) {
        edtNome.setEnabled(habilitar);
        edtCrm.setEnabled(habilitar);
        edtEspecialidade.setEnabled(habilitar);
        edtHorarios.setEnabled(habilitar);
        btnSalvar.setEnabled(habilitar);
        btnCancelar.setEnabled(habilitar);
    }

    private void limparCampos() {
        edtId.setText("");
        edtNome.setText("");
        edtCrm.setText("");
        edtEspecialidade.setText("");
        edtHorarios.setText("");
    }

    private void atualizarLista() {
        StringBuilder sb = new StringBuilder();
        for (Medico m : GerenciadorMedico.getLista()) {
            sb.append(m.toString());
        }
        edtLista.setText(sb.toString());
    }
}
