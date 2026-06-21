import java.util.ArrayList;
import java.util.List;

/**
 * Representa um cliente da empresa.
 * <p>Armazena dados pessoais e o histórico de compras realizadas no sistema.</p>
 */
public class Pessoa {
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private List<Sale> compras;

    /**
     * Cria uma nova instância de Pessoa com os dados informados.
     *
     * @param cpf2     CPF do cliente (com ou sem formatação)
     * @param nome     nome completo
     * @param email    endereço de e-mail
     * @param telefone número de telefone
     * @param endereco endereço completo
     */
    public Pessoa(String cpf2, String nome, String email, String telefone, String endereco) {
        this.cpf = cpf2;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.compras = new ArrayList<>();
    }

    /**
     * Retorna o histórico de compras do cliente.
     *
     * @return lista de {@link Sale} associadas a este cliente
     */
    public List<Sale> getCompras() {
        return compras;
    }

    /**
     * Adiciona uma compra ao histórico do cliente.
     *
     * @param compra venda a ser adicionada
     */
    public void adicionarCompra(Sale compra) {
        compras.add(compra);
    }

    /**
     * Define o CPF do cliente.
     *
     * @param cpf CPF (com ou sem formatação)
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna o CPF do cliente.
     *
     * @return CPF formatado
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome nome completo
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o nome do cliente.
     *
     * @return nome completo
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o e-mail do cliente.
     *
     * @param email endereço de e-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna o e-mail do cliente.
     *
     * @return endereço de e-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o telefone do cliente.
     *
     * @param telefone número de telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna o telefone do cliente.
     *
     * @return número de telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o endereço do cliente.
     *
     * @param endereco endereço completo
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Retorna o endereço do cliente.
     *
     * @return endereço completo
     */
    public String getEndereco() {
        return endereco;
    }

}
