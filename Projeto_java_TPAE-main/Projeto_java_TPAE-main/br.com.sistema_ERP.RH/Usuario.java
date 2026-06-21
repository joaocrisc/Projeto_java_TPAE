
/**
 * Representa um usuário do sistema ERP.
 * <p>É a classe base da hierarquia de recursos humanos. Pode ser especializada em
 * {@link Funcionario} ou {@link Gerente}.</p>
 */
public class Usuario {

    private int id;
    private String nome;
    private String email;
    private int senha;
    private boolean ativo;
    private String departamento;
    private double salario;
    private int numeroFaltas;

    /**
     * Cria um novo usuário com os dados básicos.
     *
     * @param id           identificador único
     * @param nome         nome completo
     * @param email        endereço de e-mail
     * @param senha        senha numérica
     * @param numeroFaltas número inicial de faltas
     */
    protected Usuario(int id, String nome, String email, int senha, int numeroFaltas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = true;
        this.numeroFaltas = numeroFaltas;
    }

    /**
     * Retorna o identificador do usuário.
     *
     * @return id do usuário
     */
    public int getID() {
        return this.id;
    }

    /**
     * Retorna o número de faltas registradas.
     *
     * @return número de faltas
     */
    public int getNumeroFaltas() {
        return this.numeroFaltas;
    }

    /**
     * Retorna o nome do usuário.
     *
     * @return nome completo
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna o e-mail do usuário.
     *
     * @return endereço de e-mail
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Retorna a senha numérica do usuário.
     *
     * @return senha
     */
    public int getSenha() {
        return this.senha;
    }

    /**
     * Retorna se o usuário está ativo no sistema.
     *
     * @return {@code true} se ativo
     */
    public boolean getAtivo() {
        return this.ativo;
    }

    /**
     * Retorna o departamento do usuário.
     *
     * @return nome do departamento, ou {@code null} se não vinculado
     */
    public String getDepartamento() {
        return this.departamento;
    }

    /**
     * Retorna o salário do usuário.
     *
     * @return salário em reais
     */
    public double getSalario() {
        return this.salario;
    }

    /**
     * Define o identificador do usuário.
     *
     * @param id novo identificador
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Define o nome do usuário.
     *
     * @param nome nome completo
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o e-mail do usuário.
     *
     * @param email endereço de e-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Define a senha do usuário.
     *
     * @param senha senha numérica
     */
    public void setSenha(int senha) {
        this.senha = senha;
    }

    /**
     * Define o número de faltas do usuário.
     *
     * @param numeroFaltas quantidade de faltas
     */
    public void setNumeroFaltas(int numeroFaltas) {
        this.numeroFaltas = numeroFaltas;
    }

    /**
     * Ativa ou desativa o usuário no sistema.
     *
     * @param ativo {@code true} para ativar
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * Define o departamento do usuário.
     *
     * @param departamento nome do departamento
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * Define o salário do usuário.
     *
     * @param salario salário em reais
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Verifica as credenciais do usuário e exibe o resultado no console.
     *
     * @param email e-mail informado
     * @param senha senha informada
     */
    protected void login(String email, int senha) {
        if (this.email.equals(email) && this.senha == senha) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }

}
