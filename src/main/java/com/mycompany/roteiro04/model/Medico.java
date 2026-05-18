package com.mycompany.roteiro04.model;

import java.util.Objects;

public class Medico {

    private int id;
    private String nome;
    private String crm;
    private String especialidade;
    private String horariosDisponiveis;

    public Medico() {
    }

    public Medico(int id, String nome, String crm, String especialidade, String horariosDisponiveis) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.horariosDisponiveis = horariosDisponiveis;
    }

    public Medico(Medico outro) {
        this.id = outro.id;
        this.nome = outro.nome;
        this.crm = outro.crm;
        this.especialidade = outro.especialidade;
        this.horariosDisponiveis = outro.horariosDisponiveis;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public String getHorariosDisponiveis() { return horariosDisponiveis; }
    public void setHorariosDisponiveis(String horariosDisponiveis) { this.horariosDisponiveis = horariosDisponiveis; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medico)) return false;
        Medico m = (Medico) o;
        return Objects.equals(crm, m.crm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crm);
    }

    @Override
    public String toString() {
        return "-------- Medico --------\n"
                + "Id:" + id + "\n"
                + "Nome:" + nome + "\n"
                + "CRM:" + crm + "\n"
                + "Especialidade:" + especialidade + "\n"
                + "Horarios:" + horariosDisponiveis + "\n"
                + "------------------------\n";
    }

    public void imprimir() {
        System.out.println(this);
    }

    public void preencher(Medico outro) {
        this.id = outro.id;
        this.nome = outro.nome;
        this.crm = outro.crm;
        this.especialidade = outro.especialidade;
        this.horariosDisponiveis = outro.horariosDisponiveis;
    }

    public Medico copiar() {
        return new Medico(this);
    }
}
