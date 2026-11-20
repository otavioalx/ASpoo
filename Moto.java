/**
 * =============================================================================
 * CLASSE CONCRETA MOTO
 * =============================================================================
 * 
 * CONCEITOS DE POO:
 * 
 * 1. HERANCA (extends Veiculo):
 *    - Moto e uma subclasse de Veiculo (relacao "e-um": Moto E UM Veiculo).
 *    - Reaproveita toda a estrutura comum definida na classe pai.
 * 
 * 2. POLIMORFISMO E SOBRESCRITA (@Override):
 *    - Moto sobrescreve 'calcularValor(int dias)' com sua regra propria de tarifacao:
 *      dias * diaria + taxa de limpeza de R$ 5.
 *    - O programa pode tratar uma Moto como Veiculo ou como Tarifavel,
 *      e em tempo de execucao a formula correta da Moto sera executada dinamicamente.
 */
public class Moto extends Veiculo {

    // =========================================================================
    // CONSTRUTOR
    // =========================================================================

    /**
     * Construtor da classe Moto.
     * 
     * @param codigo      Codigo identificador da moto
     * @param modelo      Nome do modelo (ex: "Biz")
     * @param valorDiaria Preco da diaria
     */
    public Moto(int codigo, String modelo, double valorDiaria) {
        // Invoca o construtor da superclasse Veiculo para inicializar os atributos herdados
        super(codigo, modelo, valorDiaria);
    }

    // =========================================================================
    // SOBRESCRITA DO CALCULO DE TARIFA (POLIMORFISMO - TODO 3)
    // =========================================================================

    /**
     * Calcula o orcamento de locacao especifico para Moto.
     * 
     * Regras de negocio (TODO 3 do enunciado):
     * 1. Dias <= 0: retorna 0 (dias invalidos nao cobram diarias nem taxa).
     * 2. Dias > 0: formula = (dias * valorDiaria) + taxa fixa de limpeza de R$ 5,00.
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

        // Formula: dias * diaria + R$ 5 de taxa de limpeza cobrada uma unica vez
        return dias * getValorDiaria() + 5;
    }
}
