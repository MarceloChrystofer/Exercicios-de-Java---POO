package com.mycompany.ex2;
import java.util.Scanner;

public class Ex2 {

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);

        int soma = 0;

        System.out.println("Informe o valor:");
        int numero = ler.nextInt();

        for (int numeroImpar = 1; numeroImpar <= numero; numeroImpar += 2) {
            soma = soma + numeroImpar;
        }

        System.out.println("A soma dos ímpares é: " + soma);
    }
}
