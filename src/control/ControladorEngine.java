package control;

import engines.view.NovaPartidaConfiguracoes;
import engines.view.NovaPartidaEngine;
import enums.Cores;
import java.awt.event.MouseEvent;
import partida.model.EngineUCI;
import partida.view.ButtonCasa;

public class ControladorEngine extends Controlador {

    EngineUCI engine;
    Cores lado;

    public ControladorEngine(NovaPartidaConfiguracoes configuracoes) {
        super();
        engine = new EngineUCI(configuracoes.getExecutavelEngine(), configuracoes.getDificuldade());
        engine.start();

        switch (configuracoes.getLadoInicial()) {
            case NovaPartidaConfiguracoes.BRANCAS:
                this.lado = Cores.BRANCO;
                break;
            case NovaPartidaConfiguracoes.NEGRAS:
                this.lado = Cores.PRETO;
                break;
            case NovaPartidaConfiguracoes.ALEATORIO:
                if (Math.random() > 0.5) {
                    this.lado = Cores.BRANCO;
                } else {
                    this.lado = Cores.PRETO;
                }
                break;
        }

        if (this.lado == Cores.PRETO) {
            engine.receberMovimento(null);
            movimentoEngine();
        }

        panelTabuleiro.setPontoDeVistaPadrao(lado);
    }

    @Override
    protected void adicionarListenerBotoes() {
        movimentoListener = new ControladorEngine.MovimentoEngineListener();

        ButtonCasa[][] buttonsCasas = this.panelTabuleiro.getBotoesCasas();
        for (int y = 0; y < buttonsCasas.length; y++) {
            for (int x = 0; x < buttonsCasas[0].length; x++) {
                buttonsCasas[y][x].addMouseListener(movimentoListener);
                buttonsCasas[y][x].addMouseMotionListener(movimentoListener);
            }
        }
    }

    @Override
    protected void novaPartida() {
        NovaPartidaEngine telaNovaPartida = new NovaPartidaEngine(framePartida, true);
        telaNovaPartida.setVisible(true);

        NovaPartidaConfiguracoes configuracoes = telaNovaPartida.getConfiguracoes();
        if (configuracoes == null) {
            return;
        }

        framePartida.dispose();
        new ControladorEngine(configuracoes);
    }

    class MovimentoEngineListener extends MovimentoListener {
        @Override
        public void clique(Object source) {
            if (lado != tabuleiro.getTurno()) {
                return;
            }

            super.clique(source);
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            super.mouseReleased(me);

            if (sucessoUltimoMovimento) {
                engine.receberMovimento(ultimoMovimento.toString());
                movimentoEngine();

                sucessoUltimoMovimento = false;
            }
        }
    }

    public void movimentoEngine() {
        new Thread() {
            @Override
            public void run() {
                String movimento = engine.enviarMovimento();
                tabuleiro.realizarMovimento(movimento);
            }
        }.start();
    }

}
