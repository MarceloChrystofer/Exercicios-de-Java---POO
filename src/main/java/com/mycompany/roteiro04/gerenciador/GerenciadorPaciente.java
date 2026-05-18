package com.mycompany.roteiro04.gerenciador;

import com.mycompany.roteiro04.model.Paciente;
import java.util.ArrayList;

public class GerenciadorPaciente {

    private static ArrayList<Paciente> lista = new ArrayList<>();
    private static int proximoId = 1;

    public static void adicionar(Paciente p) {
        p.setId(proximoId++);
        lista.add(p);
    }

    public static void remover(Paciente p) {
        lista.remove(p);
    }

    public static void alterar(int id, Paciente atualizado) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                atualizado.setId(id);
                lista.set(i, atualizado);
                return;
            }
        }
    }

    public static Paciente buscarPorId(int id) {
        for (Paciente p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public static Paciente buscarPorCpf(String cpf) {
        for (Paciente p : lista) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }

    public static ArrayList<Paciente> getLista() {
        return lista;
    }

    public static void setLista(ArrayList<Paciente> novaLista) {
        lista = novaLista;
        if (!lista.isEmpty()) {
            int maxId = lista.stream().mapToInt(Paciente::getId).max().orElse(0);
            proximoId = maxId + 1;
        }
    }
}
