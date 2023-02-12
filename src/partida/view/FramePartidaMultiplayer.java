package partida.view;

import enums.Cores;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import partida.model.Partida;

public class FramePartidaMultiplayer extends FramePartida {

    private JPanel panelChat;
    private JTextField fieldMensagem;
    
    private JPanel panelMensagens;
    
    
    public FramePartidaMultiplayer(Partida partida) {
        super(partida);

        panelChat = new JPanel();
        panelChat.setBackground(Color.white);

        panelChat.setLayout(new BorderLayout());
        fieldMensagem = new JTextField();
        
        panelMensagens = new JPanel();
        panelMensagens.setBackground(Color.white);
        panelMensagens.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        panelChat.add(fieldMensagem, BorderLayout.PAGE_END);
        panelChat.add(panelMensagens, BorderLayout.CENTER);
        
        panelLateral.setLayout(new GridLayout(2, 1, 0, 2));
        panelLateral.add(panelChat);
        panelLateral.setPreferredSize(new Dimension(300, 600));
    }

    private void mostrarMensagem(String mensagem, int alinhamento) {
        JLabel label = new JLabel("<html><p>" + mensagem + "</p></html>", alinhamento);
        label.setPreferredSize(new Dimension(panelMensagens.getWidth() - 16, 16));
        
        panelMensagens.add(label);
        panelMensagens.validate();
    }

    public void mostrarPropriaMensagem(String mensagem) {
        mostrarMensagem(mensagem, JLabel.RIGHT);
    }
    
    public void mostrarMensagemOutroJogador(String mensagem) {
        mostrarMensagem(mensagem, JLabel.LEFT);
    }
    
    public JTextField getFieldMensagem() {
        return fieldMensagem;
    }

    public String getMensagem() {
        return fieldMensagem.getText();
    }
    
    public void limparMensagem() {
        fieldMensagem.setText("");
    }
    
}
