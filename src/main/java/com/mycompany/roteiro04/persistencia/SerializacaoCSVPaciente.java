package com.mycompany.roteiro04.persistencia;

import com.mycompany.roteiro04.gerenciador.GerenciadorPaciente;
import com.mycompany.roteiro04.model.Paciente;
import java.io.*;
import java.util.ArrayList;

public class SerializacaoCSVPaciente {

    private static final String ARQUIVO = "pacientes.csv";

    public static void salvar() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Paciente p : GerenciadorPaciente.getLista()) {
                pw.println(p.getId() + ";"
                        + p.getNome() + ";"
                        + p.getCpf() + ";"
                        + p.getDataNascimento() + ";"
                        + p.getHistoricoMedico());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar pacientes: " + e.getMessage());
        }
    }

    public static void carregar() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return;
        }
        ArrayList<Paciente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(";", -1);
                if (p.length == 5) {
                    lista.add(new Paciente(Integer.parseInt(p[0]), p[1], p[2], p[3], p[4]));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar pacientes: " + e.getMessage());
        }
        GerenciadorPaciente.setLista(lista);
    }
}
