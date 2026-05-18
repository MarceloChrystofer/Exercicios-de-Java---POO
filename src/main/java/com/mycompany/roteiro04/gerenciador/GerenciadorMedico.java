package com.mycompany.roteiro04.gerenciador;

import com.mycompany.roteiro04.model.Medico;
import java.util.ArrayList;

public class GerenciadorMedico {

    private static ArrayList<Medico> lista = new ArrayList<>();
    private static int proximoId = 1;

    public static void adicionar(Medico m) {
        m.setId(proximoId++);
        lista.add(m);
    }

    public static void remover(Medico m) {
        lista.remove(m);
    }

    public static void alterar(int id, Medico atualizado) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                atualizado.setId(id);
                lista.set(i, atualizado);
                return;
            }
        }
    }

    public static Medico buscarPorId(int id) {
        for (Medico m : lista) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public static Medico buscarPorCrm(String crm) {
        for (Medico m : lista) {
            if (m.getCrm().equals(crm)) {
                return m;
            }
        }
        return null;
    }

    public static ArrayList<Medico> getLista() {
        return lista;
    }

    public static void setLista(ArrayList<Medico> novaLista) {
        lista = novaLista;
        if (!lista.isEmpty()) {
            int maxId = lista.stream().mapToInt(Medico::getId).max().orElse(0);
            proximoId = maxId + 1;
        }
    }
}
