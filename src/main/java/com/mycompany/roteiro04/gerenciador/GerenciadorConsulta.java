package com.mycompany.roteiro04.gerenciador;

import com.mycompany.roteiro04.model.Consulta;
import java.util.ArrayList;

public class GerenciadorConsulta {

    private static ArrayList<Consulta> lista = new ArrayList<>();
    private static int proximoId = 1;

    public static void adicionar(Consulta c) {
        c.setId(proximoId++);
        lista.add(c);
    }

    public static void remover(int id) {
        Consulta alvo = buscarPorId(id);
        if (alvo != null) {
            lista.remove(alvo);
        }
    }

    public static Consulta buscarPorId(int id) {
        for (Consulta c : lista) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public static ArrayList<Consulta> getLista() {
        return lista;
    }

    public static void setLista(ArrayList<Consulta> novaLista) {
        lista = novaLista;
        if (!lista.isEmpty()) {
            int maxId = lista.stream().mapToInt(Consulta::getId).max().orElse(0);
            proximoId = maxId + 1;
        }
    }
}
