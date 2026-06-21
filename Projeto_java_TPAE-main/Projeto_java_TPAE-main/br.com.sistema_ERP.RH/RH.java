import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Módulo de RH do sistema ERP.
 * <p>Gerencia o ciclo de vida dos usuários: cadastro, login, controle de acesso
 * por departamento, registro de ponto, faltas e folha de pagamento.
 * Os dados são persistidos em {@code Usuarios.csv} e {@code FolhaPagamento.csv}.</p>
 */
public class RH {

    private static HashMap<Integer, Usuario> banco_usuarios = new HashMap<>();

    static {
        LerDoArquivoCSV();
    }

    /**
     * Busca um usuário pelo ID e exibe seus dados no console.
     *
     * @param id identificador do usuário
     * @return mapa completo de usuários (para encadeamento, se necessário)
     */
    protected static HashMap<Integer, Usuario> BuscarUsuario(int id) {
        if (banco_usuarios.containsKey(id)) {

            System.out.println("Usuário encontrado: " + banco_usuarios.get(id).getNome());
            System.out.println("Email: " + banco_usuarios.get(id).getEmail());
            System.out.println("Ativo: " + banco_usuarios.get(id).getAtivo());
            System.out.println("Cargo: " + banco_usuarios.get(id).getClass().getSimpleName());

            if (banco_usuarios.get(id) instanceof Funcionario) {
                Funcionario funcionario = (Funcionario) banco_usuarios.get(id);
                System.out.println("Departamento: " + funcionario.getDepartamento());
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }

        return banco_usuarios;
    }

    /**
     * Exporta todos os usuários para {@code Usuarios.csv} e a folha de pagamento
     * calculada para {@code FolhaPagamento.csv}.
     */
    public static void registrarEmArquivoCSV() {

        try {

            PrintWriter writer = new PrintWriter("Usuarios.csv");

            writer.println("ID;Nome;Email;Senha;Ativo;Cargo;Departamento;Numero_de_Faltas");

            for (Map.Entry<Integer, Usuario> entry : banco_usuarios.entrySet()) {

                Integer id = entry.getKey();
                Usuario usuario = entry.getValue();

                String departamento = usuario.getDepartamento();
                if (departamento == null || departamento.isEmpty()) {
                    departamento = "N/A";
                }

                writer.println(
                        id + ";" +
                                usuario.getNome() + ";" +
                                usuario.getEmail() + ";" +
                                usuario.getSenha() + ";" +
                                usuario.getAtivo() + ";" +
                                usuario.getClass().getSimpleName() + ";" +
                                departamento + ";" +
                                usuario.getNumeroFaltas());
            }

          System.out.println("==================================================");
            System.out.println("Arquivo Usuarios.csv criado com sucesso!");
            System.out.println("==================================================");
            writer.close();
        } catch (Exception e) {
            System.out.println("==================================================");
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
            System.out.println("==================================================");
        }

        try {
            PrintWriter writer = new PrintWriter("FolhaPagamento.csv");
            writer.println("ID;Nome;Cargo;Departamento;Pagamento_mes");
            for (Map.Entry<Integer, Usuario> entry : banco_usuarios.entrySet()) {
                Integer id = entry.getKey();
                Usuario usuario = entry.getValue();
                int dias_faltas = usuario.getNumeroFaltas();
                // descontar dias faltados
                double proporcional = (dias_faltas / 20.0) * usuario.getSalario();

                if (usuario instanceof Funcionario) {
                    Funcionario funcionario = (Funcionario) usuario;
                    String departamento = funcionario.getDepartamento();
                    if (departamento == null || departamento.isEmpty()) {
                        departamento = "N/A";
                    }
                    writer.println(
                            id + ";" +
                                    funcionario.getNome() + ";" +
                                    "Funcionario" + ";" +
                                    departamento + ";" +
                                    (funcionario.getSalario() - proporcional));
                } else if (usuario instanceof Gerente) {
                    Gerente gerente = (Gerente) usuario;
                    String departamento = gerente.getDepartamento();
                    if (departamento == null || departamento.isEmpty()) {
                        departamento = "N/A";
                    }
                    writer.println(
                            id + ";" +
                                    gerente.getNome() + ";" +
                                    "Gerente" + ";" +
                                    departamento + ";" +
                                    (gerente.getSalario() - proporcional));
                }
            }
           System.out.println("==================================================");
            System.out.println("Arquivo FolhaPagamento.csv criado com sucesso!");
            System.out.println("==================================================");
            writer.close();
        } catch (Exception e) {
            // Print de erro enfeitado
            System.out.println("==================================================");
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
            System.out.println("==================================================");
        }
        
    }

    /**
     * Popula o banco de usuários com um conjunto padrão de gerentes e funcionários
     * de exemplo para uso em testes e demonstração do sistema.
     */
    protected static void criarUsuarioPadrao() {

    Gerente usuario_padrao = new Gerente(0, "Administrador", "admin@sistema.com", 0, "Geral", 0.0);
    banco_usuarios.put(0, usuario_padrao);

    Gerente gerente1 = new Gerente(1, "João Silva", "joao@example.com", 123, "Vendas", 6000.0);
    banco_usuarios.put(1, gerente1);

    Gerente gerente2 = new Gerente(2, "Ana Pereira", "ana@example.com", 456, "Financeiro", 6500.0);
    banco_usuarios.put(2, gerente2);

    Funcionario funcionario1 = new Funcionario(3, "Carlos Oliveira", "carlos@example.com", 111, "Estoque", 2500.0);
    banco_usuarios.put(3, funcionario1);

    Funcionario funcionario2 = new Funcionario(4, "Maria Souza", "maria@example.com", 222, "RH", 2800.0);
    banco_usuarios.put(4, funcionario2);

    Funcionario funcionario3 = new Funcionario(5, "Pedro Almeida", "pedro@example.com", 333, "Vendas", 2700.0);
    banco_usuarios.put(5, funcionario3);

    Funcionario funcionario4 = new Funcionario(6, "Juliana Martins", "juliana@example.com", 444, "Financeiro", 3000.0);
    banco_usuarios.put(6, funcionario4);

    Funcionario funcionario5 = new Funcionario(7, "Lucas Santos", "lucas@example.com", 555, "Estoque", 2400.0);
    banco_usuarios.put(7, funcionario5);

    Funcionario funcionario6 = new Funcionario(8, "Camila Rocha", "camila@example.com", 666, "RH", 2900.0);
    banco_usuarios.put(8, funcionario6);

    Funcionario funcionario7 = new Funcionario(9, "Rafael Gomes", "rafael@example.com", 777, "Vendas", 2750.0);
    banco_usuarios.put(9, funcionario7);

    Funcionario funcionario8 = new Funcionario(10, "Beatriz Ferreira", "beatriz@example.com", 888, "Financeiro", 3100.0);
    banco_usuarios.put(10, funcionario8);

    Funcionario funcionario9 = new Funcionario(11, "Gabriel Mendes", "gabriel@example.com", 999, "Estoque", 2550.0);
    banco_usuarios.put(11, funcionario9);

    Funcionario funcionario10 = new Funcionario(12, "Larissa Barbosa", "larissa@example.com", 1010, "Vendas", 2850.0);
    banco_usuarios.put(12, funcionario10);

    Funcionario funcionario11 = new Funcionario(13, "Bruno Costa", "bruno@example.com", 1111, "RH", 2700.0);
    banco_usuarios.put(13, funcionario11);

    Funcionario funcionario12 = new Funcionario(14, "Fernanda Lima", "fernanda@example.com", 1212, "Financeiro", 3200.0);
    banco_usuarios.put(14, funcionario12);

    Funcionario funcionario13 = new Funcionario(15, "Thiago Ribeiro", "thiago@example.com", 1313, "Estoque", 2500.0);
    banco_usuarios.put(15, funcionario13);

    Funcionario funcionario14 = new Funcionario(16, "Patrícia Alves", "patricia@example.com", 1414, "RH", 2800.0);
    banco_usuarios.put(16, funcionario14);

    Funcionario funcionario15 = new Funcionario(17, "Eduardo Nunes", "eduardo@example.com", 1515, "Vendas", 2950.0);
    banco_usuarios.put(17, funcionario15);

    Funcionario funcionario16 = new Funcionario(18, "Vanessa Carvalho", "vanessa@example.com", 1616, "Financeiro", 3050.0);
    banco_usuarios.put(18, funcionario16);

    Funcionario funcionario17 = new Funcionario(19, "Ricardo Moreira", "ricardo@example.com", 1717, "Estoque", 2450.0);
    banco_usuarios.put(19, funcionario17);

    Funcionario funcionario18 = new Funcionario(20, "Aline Rodrigues", "aline@example.com", 1818, "Vendas", 2900.0);
    banco_usuarios.put(20, funcionario18);
}

    /**
     * Solicita os dados ao usuário e cadastra um novo usuário no sistema.
     *
     * @param scanner instância de {@link Scanner} para leitura da entrada
     * @return mapa atualizado de usuários
     */
    protected static HashMap<Integer, Usuario> cadastrarUsuario(Scanner scanner) {
        System.out.println("Cadastro de Usuário:");
        // Lógica para cadastrar usuário
        System.out.println("Digite o nome do usuário:");
        scanner.nextLine(); // Limpar o buffer do scanner
        String nome = scanner.nextLine();
        System.out.println("Digite o email do usuário:");
        String email = scanner.nextLine();
        System.out.println("Digite a senha do usuário:");
        int senha = TesteEntrada.nextInt(scanner);
        int id = banco_usuarios.size();

        Usuario novoUsuario = new Usuario(id, nome, email, senha, 0);
        banco_usuarios.put(novoUsuario.getID(), novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
        System.out.println("ID do usuário:" + novoUsuario.getID());

        return banco_usuarios;
    }

    /**
     * Solicita os dados complementares e transforma um usuário em funcionário.
     *
     * @param usuario usuário base já cadastrado no sistema
     * @param scanner instância de {@link Scanner} para leitura da entrada
     * @return novo objeto {@link Funcionario} criado
     */
    protected static Funcionario cadastrarFuncionario(Usuario usuario, Scanner scanner) {

        System.out.println("Cadastro de Funcionário:");
        // Lógica para cadastrar funcionário

        System.out.println("Digite o departamento do funcionário:");
        System.out.println("1. Vendas");
        System.out.println("2. Estoque");
        System.out.println("3. RH");
        System.out.println("4. Financeiro");
        int departamento_opcao = TesteEntrada.nextInt(scanner);
        scanner.nextLine(); // Limpar o buffer do scanner
        String departamento = "";
        switch (departamento_opcao) {
            case 1:
                departamento = "Vendas";
                break;
            case 2:
                departamento = "Estoque";
                break;
            case 3:
                departamento = "RH";
                break;
            case 4:
                departamento = "Financeiro";
                break;
            default:
                System.out.println("Opção inválida. Definindo departamento como 'Geral'.");
                departamento = "Geral";
        }

        System.out.println("salario do funcionário:");
        double salario = TesteEntrada.nextDouble(scanner);
        scanner.nextLine(); // Limpar o buffer do scanner

        Funcionario novoFuncionario = new Funcionario(usuario.getID(), usuario.getNome(), usuario.getEmail(),
                usuario.getSenha(), departamento, salario);
        novoFuncionario.setDepartamento(departamento);

        System.out.println("Funcionário cadastrado com sucesso!");
        System.out.println("Departamento: " + departamento);
        System.out.println("ID do funcionário: " + novoFuncionario.getID());

        return novoFuncionario;
    }

    /**
     * Solicita os dados complementares e transforma um usuário em gerente.
     *
     * @param usuario usuário base já cadastrado no sistema
     * @param scanner instância de {@link Scanner} para leitura da entrada
     * @return novo objeto {@link Gerente} criado
     */
    protected static Gerente cadastrarGerente(Usuario usuario, Scanner scanner) {
        System.out.println("Cadastro de Gerente:");

        System.out.println("Digite o departamento do gerente:");
        System.out.println("1. Vendas");
        System.out.println("2. Estoque");
        System.out.println("3. RH");
        System.out.println("4. Financeiro");
        int departamento_opcao = TesteEntrada.nextInt(scanner);
        scanner.nextLine(); // Limpar o buffer do scanner
        String departamento = "";
        switch (departamento_opcao) {
            case 1:
                departamento = "Vendas";
                break;
            case 2:
                departamento = "Estoque";
                break;
            case 3:
                departamento = "RH";
                break;
            case 4:
                departamento = "Financeiro";
                break;
            default:
                System.out.println("Opção inválida. Definindo departamento como 'Geral'.");
                departamento = "Geral";
        }

        System.out.println("salario do gerente:");
        double salario = TesteEntrada.nextDouble(scanner);
        scanner.nextLine(); // Limpar o buffer do scanner

        Gerente novoGerente = new Gerente(usuario.getID(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(),
                departamento, salario);

        System.out.println("Gerente cadastrado com sucesso!");
        System.out.println("ID do gerente:" + novoGerente.getID());

        return novoGerente;
    }

    /**
     * Promove um funcionário existente ao cargo de gerente, mantendo seus dados.
     *
     * @param id      identificador do usuário a promover
     * @param usuario objeto {@link Usuario} a ser promovido
     * @return novo {@link Gerente} criado, ou {@code null} se o usuário já for gerente
     *         ou não for funcionário
     */
    protected static Gerente promoverFuncionario(int id, Usuario usuario) {
        if (usuario instanceof Funcionario) {
            Funcionario funcionario = (Funcionario) usuario;
            Gerente gerente_promovido = new Gerente(funcionario.getID(), funcionario.getNome(), funcionario.getEmail(),
                    funcionario.getSenha(), funcionario.getDepartamento(), funcionario.getSalario());
            System.out.println("Usuário promovido a gerente com sucesso! ID: " + gerente_promovido.getID());
            return gerente_promovido;

        } else if (usuario instanceof Gerente) {
            System.out.println("O usuário já é um gerente.");
        } else {
            System.out.println("O usuário não é um funcionário e não pode ser promovido a gerente.");
        }
        return null;
    }

    /**
     * Realiza o login no sistema solicitando ID e senha.
     *
     * @param scanner instância de {@link Scanner} para leitura da entrada
     * @return ID do usuário autenticado, ou -1 em caso de falha
     */
    protected static int login(Scanner scanner) {

        System.out.println("===========================");
        System.out.println("Faça login para acessar as opções:");
        System.out.println("Digite seu id:");

        int id = TesteEntrada.nextInt(scanner);
        System.out.println("Digite sua senha:");
        int senha = TesteEntrada.nextInt(scanner);
        scanner.nextLine(); // Limpar o buffer do scanner

        if (banco_usuarios.containsKey(id) && senha == banco_usuarios.get(id).getSenha()
                && banco_usuarios.get(id).getAtivo()) {
            System.out.println("Login bem-sucedido! Bem-vindo, " + banco_usuarios.get(id).getNome() + "!");
        } else {
            System.out.println("===========================");
            System.out.println("Email ou senha incorretos ou usuário inativo. Acesso negado.");
            return -1;
        }
        System.out.println("===========================");
        return id;
    }

    /**
     * Processa as opções do submenu de cadastro (cadastrar usuário ou promover funcionário).
     * Apenas gerentes podem executar estas ações.
     *
     * @param id     identificador do gerente logado
     * @param seta   opção escolhida (1=cadastrar, 2=promover)
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    protected static void submenuCadastrar(int id, int seta, Scanner scanner) {

        if (id >= 0 && banco_usuarios.get(id) instanceof Gerente) {

            if (seta == 1) {
                banco_usuarios = RH.cadastrarUsuario(scanner);
                int ultimo_id = banco_usuarios.size() - 1;
                Usuario usuario_cadastrado = banco_usuarios.get(ultimo_id);
                System.out.println("Usuário cadastrado com sucesso! ID: " + usuario_cadastrado.getID());
                scanner.nextLine(); // Limpar o buffer do scanner

                System.out.println("Deseja cadastrar um funcionário para este usuário? (s/n)");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("S")) {
                    Funcionario novoFuncionario = RH.cadastrarFuncionario(usuario_cadastrado, scanner);
                    banco_usuarios.put(usuario_cadastrado.getID(), novoFuncionario);
                    System.out.println("Funcionário cadastrado com sucesso! ID: " + novoFuncionario.getID());

                } else if (resposta.equalsIgnoreCase("N")) {
                    System.out.println("Deseja cadastrar um gerente para este usuário? (s/n)");
                    String resposta_gerente = scanner.nextLine();

                    if (resposta_gerente.equalsIgnoreCase("S")) {
                        Gerente novoGerente = RH.cadastrarGerente(usuario_cadastrado, scanner);

                        banco_usuarios.put(usuario_cadastrado.getID(), novoGerente);
                        System.out.println("Gerente cadastrado com sucesso! ID: " + novoGerente.getID());
                    } else {
                        System.out.println("Usuário cadastrado sem vínculo empregatício.");
                    }

                }

            } else if (seta == 2) {
                System.out.println("Digite o ID do usuário para promover:");
                id = TesteEntrada.nextInt(scanner);
                System.out.println("===========================");
                Gerente novo_gerente = RH.promoverFuncionario(id, banco_usuarios.get(id));
                if (novo_gerente != null) {
                    banco_usuarios.put(id, novo_gerente);
                    System.out.println("Usuário promovido a gerente com sucesso! ID: " + novo_gerente.getID());
                }
                System.out.println("===========================");
            } else if (seta == 0) {
                System.out.println("voltando ao menu principal...");
            } else {

                System.out.println("Opção inválida. Tente novamente.");

            }

            System.out.println("===========================");

        }
    }

    /**
     * Processa as opções do submenu de busca de usuários.
     * Apenas gerentes podem executar estas ações.
     *
     * @param id     identificador do gerente logado
     * @param seta   opção escolhida (1=buscar por ID)
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    protected static void submenuBuscar(int id, int seta, Scanner scanner) {

        if (id >= 0 && banco_usuarios.get(id) instanceof Gerente) {
            if (seta == 1) {
                System.out.println("Digite o ID do usuário para buscar:");
                id = TesteEntrada.nextInt(scanner);
                RH.BuscarUsuario(id);
            } else if (seta == 0) {
                System.out.println("voltando ao menu principal...");
            } else {

                System.out.println("Opção inválida. Tente novamente.");

            }
        }
        System.out.println("===========================");
    }

    /**
     * Determina o nível de acesso do usuário com base em seu cargo e departamento.
     *
     * @param id identificador do usuário
     * @return 1=Gerente, 2=Vendas, 3=Estoque, 4=RH, 5=Financeiro, 0=desconhecido
     */
    protected static int departamento(int id) {
        if (banco_usuarios.get(id).getClass().getSimpleName().equals("Gerente")) {
            return 1;
        } else if (banco_usuarios.get(id).getDepartamento().equals("Vendas")) {
            System.out.println("Acesso ao departamento de vendas concedido.");
            return 2;
        } else if (banco_usuarios.get(id).getDepartamento().equals("Estoque")) {
            System.out.println("Acesso ao departamento de estoque concedido.");
            return 3;
        } else if (banco_usuarios.get(id).getDepartamento().equals("RH")) {
            System.out.println("Acesso ao departamento de RH concedido.");
            return 4;
        } else if (banco_usuarios.get(id).getDepartamento().equals("Financeiro")) {
            System.out.println("Acesso ao departamento de financeiro concedido.");
            return 5;
        } else {
            return 0;
        }
    }

    /**
     * Registra o ponto de um funcionário pelo seu ID.
     * Apenas usuários do tipo {@link Funcionario} podem ter o ponto registrado.
     *
     * @param id identificador do funcionário
     */
    protected static void registrarPonto(int id) {
        if (banco_usuarios.get(id) instanceof Funcionario) {
            Funcionario funcionario = (Funcionario) banco_usuarios.get(id);
            funcionario.registrarPonto();
        } else {
            System.out.println("Apenas funcionários do RH podem registrar ponto.");
        }
    }

    /**
     * Calcula e exibe a folha de pagamento de um usuário específico,
     * aplicando descontos proporcionais ao número de faltas.
     *
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    protected static void FolhaPagamento(Scanner scanner){
           System.out.println("Digite o ID do usuario:");
            int id = scanner.nextInt();
           Usuario usuario =  banco_usuarios.get(id);
    
                 if (usuario != null) {
             int dias_faltas = usuario.getNumeroFaltas();
             //descontar dias faltados
        double proporcional = (dias_faltas / 20.0) * usuario.getSalario();

        System.out.println("==================================================");
                System.out.println("Calculando folha de pagamento para " + usuario.getNome() + "...");
                System.out.println("--------------------------------------------------");
                System.out.println("Salário Bruto: " + usuario.getSalario());
                System.out.println("Número de faltas: " + dias_faltas);
                System.out.println("Desconto por faltas: " + proporcional);
                System.out.println("--------------------------------------------------");
                System.out.println("Salário final: " + (usuario.getSalario() - proporcional));
                System.out.println("==================================================");
            } else {
                System.out.println("==================================================");
                System.out.println("Usuario não encontrado.");
                System.out.println("==================================================");
            }

    }

    /**
     * Calcula e exibe o custo total da folha de pagamento agrupado por departamento,
     * aplicando descontos proporcionais ao número de faltas de cada colaborador.
     */
    protected static void FolhaPagamentoTotal() {
        double Somatorio_rh = 0;
        double Somatorio_vendas = 0;
        double Somatorio_estoque = 0;
        double Somatorio_financeiro = 0;
        double Somatorio_gerentes = 0;

        for (Map.Entry<Integer, Usuario> entry : banco_usuarios.entrySet()) {
            Usuario usuario = entry.getValue();

            if (usuario instanceof Funcionario) {
                Funcionario funcionario = (Funcionario) usuario;
                // descontar dias faltados
                int dias_faltas = funcionario.getNumeroFaltas();
                double proporcional = (dias_faltas / 20.0) * funcionario.getSalario();

                switch (funcionario.getDepartamento()) {
                    case "RH":
                        Somatorio_rh += funcionario.getSalario() - proporcional;
                        break;
                    case "Vendas":
                        Somatorio_vendas += funcionario.getSalario() - proporcional;
                        break;
                    case "Estoque":
                        Somatorio_estoque += funcionario.getSalario() - proporcional;
                        break;
                    case "Financeiro":
                        Somatorio_financeiro += funcionario.getSalario() - proporcional;
                        break;
                }
            } else if (usuario instanceof Gerente) {
                Gerente gerente = (Gerente) usuario;
                Somatorio_gerentes += gerente.getSalario();
            }
        }

        System.out.println("========== RELATÓRIO DE FOLHA DE PAGAMENTO ==========");
        System.out.printf("Total RH: R$ %.2f%n", Somatorio_rh);
        System.out.printf("Total Vendas: R$ %.2f%n", Somatorio_vendas);
        System.out.printf("Total Estoque: R$ %.2f%n", Somatorio_estoque);
        System.out.printf("Total Financeiro: R$ %.2f%n", Somatorio_financeiro);
        System.out.printf("Total Gerentes: R$ %.2f%n", Somatorio_gerentes);
        System.out.println("-----------------------------------------------------");

        double totalGeral = Somatorio_rh + Somatorio_vendas + Somatorio_estoque + Somatorio_financeiro + Somatorio_gerentes;
        System.out.printf("VALOR TOTAL DA FOLHA: R$ %.2f%n", totalGeral);
        System.out.println("=====================================================");

    }

    /**
     * Registra uma falta para um funcionário, incrementando seu contador de faltas.
     *
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void registrarFalta(Scanner scanner) {
        System.out.println("Registrando falta para qual funcionário? (Digite o ID do funcionário)");
        int funcionarioId = TesteEntrada.nextInt(scanner);
        scanner.nextLine(); // Limpar o buffer do scanner
        banco_usuarios.get(funcionarioId).setNumeroFaltas(banco_usuarios.get(funcionarioId).getNumeroFaltas() + 1);
        System.out.println("Falta registrada para o funcionário ID: " + funcionarioId + ". Total de faltas: "
                + banco_usuarios.get(funcionarioId).getNumeroFaltas());

    }

    /**
     * Carrega os usuários do arquivo {@code Usuarios.csv} para o banco de usuários.
     * Se o arquivo não existir, o banco permanece vazio.
     */
    private static void LerDoArquivoCSV() {
        try (Scanner scanner = new Scanner(new java.io.File("Usuarios.csv"))) {
            String linha = scanner.nextLine();
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                if (linha.trim().isEmpty()) {
                    continue;
                }

                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String email = dados[2];
                int senha = Integer.parseInt(dados[3]);
                boolean ativo = Boolean.parseBoolean(dados[4]);
                String cargo = dados[5];
                String departamento = dados[6];
                int numeroFaltas = dados.length > 7 ? Integer.parseInt(dados[7]) : 0;

                Usuario usuario;
                if ("Funcionario".equals(cargo)) {
                    usuario = new Funcionario(id, nome, email, senha, departamento, 0.0);
                } else if ("Gerente".equals(cargo)) {
                    usuario = new Gerente(id, nome, email, senha, departamento, 0.0);
                } else {
                    usuario = new Usuario(id, nome, email, senha, 0);
                }

                usuario.setAtivo(ativo);
                usuario.setNumeroFaltas(numeroFaltas);
                banco_usuarios.put(id, usuario);
            }
        } catch (Exception e) {
            System.out.println("Arquivo Usuarios.csv não encontrado ou erro ao ler: " + e.getMessage());
        }
    }

    /**
     * Exibe a lista de todos os funcionários cadastrados no console.
     */
    public static void exibirFuncionarios() {
        System.out.println("Funcionários Cadastrados:");
        for (Map.Entry<Integer, Usuario> entry : banco_usuarios.entrySet()) {
            Usuario usuario = entry.getValue();
            if (usuario instanceof Funcionario) {
                System.out.println("ID: " + usuario.getID() + ", Nome: " + usuario.getNome() + ", Departamento: "
                        + usuario.getDepartamento());
            }
        }
    }

    /**
     * Exibe a lista de todos os gerentes cadastrados no console.
     */
    public static void exibirGerentes() {
        System.out.println("Gerentes Cadastrados:");
        for (Map.Entry<Integer, Usuario> entry : banco_usuarios.entrySet()) {
            Usuario usuario = entry.getValue();
            if (usuario instanceof Gerente) {
                System.out.println("ID: " + usuario.getID() + ", Nome: " + usuario.getNome() + ", Departamento: "
                        + usuario.getDepartamento());
            }
        }
    }

}
