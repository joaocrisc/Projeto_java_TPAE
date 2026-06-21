import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Módulo de vendas do sistema ERP.
 * <p>Gerencia o registro de vendas, associação com clientes e exportação para CSV.
 * Ao registrar uma venda, o estoque é atualizado e o faturamento é lançado
 * automaticamente no módulo {@link Financeiro}.</p>
 */
public class Vendas {

    private static HashMap<Integer, Sale> banco_vendas = new HashMap<>();
    static {
        Vendas.LeituraCSV(banco_vendas);
    }

    /**
     * Popula o banco de vendas com registros de exemplo para testes.
     */
    public static void dados_teste() {

        adicionarVenda(1,
                new Sale(1001, 2, "01/01/2024", "Arroz", "123.456.789-00"));

        adicionarVenda(2,
                new Sale(1002, 3, "02/01/2024", "Feijão Carioca", "987.654.321-00"));

        adicionarVenda(3,
                new Sale(1003, 5, "03/01/2024", "Macarrão Espaguete", "456.789.123-10"));

        adicionarVenda(4,
                new Sale(2001, 4, "04/01/2024", "Detergente", "741.852.963-20"));

        adicionarVenda(5,
                new Sale(2002, 2, "05/01/2024", "Água Sanitária", "258.369.147-30"));

        adicionarVenda(6,
                new Sale(2003, 1, "06/01/2024", "Desinfetante", "369.258.147-40"));

        adicionarVenda(7,
                new Sale(3001, 1, "08/01/2024", "Smartphone", "159.357.486-50"));

        adicionarVenda(8,
                new Sale(3002, 1, "10/01/2024", "Notebook", "852.741.963-60"));

        adicionarVenda(9,
                new Sale(3003, 3, "12/01/2024", "Fone Bluetooth", "963.852.741-70"));

        adicionarVenda(10,
                new Sale(1001, 8, "15/01/2024", "Arroz", "147.258.369-80"));

        adicionarVenda(11,
                new Sale(1004, 2, "18/01/2024", "Açúcar", "123.456.789-00"));

        adicionarVenda(12,
                new Sale(1005, 6, "20/01/2024", "Café", "123.456.789-00"));

        adicionarVenda(13,
                new Sale(3004, 1, "22/01/2024", "Mouse Gamer", "987.654.321-00"));

        adicionarVenda(14,
                new Sale(2004, 3, "24/01/2024", "Sabão em Pó", "741.852.963-20"));

        adicionarVenda(15,
                new Sale(3005, 2, "26/01/2024", "Teclado Mecânico", "159.357.486-50"));
    }

    /**
     * Adiciona uma venda ao banco de vendas e registra a compra no histórico do cliente.
     *
     * @param codigoVenda código identificador da venda
     * @param venda       objeto {@link Sale} com os dados da venda
     */
    private static void adicionarVenda(int codigoVenda, Sale venda) {
        banco_vendas.put(codigoVenda, venda);
        for (Pessoa cliente : Cliente.getBancoClientes().values()) {
            if (cliente.getCpf().equals(venda.getCpfCliente())) {
                cliente.adicionarCompra(venda);
            }
        }
    }

