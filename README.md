# Locadora de Veículos — Roteiro de Apresentação

> **Projeto:** AS — Programação Orientada a Objetos  
> **Entrega:** 24/11  
> **Arquivos:** `Tarifavel.java`, `Veiculo.java`, `Carro.java`, `Moto.java`, `Main.java`

---

## 🎬 Estrutura do Vídeo

O vídeo deve cobrir os **5 conceitos de POO** exigidos na avaliação:
1. Interface
2. Classe Abstrata
3. Herança
4. Encapsulamento
5. Polimorfismo

Duração sugerida: **5 a 8 minutos**

---

## 📋 Roteiro Completo — O que falar em cada parte

---

### ▶ PARTE 1 — Abertura (≈ 30 segundos)

**Mostre:** tela com os arquivos abertos na IDE

**Fale:**
> "Olá professor! Nosso projeto é uma Locadora de Veículos feita em Java 21.
> O sistema permite consultar preço, alugar e devolver carros e motos.
> Usamos os cinco conceitos de POO: interface, classe abstrata, herança, encapsulamento e polimorfismo.
> Vamos passar por cada arquivo explicando a responsabilidade de cada um."

---

### ▶ PARTE 2 — Interface `Tarifavel` (≈ 45 segundos)

**Mostre:** abra o arquivo `Tarifavel.java`

**Fale:**
> "Começamos pela interface `Tarifavel`. Uma interface é um contrato:
> qualquer classe que a implemente é obrigada a ter o método `calcularValor(int dias)`.
> Ela não guarda dados nem define a fórmula — só declara o que deve existir.
>
> No nosso projeto, `Veiculo` implementa `Tarifavel`.
> Isso significa que `Carro` e `Moto`, por herdarem de `Veiculo`, também respeitam esse contrato
> e podem ser tratados como `Tarifavel` na Main — isso é o que permite o polimorfismo de interface."

---

### ▶ PARTE 3 — Classe Abstrata `Veiculo` (≈ 1 minuto 30 segundos)

**Mostre:** abra o arquivo `Veiculo.java`

**Fale:**
> "`Veiculo` é uma classe abstrata. Ela não pode ser instanciada diretamente — não existe
> um 'veículo genérico', só existe Carro ou Moto. Por isso ela é abstrata.
>
> **Encapsulamento:** os quatro atributos — `codigo`, `modelo`, `valorDiaria` e `disponivel` —
> são todos `private`. Nenhuma classe de fora acessa esses dados diretamente.
> Só é possível lê-los pelos getters: `getCodigo()`, `getModelo()`, `getValorDiaria()` e `isDisponivel()`.
> Não há setters para código, modelo ou disponibilidade — isso protege o estado do objeto.
>
> **Regra do aluguel:** o método `alugar()` verifica se o veículo está disponível.
> Se já estiver alugado, retorna `false` e preserva o estado. Se estiver disponível,
> muda `disponivel` para `false` e retorna `true`. O objeto controla o próprio estado.
>
> **Devolução:** `devolver()` faz o inverso — só devolve se estiver alugado.
>
> **Alterar diária:** `alterarValorDiaria()` aceita apenas valores maiores que zero.
> Se o valor for zero ou negativo, não altera nada e retorna `false`.
>
> Por fim, `calcularValor` é declarado `abstract` — a fórmula de cálculo fica nas subclasses,
> porque cada tipo de veículo tem uma taxa diferente."

---

### ▶ PARTE 4 — Herança: `Carro` e `Moto` (≈ 1 minuto)

**Mostre:** abra `Carro.java` e depois `Moto.java`

**Fale:**
> "Aqui entra a herança. `Carro` e `Moto` estendem `Veiculo` com `extends`.
> Isso significa que elas herdam todos os atributos e métodos de `Veiculo`:
> o construtor chama `super()` para inicializar os dados da classe pai.
>
> Cada subclasse **sobrescreve** `calcularValor` com `@Override` e define sua própria fórmula:
>
> - **Carro:** `dias × diária + R$ 20` de taxa de limpeza. Com 3 dias e diária 100: 3 × 100 + 20 = **320**.
> - **Moto:** `dias × diária + R$ 5` de taxa de limpeza. Com 3 dias e diária 50: 3 × 50 + 5 = **155**.
>
> Ambas retornam **0** quando `dias` é menor ou igual a zero — sem cobrar nem alterar o estado.
>
> Não usamos `instanceof`, `cast` nem `if/switch` para descobrir o tipo — isso seria errado.
> O próprio objeto sabe como calcular o seu valor."

