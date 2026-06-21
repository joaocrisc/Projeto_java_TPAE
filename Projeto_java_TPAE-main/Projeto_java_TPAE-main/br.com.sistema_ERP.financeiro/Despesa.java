/**
 * Representa uma despesa financeira da empresa.
 * <p>Uma despesa pode ser classificada como "operacional" (custos fixos como
 * aluguel, energia) ou "compras" (reposição de estoque e aquisições).</p>
 */
public class Despesa {

    private double valor;
    private String descricao;
    private String data;
    private String categoria;
    private int codigo;

    /**
     * Retorna a categoria da despesa.
     *
     * @return "operacional" ou "compras"
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Retorna o código interno da despesa.
     *
     * @return código da despesa
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Retorna a data da despesa.
     *
     * @return data no formato dd/MM/yyyy
     */
    public String getData() {
        return data;
    }

    /**
     * Retorna a descrição da despesa.
     *
     * @return texto descritivo da despesa
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o valor monetário da despesa.
     *
     * @return valor em reais
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define a categoria da despesa.
     *
     * @param categoria "operacional" ou "compras"
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Define o código interno da despesa.
     *
     * @param codigo código identificador
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Define a data da despesa.
     *
     * @param data data no formato dd/MM/yyyy
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Define a descrição da despesa.
     *
     * @param descricao texto descritivo
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Define o valor monetário da despesa.
     *
     * @param valor valor em reais
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
}
