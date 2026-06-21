import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Módulo de clientes do sistema ERP.
 * <p>Gerencia o cadastro, exibição, remoção e alteração de dados de clientes.
 * Cada cliente é representado por uma {@link Pessoa} e vinculado às suas compras.
 * Os dados são persistidos em {@code clientes.csv} e {@code compras.csv}.</p>
 */
public class Cliente {

    private static HashMap<Integer, Pessoa> banco_clientes = new HashMap<>();
    static {
        carregarDeArquivoCSV();
    }

    /**
     * Popula o banco de clientes com dados de exemplo para testes.
     */
    public static void dados_teste() {

    Pessoa cliente1 = new Pessoa(
            "123.456.789-00",
            "João Silva",
            "joao.silva@example.com",
            "11 99999-9999",
            "Rua Exemplo, 123, São Paulo/SP");
    banco_clientes.put(1, cliente1);

    Pessoa cliente2 = new Pessoa(
            "987.654.321-00",
            "Maria Souza",
            "maria.souza@example.com",
            "31 98888-1111",
            "Av. Central, 456, Belo Horizonte/MG");
    banco_clientes.put(2, cliente2);

    Pessoa cliente3 = new Pessoa(
            "456.789.123-10",
            "Carlos Oliveira",
            "carlos.oliveira@example.com",
            "21 97777-2222",
            "Rua das Flores, 78, Rio de Janeiro/RJ");
    banco_clientes.put(3, cliente3);

    Pessoa cliente4 = new Pessoa(
            "741.852.963-20",
            "Fernanda Lima",
            "fernanda.lima@example.com",
            "41 96666-3333",
            "Rua Paraná, 890, Curitiba/PR");
    banco_clientes.put(4, cliente4);

    Pessoa cliente5 = new Pessoa(
            "258.369.147-30",
            "Pedro Almeida",
            "pedro.almeida@example.com",
            "85 95555-4444",
            "Av. Beira Mar, 1020, Fortaleza/CE");
    banco_clientes.put(5, cliente5);

    Pessoa cliente6 = new Pessoa(
            "369.258.147-40",
            "Juliana Martins",
            "juliana.martins@example.com",
            "61 94444-5555",
            "SQN 308, Brasília/DF");
    banco_clientes.put(6, cliente6);

    Pessoa cliente7 = new Pessoa(
            "159.357.486-50",
            "Lucas Santos",
            "lucas.santos@example.com",
            "51 93333-6666",
            "Rua da Praia, 55, Porto Alegre/RS");
    banco_clientes.put(7, cliente7);

    Pessoa cliente8 = new Pessoa(
            "852.741.963-60",
            "Camila Rocha",
            "camila.rocha@example.com",
            "71 92222-7777",
            "Av. Oceânica, 310, Salvador/BA");
    banco_clientes.put(8, cliente8);

    Pessoa cliente9 = new Pessoa(
            "963.852.741-70",
            "Gabriel Mendes",
            "gabriel.mendes@example.com",
            "19 91111-8888",
            "Rua Campinas, 150, Campinas/SP");
    banco_clientes.put(9, cliente9);

    Pessoa cliente10 = new Pessoa(
            "147.258.369-80",
            "Larissa Barbosa",
            "larissa.barbosa@example.com",
            "32 90000-9999",
            "Rua Tiradentes, 340, Juiz de Fora/MG");
    banco_clientes.put(10, cliente10);
}

    /**
     * Retorna o mapa interno de clientes.
     *
     * @return mapa com código do cliente como chave e {@link Pessoa} como valor
     */
    public static HashMap<Integer, Pessoa> getBancoClientes() {
        return banco_clientes;
    }

