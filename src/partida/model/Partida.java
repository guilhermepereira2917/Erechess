package partida.model;

import enums.Cores;
import enums.EstadosPartida;
import java.util.Observable;

public class Partida extends Observable {

    private int id;
    private EstadosPartida estado = EstadosPartida.INICIANDO;

    private Tabuleiro tabuleiro;
    private HistoricoMovimentos historico;
    private Cores turno = Cores.BRANCO;

    private boolean jogarComRelogio = false;
    private Relogio relogioBrancas, relogioNegras;
    private final int segundos = 300, acrescimo = 5;

    public Partida(int id) {
        this.id = id;

        tabuleiro = new Tabuleiro(this);
        setEstado(EstadosPartida.EM_ANDAMENTO);

        historico = new HistoricoMovimentos(tabuleiro);

        if (jogarComRelogio) {
            relogioBrancas = new Relogio(this, Cores.BRANCO, segundos, acrescimo);
            relogioNegras = new Relogio(this, Cores.PRETO, segundos, acrescimo);

            addObserver(relogioBrancas);
            addObserver(relogioNegras);

            relogioBrancas.iniciar();
        }

    }

    public Cores getTurno() {
        return turno;
    }

    public void setTurno(Cores turno) {
        this.turno = turno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        setChanged();
        notifyObservers();
    }

    public EstadosPartida getEstado() {
        return estado;
    }

    public void setEstado(EstadosPartida estado) {
        this.estado = estado;

        if (jogarComRelogio && (estado == EstadosPartida.BRANCAS_VENCERAM || estado == EstadosPartida.NEGRAS_VENCERAM)) {
            relogioBrancas.parar();
            relogioNegras.parar();
        }

        setChanged();
        notifyObservers();
    }

    public void trocarTurno() {
        turno = turno == Cores.BRANCO ? Cores.PRETO : Cores.BRANCO;

        setChanged();
        notifyObservers();
    }

    public HistoricoMovimentos getHistorico() {
        return historico;
    }

    public Relogio getRelogioBrancas() {
        return relogioBrancas;
    }

    public Relogio getRelogioNegras() {
        return relogioNegras;
    }

    public boolean isJogarComRelogio() {
        return jogarComRelogio;
    }
    
}
