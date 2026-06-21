/**
 * Classe abstrata base para todos os produtos do estoque.
 * <p>Define os atributos e comportamentos comuns a qualquer produto:
 * nome, descrição, preço de venda, custo, quantidade em estoque e status ativo/inativo.</p>
 *
 * @see ProdutoAlimenticio
 * @see ProdutoLimpeza
 * @see ProdutoEletronico
 */
public abstract class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double precoVenda;
    private double custo;
    private int estoque;
    private boolean ativo;

    /**
     * Retorna o tipo numérico do produto.
     * Subclasses devem sobrescrever: 1=Alimentício, 2=Limpeza, 3=Eletrônico.
     *
     * @return código do tipo de produto (0 na classe base)
     */
    public int tipoProduto() {
        return 0;
    }

    /**
     * Retorna o identificador interno do produto.
     *
     * @return id do produto
     */
    public int getid() {
        return id;
    }

    /**
     * Define o identificador interno do produto.
     *
     * @param id identificador
     */
    public void setid(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a descrição do produto.
     *
     * @return descrição
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do produto.
     * Subclasses podem sobrescrever para gerar descrições automáticas.
     *
     * @param descricao texto descritivo
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o preço de venda do produto.
     *
     * @return preço de venda em reais
     */
    public double getPrecoVenda() {
        return precoVenda;
    }

    /**
     * Define o preço de venda do produto.
     *
     * @param precoVenda preço de venda em reais
     */
    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    /**
     * Retorna o custo de aquisição do produto.
     *
     * @return custo em reais
     */
    public double getCusto() {
        return custo;
    }

    /**
     * Define o custo de aquisição do produto.
     *
     * @param custo custo em reais
     */
    public void setCusto(double custo) {
        this.custo = custo;
    }

    /**
     * Retorna a quantidade disponível em estoque.
     *
     * @return quantidade de unidades
     */
    public int getQuantidade() {
        return estoque;
    }

    /**
     * Define a quantidade disponível em estoque.
     *
     * @param estoque quantidade de unidades
     */
    public void setQuantidade(int estoque) {
        this.estoque = estoque;
    }

    /**
     * Informa se o produto está ativo no sistema.
     *
     * @return {@code true} se ativo, {@code false} se inativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * Define o status ativo/inativo do produto.
     *
     * @param ativo {@code true} para ativar, {@code false} para desativar
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * Exibe no console se o produto está gerando lucro, prejuízo ou equilíbrio.
     */
    public void getLucro() {
        double lucro = this.precoVenda - this.custo;
        if (lucro < 0) {
            System.out.println("Você está perdendo " + (lucro * -1) + " reais");
        } else if (lucro > 0) {
            System.out.println("Você está lucrando " + lucro + " reais");
        } else {
            System.out.println("Você não está tendo lucro algum.");
        }
    }
}