    /**
     * Solicita os dados ao usuário e cadastra um novo cliente.
     *
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void cadastrarCliente(Scanner scanner) {
        System.out.println("Registrar Cliente");
        System.out.println("Digite o nome do cliente:");
        scanner.nextLine(); // Limpar buffer do scanner
        String nome = scanner.nextLine();
        System.out.println("Digite o email do cliente:");
        String email = scanner.nextLine();
        System.out.println("Digite o CPF do cliente:");
        String cpf = scanner.nextLine();
        System.out.println("Digite o telefone do cliente:");
        String telefone = scanner.nextLine();
        System.out.println("Digite o endereço do cliente:");
        String endereco = scanner.nextLine();

        Pessoa cliente = new Pessoa(cpf, nome, email, telefone, endereco);

        int codigo = banco_clientes.size() + 1;
        banco_clientes.put(codigo, cliente);
        System.out.println("Cliente registrado: " + banco_clientes.get(codigo).getNome());

    }

    /**
     * Exibe a lista resumida de todos os clientes cadastrados.
     */
    public static void exibirClientes() {
        System.out.println("Clientes Cadastrados:");
        for (Integer codigo : banco_clientes.keySet()) {
            System.out.println("Código: " + codigo + ", Nome: " + banco_clientes.get(codigo).getNome() + ", Email: " + banco_clientes.get(codigo).getEmail() + ", Número: " + banco_clientes.get(codigo).getTelefone() + ", CPF: " + banco_clientes.get(codigo).getCpf());
        }
    }

