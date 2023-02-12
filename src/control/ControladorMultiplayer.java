package control;

import enums.Cores;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import partida.model.Partida;
import partida.view.ButtonCasa;
import partida.view.FramePartida;
import partida.view.FramePartidaMultiplayer;
import server.model.SocketCliente;

public class ControladorMultiplayer extends Controlador {

    Cores lado;
    SocketCliente socketCliente = new SocketCliente();

    public ControladorMultiplayer(String ip, int porta) {
        super();
        socketCliente.iniciarConexao(ip, porta);
        ((FramePartidaMultiplayer) (framePartida)).getFieldMensagem().addKeyListener(new MensagemListener());

        this.lado = socketCliente.getLado();
        panelTabuleiro.setPontoDeVistaPadrao(lado);

        socketCliente.receberDado(tabuleiro, (FramePartidaMultiplayer) framePartida);
    }

    @Override
    public FramePartida construirFrame(Partida partida) {
        return new FramePartidaMultiplayer(partida);
    }

    @Override
    protected void novaPartida() {
        framePartida.aviso("Para criar uma nova partida multiplayer, é preciso voltar ao menu inicial");
    }
    
    @Override
    protected void adicionarListenerBotoes() {
        movimentoListener = new MovimentoMultiplayerListener();

        ButtonCasa[][] buttonsCasas = this.panelTabuleiro.getBotoesCasas();
        for (int y = 0; y < buttonsCasas.length; y++) {
            for (int x = 0; x < buttonsCasas[0].length; x++) {
                buttonsCasas[y][x].addMouseListener(movimentoListener);
                buttonsCasas[y][x].addMouseMotionListener(movimentoListener);
            }
        }
    }

    class MovimentoMultiplayerListener extends MovimentoListener {

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
                socketCliente.enviarMovimento(ultimoMovimento);
                sucessoUltimoMovimento = false;
            }

        }
    }

    class MensagemListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) {
            if (ke.getKeyChar()== KeyEvent.VK_ENTER) {
                FramePartidaMultiplayer frameMensagem = (FramePartidaMultiplayer) framePartida;
                
                String mensagem = frameMensagem.getMensagem();
                
                socketCliente.enviarMensagem(mensagem);
                
                frameMensagem.limparMensagem();
                frameMensagem.mostrarPropriaMensagem(mensagem);
            }
        }

        @Override
        public void keyPressed(KeyEvent ke) {

        }

        @Override
        public void keyReleased(KeyEvent ke) {

        }

    }
}
