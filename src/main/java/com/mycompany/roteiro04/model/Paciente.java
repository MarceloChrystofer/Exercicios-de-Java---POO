package com.mycompany.roteiro04.model;

import java.util.Objects;

public class Paciente {

    private int id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String historicoMedico;

    public Paciente() {
    }

    public Paciente(int id, String nome, String cpf, String dataNascimento, String historicoMedico) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.historicoMedico = historicoMedico;
    }

    public Paciente(Paciente outro) {
        this.id = outro.id;
        this.nome = outro.nome;
        this.cpf = outro.cpf;
        this.dataNascimento = outro.dataNascimento;
        this.historicoMedico = outro.historicoMedico;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getHistoricoMedico() { return historicoMedico; }
    public void setHistoricoMedico(String historicoMedico) { this.historicoMedico = historicoMedico; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente)) return false;
        Paciente p = (Paciente) o;
        return Objects.equals(cpf, p.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "-------- Paciente --------\n"
                + "Id:" + id + "\n"
                + "Nome:" + nome + "\n"
                + "CPF:" + cpf + "\n"
                + "DataNasc:" + dataNascimento + "\n"
                + "Historico:" + historicoMedico + "\n"
                + "--------------------------\n";
    }

    public void imprimir() {
        System.out.println(this);
    }

    public void preencher(Paciente outro) {
        this.id = outro.id;
        this.nome = outro.nome;
        this.cpf = outro.cpf;
        this.dataNascimento = outro.dataNascimento;
        this.historicoMedico = outro.historicoMedico;
    }

    public Paciente copiar() {
        return new Paciente(this);
    }
}
