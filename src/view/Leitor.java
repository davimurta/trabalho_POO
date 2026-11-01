package view;

import java.util.Scanner;

public class Leitor {
    private static final Scanner scanner = new Scanner(System.in);

    public static String texto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    public static int inteiro(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(texto(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido.");
            }
        }
    }
}
