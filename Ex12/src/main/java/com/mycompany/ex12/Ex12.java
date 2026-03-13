package com.mycompany.ex12;

import Classes.Musica;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex12 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Musica> musicas = new ArrayList<>();

        int opcao;

        do {

            System.out.println("\n=== CADASTRO DE MUSICAS ===");
            System.out.println("1 - Adicionar musica");
            System.out.println("2 - Remover musica");
            System.out.println("3 - Pesquisar musica");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:

                    Musica nova = new Musica();
                    nova.preencher();

                    musicas.add(nova);

                    System.out.println("Musica adicionada!");
                    break;

                case 2:

                    System.out.print("Titulo da musica para remover: ");
                    String tituloRemover = sc.nextLine();

                    for (int i = 0; i < musicas.size(); i++) {

                        if (musicas.get(i).getTitulo().equalsIgnoreCase(tituloRemover)) {

                            musicas.remove(i);
                            System.out.println("Musica removida!");
                            break;
                        }
                    }

                    break;

                case 3:

                    System.out.print("Titulo: ");
                    String titulo = sc.nextLine();

                    System.out.print("Artista: ");
                    String artista = sc.nextLine();

                    boolean encontrou = false;

                    for (Musica m : musicas) {

                        if (m.getTitulo().equalsIgnoreCase(titulo)
                                && m.getArtista().equalsIgnoreCase(artista)) {

                            m.imprimir();
                            encontrou = true;
                        }
                    }

                    if (!encontrou) {
                        System.out.println("Musica nao encontrada.");
                    }

                    break;

            }

        } while (opcao != 0);

    }

}