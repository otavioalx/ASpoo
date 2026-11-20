/**
 * =============================================================================
 * INTERFACE TARIFAVEL
 * =============================================================================
 * 
 * CONCEITO DE POO: INTERFACE
 * 
 * O que e uma Interface?
 * - Uma interface e um contrato formal de programacao.
 * - Ela define O QUE uma classe deve ser capaz de fazer, mas NAO define COMO.
 * - Ela nao possui atributos de instancia (dados do veiculo) nem codigo executavel
 *   para seus metodos abstratos.
 * 
 * Por que usamos Tarifavel neste projeto?
 * - Para separar o conceito de "calcular preco" da hierarquia de "Veiculos".
 * - Qualquer elemento que possa ter um preco calculado por dias (por exemplo,
 *   no futuro: um seguro, um servico de guincho, um equipamento) pode implementar
 *   Tarifavel sem precisar herdar de Veiculo.
 * - Na Main, o metodo 'mostrarOrcamento(Tarifavel item, int dias)' recebe
 *   qualquer objeto que cumpra este contrato, demonstrando o POLIMORFISMO DE INTERFACE.
 */
public interface Tarifavel {

    /**
     * Metodo abstrato do contrato:
     * 
     * - 'double': tipo do retorno (o valor monetario calculado).
     * - 'calcularValor': nome da operacao exigida.
     * - 'int dias': quantidade de dias para o calculo da tarifa.
     * - ';': por ser uma assinatura de metodo em interface, nao possui corpo {}.
     * 
     * Toda classe concreta que implementar Tarifavel (diretamente ou via heranca)
     * e OBRIGADA pelo compilador a fornecer uma implementacao com essa exata assinatura.
     */
    double calcularValor(int dias);
}
