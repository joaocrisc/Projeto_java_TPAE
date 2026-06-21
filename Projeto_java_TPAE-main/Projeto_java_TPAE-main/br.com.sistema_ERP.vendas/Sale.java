/**
 * Representa uma venda (ou compra do ponto de vista do cliente) realizada no sistema.
 * <p>Associa um produto a uma quantidade, data e CPF do cliente.</p>
 */
public class Sale {
    private int codigoProduto;
    private int quantidadeVendida;
    private String dataVenda;
    private String nomeProduto;
    private String CpfCliente;

    /**
     * Cria uma nova instância de Sale com todos os dados da venda.
     *
     * @param codigoProduto    código do produto vendido
     * @param quantidadeVendida quantidade de unidades vendidas
     * @param dataVenda        data da venda no formato dd/MM/yyyy
     * @param nomeProduto      nome do produto vendido
     * @param CpfCliente       CPF do cliente comprador
     */
    public Sale(int codigoProduto, int quantidadeVendida, String dataVenda, String nomeProduto, String CpfCliente) {
        this.codigoProduto = codigoProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.dataVenda = dataVenda;
        this.nomeProduto = nomeProduto;
        this.CpfCliente = CpfCliente;
    }

    /**
     * Retorna o código do produto vendido.
     *
     * @return código do produto
     */
    public int getCodigoProduto() {
        return codigoProduto;
    }

    /**
     * Retorna a data da venda.
     *
     * @return data no formato dd/MM/yyyy
     */
    public String getDataVenda() {
        return dataVenda;
    }

    /**
     * Retorna o nome do produto vendido.
     *
     * @return nome do produto
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * Retorna a quantidade de unidades vendidas.
     *
     * @return quantidade vendida
     */
    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    /**
     * Retorna o CPF do cliente que realizou a compra.
     *
     * @return CPF do cliente
     */
    public String getCpfCliente() {
        return CpfCliente;
    }

    /**
     * Define o CPF do cliente.
     *
     * @param cpfCliente CPF do cliente
     */
    public void setCpfCliente(String cpfCliente) {
        CpfCliente = cpfCliente;
    }

    /**
     * Define o código do produto.
     *
     * @param codigoProduto código do produto
     */
    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    /**
     * Define a data da venda.
     *
     * @param dataVenda data no formato dd/MM/yyyy
     */
    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    /**
     * Define o nome do produto.
     *
     * @param nomeProduto nome do produto
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     * Define a quantidade vendida.
     *
     * @param quantidadeVendida quantidade de unidades
     */
    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

}
