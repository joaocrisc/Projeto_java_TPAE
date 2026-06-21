/**
 * Produto alimentício do estoque.
 * <p>Além dos atributos herdados de {@link Produto}, possui data de validade.
 * A descrição é gerada automaticamente no formato "Nome que vence em dd/MM/yyyy".</p>
 */
public class ProdutoAlimenticio extends Produto {
    private String dataValidade;

    /**
     * Retorna a data de validade do produto.
     *
     * @return data de validade no formato dd/MM/yyyy
     */
    public String getDataValidade() {
        return dataValidade;
    }

    /**
     * Define a data de validade do produto.
     *
     * @param dataValidade data de validade no formato dd/MM/yyyy
     */
    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    /**
     * Gera automaticamente a descrição com o nome e a data de validade do produto.
     *
     * @param descricao parâmetro ignorado; a descrição é sempre gerada automaticamente
     */
    @Override
    public void setDescricao(String descricao) {
        descricao = (this.getNome() + " que vence em " + this.getDataValidade());
        super.setDescricao(descricao);
    }

    /**
     * Retorna o tipo deste produto.
     *
     * @return 1 (alimentício)
     */
    @Override
    public int tipoProduto() {
        return 1;
    }
}
