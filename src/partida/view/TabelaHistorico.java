package partida.view;

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import partida.model.HistoricoMovimentos;

public class TabelaHistorico extends JTable implements Observer {

    HistoricoMovimentos historicoMovimentos;

    DefaultTableModel tableModel = new DefaultTableModel();

    public TabelaHistorico(HistoricoMovimentos historicoMovimentos) {

        this.historicoMovimentos = historicoMovimentos;
        historicoMovimentos.addObserver(this);

        setRowHeight(24);
        Font fonte = new Font("Segoe UI", Font.BOLD, 14);
        setFont(fonte);
        getTableHeader().setFont(fonte);
        
        // setFillsViewportHeight(true);
        tableModel.setColumnIdentifiers(new Object[]{"Brancas", "Negras"});
        setModel(tableModel);

        DefaultTableCellRenderer renderizador = new DefaultTableCellRenderer();
        renderizador.setHorizontalAlignment(SwingUtilities.CENTER);

        getColumnModel().getColumn(0).setCellRenderer(renderizador);
        getColumnModel().getColumn(1).setCellRenderer(renderizador);
        
        setEnabled(false);
    }

    @Override
    public void update(Observable o, Object o1) {
        int ultimaLinha = getRowCount() - 1;
        String movimentoFormatado
                = historicoMovimentos.tamanho() + ". " + historicoMovimentos.ultimoMovimento();

        if (historicoMovimentos.tamanho() % 2 == 1) {
            tableModel.addRow(new Object[]{movimentoFormatado, ""});
        } else {
            tableModel.setValueAt(movimentoFormatado, ultimaLinha, 1);
        }
    }

}
