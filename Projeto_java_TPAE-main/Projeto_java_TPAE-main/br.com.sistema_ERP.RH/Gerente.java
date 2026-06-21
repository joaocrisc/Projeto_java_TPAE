/**
 * Representa um gerente no sistema ERP.
 * <p>Estende {@link Usuario} com permissões ampliadas que habilitam acesso a todas
 * as funcionalidades do sistema, incluindo cadastro de usuários, relatórios e
 * gerenciamento de todos os departamentos.</p>
 */
public class Gerente extends Usuario {
    private String Permissoes;

    /**
     * Cria um novo gerente com todos os dados necessários.
     *
     * @param id           identificador único
     * @param nome         nome completo
     * @param email        endereço de e-mail
     * @param senha        senha numérica
     * @param departamento departamento gerenciado
     * @param salario      salário mensal em reais
     */
    public Gerente(int id, String nome, String email, int senha, String departamento, double salario) {
        super(id, nome, email, senha, 0);
        setDepartamento(departamento);
        setSalario(salario);
        this.Permissoes = "Vizualizar Relatórios, Cadastrar Funcionários,Cadastrar Estoque, Gerenciar Vendas";
    }

    /**
     * Retorna as permissões concedidas ao gerente.
     *
     * @return string com as permissões separadas por vírgula
     */
    public String getPermissoes() {
        return this.Permissoes;
    }

    /**
     * Define as permissões do gerente.
     *
     * @param permissoes string com as permissões
     */
    public void setPermissoes(String permissoes) {
        this.Permissoes = permissoes;
    }
}
