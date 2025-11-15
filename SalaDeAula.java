
public class SalaDeAula extends Sala {

    public SalaDeAula(String nome,int numero, int capacidade, String localizacao) {
        super(nome,numero, capacidade, localizacao);
    }

    @Override
    public String getDescricao() {
        // Você pode customizar esta descrição como quiser
        return "[SALA DE AULA] Sala " + getNumero() + 
               " (" + getBloco() + ") - " +
               getCapacidade() + " lugares";
    }
}