
import java.util.Scanner;

/**
 * Camada de interface do sistema ERP.
 * <p>Responsável por exibir menus, ler opções do usuário e delegar as ações
 * aos módulos correspondentes ({@link RH}, {@link Estoque}, {@link Financeiro},
 * {@link Vendas}, {@link Cliente}).</p>
 * <p>O menu exibido varia conforme o cargo do usuário logado.</p>
 */
public class Interface {

    /**
     * Exibe o menu principal adequado ao cargo do usuário e processa a opção escolhida.
     *
     * @param scanner instância de {@link Scanner} para leitura da entrada
     * @param id      identificador do usuário logado
     */
    public static void telaInicial(Scanner scanner, int id) {
        if (id > -1) {
            int cargo = RH.departamento(id);
            if (cargo == 1) {
                Interface.ExibirMenuGerencia();
                int seta = TesteEntrada.nextInt(scanner);
                if (seta > 0) {
                    scanner.nextLine(); // Limpar o buffer do scanner
                    Interface.ExibirSubmenuGerencia(id, seta, scanner);
                }
            } else if (cargo == 2) {
                Interface.ExibirMenuVendas();
                int seta = TesteEntrada.nextInt(scanner);
                if (seta > 0) {
                    scanner.nextLine(); // Limpar o buffer do scanner
                    Interface.ExibirSubmenuVendas(id, seta, scanner);
                }
            } else if (cargo == 3) {
                Interface.ExibirMenuEstoque();
                int seta = TesteEntrada.nextInt(scanner);
                if (seta == 1) {
                    scanner.nextLine(); // Limpar o buffer do scanner
                    Interface.ExibirSubmenuEstoque(id, seta, scanner);
                }

            } else if (cargo == 4) {
                Interface.ExibirMenuRH();
                int seta = TesteEntrada.nextInt(scanner);
                if (seta == 1) {
                    scanner.nextLine(); // Limpar o buffer do scanner
                    Interface.ExibirSubmenuRH(id, seta, scanner);
                }
            } else if (cargo == 5) {
                Interface.ExibirMenuFinanceiro();
                int seta = TesteEntrada.nextInt(scanner);
                if (seta == 1) {
                    scanner.nextLine(); // Limpar o buffer do scanner
                    Interface.ExibirSubmenuFinanceiro(id, seta, scanner);
                }
            }

            else {
                System.out.println("Cargo desconhecido. Acesso limitado.");
            }

        } else {
            System.out.println("Usuario não encontrado ou senha incorreta. Acesso negado.");
        }
    }

    /**
     * Exibe o menu principal do gerente com todas as opções disponíveis.
     */
    public static void ExibirMenuGerencia() {
        System.out.println("Bem-vindo ao Sistema ERP!");
        System.out.println("===========================");
        System.out.println("1. Cadastrar");
        System.out.println("2. Buscar");
        System.out.println("3. Exibir Todos");
        System.out.println("4. Gerenciar Vendas");
        System.out.println("5. Gerenciar RH");
        System.out.println("6. Gerenciar Estoque");
        System.out.println("7. Gerenciar financeiro");
        System.out.println("0. Sair");
        System.out.println("===========================");
    }

    /**
     * Exibe o menu principal do departamento de vendas.
     */
    public static void ExibirMenuVendas() {
        System.out.println("Bem-vindo ao Sistema ERP!");
        System.out.println("===========================");
        System.out.println("1. Registrar Venda");
        System.out.println("2. Exibir Vendas");
        System.out.println("3. Exibir Vendas por Data");
        System.out.println("4. Cadastrar Cliente");
        System.out.println("5. Exibir Clientes");
        System.out.println("6. Remover Cliente");
        System.out.println("7. Listar compras");
        System.out.println("8. Alterar dados do cliente");
        System.out.println("9. Exibir Cliente");
        System.out.println("0. Sair");
        System.out.println("===========================");
    }

    /**
     * Exibe o menu principal do departamento de RH.
     */
    public static void ExibirMenuRH() {
        System.out.println("Bem-vindo ao Sistema ERP!");
        System.out.println("===========================");
        System.out.println("1. Gerenciar RH");
        System.out.println("0. Sair");
        System.out.println("===========================");
    }

    /**
     * Exibe o menu principal do departamento de estoque.
     */
    public static void ExibirMenuEstoque() {
        System.out.println("Bem-vindo ao Sistema ERP!");
        System.out.println("===========================");
        System.out.println("1. Gerenciar Estoque");
        System.out.println("0. Sair");
        System.out.println("===========================");
    }

    /**
     * Exibe o menu principal do departamento financeiro.
     */
    public static void ExibirMenuFinanceiro() {
        System.out.println("Bem-vindo ao Sistema ERP!");
        System.out.println("===========================");
        System.out.println("1. Gerenciar Financeiro");
        System.out.println("0. Sair");
        System.out.println("===========================");
    }

