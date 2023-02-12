
package server.view;

import control.ControladorMultiplayer;
import javax.swing.JOptionPane;

public class InputSocketCliente {
    
    public static ControladorMultiplayer conectarPartida() {
        String ip = JOptionPane.showInputDialog(null, "Digite o ip da partida (ex: 192.1.2.128): ", "localhost");
        
        if (ip == null)
            return null;
        
        String resposta = JOptionPane.showInputDialog(null, "Digite a porta na qual o servidor está localizado: ", 2917);
        
        if (resposta == null)
            return null;
        
        int porta = Integer.parseInt(resposta);
        return new ControladorMultiplayer(ip, porta);
    }
    
}
