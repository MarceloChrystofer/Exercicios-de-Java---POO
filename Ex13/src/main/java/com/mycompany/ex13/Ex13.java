package com.mycompany.ex13;

import Classes.Filme;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex13 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Filme> filmes = new ArrayList<>();

        int opcao;

        do {

            System.out.println("\n=== CADASTRO DE FILMES ===");
            System.out.println("1 - Adicionar filme");
            System.out.println("2 - Remover filme");
            System.out.println("3 - Pesquisar filme");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:

                    Filme f = new Filme();
                    f.preencher();

                    filmes.add(f);

                    System.out.println("Filme adicionado!");
                    break;

                case 2:

                    System.out.print("Titulo do filme para remover: ");
                    String tituloRemover = sc.nextLine();

                    for (int i = 0; i < filmes.size(); i++) {

                        if (filmes.get(i).getTitulo().equalsIgnoreCase(tituloRemover)) {

                            filmes.remove(i);
                            System.out.println("Filme removido!");
                            break;
                        }
                    }

                    break;

                case 3:

                    System.out.print("Titulo: ");
                    String titulo = sc.nextLine();

                    System.out.print("Diretor: ");
                    String diretor = sc.nextLine();

                    boolean encontrou = false;

                    for (Filme filme : filmes) {

                        if (filme.getTitulo().equalsIgnoreCase(titulo)
                                && filme.getDiretor().equalsIgnoreCase(diretor)) {

                            filme.imprimir();
                            encontrou = true;
                        }
                    }

                    if (!encontrou) {
                        System.out.println("Filme nao encontrado.");
                    }

                    break;

            }

        } while (opcao != 0);

    }

}