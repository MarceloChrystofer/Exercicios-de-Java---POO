package com.mycompany.roteiro04.model;

import java.util.Objects;

public class Consulta {

    private int id;
    private Paciente paciente;
    private Medico medico;
    private String data;
    private String hora;

    public Consulta() {
    }

    public Consulta(int id, Paciente paciente, Medico medico, String data, String hora) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.hora = hora;
    }

    public Consulta(Consulta outra) {
        this.id = outra.id;
        this.paciente = outra.paciente != null ? outra.paciente.copiar() : null;
        this.medico = outra.medico != null ? outra.medico.copiar() : null;
        this.data = outra.data;
        this.hora = outra.hora;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consulta)) return false;
        Consulta c = (Consulta) o;
        return id == c.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        String nomePaciente = paciente != null ? paciente.getNome() : "Nao definido";
        String cpfPaciente  = paciente != null ? paciente.getCpf()  : "-";
        String nomeMedico   = medico   != null ? medico.getNome()   : "Nao definido";
        String crmMedico    = medico   != null ? medico.getCrm()    : "-";

        return "---------- Consulta ----------\n"
                + "Id:" + id + "\n"
                + "-------- Paciente --------\n"
                + "Nome:" + nomePaciente + "\n"
                + "CPF:" + cpfPaciente + "\n"
                + "--------------------------\n"
                + "-------- Medico --------\n"
                + "Nome:" + nomeMedico + "\n"
                + "CRM:" + crmMedico + "\n"
                + "------------------------\n"
                + "Data:" + data + "\n"
                + "Hora:" + hora + "\n"
                + "------------------------------\n";
    }

    public void imprimir() {
        System.out.println(this);
    }

    public void preencher(Consulta outra) {
        this.id = outra.id;
        this.paciente = outra.paciente;
        this.medico = outra.medico;
        this.data = outra.data;
        this.hora = outra.hora;
    }

    public Consulta copiar() {
        return new Consulta(this);
    }
}
