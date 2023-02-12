package partida.view;

import enums.Cores;
import enums.EstadosPartida;
import java.awt.BorderLayout;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import partida.model.Partida;

public class FramePartida extends JFrame implements Observer {

    Partida partida;
    protected PanelTabuleiro panelTabuleiro;
    protected TabelaHistorico tabelaHistorico;
    protected JScrollPane scrollTabela;

    protected PanelRelogio relogioBrancas;
    protected PanelRelogio relogioNegras;
    
    JPanel panelArrastavel;
    JLabel arrastavel = new JLabel();
    
    public FramePartida(Partida partida) {
        initComponents();
        this.partida = partida;
        setup();

        setVisible(true);
    }

    private void setup() {
        partida.addObserver(this);

        // Tabuleiro
        panelTabuleiro = new PanelTabuleiro(partida.getTabuleiro());
        panelTabuleiro.desenharTabuleiro(partida.getTabuleiro().getTurno());

        panelArrastavel = new JPanel();

        panelContainer.removeAll();
        panelContainer.add(panelTabuleiro, new Integer(0));
        panelTabuleiro.resize();

        // Peça exibida ao ser arrastada pelo jogador
        panelArrastavel.setSize(panelTabuleiro.getSize());
        panelArrastavel.setOpaque(false);
        panelArrastavel.setLayout(null);
        panelContainer.add(panelArrastavel, new Integer(1));

        arrastavel.setIcon(panelTabuleiro.getBotoesCasas()[0][0].getIcon());
        arrastavel.setSize(80, 80);
        arrastavel.setVisible(false);

        panelArrastavel.add(arrastavel);

        // Tabela de histórico de movimentos
        tabelaHistorico = new TabelaHistorico(partida.getHistorico());
        scrollTabela = new JScrollPane(tabelaHistorico);

        panelLateral.removeAll();
        panelLateral.add(scrollTabela, BorderLayout.CENTER);
        panelLateral.setSize(300, panelContainer.getHeight());

        // Relógios
        if (partida.isJogarComRelogio()) {
            relogioBrancas = new PanelRelogio(partida.getRelogioBrancas(), partida.getTabuleiro());
            relogioNegras = new PanelRelogio(partida.getRelogioNegras(), partida.getTabuleiro());

            panelAdversario.add(relogioNegras);
            panelAliado.add(relogioBrancas);
        }

        setTitle("Erechess - " + partida.getEstado());
    }

    public String escolherPecaCoroacao() {
        String[] opcoes = new String[]{"Dama", "Torre", "Cavalo", "Bispo"};
        int opcaoEscolhida = JOptionPane.showOptionDialog(
                this,
                "Escolha em qual peça ele se transformará!",
                "Coroação!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        if (opcaoEscolhida == JOptionPane.CLOSED_OPTION) {
            return null;
        }

        return opcoes[opcaoEscolhida];
    }

    public void aviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
    
    public boolean confirmarCriacaoNovaPartida() {
        return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Tem certeza que deseja criar uma nova partida?", "Nova partida", JOptionPane.YES_NO_OPTION);
    }

