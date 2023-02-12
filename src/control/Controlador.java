package control;

import enums.Cores;
import enums.EstadosPartida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import partida.model.Casa;
import partida.model.Partida;
import partida.model.Tabuleiro;
import enums.TiposMovimentoEspecial;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import partida.model.Movimento;
import partida.view.ButtonCasa;
import partida.view.FramePartida;
import partida.view.MenuInicial;
import partida.view.PanelTabuleiro;

public class Controlador {

    protected FramePartida framePartida;
    protected PanelTabuleiro panelTabuleiro;
    protected PanelTabuleiro panelArrastar;

    protected Partida partida;
    protected Tabuleiro tabuleiro;

    protected boolean sucessoUltimoMovimento;
    protected Movimento ultimoMovimento;
    protected ArrayList<Movimento> movimentosPossiveisParaSelecao;

    protected MovimentoListener movimentoListener;

    public Controlador() {
        this.partida = new Partida(2917);
        this.framePartida = construirFrame(partida);

        this.tabuleiro = partida.getTabuleiro();
        this.panelTabuleiro = framePartida.getPanelTabuleiro();

        // Adiciona um ActionListener para cada um dos 64 botões do tabuleiro
        adicionarListenerBotoes();

        // Adiciona um ActionListener para itens da lista de menu do jframe
        framePartida.getItemNovaPartida().addActionListener(new NovaPartidaListener());
        framePartida.getItemVoltarAoMenuPrincipal().addActionListener(new RetornarAoMenuPrincipalListener());

        framePartida.setVisible(true);
    }

    public FramePartida construirFrame(Partida partida) {
        return new FramePartida(partida);
    }

    protected void adicionarListenerBotoes() {
        movimentoListener = new MovimentoListener();

        ButtonCasa[][] buttonsCasas = this.panelTabuleiro.getBotoesCasas();
        for (int y = 0; y < buttonsCasas.length; y++) {
            for (int x = 0; x < buttonsCasas[0].length; x++) {
                buttonsCasas[y][x].addMouseListener(movimentoListener);
                buttonsCasas[y][x].addMouseMotionListener(movimentoListener);
            }
        }
    }

    protected void novaPartida() {
        this.partida = new Partida(1);
        this.tabuleiro = partida.getTabuleiro();

        framePartida.setPartida(partida);
        panelTabuleiro = framePartida.getPanelTabuleiro();

        adicionarListenerBotoes();
    }
    
    private Casa primeiraCasaSelecionada, segundaCasaSelecionada, casaClicada;

    class MovimentoListener implements MouseMotionListener, MouseListener {

        public void clique(Object source) {
            if (partida.getEstado() != EstadosPartida.EM_ANDAMENTO) {
                return;
            }

            ButtonCasa botaoClicado = (ButtonCasa) source;
            casaClicada = botaoClicado.getCasa();

            if (primeiroClique()) {
                return;
            }

            segundoClique();
        }

        private boolean primeiroClique() {

            if (primeiraCasaSelecionada != null || !Movimento.validarPrimeiraSelecao(casaClicada)) {
                return false;
            }

            primeiraCasaSelecionada = casaClicada;
            movimentosPossiveisParaSelecao = casaClicada.getPeca().movimentosValidos(tabuleiro);
            panelTabuleiro.marcarMovimentosPossiveis(primeiraCasaSelecionada);

            segundaCasaSelecionada = null;

            return true;
        }

        private boolean segundoClique() {
            if (primeiraCasaSelecionada == null) {
                return false;
            }

            if (primeiraCasaSelecionada.equals(casaClicada)) {
                return false;
            }

            panelTabuleiro.desmarcarMovimentosPossiveis();

            if (Movimento.validarSegundaSelecao(primeiraCasaSelecionada, casaClicada)) {
                segundaCasaSelecionada = casaClicada;

                Movimento movimentoSelecionado = tabuleiro.getMovimento(primeiraCasaSelecionada, segundaCasaSelecionada);

                if (movimentoSelecionado == null) {
                    primeiraCasaSelecionada = null;
                    segundaCasaSelecionada = null;
                    return false;
                }

                if (movimentoSelecionado.getTipo() == TiposMovimentoEspecial.COROACAO) {
                    String opcao = framePartida.escolherPecaCoroacao();
                    movimentoSelecionado.setArgumentoOpcional(opcao);
                }

                sucessoUltimoMovimento = tabuleiro.realizarMovimento(movimentoSelecionado);

                if (sucessoUltimoMovimento) {
                    sucessoUltimoMovimento = true;
                    ultimoMovimento = movimentoSelecionado;
                }
            }

            primeiraCasaSelecionada = null;
            segundaCasaSelecionada = null;

            return true;
        }

        @Override
        public void mousePressed(MouseEvent me) {
            clique(me.getSource());

            ButtonCasa button = (ButtonCasa) me.getSource();
            framePartida.atualizarIconeArrastavel(button);
        }

        @Override
        public void mouseClicked(MouseEvent me) {

        }

        @Override
        public void mouseReleased(MouseEvent me) {
            int xTela = (int) (me.getLocationOnScreen().getX() - panelTabuleiro.getLocationOnScreen().getX());
            int yTela = (int) (me.getLocationOnScreen().getY() - panelTabuleiro.getLocationOnScreen().getY());

            if (panelTabuleiro.getPontoDeVistaPadrao() == Cores.PRETO || (panelTabuleiro.getGirar() && partida.getTurno() == Cores.PRETO)) {
                xTela = Math.abs(panelTabuleiro.getWidth() - xTela);
                yTela = Math.abs(panelTabuleiro.getHeight() - yTela);
            }

            int xPanel = panelTabuleiro.getWidth() / 8;
            int yPanel = panelTabuleiro.getHeight() / 8;

            int xCasa = xTela / xPanel;
            int yCasa = yTela / yPanel;

            framePartida.esconderArrastavel();

            ButtonCasa botaoCasa = (ButtonCasa) me.getSource();
            botaoCasa.mostrarIcone();

            if (xCasa > 7 || xCasa < 0 || yCasa > 7 || yCasa < 0) {
                return;
            }

            ButtonCasa convertido = panelTabuleiro.getBotoesCasas()[yCasa][xCasa];

            if (!convertido.getCasa().equals(primeiraCasaSelecionada)) {
                segundaCasaSelecionada = convertido.getCasa();
                clique(convertido);
            }

        }

        @Override
        public void mouseEntered(MouseEvent me) {
            ButtonCasa botao = (ButtonCasa) me.getSource();
            botao.aumentar();
        }

        @Override
        public void mouseExited(MouseEvent me) {
            ButtonCasa botao = (ButtonCasa) me.getSource();
            botao.diminuir();
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            framePartida.mostrarArrastavel();
            ButtonCasa botao = (ButtonCasa) me.getSource();
            botao.esconderIcone();

            framePartida.atualizarPosicaoArrastavel(me.getLocationOnScreen());
        }

        @Override
        public void mouseMoved(MouseEvent me) {

        }

    }

    class NovaPartidaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (framePartida.confirmarCriacaoNovaPartida()) {
                novaPartida();
            }
        }
    }

    class RetornarAoMenuPrincipalListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (framePartida.confirmarVoltarAoMenuPrincipal()) {
                new MenuInicial().setVisible(true);
                framePartida.dispose();
            }
        }
    }

    public Partida getPartida() {
        return partida;
    }

}
