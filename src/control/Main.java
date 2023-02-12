package control;

import partida.view.MenuInicial;
import server.model.SocketServidor;

public class Main {

    public static void main(String[] args) {
        new MenuInicial().setVisible(true);
    }

    public static void sing() {
        new Controlador();
    }
    
    public static void multi() {
        new Thread() {
            @Override
            public void run() {
                new SocketServidor().iniciar(2917);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                new ControladorMultiplayer("localhost", 2917);
            }
        }.start();
        
        new Thread() {
            @Override
            public void run() {
                new ControladorMultiplayer("localhost", 2917);
            }
        }.start();
    }
    
}
