
/**
 * Representa um funcionário da empresa no sistema ERP.
 * <p>Estende {@link Usuario} adicionando cargo, salário e a capacidade de
 * registrar ponto. Um funcionário pertence a um departamento específico
 * (Vendas, Estoque, RH ou Financeiro).</p>
 */
public class Funcionario extends Usuario {
    private String cargo = "Funcionário";
    private double salario;

    /**
     * Cria um novo funcionário com todos os dados necessários.
     *
     * @param id           identificador único
     * @param nome         nome completo
     * @param email        endereço de e-mail
     * @param senha        senha numérica
     * @param departamento departamento ao qual pertence
     * @param salario      salário mensal em reais
     */
    Funcionario(int id, String nome, String email, int senha, String departamento, double salario) {
        super(id, nome, email, senha, 0);
        this.salario = salario;
        setDepartamento(departamento);
    }

    /**
     * Retorna o cargo do funcionário.
     *
     * @return cargo (padrão: "Funcionário")
     */
    public String getCargo() {
        return this.cargo;
    }

    /**
     * Retorna o salário do funcionário.
     *
     * @return salário em reais
     */
    public double getSalario() {
        return this.salario;
    }

    /**
     * Define o salário do funcionário.
     *
     * @param salario salário em reais
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Define o cargo do funcionário.
     *
     * @param cargo título do cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Registra o ponto do funcionário exibindo uma confirmação no console.
     */
    public void registrarPonto() {
        System.out.println("Ponto registrado para " + getNome());
    }
}
