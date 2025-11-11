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

}
