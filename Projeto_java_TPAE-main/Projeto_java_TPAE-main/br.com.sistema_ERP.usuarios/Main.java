import java.util.Scanner;

/**
 * @author André Luca Souza Sampaio 202510525
 * @author Bruno Trindade Faria 202510540
 * @author João Gabriel Carvalho Criscolo 202511049
 * @author Luis Felipe Sousa Melo 202510531
 * @author Plínio Cardoso Alves 202510938
 * Turma: 22B
 */

/**
 * Ponto de entrada do sistema ERP.
 * <p>Inicializa os módulos com dados padrão, realiza o login do usuário e
 * mantém o loop principal do sistema até que o usuário opte por sair.
 * Ao encerrar, persiste todos os dados nos arquivos CSV correspondentes.</p>
 */
public class Main {

    /**
     * Método principal da aplicação.
     * <p>Sequência de execução:
     * <ol>
     *   <li>Cria usuários padrão no módulo RH</li>
     *   <li>Carrega dados de teste em todos os módulos</li>
     *   <li>Realiza o login do usuário</li>
     *   <li>Exibe o menu e processa as ações em loop</li>
     *   <li>Salva todos os dados em CSV ao encerrar</li>
     * </ol>
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {

        RH.criarUsuarioPadrao();
        Scanner scanner = new Scanner(System.in);
        int id;

        // dados de teste
        Financeiro.dados_teste();
        Estoque.dados_teste();
        Vendas.dados_teste();
        Cliente.dados_teste();
        Vendas.dados_teste();

        try {
            id = RH.login(scanner);
            if (id == -1) {
                System.out.println("Encerrando o sistema devido a falha no login.");
                System.out.println("===========================");
                scanner.close();
                return;
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante o login: " + e.getMessage());
            scanner.close();
            return;
        }

        int seta = -1;
        while (seta != 0) {
            Interface.telaInicial(scanner, id);
            System.out.print("Deseja continuar? (1 - Sim, 0 - Não): ");
            seta = TesteEntrada.nextInt(scanner);
            scanner.nextLine(); // Limpar o buffer do scanner
            while (seta != 0 && seta != 1) {
                System.out.print("Entrada inválida. Por favor, digite 1 para Sim ou 0 para Não: ");
                seta = TesteEntrada.nextInt(scanner);
                scanner.nextLine(); // Limpar o buffer do scanner
            }
        }

        Financeiro.registrarEmArquivoCSV();
        RH.registrarEmArquivoCSV();
        Vendas.registrarEmArquivoCSV();
        Estoque.registrarCSV();
        Cliente.registrarEmArquivoCSV();

        scanner.close();
        System.out.println("Sistema encerrado.");
    }
}