    /**
     * Exibe todos os dados de um único cliente pelo seu código.
     *
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void exibirCliente(Scanner scanner) {
        System.out.println("Digite o código do cliente:");
        int codigo = TesteEntrada.nextInt(scanner);
        scanner.nextLine();
        if (!banco_clientes.containsKey(codigo)) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        Pessoa cliente = banco_clientes.get(codigo);
        System.out.println("===========================");
        System.out.println("Código: " + codigo);
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Endereço: " + cliente.getEndereco());
        System.out.println("===========================");
    }

    /**
     * Remove um cliente do banco de dados pelo seu código.
     *
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void removerCliente(Scanner scanner) {
        System.out.println("Remover Cliente");
        System.out.println("Digite o código do cliente:");
        int codigoCliente = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        if (banco_clientes.containsKey(codigoCliente)) {
            System.out.println("Cliente removido: " + banco_clientes.get(codigoCliente).getNome());
            banco_clientes.remove(codigoCliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    /**
     * Exporta todos os clientes e suas compras para {@code clientes.csv} e {@code compras.csv}.
     */
    public static void registrarEmArquivoCSV() {
        try (PrintWriter writer = new PrintWriter("clientes.csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("codigo;nome;email;cpf;telefone;endereco\n");
            for (Integer codigo : banco_clientes.keySet()) {
                Pessoa cliente = banco_clientes.get(codigo);
                sb.append(codigo).append(";")
                        .append(cliente.getNome()).append(";")
                        .append(cliente.getEmail()).append(";")
                        .append(cliente.getCpf()).append(";")
                        .append(cliente.getTelefone()).append(";")
                        .append(cliente.getEndereco()).append("\n");
            }
            writer.write(sb.toString());
            System.out.println("Clientes registrados em clientes.csv");
        } catch (Exception erro) {
            System.out.println("Erro ao registrar clientes em arquivo CSV: " + erro.getMessage());
        }
          try {

        PrintWriter writer = new PrintWriter("compras.csv");

        writer.println("cpfCliente;codigoProduto;quantidadeVendida;dataVenda;nomeProduto");

        for (Pessoa cliente : banco_clientes.values()) {

            for (Sale compra : cliente.getCompras()) {

                writer.println(
                        cliente.getCpf() + ";" +
                        compra.getCodigoProduto() + ";" +
                        compra.getQuantidadeVendida() + ";" +
                        compra.getDataVenda() + ";" +
                        compra.getNomeProduto());
            }
        }

        writer.close();

        System.out.println("==================================================");
        System.out.println("Arquivo compras.csv criado com sucesso!");
        System.out.println("==================================================");

    } catch (Exception erro) {

        System.out.println("==================================================");
        System.out.println("Ocorreu um erro ao criar o arquivo compras.csv.");
        System.out.println("==================================================");
    }
}
    

    /**
     * Carrega os clientes do arquivo {@code clientes.csv} para o banco de clientes.
     */
    public static void carregarDeArquivoCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("clientes.csv"))) {
            banco_clientes.clear();

            String linha = reader.readLine();

            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue;
                }

                String[] dados = linha.split(";", 6);

                if (dados.length == 6) {
                    int codigo = Integer.parseInt(dados[0].trim());
                    String nome = dados[1].trim();
                    String email = dados[2].trim();
                    String cpf = dados[3].trim();
                    String telefone = dados[4].trim();
                    String endereco = dados[5].trim();

                    Pessoa cliente = new Pessoa(cpf, nome, email, telefone, endereco);
                    cliente.setNome(nome);
                    cliente.setEmail(email);
                    cliente.setCpf(cpf);
                    cliente.setTelefone(telefone);
                    cliente.setEndereco(endereco);
                    
                    banco_clientes.put(codigo, cliente);
                }
            }

            System.out.println("Clientes carregados de clientes.csv com sucesso!");

        } catch (Exception erro) {
            System.out.println("Erro ao carregar clientes do arquivo CSV: " + erro.getMessage());
        }
    }

    /**
     * Lista todas as compras de um cliente específico, identificado pelo CPF.
     *
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void listarCompras(Scanner scanner) {

    System.out.println("Digite o CPF do cliente:");
    scanner.nextLine();
    String CPF = scanner.nextLine();

    String formatoCPF;

    if (CPF.matches("\\d+")) {
        formatoCPF = CPF.substring(0, 3) + "." +
                     CPF.substring(3, 6) + "." +
                     CPF.substring(6, 9) + "-" +
                     CPF.substring(9, 11);
    } else {
        formatoCPF = CPF;
    }

    Pessoa cliente = null;


    for (Pessoa p : banco_clientes.values()) {
        if (p.getCpf().equals(formatoCPF)) {
            cliente = p;
            
        }
    }

    if (cliente == null) {
        System.out.println("Cliente não encontrado.");
       
    }

    if (cliente.getCompras().isEmpty()) {
        System.out.println("Nenhuma compra encontrada para o cliente.");
      
    }

    System.out.println("Compras de " + cliente.getNome() + ":");

    for (Sale venda : cliente.getCompras()) {

        System.out.println(
                "Produto: " + venda.getNomeProduto() +
                " | Quantidade: " + venda.getQuantidadeVendida() +
                " | Data: " + venda.getDataVenda());
    }
}
    /**
     * Lista as compras de todos os clientes cadastrados.
     */
    public static void listarTodasAsCompras() {

    System.out.println("========== COMPRAS DOS CLIENTES ==========");

    for (Pessoa cliente : banco_clientes.values()) {

        if (!cliente.getCompras().isEmpty()) {

            System.out.println("\nCliente: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());

            for (Sale venda : cliente.getCompras()) {

                System.out.println("- Produto: " + venda.getNomeProduto()
                        + " | Quantidade: " + venda.getQuantidadeVendida()
                        + " | Data: " + venda.getDataVenda());
            }
        }
    }
}
    
    /**
     * Permite alterar os dados cadastrais de um cliente existente.
     *
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void alterarDados(Scanner scanner) {

    exibirClientes();

    System.out.println("Digite o código do cliente que deseja alterar:");
    int codigo = scanner.nextInt();
    scanner.nextLine();

    if (!banco_clientes.containsKey(codigo)) {
        System.out.println("Cliente não encontrado.");
        return;
    }

    Pessoa cliente = banco_clientes.get(codigo);

    System.out.println("""
            O que deseja alterar?
            1 - Nome
            2 - Email
            3 - CPF
            4 - Telefone
            5 - Endereço
            """);

    int opcao = scanner.nextInt();
    scanner.nextLine();

    switch (opcao) {

        case 1:
            System.out.println("Novo nome:");
            cliente.setNome(scanner.nextLine());
            break;

        case 2:
            System.out.println("Novo email:");
            cliente.setEmail(scanner.nextLine());
            break;

        case 3:
            System.out.println("Novo CPF:");
            cliente.setCpf(scanner.nextLine());
            break;

        case 4:
            System.out.println("Novo telefone:");
            cliente.setTelefone(scanner.nextLine());
            break;

        case 5:
            System.out.println("Novo endereço:");
            cliente.setEndereco(scanner.nextLine());
            break;

        default:
            System.out.println("Opção inválida.");
            return;
    }

    // Atualiza o HashMap
    banco_clientes.put(codigo, cliente);


    System.out.println("Dados alterados com sucesso!");
}

}
