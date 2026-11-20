/**
 * =============================================================================
 * CLASSE CONCRETA CARRO
 * =============================================================================
 * 
 * CONCEITOS DE POO:
 * 
 * 1. HERANCA (extends Veiculo):
 *    - Carro e uma subclasse de Veiculo (relacao "e-um": Carro E UM Veiculo).
 *    - Herda todos os atributos (codigo, modelo, valorDiaria, disponivel) e metodos
 *      (alugar, devolver, alterarValorDiaria, getters) implementados em Veiculo.
 * 
 * 2. POLIMORFISMO E SOBRESCRITA (@Override):
 *    - Carro implementa o metodo abstrato 'calcularValor(int dias)' definido em Veiculo.
 *    - Aplica a formula especifica de cobranca de Carro: dias * diaria + taxa de limpeza de R$ 20.
 */
public class Carro extends Veiculo {

    // =========================================================================
    // CONSTRUTOR
    // =========================================================================

    /**
     * Construtor da classe Carro.
     * 
     * @param codigo      Codigo identificador do carro
     * @param modelo      Nome do modelo (ex: "Onix")
     * @param valorDiaria Preco da diaria
     */
    public Carro(int codigo, String modelo, double valorDiaria) {
        // 'super(...)' invoca o construtor da superclasse (Veiculo).
        // Isso reaproveita a logica de atribuicao e respeita o encapsulamento,
        // ja que os atributos privados residem na classe pai.
        super(codigo, modelo, valorDiaria);
    }

    // =========================================================================
    // SOBRESCRITA DO CALCULO DE TARIFA (POLIMORFISMO)
    // =========================================================================

    /**
     * Calcula o orcamento de locacao especifico para Carro.
     * 
     * Regras de negocio:
     * 1. Dias <= 0: retorna 0 (dias invalidos nao cobram diarias nem taxa).
     * 2. Dias > 0: formula = (dias * valorDiaria) + taxa fixa de limpeza de R$ 20,00.
     * 
     * Note o uso de 'getValorDiaria()': como 'valorDiaria' e privado em Veiculo,
     * a subclasse respeita o encapsulamento acessando-o por meio do getter publico.
     * 
     * @param dias Quantidade de dias da locacao
     * @return Valor total calculado em reais
     */
    @Override
    public double calcularValor(int dias) {
        // Regra para dias invalidos: se dias for 0 ou negativo, retorna zero
        if (dias <= 0) {
            return 0;
        }

        // Formula: dias * diaria + R$ 20 de taxa de limpeza cobrada uma unica vez
        return dias * getValorDiaria() + 20;
    }
}
