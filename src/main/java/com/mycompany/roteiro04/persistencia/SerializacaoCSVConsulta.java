package com.mycompany.roteiro04.persistencia;

import com.mycompany.roteiro04.gerenciador.GerenciadorConsulta;
import com.mycompany.roteiro04.gerenciador.GerenciadorMedico;
import com.mycompany.roteiro04.gerenciador.GerenciadorPaciente;
import com.mycompany.roteiro04.model.Consulta;
import com.mycompany.roteiro04.model.Medico;
import com.mycompany.roteiro04.model.Paciente;
import java.io.*;
import java.util.ArrayList;

public class SerializacaoCSVConsulta {

    private static final String ARQUIVO = "consultas.csv";

    public static void salvar() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Consulta c : GerenciadorConsulta.getLista()) {
                int idPaciente = c.getPaciente() != null ? c.getPaciente().getId() : 0;
                int idMedico   = c.getMedico()   != null ? c.getMedico().getId()   : 0;
                pw.println(c.getId() + ";"
                        + idPaciente + ";"
                        + idMedico + ";"
                        + c.getData() + ";"
                        + c.getHora());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar consultas: " + e.getMessage());
        }
    }

    public static void carregar() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return;
        }
        ArrayList<Consulta> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] c = linha.split(";", -1);
                if (c.length == 5) {
                    int id         = Integer.parseInt(c[0]);
                    int idPaciente = Integer.parseInt(c[1]);
                    int idMedico   = Integer.parseInt(c[2]);
                    String data    = c[3];
                    String hora    = c[4];

                    Paciente paciente = GerenciadorPaciente.buscarPorId(idPaciente);
                    Medico medico     = GerenciadorMedico.buscarPorId(idMedico);

                    lista.add(new Consulta(id, paciente, medico, data, hora));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar consultas: " + e.getMessage());
        }
        GerenciadorConsulta.setLista(lista);
    }
}
