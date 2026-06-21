/**
 * Representa um faturamento gerado por uma venda realizada pela empresa.
 */
public class Faturamento {
    private int codigo;
    private double valor;
    private String descricao;

    /**
     * Retorna o código interno do faturamento.
     *
     * @return código do faturamento
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Define o código interno do faturamento.
     *
     * @param codigo código identificador
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Retorna a descrição do faturamento.
     *
     * @return texto descritivo (produto vendido e data)
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do faturamento.
     *
     * @param descricao texto descritivo
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o valor do faturamento.
     *
     * @return valor total em reais
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor do faturamento.
     *
     * @param valor valor total em reais
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

}
