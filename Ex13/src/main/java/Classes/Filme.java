package Classes;

import java.util.Scanner;

public class Filme {

    private String titulo;
    private String diretor;
    private int duracao;
    private double preco;

    
    public Filme() {
    }

    
    public Filme(String titulo, String diretor, int duracao, double preco) {
        this.titulo = titulo;
        this.diretor = diretor;
        this.duracao = duracao;
        this.preco = preco;
    }

    
    public String getTitulo() {
        return titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

   
    public void preencher() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Titulo: ");
        titulo = sc.nextLine();

        System.out.print("Diretor: ");
        diretor = sc.nextLine();

        System.out.print("Duracao: ");
        duracao = sc.nextInt();

        System.out.print("Preco: ");
        preco = sc.nextDouble();
    }

    public void imprimir() {

        System.out.println("Titulo: " + titulo);
        System.out.println("Diretor: " + diretor);
        System.out.println("Duracao: " + duracao);
        System.out.println("Preco: " + preco);
    }

    public void copiar(Filme outro) {

        this.titulo = outro.titulo;
        this.diretor = outro.diretor;
        this.duracao = outro.duracao;
        this.preco = outro.preco;
    }

}