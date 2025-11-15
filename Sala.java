import java.util.Objects;

public abstract class Sala {
    private String nome;
    private int numero;
    private int capacidade;
    private String bloco;

    public Sala(String nome, int numero, int capacidade, String bloco) {
        this.nome = nome;
        this.numero = numero;
        this.capacidade = capacidade;
        this.bloco = bloco;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public String getBloco() {
        return bloco;
    }

    public abstract String getDescricao();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Sala sala = (Sala) obj;

        return numero == sala.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}