    /**
     * Solicita os dados ao usuário e registra uma nova venda.
     * <p>Verifica se há estoque suficiente antes de confirmar a venda.
     * Ao confirmar, atualiza o estoque e lança o faturamento no módulo financeiro.</p>
     *
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void registrarVenda(Scanner scanner) {
        try {
            System.out.println("Registrar Venda");
            System.out.println("Digite o código do produto:");
            int codigoProduto = TesteEntrada.nextInt(scanner);
            scanner.nextLine();
            System.out.println("Digite a quantidade vendida:");
            int quantidadeVendida = TesteEntrada.nextInt(scanner);
            scanner.nextLine();
            System.out.println("Digite a data da venda (dd/mm/yyyy):");
            String dataVenda = scanner.nextLine();
            System.out.println("Digite o CPF do cliente:");
            String cpfCliente = scanner.nextLine();
            if (cpfCliente.length() == 11) {
                cpfCliente = cpfCliente.substring(0, 3) + "." + cpfCliente.substring(3, 6) + "." + cpfCliente.substring(6, 9) + "-" + cpfCliente.substring(9, 11);
            }

            if (Estoque.verificarEstoque(codigoProduto) >= quantidadeVendida) {
                Estoque.atualizarQuantidade(codigoProduto, -quantidadeVendida);
                System.out.println("Venda registrada: Produto código " + codigoProduto + ", Quantidade vendida: "
                        + quantidadeVendida);
                Financeiro.registrarFaturamento(Estoque.valorTotalProdutos(codigoProduto, quantidadeVendida),
                        Estoque.NomeProduto(codigoProduto), dataVenda);
                Sale venda = new Sale(codigoProduto, quantidadeVendida, dataVenda, Estoque.NomeProduto(codigoProduto),
                        cpfCliente);
                banco_vendas.put(codigoProduto, venda);
            } else {
                System.out.println("Estoque insuficiente para o produto código " + codigoProduto);
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao registrar a venda: ");
        }
    }

    /**
     * Exibe todas as vendas realizadas em uma data específica.
     *
     * @param data data no formato dd/MM/yyyy
     */
    public static void exibirVendasPorData(String data) {
        System.out.println("Vendas na data: " + data);
        for (Integer codigo : banco_vendas.keySet()) {
            if (banco_vendas.get(codigo).getDataVenda().equalsIgnoreCase(data)) {
                System.out.println("codigo: " + codigo + ", Venda: " + banco_vendas.get(codigo).getNomeProduto());
            }
        }
    }

    /**
     * Exporta todas as vendas para o arquivo {@code vendas.csv}.
     */
    public static void registrarEmArquivoCSV() {
        try {
            PrintWriter writer = new PrintWriter("vendas.csv");
            writer.println("codigoProduto;quantidadeVendida;dataVenda;nomeProduto;cpfCliente");
            for (Map.Entry<Integer, Sale> entry : banco_vendas.entrySet()) {
                Integer codigo = entry.getKey();
                Sale venda = entry.getValue();
                writer.println(
                        codigo + ";" +
                                venda.getQuantidadeVendida() + ";" +
                                venda.getDataVenda() + ";" +
                                venda.getNomeProduto() + ";" +
                                venda.getCpfCliente());
            }
            writer.close();
            System.out.println("==================================================");
            System.out.println("Arquivo vendas.csv criado com sucesso!");
            System.out.println("==================================================");
        } catch (Exception erro) {
            System.out.println("==================================================");
            System.out.println("Ocorreu um erro ao criar o arquivo vendas.csv: ");
            System.out.println("==================================================");
        }
    }

    /**
     * Permite editar a quantidade e a data de uma venda existente.
     *
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void editarVenda(Scanner scanner) {
        System.out.println("Editar Venda");
        System.out.println("Digite o código do produto da venda que deseja editar:");
        int codigoProduto = scanner.nextInt();
        scanner.nextLine();

        if (banco_vendas.containsKey(codigoProduto)) {
            Sale venda = banco_vendas.get(codigoProduto);
            System.out.println("Venda encontrada: " + venda);
            System.out.println("Digite a nova quantidade vendida:");
            int novaQuantidadeVendida = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Digite a nova data da venda (dd/mm/yyyy):");
            String novaDataVenda = scanner.nextLine();

            venda.setQuantidadeVendida(novaQuantidadeVendida);
            venda.setDataVenda(novaDataVenda);
            banco_vendas.put(codigoProduto, venda);

            System.out.println("Venda atualizada: " + venda);
        } else {
            System.out.println("Venda não encontrada para o código do produto: " + codigoProduto);
        }
    }

    /**
     * Carrega as vendas do arquivo {@code vendas.csv} para o banco de vendas.
     *
     * @param banco_vendas mapa de destino para as vendas carregadas
     */
    public static void LeituraCSV(HashMap<Integer, Sale> banco_vendas) {
        try (Scanner scanner = new Scanner(new File("vendas.csv"))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (linha.trim().isEmpty()) {
                    continue;
                }
                String[] campos = linha.split(";");
                int codigoProduto = Integer.parseInt(campos[0]);
                int quantidadeVendida = Integer.parseInt(campos[1]);
                String dataVenda = campos[2];
                String nomeProduto = campos[3];
                String cpfCliente = campos[4];
                Sale venda = new Sale(codigoProduto, quantidadeVendida, dataVenda, nomeProduto, cpfCliente);
                banco_vendas.put(codigoProduto, venda);
            }
        } catch (Exception erro) {
            System.out.println("Ocorreu um erro ao ler o arquivo vendas.csv: " + erro.getMessage());
        }
    }

}
