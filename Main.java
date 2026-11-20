/**
 * =============================================================================
 * CLASSE PRINCIPAL MAIN
 * =============================================================================
 * 
 * Esta classe contem o ponto de entrada (metodo main) do sistema da Locadora de Veiculos.
 * Ela executa uma bateria de 40 verificacoes automatizadas cobrindo todos os cenarios
 * descritos no enunciado do projeto:
 * 
 * 1. Dados e estado inicial (construtores, getters e disponibilidade inicial).
 * 2. Orcamentos e Polimorfismo (calculo especifico de Carro e Moto, contrato Tarifavel).
 * 3. Dias invalidos (garantia de que 0 e valores negativos retornam 0 sem alterar estado).
 * 4. Alugar e devolver (maquina de estados e protecao contra alugueis/devolucoes duplicadas).
 * 5. Alteracao da diaria (regras de validacao de valores > 0).
 */
public class Main {

    /**
     * Metodo principal (ponto de partida da aplicacao Java).
     */
    public static void main(String[] args) {

        // =====================================================================
        // POLIMORFISMO NA DECLARACAO:
        // Declaramos as variaveis com o tipo da classe base 'Veiculo',
        // mas instanciamos os objetos reais como 'Carro' e 'Moto'.
        // =====================================================================
        Veiculo carro = new Carro(1, "Onix", 100);
        Veiculo moto = new Moto(2, "Biz", 50);

        // =====================================================================
        // BLOCO 1: DADOS E ESTADO INICIAL
        // =====================================================================
        System.out.println("=== 1. DADOS E ESTADO INICIAL ===");
        
        // Exibe no console os dados iniciais formatados de cada veiculo
        exibir(carro);
        exibir(moto);

        // Valida se os getters retornam exatamente os dados passados no construtor
        conferir("Dados do carro", carro.getCodigo() == 1
                && carro.getModelo().equals("Onix")
                && carro.getValorDiaria() == 100);
                
        conferir("Dados da moto", moto.getCodigo() == 2
                && moto.getModelo().equals("Biz")
                && moto.getValorDiaria() == 50);

        // Regra: Todo veiculo deve nascer disponivel (true)
        conferir("Carro disponivel", carro.isDisponivel());
        conferir("Moto disponivel", moto.isDisponivel());

        // =====================================================================
        // BLOCO 2: ORCAMENTOS E POLIMORFISMO
        // =====================================================================
        System.out.println("\n=== 2. ORCAMENTOS E POLIMORFISMO ===");

        // Teste de calculo de 3 dias para Carro: 3 * 100 + 20 = 320
        conferir("Carro: 3 dias = 320", carro.calcularValor(3) == 320);

        // Teste de calculo de 3 dias para Moto: 3 * 50 + 5 = 155
        conferir("Moto: 3 dias = 155", moto.calcularValor(3) == 155);

        // Chama o metodo 'mostrarOrcamento', que aceita qualquer 'Tarifavel'
        // Demonstracao de polimorfismo: o metodo nao sabe se e Carro ou Moto,
        // mas a JVM executa a implementacao correta correspondente a cada objeto.
        mostrarOrcamento(carro, 3); // Esperado no console: 320.00
        mostrarOrcamento(moto, 3);  // Esperado no console: 155.00

        // Atribuicao a variaveis do tipo da Interface Tarifavel (Polimorfismo de Interface)
        Tarifavel tarifaCarro = carro;
        Tarifavel tarifaMoto = moto;
        
        conferir("Contrato Tarifavel: carro", tarifaCarro.calcularValor(3) == 320);
        conferir("Contrato Tarifavel: moto", tarifaMoto.calcularValor(3) == 155);

        // Regra: Consultar orcamento NUNCA deve alterar a disponibilidade do veiculo
        conferir("Orcamentos preservam estado inicial",
                carro.isDisponivel() && moto.isDisponivel());

        // =====================================================================
        // BLOCO 3: DIAS INVALIDOS (0 OU NEGATIVOS)
        // =====================================================================
        System.out.println("\n=== 3. DIAS INVALIDOS ===");

        // Para Carro: 0 e -1 dias devem retornar 0, sem cobrar diaria nem taxa
        conferir("Carro: 0 dia", carro.calcularValor(0) == 0);
        conferir("Carro: -1 dia", carro.calcularValor(-1) == 0);

        // Para Moto: 0 e -1 dias devem retornar 0, sem cobrar diaria nem taxa
        conferir("Moto: 0 dia", moto.calcularValor(0) == 0);
        conferir("Moto: -1 dia", moto.calcularValor(-1) == 0);

        // Consulta com dias invalidos tambem nao pode afetar a disponibilidade
        conferir("Dias invalidos preservam disponibilidade",
                carro.isDisponivel() && moto.isDisponivel());

        // =====================================================================
        // BLOCO 4: ALUGAR E DEVOLVER (MAQUINA DE ESTADOS)
        // =====================================================================
        System.out.println("\n=== 4. ALUGAR E DEVOLVER ===");

        // Testa o fluxo completo de locacao e devolucao para o carro e para a moto
        testarLocacao(carro);
        testarLocacao(moto);

        // =====================================================================
        // BLOCO 5: ALTERACAO DA DIARIA (VALIDACAO DE ENCAPSULAMENTO)
        // =====================================================================
        System.out.println("\n=== 5. ALTERACAO DA DIARIA ===");

        // Tentativa 1: Alterar para 0 -> deve falhar (retornar false) e manter 100
        conferir("Diaria zero: false", !carro.alterarValorDiaria(0));
        conferir("Diaria continua 100", carro.getValorDiaria() == 100);

        // Tentativa 2: Alterar para valor negativo (-10) -> deve falhar e manter 100
        conferir("Diaria negativa: false", !carro.alterarValorDiaria(-10));
        conferir("Diaria ainda e 100", carro.getValorDiaria() == 100);

        // Tentativa 3: Alterar para valor valido (120) -> deve suceder (true) e atualizar
        conferir("Diaria 120: true", carro.alterarValorDiaria(120));
        conferir("Diaria atualizada", carro.getValorDiaria() == 120);

        // Novo orcamento com diaria 120: 3 * 120 + 20 = 380
        conferir("Novo orcamento: 380", carro.calcularValor(3) == 380);

        // Garantir que a consulta de orcamento preservou a disponibilidade
        conferir("Consulta preserva disponibilidade", carro.isDisponivel());
        
        // Exibe o estado final do carro apos as alteracoes
        exibir(carro);
    }