    public boolean confirmarVoltarAoMenuPrincipal() {
        return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Tem certeza que deseja voltar ao menu principal?", "Voltar ao menu principal", JOptionPane.YES_NO_OPTION);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof Partida) {
            partida = (Partida) observable;
            EstadosPartida estado = partida.getEstado();

            setTitle("Erechess - " + partida.getEstado());
            if (null != estado) {
                switch (estado) {
                    case EM_ANDAMENTO:
                        return;
                    case BRANCAS_VENCERAM:
                        notificaoXequeMate("Brancas");
                        break;
                    case NEGRAS_VENCERAM:
                        notificaoXequeMate("Negras");
                        break;
                    case EMPATADA:
                        notificacaoEmpate();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void notificaoXequeMate(String lado) {
        JOptionPane.showMessageDialog(null, lado + " ganharam!", "Xeque-Mate", JOptionPane.INFORMATION_MESSAGE);
    }

    public void notificacaoEmpate() {
        JOptionPane.showMessageDialog(null, "A partida empatou.", "Empate", JOptionPane.INFORMATION_MESSAGE);
    }

    public void atualizarPosicaoArrastavel(Point location) {
        int xTela = (int) (location.getX() - panelTabuleiro.getLocationOnScreen().getX()) - 30;
        int yTela = (int) (location.getY() - panelTabuleiro.getLocationOnScreen().getY()) - 30;

        arrastavel.setLocation(xTela, yTela);
    }
    
    @SuppressWarnings("all")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContainer = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        panelLateral = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelAdversario = new javax.swing.JPanel();
        panelAliado = new javax.swing.JPanel();
        barraMenu = new javax.swing.JMenuBar();
        menuEditar = new javax.swing.JMenu();
        itemNovaPartida = new javax.swing.JMenuItem();
        separador1 = new javax.swing.JPopupMenu.Separator();
        checkBoxMostarHistorico = new javax.swing.JCheckBoxMenuItem();
        checkBoxGirarPerspectiva = new javax.swing.JCheckBoxMenuItem();
        checkBoxDestacarUltimoMovimento = new javax.swing.JCheckBoxMenuItem();
        separador2 = new javax.swing.JPopupMenu.Separator();
        itemVoltarAoMenuPrincipal = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Erechess");
        setMinimumSize(new java.awt.Dimension(886, 668));
        setResizable(false);

        panelContainer.setBackground(new java.awt.Color(204, 204, 204));
        panelContainer.setOpaque(true);
        panelContainer.setPreferredSize(new java.awt.Dimension(600, 600));

        jLabel1.setText("jLabel1");

        panelContainer.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panelContainerLayout = new javax.swing.GroupLayout(panelContainer);
        panelContainer.setLayout(panelContainerLayout);
        panelContainerLayout.setHorizontalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(556, Short.MAX_VALUE))
        );
        panelContainerLayout.setVerticalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(600, Short.MAX_VALUE))
        );

        panelLateral.setBackground(new java.awt.Color(204, 204, 204));
        panelLateral.setMaximumSize(new java.awt.Dimension(0, 0));
        panelLateral.setLayout(new java.awt.BorderLayout());

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("jLabel2");
        panelLateral.add(jLabel2, java.awt.BorderLayout.CENTER);

        panelAdversario.setBackground(new java.awt.Color(204, 204, 204));
        panelAdversario.setLayout(new java.awt.BorderLayout());

        panelAliado.setBackground(new java.awt.Color(204, 204, 204));
        panelAliado.setLayout(new java.awt.BorderLayout());

        menuEditar.setText("Editar");

        itemNovaPartida.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        itemNovaPartida.setText("Nova Partida");
        itemNovaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNovaPartidaActionPerformed(evt);
            }
        });
        menuEditar.add(itemNovaPartida);
        menuEditar.add(separador1);

        checkBoxMostarHistorico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        checkBoxMostarHistorico.setSelected(true);
        checkBoxMostarHistorico.setText("Mostrar Histórico de Movimentos");
        checkBoxMostarHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxMostarHistoricoActionPerformed(evt);
            }
        });
        menuEditar.add(checkBoxMostarHistorico);

        checkBoxGirarPerspectiva.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        checkBoxGirarPerspectiva.setText("Girar Tabuleiro Automaticamente");
        checkBoxGirarPerspectiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxGirarPerspectivaActionPerformed(evt);
            }
        });
        menuEditar.add(checkBoxGirarPerspectiva);

        checkBoxDestacarUltimoMovimento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        checkBoxDestacarUltimoMovimento.setSelected(true);
        checkBoxDestacarUltimoMovimento.setText("Destacar Ultimo Movimento");
        checkBoxDestacarUltimoMovimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxDestacarUltimoMovimentoActionPerformed(evt);
            }
        });
        menuEditar.add(checkBoxDestacarUltimoMovimento);
        menuEditar.add(separador2);

        itemVoltarAoMenuPrincipal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        itemVoltarAoMenuPrincipal.setText("Voltar ao Menu Principal");
        menuEditar.add(itemVoltarAoMenuPrincipal);

        barraMenu.add(menuEditar);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panelAdversario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelContainer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelAliado, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelAdversario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelAliado, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void checkBoxGirarPerspectivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxGirarPerspectivaActionPerformed
       panelTabuleiro.setGirarTabuleiro(checkBoxGirarPerspectiva.isSelected());
    }//GEN-LAST:event_checkBoxGirarPerspectivaActionPerformed

    private void checkBoxDestacarUltimoMovimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxDestacarUltimoMovimentoActionPerformed
        panelTabuleiro.setDestacarUltimoMovimento(checkBoxDestacarUltimoMovimento.isSelected());
    }//GEN-LAST:event_checkBoxDestacarUltimoMovimentoActionPerformed

    private void checkBoxMostarHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxMostarHistoricoActionPerformed
        panelLateral.setVisible(checkBoxMostarHistorico.isSelected());
    }//GEN-LAST:event_checkBoxMostarHistoricoActionPerformed

    private void itemNovaPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNovaPartidaActionPerformed
        
    }//GEN-LAST:event_itemNovaPartidaActionPerformed

    public PanelTabuleiro getPanelTabuleiro() {
        return panelTabuleiro;
    }

    public javax.swing.JMenuItem getItemNovaPartida() {
        return itemNovaPartida;
    }

    public javax.swing.JLayeredPane getPanelContainer() {
        return panelContainer;
    }

    public javax.swing.JPanel getPanelArrastavel() {
        return panelArrastavel;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;

        setup();
    }

    public javax.swing.JMenuItem getItemVoltarAoMenuPrincipal() {
        return itemVoltarAoMenuPrincipal;
    }

    public JLabel getArrastavel() {
        return arrastavel;
    }

    public void mostrarArrastavel() {
        arrastavel.setVisible(true);
    }

    public void esconderArrastavel() {
        arrastavel.setVisible(false);
    }

    public void atualizarIconeArrastavel(ButtonCasa botaoClicado) {
        if (!botaoClicado.getCasa().estaVazia()) {
            arrastavel.setIcon(botaoClicado.getIcone());
        } else {
            arrastavel.setIcon(null);
        }
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JCheckBoxMenuItem checkBoxDestacarUltimoMovimento;
    private javax.swing.JCheckBoxMenuItem checkBoxGirarPerspectiva;
    private javax.swing.JCheckBoxMenuItem checkBoxMostarHistorico;
    private javax.swing.JMenuItem itemNovaPartida;
    private javax.swing.JMenuItem itemVoltarAoMenuPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu menuEditar;
    private javax.swing.JPanel panelAdversario;
    private javax.swing.JPanel panelAliado;
    private javax.swing.JLayeredPane panelContainer;
    protected javax.swing.JPanel panelLateral;
    private javax.swing.JPopupMenu.Separator separador1;
    private javax.swing.JPopupMenu.Separator separador2;
    // End of variables declaration//GEN-END:variables

}
