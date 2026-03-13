package Classes;

import java.util.Scanner;

public class Musica {

    private String titulo;
    private String artista;
    private int duracao;
    private double preco;

    // construtor vazio
    public Musica() {

    }

    // construtor completo
    public Musica(String titulo, String artista, int duracao, double preco) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
        this.preco = preco;
    }

    // getters e setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // preencher dados
    public void preencher() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Titulo: ");
        titulo = sc.nextLine();

        System.out.print("Artista: ");
        artista = sc.nextLine();

        System.out.print("Duracao: ");
        duracao = sc.nextInt();

        System.out.print("Preco: ");
        preco = sc.nextDouble();
    }

    // imprimir dados
    public void imprimir() {

        System.out.println("Titulo: " + titulo);
        System.out.println("Artista: " + artista);
        System.out.println("Duracao: " + duracao);
        System.out.println("Preco: " + preco);

    }

    // copiar objeto
    public void copiar(Musica outra) {

        this.titulo = outra.titulo;
        this.artista = outra.artista;
        this.duracao = outra.duracao;
        this.preco = outra.preco;

    }

    // método mockado para tocar música
    public void tocarMusica() {
        System.out.println("A musica " + this.titulo + " está tocando!");
    }

    // método mockado para desligar música
    public void desligarMusica() {
        System.out.println("A musica " + this.titulo + " foi desligada!");
    }

}