    // =========================================================================
    // METODOS AUXILIARES
    // =========================================================================

    /**
     * DEMONSTRACAO DE POLIMORFISMO DE INTERFACE:
     * 
     * Este metodo recebe qualquer objeto que implemente a interface 'Tarifavel'.
     * Ele nao sabe e nao precisa saber se o item e um Carro, uma Moto ou qualquer outro tipo.
     * Ao chamar 'item.calcularValor(dias)', o Java invoca a formula exata daquele objeto.
     * 
     * @param item Objeto que assina o contrato Tarifavel
     * @param dias Quantidade de dias da locacao
     */
    public static void mostrarOrcamento(Tarifavel item, int dias) {
        System.out.printf("Orcamento (%d dias): R$ %.2f%n",
                dias, item.calcularValor(dias));
    }

    /**
     * TESTE COMPLETO DE ALUGUEL E DEVOLUCAO:
     * 
     * Recebe uma referencia generica do tipo 'Veiculo' (Polimorfismo de Heranca).
     * Testa todo o ciclo de vida do veiculo:
     * 1. Primeiro aluguel: deve ter sucesso (true) e deixar o veiculo alugado.
     * 2. Segundo aluguel seguido: deve falhar (false), pois o veiculo ja esta alugado.
     * 3. Orcamento enquanto alugado: deve calcular sem alterar a condicao de alugado.
     * 4. Primeira devolucao: deve ter sucesso (true) e deixar o veiculo disponivel.
     * 5. Segunda devolucao seguida: deve falhar (false), pois o veiculo ja esta disponivel.
     * 
     * @param veiculo Objeto Veiculo a ser testado
     */
    public static void testarLocacao(Veiculo veiculo) {
        System.out.println("-- " + veiculo.getModelo() + " --");
        exibir(veiculo);

        // 1. Primeiro aluguel (deve funcionar)
        conferir("Primeiro aluguel: true", veiculo.alugar());
        conferir("Ficou alugado", !veiculo.isDisponivel());
        exibir(veiculo);

        // 2. Segundo aluguel imediato (deve falhar, pois ja esta alugado)
        conferir("Segundo aluguel: false", !veiculo.alugar());
        conferir("Continua alugado", !veiculo.isDisponivel());
        exibir(veiculo);

        // Consultar preco tambem nao pode devolver um veiculo alugado
        mostrarOrcamento(veiculo, 3);
        conferir("Orcamento preserva estado alugado", !veiculo.isDisponivel());

        // 3. Primeira devolucao (deve funcionar)
        conferir("Primeira devolucao: true", veiculo.devolver());
        conferir("Ficou disponivel", veiculo.isDisponivel());
        exibir(veiculo);

        // 4. Segunda devolucao imediata (deve falhar, pois ja foi devolvido)
        conferir("Segunda devolucao: false", !veiculo.devolver());
        conferir("Continua disponivel", veiculo.isDisponivel());
        exibir(veiculo);
    }

    /**
     * Exibe os atributos atuais de um veiculo de forma formatada.
     * Utiliza os getters para demonstrar o respeito ao encapsulamento.
     * 
     * @param veiculo O veiculo a ser exibido
     */
    public static void exibir(Veiculo veiculo) {
        System.out.printf("%d | %s | diaria: %.2f | disponivel: %b%n",
                veiculo.getCodigo(), veiculo.getModelo(),
                veiculo.getValorDiaria(), veiculo.isDisponivel());
    }

    /**
     * Metodo simples de assercao/teste.
     * Imprime "PASSOU: <descricao>" se o teste for verdadeiro,
     * ou "PENDENTE: <descricao>" caso contrario.
     * 
     * @param descricao Texto explicativo do teste
     * @param resultado Valor booleano indicando sucesso (true) ou falha (false)
     */
    public static void conferir(String descricao, boolean resultado) {
        if (resultado) {
            System.out.println("PASSOU: " + descricao);
        } else {
            System.out.println("PENDENTE: " + descricao);
        }
    }
}
