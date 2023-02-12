package server.model;

import enums.Cores;
import enums.EstadosPartida;
import java.io.IOException;
import partida.model.Casa;
import partida.model.Movimento;
import partida.model.Partida;
import partida.model.Tabuleiro;
import util.AuxiliarMovimentos;

public class ThreadPartida {

    Partida partida;
    Tabuleiro tabuleiro;
    ThreadJogador jogadorBrancas, jogadorNegras;

    public ThreadPartida(ThreadJogador primeiroJogador, ThreadJogador segundoJogador) {
        jogadorBrancas = primeiroJogador;
        jogadorNegras = segundoJogador;

        jogadorBrancas.setCorDoLado(Cores.BRANCO);
        jogadorBrancas.enviarMensagem("BRANCO");

        jogadorNegras.setCorDoLado(Cores.PRETO);
        jogadorNegras.enviarMensagem("PRETO");

        partida = new Partida(0);
        tabuleiro = partida.getTabuleiro();
    }

    public void iniciar() {
        new Ponte(jogadorBrancas, jogadorNegras).start();
        new Ponte(jogadorNegras, jogadorBrancas).start();
    }

    public class Ponte extends Thread {

        ThreadJogador primeiroJogador, segundoJogador;
        Byte byteIdentificador;
        
        public Ponte(ThreadJogador primeiroJogador, ThreadJogador segundoJogador) {
            this.primeiroJogador = primeiroJogador;
            this.segundoJogador = segundoJogador;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    byteIdentificador = primeiroJogador.entradaDeDados.readByte();
                    segundoJogador.saidaDeDados.writeByte(byteIdentificador);
                    
                    switch (TipoMensagem.valorDaMensagem(byteIdentificador)) {
                        case JOGADA:
                            receberJogada();
                            break;
                        case MENSAGEM:
                            receberMensagem();
                            break;
                    }
                    
                    segundoJogador.saidaDeDados.flush();
                } catch (Exception ex) {
                    System.out.println("Erro ao receber movimento do jogador.");
                    ex.printStackTrace();
                    break;
                }
            }
        }

        public void receberJogada() throws IOException, ClassNotFoundException {
            int y1 = primeiroJogador.entradaDeDados.readInt();
            int x1 = primeiroJogador.entradaDeDados.readInt();
            int y2 = primeiroJogador.entradaDeDados.readInt();
            int x2 = primeiroJogador.entradaDeDados.readInt();
            String opcional = (String) primeiroJogador.entradaDeDados.readObject();

            Casa primeiraCasa = AuxiliarMovimentos.converterCasa(tabuleiro.getCasas(), y1, x1);
            Casa segundaCasa = AuxiliarMovimentos.converterCasa(tabuleiro.getCasas(), y2, x2);

            Movimento movimento = tabuleiro.getMovimento(primeiraCasa, segundaCasa);
            movimento.setArgumentoOpcional(opcional);

            tabuleiro.realizarMovimento(movimento);

            System.out.println(movimento.getTipo());
            System.out.println(tabuleiro.toString());
            
            segundoJogador.saidaDeDados.writeInt(y1);
            segundoJogador.saidaDeDados.writeInt(x1);
            segundoJogador.saidaDeDados.writeInt(y2);
            segundoJogador.saidaDeDados.writeInt(x2);
            segundoJogador.saidaDeDados.writeObject(opcional);
        }
        
        public void receberMensagem() throws IOException, ClassNotFoundException {
            Object mensagem = primeiroJogador.entradaDeDados.readObject();
            
            segundoJogador.saidaDeDados.writeObject(mensagem);
        }
    }

}
