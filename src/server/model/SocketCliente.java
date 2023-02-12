package server.model;

import enums.Cores;
import java.net.*;
import java.io.*;
import partida.model.Casa;
import partida.model.Movimento;
import partida.model.Tabuleiro;
import partida.view.FramePartida;
import partida.view.FramePartidaMultiplayer;
import util.AuxiliarMovimentos;

public class SocketCliente {

    private Socket socket;
    private ObjectOutputStream saidaDeDados;
    private ObjectInputStream entradaDeDados;

    private Cores lado;

    final static String IP_OFICIAL = "localhost";
    final static int PORTA = 2917;

    public void iniciarConexao(String ip, int porta) {
        try {
            socket = new Socket(ip, porta);

            saidaDeDados = new ObjectOutputStream(socket.getOutputStream());
            entradaDeDados = new ObjectInputStream(socket.getInputStream());

            String comprimento = (String) entradaDeDados.readObject();
            System.out.println(comprimento);

            lado = ((String) entradaDeDados.readObject()).equals("BRANCO") ? Cores.BRANCO : Cores.PRETO;

            System.out.println(lado);
        } catch (Exception ex) {
            System.out.println("Erro ao iniciar conexão com o servidor.");
        }
    }

    public void terminarConexao() {
        try {
            entradaDeDados.close();
            saidaDeDados.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Erro ao terminar conexão.");
        }
    }

    public void enviarMovimento(Movimento movimento) {
        Casa primeiraCasa = movimento.getCasaInicial();
        Casa segundaCasa = movimento.getCasaFinal();
        String opcional = movimento.getArgumentoOpcional();

        byte byteIdentificador = TipoMensagem.JOGADA.valorByte;
        int y1 = primeiraCasa.getCoordenada().getY();
        int x1 = primeiraCasa.getCoordenada().getX();
        int y2 = segundaCasa.getCoordenada().getY();
        int x2 = segundaCasa.getCoordenada().getX();

        try {
            saidaDeDados.write(byteIdentificador);
            saidaDeDados.writeInt(y1);
            saidaDeDados.writeInt(x1);
            saidaDeDados.writeInt(y2);
            saidaDeDados.writeInt(x2);
            saidaDeDados.writeObject(opcional);
            saidaDeDados.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void receberMovimento(Tabuleiro tabuleiro) throws IOException, ClassNotFoundException {
        int y1 = entradaDeDados.readInt();
        int x1 = entradaDeDados.readInt();
        int y2 = entradaDeDados.readInt();
        int x2 = entradaDeDados.readInt();
        String opcional = (String) entradaDeDados.readObject();

        Casa primeiraCasa = AuxiliarMovimentos.converterCasa(tabuleiro.getCasas(), y1, x1);
        Casa segundaCasa = AuxiliarMovimentos.converterCasa(tabuleiro.getCasas(), y2, x2);

        Movimento movimento = tabuleiro.getMovimento(primeiraCasa, segundaCasa);
        movimento.setArgumentoOpcional(opcional);

        System.out.println(movimento.toString());

        tabuleiro.realizarMovimento(movimento);
    }

    public void enviarMensagem(String mensagem) {
        byte byteIdentificador = TipoMensagem.MENSAGEM.valorByte;
        
        try {
            saidaDeDados.write(byteIdentificador);
            saidaDeDados.writeObject((Object) mensagem);
            saidaDeDados.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void receberMensagem(FramePartidaMultiplayer frame) throws IOException, ClassNotFoundException {
        String mensagem = (String) entradaDeDados.readObject();
        frame.mostrarMensagemOutroJogador(mensagem);
    }

    public void receberDado(Tabuleiro tabuleiro, FramePartidaMultiplayer frame) {
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        byte byteIdentificador = entradaDeDados.readByte();
                        switch (TipoMensagem.valorDaMensagem(byteIdentificador)) {
                            case JOGADA:
                                receberMovimento(tabuleiro);
                                break;
                            case MENSAGEM:
                                receberMensagem(frame);
                                break;
                            case SAIR:
                                break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    public Cores getLado() {
        return this.lado;
    }

}
