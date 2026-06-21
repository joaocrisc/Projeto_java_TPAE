/**
 * Produto de limpeza do estoque.
 * <p>Além dos atributos herdados de {@link Produto}, possui unidade de medida,
 * ingredientes ativos e volume. A descrição é gerada automaticamente no formato
 * "Nome com volume de X unidade".</p>
 */
public class ProdutoLimpeza extends Produto {
    private String unidadeMedida;
    private String ingredientesAtivos;
    private double volume;

    /**
     * Retorna a unidade de medida do produto (L, ML ou OZ).
     *
     * @return unidade de medida
     */
    public String getunidadeMedida() {
        return unidadeMedida;
    }

    /**
     * Define a unidade de medida do produto.
     *
     * @param unidadeMedida "L", "ML" ou "OZ"
     */
    public void setunidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    /**
     * Retorna os ingredientes ativos do produto.
     *
     * @return lista de ingredientes ativos
     */
    public String getIngredientesAtivos() {
        return ingredientesAtivos;
    }

    /**
     * Define os ingredientes ativos do produto.
     *
     * @param ingredientesAtivos ingredientes ativos
     */
    public void setIngredientesAtivos(String ingredientesAtivos) {
        this.ingredientesAtivos = ingredientesAtivos;
    }

    /**
     * Retorna o volume do produto.
     *
     * @return volume numérico
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Define o volume do produto.
     *
     * @param volume volume numérico (maior que 0)
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * Gera automaticamente a descrição com o nome, volume e unidade de medida.
     *
     * @param descricao parâmetro ignorado; a descrição é sempre gerada automaticamente
     */
    @Override
    public void setDescricao(String descricao) {
        descricao = (this.getNome() + " com volume de " + this.getVolume() + " " + this.getunidadeMedida());
        super.setDescricao(descricao);
    }

    /**
     * Retorna o tipo deste produto.
     *
     * @return 2 (limpeza)
     */
    @Override
    public int tipoProduto() {
        return 2;
    }
}
