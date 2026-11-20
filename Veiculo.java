/**
 * =============================================================================
 * CLASSE ABSTRATA VEICULO
 * =============================================================================
 * 
 * CONCEITOS DE POO PRESENTES NESTA CLASSE:
 * 
 * 1. CLASSE ABSTRATA (public abstract class):
 *    - Serve como superclasse (classe base) para Carro e Moto.
 *    - Nao pode ser instanciada diretamente com "new Veiculo()", pois no mundo real
 *      nao alugamos um "veiculo generico", mas sim um modelo concreto (um Carro ou uma Moto).
 *    - Centraliza os atributos e comportamentos comuns a todos os veiculos.
 * 
 * 2. IMPLEMENTACAO DE INTERFACE (implements Tarifavel):
 *    - Veiculo assume o contrato da interface Tarifavel.
 *    - Como Veiculo e uma classe abstrata, ela nao precisa calcular o valor de imediato;
 *      ela apenas declara o metodo como abstrato para que as subclasses (Carro e Moto)
 *      implementem a formula especifica de cada uma.
 * 
 * 3. ENCAPSULAMENTO:
 *    - Todos os atributos sao definidos com modificador de acesso 'private'.
 *    - Nenhuma classe externa tem acesso direto aos dados internos do objeto.
 *    - A leitura dos dados e feita exclusivamente por metodos seletores (getters).
 *    - A alteracao de estado e restrita a metodos de negocio (alugar, devolver, alterarValorDiaria),
 *      garantindo consistencia e evitando estados invalidos.
 */
public abstract class Veiculo implements Tarifavel {

    // =========================================================================
    // ATRIBUTOS PRIVADOS (ENCAPSULAMENTO)
    // =========================================================================

    // Identificador numerico unico do veiculo (ex: 1, 2)
    private int codigo;

    // Nome descritivo do modelo do veiculo (ex: "Onix", "Biz")
    private String modelo;

    // Preco base cobrado por cada dia de locacao (ex: 100.00, 50.00)
    private double valorDiaria;

    // Estado da disponibilidade: true = disponivel para locacao; false = alugado
    private boolean disponivel;

    // =========================================================================
    // CONSTRUTOR
    // =========================================================================

    /**
     * Construtor da classe base Veiculo.
     * E invocado pelas subclasses por meio da instrucao super(...).
     * 
     * @param codigo      Codigo unico do veiculo
     * @param modelo      Nome do modelo
     * @param valorDiaria Valor da diaria em reais
     */
    public Veiculo(int codigo, String modelo, double valorDiaria) {
        // 'this.codigo' refere-se ao atributo privado da classe;
        // 'codigo' refere-se ao parametro recebido neste construtor.
        this.codigo = codigo;
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;

        // Regra do enunciado: Todo veiculo comeca obrigatoriamente disponivel (true).
        this.disponivel = true;
    }

    // =========================================================================
    // METODOS GETTERS (ENCAPSULAMENTO - LEITURA CONTROLADA)
    // =========================================================================
    // Observacao importante: NAO existem metodos setters para 'codigo', 'modelo'
    // ou 'disponivel'. Isso impede que dados cruciais sejam alterados sem controle.

    // Retorna o codigo identificador do veiculo
    public int getCodigo() { 
        return codigo; 
    }

    // Retorna o nome/modelo do veiculo
    public String getModelo() { 
        return modelo; 
    }

    // Retorna o valor atual cobrado por diaria
    public double getValorDiaria() { 
        return valorDiaria; 
    }

    // Retorna se o veiculo esta disponivel (true) ou alugado (false)
    public boolean isDisponivel() { 
        return disponivel; 
    }

    // =========================================================================
    // METODOS DE REGRA DE NEGOCIO (CONTROLE DE ESTADO DO OBJETO)
    // =========================================================================

    /**
     * Realiza a operacao de aluguel do veiculo.
     * 
     * Regra de negocio (Maquina de Estados):
     * 1. Verifica se o veiculo NAO esta disponivel (!disponivel == true, ou seja, ja esta alugado).
     *    - Se ja estiver alugado: retorna false e mantem o estado como alugado.
     * 2. Se estiver disponivel:
     *    - Altera o atributo 'disponivel' para false (agora esta alugado).
     *    - Retorna true confirmando o sucesso da operacao.
     * 
     * @return true se conseguiu alugar; false se ja estava alugado.
     */
    public boolean alugar() {
        if (!disponivel) {
            return false; // Nao pode alugar um veiculo que ja se encontra alugado
        }
        disponivel = false; // Transicao de estado: DISPONIVEL -> ALUGADO
        return true;        // Operacao realizada com sucesso
    }

    /**
     * Realiza a operacao de devolucao do veiculo.
     * 
     * Regra de negocio (TODO 1 do enunciado):
     * 1. Devolver apenas se estiver alugado (ou seja, disponivel == false).
     * 2. Se o veiculo ja estiver disponivel (disponivel == true):
     *    - Retorna false e preserva o estado (nao ha como devolver o que nao foi alugado).
     * 3. Se estiver alugado:
     *    - Altera 'disponivel' para true.
     *    - Retorna true confirmando a devolucao.
     * 
     * @return true se conseguiu devolver; false se o veiculo ja estava disponivel.
     */
    public boolean devolver() {
        if (disponivel) {
            return false; // Nao pode devolver um veiculo que ja esta disponivel
        }
        disponivel = true;  // Transicao de estado: ALUGADO -> DISPONIVEL
        return true;        // Devolucao concluida com sucesso
    }

    /**
     * Altera o valor da diaria do veiculo com validacao previa.
     * 
     * Regra de negocio (TODO 2 do enunciado):
     * 1. Aceita somente valores estritamente maiores que zero (novoValor > 0).
     * 2. Se novoValor <= 0 (zero ou negativo):
     *    - Mantem inalterado o valor anterior e retorna false.
     * 3. Se for valido (> 0):
     *    - Atualiza 'valorDiaria' com o novo valor e retorna true.
     * 
     * @param novoValor O novo valor pretendido para a diaria
     * @return true se o valor foi atualizado; false se o valor for invalido (<= 0)
     */
    public boolean alterarValorDiaria(double novoValor) {
        if (novoValor <= 0) {
            return false; // Rejeita valores zero ou negativos
        }
        valorDiaria = novoValor; // Atualiza a diaria com o valor valido
        return true;             // Confirmacao de alteracao
    }

    // =========================================================================
    // METODO ABSTRATO (POLIMORFISMO)
    // =========================================================================

    /**
     * Metodo abstrato herdado do contrato Tarifavel.
     * 
     * - '@Override': indica sobrescrita formal do metodo da interface Tarifavel.
     * - 'abstract': informa que a classe Veiculo nao implementa o calculo aqui.
     *   A definicao da taxa e da formula exata cabe a cada subclasse concreta (Carro e Moto).
     * 
     * @param dias Quantidade de dias da locacao
     * @return Valor financeiro total calculado
     */
    @Override
    public abstract double calcularValor(int dias);
}
