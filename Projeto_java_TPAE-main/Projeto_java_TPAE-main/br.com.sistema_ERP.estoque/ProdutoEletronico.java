/**
 * Produto eletrônico do estoque.
 * <p>Além dos atributos herdados de {@link Produto}, possui marca, modelo,
 * voltagem e prazo de garantia em meses. A descrição é gerada automaticamente
 * no formato "Nome modelo X da marca Y".</p>
 */
public class ProdutoEletronico extends Produto {
    private String marca;
    private String modelo;
    private int voltagem;
    private int garantiaMeses;

    /**
     * Retorna a marca do produto.
     *
     * @return nome da marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define a marca do produto.
     *
     * @param marca nome da marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Retorna o modelo do produto.
     *
     * @return código/nome do modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo do produto.
     *
     * @param modelo código/nome do modelo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Retorna a voltagem do produto.
     *
     * @return 127 ou 220
     */
    public int getVoltagem() {
        return voltagem;
    }

    /**
     * Define a voltagem do produto.
     *
     * @param voltagem 127 ou 220
     */
    public void setVoltagem(int voltagem) {
        this.voltagem = voltagem;
    }

    /**
     * Retorna o prazo de garantia em meses.
     *
     * @return número de meses de garantia
     */
    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    /**
     * Define o prazo de garantia em meses.
     *
     * @param garantiaMeses número de meses (0 ou maior)
     */
    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    /**
     * Gera automaticamente a descrição com nome, modelo e marca.
     *
     * @param descricao parâmetro ignorado; a descrição é sempre gerada automaticamente
     */
    @Override
    public void setDescricao(String descricao) {
        descricao = (this.getNome() + " modelo " + this.getModelo() + " da marca " + this.getMarca());
        super.setDescricao(descricao);
    }

    /**
     * Retorna o tipo deste produto.
     *
     * @return 3 (eletrônico)
     */
    @Override
    public int tipoProduto() {
        return 3;
    }
}
