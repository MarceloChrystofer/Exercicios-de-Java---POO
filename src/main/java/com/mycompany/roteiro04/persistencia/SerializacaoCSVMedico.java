package com.mycompany.roteiro04.persistencia;

import com.mycompany.roteiro04.gerenciador.GerenciadorMedico;
import com.mycompany.roteiro04.model.Medico;
import java.io.*;
import java.util.ArrayList;

public class SerializacaoCSVMedico {

    private static final String ARQUIVO = "medicos.csv";

    public static void salvar() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Medico m : GerenciadorMedico.getLista()) {
                pw.println(m.getId() + ";"
                        + m.getNome() + ";"
                        + m.getCrm() + ";"
                        + m.getEspecialidade() + ";"
                        + m.getHorariosDisponiveis());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar medicos: " + e.getMessage());
        }
    }

    public static void carregar() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return;
        }
        ArrayList<Medico> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] m = linha.split(";", -1);
                if (m.length == 5) {
                    lista.add(new Medico(Integer.parseInt(m[0]), m[1], m[2], m[3], m[4]));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar medicos: " + e.getMessage());
        }
        GerenciadorMedico.setLista(lista);
    }
}
