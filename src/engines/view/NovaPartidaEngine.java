package engines.view;

public class NovaPartidaEngine extends javax.swing.JDialog {

    private NovaPartidaConfiguracoes configuracoes = null;

    public NovaPartidaEngine(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioGroupLado = new javax.swing.ButtonGroup();
        labelEngine = new javax.swing.JLabel();
        comboBoxEngine = new javax.swing.JComboBox<>();
        labelDificuldade = new javax.swing.JLabel();
        slideDificuldade = new javax.swing.JSlider();
        radioButtonBrancas = new javax.swing.JRadioButton();
        radioButtonNegras = new javax.swing.JRadioButton();
        radioButtonAleatorio = new javax.swing.JRadioButton();
        labelLado = new javax.swing.JLabel();
        imagemBrancas = new javax.swing.JLabel();
        imagemNegras = new javax.swing.JLabel();
        imagemAleatorio = new javax.swing.JLabel();
        labelTitulo = new javax.swing.JLabel();
        buttonJogar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
        separator1 = new javax.swing.JSeparator();
        separator2 = new javax.swing.JSeparator();
        imagemEngine = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);

        labelEngine.setFont(new java.awt.Font("Segoe UI Symbol", 1, 11)); // NOI18N
        labelEngine.setText("Engine:");

        comboBoxEngine.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stockfish" }));
        comboBoxEngine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxEngineActionPerformed(evt);
            }
        });

        labelDificuldade.setFont(new java.awt.Font("Segoe UI Symbol", 1, 11)); // NOI18N
        labelDificuldade.setText("Dificuldade:");

        slideDificuldade.setMajorTickSpacing(5);
        slideDificuldade.setMaximum(20);
        slideDificuldade.setMinorTickSpacing(1);
        slideDificuldade.setPaintLabels(true);
        slideDificuldade.setPaintTicks(true);
        slideDificuldade.setSnapToTicks(true);
        slideDificuldade.setValue(10);

        radioGroupLado.add(radioButtonBrancas);
        radioButtonBrancas.setText("Brancas");

        radioGroupLado.add(radioButtonNegras);
        radioButtonNegras.setText("Negras");

        radioGroupLado.add(radioButtonAleatorio);
        radioButtonAleatorio.setSelected(true);
        radioButtonAleatorio.setText("Aleatório");

        labelLado.setFont(new java.awt.Font("Segoe UI Symbol", 1, 11)); // NOI18N
        labelLado.setText("Lado:");

        imagemBrancas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icones/Brancas.png"))); // NOI18N
        imagemBrancas.setLabelFor(radioButtonBrancas);
        imagemBrancas.setToolTipText("");
        imagemBrancas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagemBrancasMouseClicked(evt);
            }
        });

        imagemNegras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icones/Negras.png"))); // NOI18N
        imagemNegras.setLabelFor(radioButtonBrancas);

        imagemAleatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icones/Aleatorio.png"))); // NOI18N
        imagemAleatorio.setLabelFor(radioButtonBrancas);

        labelTitulo.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        labelTitulo.setText("Configurações");

        buttonJogar.setText("Jogar");
        buttonJogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonJogarActionPerformed(evt);
            }
        });

        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        imagemEngine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icones/stockfish.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(slideDificuldade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(separator1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelEngine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imagemEngine, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxEngine, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(labelTitulo)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(separator2))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonJogar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelDificuldade)
                            .addComponent(labelLado)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioButtonBrancas, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(imagemBrancas)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioButtonNegras)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(imagemNegras)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioButtonAleatorio)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(imagemAleatorio)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(labelTitulo)
                .addGap(2, 2, 2)
                .addComponent(separator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEngine)
                    .addComponent(comboBoxEngine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imagemEngine))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDificuldade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slideDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelLado)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioButtonBrancas)
                            .addComponent(radioButtonNegras)
                            .addComponent(radioButtonAleatorio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imagemBrancas))
                    .addComponent(imagemNegras)
                    .addComponent(imagemAleatorio))
                .addGap(18, 18, 18)
                .addComponent(separator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonJogar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void comboBoxEngineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxEngineActionPerformed

    }//GEN-LAST:event_comboBoxEngineActionPerformed

    private void imagemBrancasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagemBrancasMouseClicked
        radioButtonBrancas.setSelected(true);
    }//GEN-LAST:event_imagemBrancasMouseClicked

    private void buttonJogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonJogarActionPerformed
        String executavel = "stockfish.exe";
        int dificuldade;
        int ladoInicial;

        if (comboBoxEngine.getSelectedIndex() == 1) {
            executavel = "outraengine.exe";
        }

        dificuldade = slideDificuldade.getValue();

        if (radioButtonAleatorio.isSelected()) {
            ladoInicial = 0;
        } else if (radioButtonBrancas.isSelected()) {
            ladoInicial = 1;
        } else {
            ladoInicial = 2;
        }

        configuracoes = new NovaPartidaConfiguracoes(executavel, dificuldade, ladoInicial);
        dispose();
    }//GEN-LAST:event_buttonJogarActionPerformed

    public NovaPartidaConfiguracoes getConfiguracoes() {
        return configuracoes;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonJogar;
    private javax.swing.JComboBox<String> comboBoxEngine;
    private javax.swing.JLabel imagemAleatorio;
    private javax.swing.JLabel imagemBrancas;
    private javax.swing.JLabel imagemEngine;
    private javax.swing.JLabel imagemNegras;
    private javax.swing.JLabel labelDificuldade;
    private javax.swing.JLabel labelEngine;
    private javax.swing.JLabel labelLado;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JRadioButton radioButtonAleatorio;
    private javax.swing.JRadioButton radioButtonBrancas;
    private javax.swing.JRadioButton radioButtonNegras;
    private javax.swing.ButtonGroup radioGroupLado;
    private javax.swing.JSeparator separator1;
    private javax.swing.JSeparator separator2;
    private javax.swing.JSlider slideDificuldade;
    // End of variables declaration//GEN-END:variables

}