    /**
     * Processa a opção selecionada no menu de gerência e exibe o submenu correspondente.
     *
     * @param id     identificador do gerente logado
     * @param setar  opção escolhida no menu principal
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void ExibirSubmenuGerencia(int id, int setar, Scanner scanner) {
        switch (setar) {
            case 1:
                System.out.println("===========================");
                System.out.println("1. Cadastrar Usuario");
                System.out.println("2. Promover Funcionario a Gerente");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.println("===========================");
                break;
            case 2:
                System.out.println("===========================");
                System.out.println("1. Buscar usuário por ID");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.println("===========================");
                break;
            case 3:
                System.out.println("===========================");
                System.out.println("1. Exibir Todos os Funcionários");
                System.out.println("2. Exibir Todos os Gerentes");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.println("===========================");
                break;
            case 4:
                Interface.ExibirMenuVendas();
                break;
            case 5:
                System.out.println("===========================");
                System.out.println("1. Gerenciar RH");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.println("===========================");
                break;
            case 6:
                System.out.println("===========================");
                System.out.println("1. Gerenciar Estoque");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.println("===========================");
                break;
            case 7:
                ExibirSubmenuFinanceiro(id, setar, scanner);
                break;
            default:
                System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
        }

        if (setar == 0) {
            System.out.println("Encerrando o sistema...");
        } else if (setar == 1) {
            setar = TesteEntrada.nextInt(scanner);
            Interface.exibirSubmenuCadastrar(id, setar, scanner);

        } else if (setar == 2) {
            setar = TesteEntrada.nextInt(scanner);
            Interface.exibirSubmenuBuscar(id, setar, scanner);
        } else if (setar == 3) {
            setar = TesteEntrada.nextInt(scanner);
            Interface.ExibirSubmenuExibir(id, setar, scanner);

        } else if (setar == 4) {
            setar = TesteEntrada.nextInt(scanner);
            Interface.ExibirSubmenuVendas(id, setar, scanner);
        } else if (setar == 5) {
            setar = TesteEntrada.nextInt(scanner);
            if (setar == 1)
                Interface.ExibirSubmenuRH(id, setar, scanner);
            else if (setar != 1 && setar != 0)
                System.out.println("Comando não reconhecido.");
        } else if (setar == 6) {
            Interface.ExibirSubmenuEstoque(id, setar, scanner);
        }
    }

    /**
     * Delega a ação de cadastro ao módulo RH conforme a opção escolhida.
     *
     * @param id     identificador do gerente logado
     * @param seta   opção escolhida no submenu de cadastro
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void exibirSubmenuCadastrar(int id, int seta, Scanner scanner) {

        RH.submenuCadastrar(id, seta, scanner);

    }

    /**
     * Delega a ação de busca ao módulo RH conforme a opção escolhida.
     *
     * @param id     identificador do gerente logado
     * @param seta   opção escolhida no submenu de busca
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void exibirSubmenuBuscar(int id, int seta, Scanner scanner) {

        RH.submenuBuscar(id, seta, scanner);

        if (seta == 0) {
            System.out.println("voltando ao menu principal...");
        }

    }

    /**
     * Processa a opção selecionada no menu de vendas.
     *
     * @param id     identificador do usuário logado
     * @param seta   opção escolhida no menu de vendas
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void ExibirSubmenuVendas(int id, int seta, Scanner scanner) {
        switch (seta) {
            case 1:
                Vendas.registrarVenda(scanner);
                break;
            case 2:
                Cliente.listarTodasAsCompras();
                break;
            case 3:
                System.out.println("Digite a data para filtrar as vendas (dd/mm/yyyy):");
                scanner.nextLine();
                String data = scanner.nextLine();
                Vendas.exibirVendasPorData(data);
                break;
            case 4:
                Cliente.cadastrarCliente(scanner);
                break;
            case 5:
                Cliente.exibirClientes();
                break;
            case 6:
                Cliente.removerCliente(scanner);
                break;
            case 7:
                System.out.println("===========================");
                System.out.println("1. Listar compras de um Cliente");
                System.out.println("2. Listar todas as compras");
                System.out.println("0. Voltar ao menu principal");
                System.out.println("===========================");
                break;
            case 8:
                Cliente.alterarDados(scanner);
                break;
            case 9:
                Cliente.exibirCliente(scanner);
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
        }
        if (seta == 0) {
            System.out.println("Encerrando o sistema...");
        } else if (seta == 7) {
            seta = scanner.nextInt();
            if (seta == 1) {
                Cliente.listarCompras(scanner);
            }
        }
    }

    /**
     * Processa a opção selecionada no menu de RH.
     *
     * @param id     identificador do usuário logado
     * @param seta   opção escolhida no menu de RH
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void ExibirSubmenuRH(int id, int seta, Scanner scanner) {
        switch (seta) {
            case 1:
                System.out.println("===========================");
                System.out.println("1. Registrar Ponto");
                System.out.println("2. Exibir Funcionários");
                System.out.println("3. Exibir Gerentes");
                System.out.println("4. Cadastrar Usuario");
                System.out.println("5. Registrar falta ");
                System.out.println("6. Folha de pagamento");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.println("===========================");
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
        }

        if (seta != 0) {
            seta = TesteEntrada.nextInt(scanner);
        }

        if (seta == 0) {
            System.out.println("Encerrando o sistema...");
        } else if (seta == 1) {
            System.out.println("Registrando ponto para qual funcionário? (Digite o ID do funcionário)");
            int funcionarioId = TesteEntrada.nextInt(scanner);
            scanner.nextLine(); // Limpar o buffer do scanner
            RH.registrarPonto(funcionarioId);
        } else if (seta == 2) {
            RH.exibirFuncionarios();
        } else if (seta == 3) {
            RH.exibirGerentes();
        } else if (seta == 4) {
            RH.cadastrarUsuario(scanner);
        } else if (seta == 5) {
            RH.registrarFalta(scanner);
        } else if (seta == 6) {
            RH.FolhaPagamento(scanner);
        }
    }

    /**
     * Exibe o submenu financeiro e processa a opção escolhida pelo usuário.
     *
     * @param id     identificador do usuário logado
     * @param seta   opção que acionou este submenu (não utilizada na lógica interna)
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void ExibirSubmenuFinanceiro(int id, int seta, Scanner scanner) {
        System.out.println("===========================");
        System.out.println("1. Cadastrar despesa");
        System.out.println("2. Exibir despesas");
        System.out.println("3. Exibir custos por departamento");
        System.out.println("4. Apagar uma despesa");
        System.out.println("5. Apagar todas as despesas");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.println("===========================");
        seta = TesteEntrada.nextInt(scanner);
        switch (seta) {
            case 1:
                Financeiro.registrarDespesa(scanner);
                break;
            case 2:
                Financeiro.exibirDespesas();
                break;
            case 3:
                RH.FolhaPagamentoTotal();
                break;
            case 4:
                Financeiro.apagarDespesa(scanner);
                break;
            case 5:
                Financeiro.apagarTodasDespesas(scanner);
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
        }
        
        if (seta == 0) {
            System.out.println("Encerrando o sistema...");

        }
    }
    /**
     * Exibe o submenu de estoque e processa a opção escolhida pelo usuário.
     *
     * @param id     identificador do usuário logado
     * @param seta   opção que acionou este submenu (não utilizada na lógica interna)
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void ExibirSubmenuEstoque(int id, int seta, Scanner scanner) {
        System.out.println("===========================");
        System.out.println("1. Visualizar Estoque Ativo");
        System.out.println("2. Visualizar Estoque Inativo");
        System.out.println("3. Adicionar Produto");
        System.out.println("4. Ativar Produto");
        System.out.println("5. Desativar Produto");
        System.out.println("6. Remover Produto");
        System.out.println("7. Comprar Produto");
        System.out.println("8. Mudar preço do Produto");
        System.out.println("9. Mudar custo do Produto");
        System.out.println("10. Buscar Produto por Código");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.println("===========================");
        seta = TesteEntrada.nextInt(scanner);
        scanner.nextLine();
        switch (seta) {
            case 0:
                System.out.println("Encerrando o sistema...");
                break;
            case 1:
                Estoque.exibirProdutosAtivos();
                break;
            case 2:
                Estoque.exibirProdutosInativos();
                break;
            case 3:
                Estoque.registrarProduto(scanner);
                break;
            case 4:
                Estoque.ativarProduto(scanner);
                break;
            case 5:
                Estoque.desativarProduto(scanner);
                break;
            case 6:
                Estoque.removerProduto(scanner);
                break;
            case 7:
                Estoque.comprarProduto(scanner);
                break;
            case 8:
                Estoque.mudarPreco(scanner);
                break;
            case 9:
                Estoque.mudarCusto(scanner);
                break;
            case 10:
                Estoque.buscarProduto(scanner);
                break;
            default:
                System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
        }
    }

    /**
     * Exibe funcionários ou gerentes conforme a opção escolhida no submenu "Exibir Todos".
     *
     * @param id     identificador do gerente logado
     * @param seta   1 para funcionários, 2 para gerentes
     * @param scanner instância de {@link Scanner} para leitura da entrada
     */
    public static void ExibirSubmenuExibir(int id, int seta, Scanner scanner) {
        if (seta == 0) {
            System.out.println("Encerrando o sistema...");
        } else if (seta == 1) {
            RH.exibirFuncionarios();
        } else if (seta == 2) {
            RH.exibirGerentes();
        }
    }

}

// A ideia é diminuir os códigos na MAIN, e deixar a MAIN apenas para chamar as
// funções, e a Interface para exibir os menus e receber as entradas do usuário,
// e as Funcoes_basicas para realizar as operações de cadastro, busca, etc.
