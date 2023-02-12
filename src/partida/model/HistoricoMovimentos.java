package partida.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class HistoricoMovimentos extends Observable implements Observer {

    private Tabuleiro tabuleiro;
    private ArrayList<Movimento> movimentos = new ArrayList();
    
    public HistoricoMovimentos(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        tabuleiro.addObserver(this);
    }

    @Override
    public void update(Observable o, Object o1) {
        Movimento ultimoMovimento = tabuleiro.getUltimoMovimento();
        movimentos.add(ultimoMovimento);
        
        setChanged();
        notifyObservers();
    }

    public ArrayList<Movimento> getMovimentos() {
        return movimentos;
    }
    
    public int tamanho() {
        return movimentos.size();
    }

    public String ultimoMovimento() {
        return movimentos.get(movimentos.size() - 1).toString();
    }
    
}
