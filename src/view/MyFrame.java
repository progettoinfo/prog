package view;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import model.*;
import java.util.Vector;

/*
 * Articolo SoloPC: JTable
 * Autore: RootkitNeo
 *
 */

public class MyFrame extends JFrame {
  // Variabili di istanza
  // ----------------------------------------------------------------------------------
  private JTable table;                // Tabella
  private MyTableModel model;          // Model personalizzato della tabella
  private JToolBar toolBar;            // ToolBar
  private JButton insert,remove;       // Bottoni
  // ----------------------------------------------------------------------------------
  
  // Costruttore
 public MyFrame() {
    super("JTable Example");
    
    try {
      // commentare il blocco di codice se volete la grafica di java
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch(Exception e) {}
    
    // Toolbar e bottoni della toolbar
    toolBar = new JToolBar();
    insert = new JButton("Inserisci");
    remove = new JButton("Rimuovi");
    
    // Aggiunta dei bottoni
    toolBar.add(insert);
    toolBar.add(remove);
    // Aggiunta dela toolbar
    add(toolBar, BorderLayout.NORTH);
    
    // Pannello contenitore della tabella
    JPanel panel = new JPanel();
    panel.setOpaque(true);
    panel.setBackground(Color.WHITE);
    
    // Definizione del model della tabella e
    // delle rispettive colonne
    model = new MyTableModel();
    model.addColumn("#");
    model.addColumn("Nome");
    model.addColumn("Cognome");
    model.addColumn("Cod.Fisc");
    
    table = new JTable(model);
    table.setDefaultRenderer(Object.class, new MyTableCellRenderer());
        
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setOpaque(true);
    scrollPane.setBackground(Color.WHITE);
    table.setBackground(Color.WHITE);
    // Dimensione fissata dello scrollpane
    scrollPane.setPreferredSize(new Dimension(400,300));
    scrollPane.setMaximumSize(new Dimension(400,300));
    
    
    // Aggiunta scroll pane, ed aggiunta del pannello al frame
    panel.add(scrollPane);
    add(panel);
    

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Eventi sulla JTable; click del mouse
    table.addMouseListener(new MouseAdapter() {
      public void mouseReleased(MouseEvent me) {
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        
        // Prendo il valore in posizione row,col
        String value = model.getValueAt(row,col);
        if(!value.equals("")) {
          JOptionPane.showMessageDialog(null,"Valore selezionato: "+value,"Mostra Valore",JOptionPane.PLAIN_MESSAGE);
        }
      }
    });
  
    
    // Click sul bottone di inserimento
    insert.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        model.addRow(createRow());
      }
    });
    
    // Rimuovo la riga selezionata
    remove.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        int index = table.getSelectedRow();
        if(index < 0) return;
        model.removeRow(index);
      }
    });
    
  }
  
  // Creo una riga vuota; ogni elemento e' una cella
  private Vector<String> createRow() {
    Vector<String> row = new Vector<String>();
    row.add("");
    row.add("");
    row.add("");
    row.add("");
    row.add("");
    return row;
  }
  
  public static void makeGUI() {
    MyFrame mf = new MyFrame();
    mf.setSize(450,400);
    mf.setResizable(true);
    mf.setVisible(true);
  }
  
  
  public static void main(String[] args) {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        public void run() {
          makeGUI();
        }
      });
    } catch(Exception e) {}
  }
}