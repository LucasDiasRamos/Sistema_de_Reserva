public class Laboratorio extends Sala {
    private int qtdComputadores;
    private String software;

    public Laboratorio(String nome, int numero, int capacidade, String bloco, int qtdComputadores ,String software ){
        super(nome,numero,capacidade,bloco);
        this.qtdComputadores = qtdComputadores;
        this.software = software;
    }

    public int getQtdComputadores() {
        return qtdComputadores;
    }

    public String getSoftware() {
        return software;
    }

    @Override
    public String getDescricao() {
        return "Laboratorio" + getNome() + "(" + getBloco()+")"+ " com " + qtdComputadores + " computadores." +
                " Software instalado: " + software + "." ;
    }
}