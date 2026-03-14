package com.mycompany.ex1;
import java.util.Scanner;

public class Ex1 {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Insira o primeiro valor: ");
        double num1 = ler.nextDouble();
        System.out.println("Informe o segundo valor:");
        double num2 = ler.nextDouble();
        
        if (num1 > num2){
            System.out.println("O primeiro valor é maior que o segundo:");
        }
        else if(num2 > num1){
            System.out.println("O segundo valor é maior que o primeiro");
        }
        else {
            System.out.println ("Os dois valores são iguais");
        }   
}
}