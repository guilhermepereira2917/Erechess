package server.view;

import javax.swing.JOptionPane;
import server.model.SocketServidor;

public class InputSocketServidor {

    public static SocketServidor criarServidor() {

        String resposta = JOptionPane.showInputDialog(null, "Digite a porta na qual você deseja criar o servidor: ", 2917);
        if (resposta == null)
            return null;
        
        int porta = Integer.parseInt(resposta);
        
        SocketServidor servidor = new SocketServidor();

        new Thread() {
            @Override
            public void run() {
                servidor.iniciar(porta);
            }
        }.start();

        JOptionPane.showMessageDialog(null, "Servidor criado com sucesso na porta " + porta + "!");
        
        return servidor;
    }

}
