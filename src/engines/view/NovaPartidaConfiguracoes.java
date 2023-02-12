package engines.view;

public class NovaPartidaConfiguracoes {

    private String executavelEngine;
    private int dificuldade;
    private int ladoInicial;

    public final static int 
            ALEATORIO = 0,
            BRANCAS = 1,
            NEGRAS = 2;

    public NovaPartidaConfiguracoes(String executavelEngine, int dificuldade, int ladoInicial) {
        this.executavelEngine = executavelEngine;
        this.dificuldade = dificuldade;
        this.ladoInicial = ladoInicial;
    }

    public String getExecutavelEngine() {
        return executavelEngine;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public int getLadoInicial() {
        return ladoInicial;
    }

}
