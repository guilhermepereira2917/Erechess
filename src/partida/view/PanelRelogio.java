package partida.view;

import enums.Cores;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import partida.model.Relogio;
import partida.model.Tabuleiro;

public class PanelRelogio extends JPanel implements Observer {

    private Relogio relogio;
    private JLabel labelTempo;

    private static final Font FONTE = new Font("Segoe UI", Font.BOLD, 24);

    public PanelRelogio(Relogio relogio, Tabuleiro tabuleiro) {
        this.relogio = relogio;
        
        relogio.addObserver(this);
        tabuleiro.addObserver(this);
        
        labelTempo = new JLabel(formataTempo());
        labelTempo.setFont(FONTE);

        if (relogio.getLado() == Cores.BRANCO) {
            setBackground(Color.white);
            labelTempo.setForeground(Color.black);
        } else {
            setBackground(Color.black);
            labelTempo.setForeground(Color.white);
        }

        add(labelTempo, BorderLayout.LINE_END);
        setSize(200, 60);
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o instanceof Relogio) {
            labelTempo.setText(formataTempo());
        } else if (o instanceof Tabuleiro) {
            System.out.println("a");
        }
        
    }

    public String formataTempo() {
        String segundos = String.valueOf(relogio.getSegundos() % 60);
        if (segundos.length() == 1) {
            segundos += "0";
        }
        
        return
            String.format("%d:%s", relogio.getSegundos() / 60, segundos);
    }

    public void trocarLado() {
        
    }
    
}
