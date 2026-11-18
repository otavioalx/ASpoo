public class Carro extends Veiculo {
    public Carro(int codigo, String modelo, double valorDiaria) {
        super(codigo, modelo, valorDiaria);
    }

    @Override
    public double calcularValor(int dias) {
        if (dias <= 0) {
            return 0;
        }
        return dias * getValorDiaria() + 20;
    }
}
