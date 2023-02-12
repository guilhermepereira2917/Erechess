package partida.model;

import enums.Cores;
import enums.EstadosPartida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;

public class Relogio extends Observable implements Observer {

    private Timer timer;
    private Partida partida;
    private Cores lado;

    private int segundos;
    private int acrescimo;

    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            segundos--;
            
            setChanged();
            notifyObservers();
            
            if (getSegundos() <= 0) {
                if (lado == Cores.BRANCO) {
                    partida.setEstado(EstadosPartida.BRANCAS_VENCERAM);
                } else {
                    partida.setEstado(EstadosPartida.NEGRAS_VENCERAM);
                }
                
                timer.stop();
            }
            
        }
    };

    public Relogio(Partida partida, Cores lado, int segundos, int acrescimo) {
        this.partida = partida;
        this.lado = lado;
        this.segundos = segundos;
        this.acrescimo = acrescimo;

        partida.addObserver(this);
        
        this.timer = new Timer(1000, listener);
    }

    @Override
    public void update(Observable o, Object o1) {
        if (partida.getTurno() == lado) {
            timer.start();
        } else {
            timer.stop();
        }
    }

    public void iniciar() {
        timer.start();
    }

    public void parar() {
        timer.stop();
    }
    
    public int getSegundos() {
        return segundos;
    }

    public Cores getLado() {
        return lado;
    }

    
    
}