---

### ▶ PARTE 5 — Main e Polimorfismo (≈ 2 minutos)

**Mostre:** abra `Main.java` e execute o programa

**Fale:**
> "A Main demonstra tudo funcionando. Veja como criamos os objetos:
>
> ```java
> Veiculo carro = new Carro(1, 'Onix', 100);
> Veiculo moto  = new Moto(2, 'Biz', 50);
> ```
>
> A variável é do tipo `Veiculo`, mas o objeto real é `Carro` ou `Moto`.
> Quando chamamos `carro.calcularValor(3)`, o Java executa a versão do `Carro` automaticamente.
> Quando chamamos `moto.calcularValor(3)`, executa a versão da `Moto`.
> **Isso é polimorfismo** — a mesma chamada produz resultados diferentes dependendo do objeto.
>
> O método `mostrarOrcamento` recebe um `Tarifavel`:
>
> ```java
> public static void mostrarOrcamento(Tarifavel item, int dias) { ... }
> ```
>
> Ele não sabe se está recebendo um Carro ou uma Moto — só sabe que o objeto sabe calcular.
> Passamos `carro` e `moto` para o mesmo método e cada um calcula corretamente.
>
> Agora veja a execução no terminal..."

**[Execute o programa e mostre a saída no terminal]**

> "Todos os testes mostram PASSOU. Vemos:
> - Dados e estado inicial corretos
> - Orçamentos calculados com as fórmulas certas
> - Dias inválidos retornando zero sem alterar disponibilidade
> - Aluguel e devolução controlando o estado corretamente
> - Alteração de diária respeitando a regra de valor maior que zero"

---

### ▶ PARTE 6 — Encerramento (≈ 30 segundos)

**Fale:**
> "Resumindo os cinco conceitos do projeto:
>
> 1. **Interface** — `Tarifavel` define o contrato de calcular valor
> 2. **Classe Abstrata** — `Veiculo` centraliza os dados e regras comuns, sem ser instanciada
> 3. **Herança** — `Carro` e `Moto` herdam de `Veiculo` e especializam o cálculo
> 4. **Encapsulamento** — atributos privados, acesso só por getters, sem setters desnecessários
> 5. **Polimorfismo** — a mesma referência ou o mesmo método se comporta diferente conforme o objeto real
>
> Obrigado professor!"

---

## ❓ Perguntas Prováveis — Respostas Prontas

| Pergunta | Resposta |
|---|---|
| **Por que `Veiculo` é abstrata?** | Porque não existe veículo genérico no mundo real — só Carro e Moto. Abstrata impede criar `new Veiculo()` diretamente e força as subclasses a implementar `calcularValor`. |
| **Para que serve `Tarifavel` se já temos `Veiculo`?** | `Tarifavel` separa a capacidade de calcular preço da hierarquia de veículos. Futuramente, qualquer coisa que calcule um valor (ex: seguro) poderia ser usada no mesmo método `mostrarOrcamento` sem herdar de `Veiculo`. |
| **O que é polimorfismo nesse projeto?** | A variável `Veiculo carro` aponta para um `Carro`, e ao chamar `calcularValor`, o Java executa a versão correta automaticamente. O método `mostrarOrcamento(Tarifavel item, ...)` funciona com Carro e Moto sem saber qual dos dois é. |
| **Por que não tem setter para `disponivel`?** | O estado disponível/alugado é controlado apenas pelos métodos `alugar()` e `devolver()`, que aplicam as regras. Ter um setter permitiria alterar o estado sem validação — quebraria o encapsulamento. |
| **O que acontece se chamar `calcularValor` com dias negativos?** | Retorna 0 imediatamente, sem cobrar e sem mudar a disponibilidade do veículo. |

---

## 📁 Arquivos do Projeto

```
aspoo/
├── Tarifavel.java   → interface
├── Veiculo.java     → classe abstrata (base)
├── Carro.java       → subclasse concreta
├── Moto.java        → subclasse concreta
└── Main.java        → execução e testes
```

## ▶ Como Compilar e Executar

```bash
javac Tarifavel.java Veiculo.java Carro.java Moto.java Main.java
java Main
```
