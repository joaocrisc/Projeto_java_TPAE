import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utilitário para leitura segura de entradas do usuário via {@link Scanner}.
 * <p>Trata {@link InputMismatchException} automaticamente, solicitando nova entrada
 * até que um valor válido seja digitado.</p>
 */
public class TesteEntrada {

    /**
     * Lê um número inteiro do scanner, repetindo a solicitação em caso de entrada inválida.
     *
     * @param scanner instância de {@link Scanner} para leitura
     * @return inteiro válido fornecido pelo usuário
     */
    public static int nextInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número válido:");
                scanner.next();
            }
        }
    }

    /**
     * Lê um número decimal (double) do scanner, repetindo a solicitação em caso de entrada inválida.
     *
     * @param scanner instância de {@link Scanner} para leitura
     * @return double válido fornecido pelo usuário
     */
    public static double nextDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número válido:");
                scanner.next();
            }
        }
    }
}
