/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telenorkalkuláció;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import telenorkalkuláció.csomagok.Klasszik;
import telenorkalkuláció.csomagok.Klasszik1;
import telenorkalkuláció.csomagok.Perc;
import telenorkalkuláció.csomagok.SmartTarifa;
import telenorkalkuláció.csomagok.SmarttarifaExtra;

/**
 *
 * @author Család
 */
public class Ablak extends javax.swing.JFrame implements MouseListener {
    private BufferedImage logo;
    
    private Perc perc35 = new Perc(1415, 35, 40.5f, 40.5f);
    private Perc perc180 = new Perc(5597, 180, 31.1f, 31.1f);
    private Klasszik1 klasszik1 = new Klasszik1(1690, 38, 40, 40);
    private Klasszik klasszik2 = new Klasszik(2590, 36.5f, 39, 39);
//    private Klasszik klasszik3 = new Klasszik(3990, 34f, 38, 38);
//    private Klasszik klasszik4 = new Klasszik(5590, 31.5f, 34.5f, 34.5f);
    private SmartTarifa smart2 = new SmartTarifa(3190, 32, 36, 39, 39);
//    private SmartTarifa smart3 = new SmartTarifa(5290, 26, 30, 37, 37);
    private SmarttarifaExtra smartExtra1 = new SmarttarifaExtra(2790, 30, 20, 41, 41);
    private SmarttarifaExtra smartExtra2 = new SmarttarifaExtra(4790, 60, 40, 38, 38);
    
    /**
     * Creates new form Ablak
     */
    public Ablak() {
        try {
            logo = ImageIO.read(new File("logo.png"));
        } catch (IOException ex) {
            Logger.getLogger(Ablak.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        setLocationRelativeTo(null);
        
        tabbedPane.addMouseListener(this);
    }
    
    public void addRowToTable(String title, int belülPerc, int belülDarab, 
            int kívülPerc, int kívülDarab, int vezetékesPerc, int vezetékesDarab, 
            int smsDarab, int családiDarab) {
        DefaultTableModel model = (DefaultTableModel) táblázat.getModel();
        float p35 = perc35.számít(belülPerc, belülDarab, kívülPerc, kívülDarab, 
                vezetékesPerc, vezetékesDarab, smsDarab, családiDarab);
        float p180 = perc180.számít(belülPerc, belülDarab, kívülPerc, kívülDarab, 
                vezetékesPerc, vezetékesDarab, smsDarab, családiDarab);
        float k1 = klasszik1.számít(belülPerc, belülDarab, kívülPerc, kívülDarab, 
                vezetékesPerc, vezetékesDarab, smsDarab, családiDarab);
        float k2 = klasszik2.számít(belülPerc, belülDarab, kívülPerc, kívülDarab, 
                vezetékesPerc, vezetékesDarab, smsDarab, családiDarab);
//        float k3 = klasszik3.számít(belülPerc, belülDarab, kívülPerc, kívülDarab, 
//                vezetékesPerc, vezetékesDarab, smsDarab, családiDarab);
//        float k4 = klasszik4.számít(belülPerc, belülDarab, kívülPerc, kívülDarab, 
//                vezetékesPerc, vezetékesDarab, smsDarab, családiDarab);
        float s2 = smart2.számít(belülPerc, belülDarab, kívülPerc, kívülDarab, 
                vezetékesPerc, vezetékesDarab, smsDarab, családiDarab);
//        float s3 = smart3.számít(belülPerc, belülDarab, kívülPerc, kívülDarab, 
//                vezetékesPerc, vezetékesDarab, smsDarab, családiDarab);
        float se1 = smartExtra1.számít(belülPerc, belülDarab, kívülPerc, kívülDarab, 
                vezetékesPerc, vezetékesDarab, smsDarab, családiDarab);
        float se2 = smartExtra2.számít(belülPerc, belülDarab, kívülPerc, kívülDarab, 
                vezetékesPerc, vezetékesDarab, smsDarab, családiDarab);
        model.addRow(new Object[]{title, p35, p180, k1, k2, s2, se1, se2});
    }
    
    private void removeRow(int index) {
        táblázat.remove(index);
    }
    
    public void save() {
        DefaultTableModel model = (DefaultTableModel) táblázat.getModel();
        int rowCount = model.getRowCount();
        for ( int i = 0; i < rowCount; i++) {
            model.removeRow(0);
        }
        
        int tabCount = tabbedPane.getTabCount() - 1;
        for ( int i = 0; i < tabCount; i++ ) {
            Számlaadatok tab = (Számlaadatok) tabbedPane.getComponent(i);
            tabbedPane.setTitleAt(i, tab.getHónapnév());
            addRowToTable(tab.getHónapnév(), 
                    tab.getBelülPerc(), tab.getBelülDarab(), 
                    tab.getKívülPerc(), tab.getKívülDarab(), 
                    tab.getVezetékesPerc(), tab.getVezetékesDarab(), 
                    tab.getSmsDarab(), tab.getCsaládiDarab());
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int index = tabbedPane.getSelectedIndex();
        if ( e.getClickCount() == 2 && tabbedPane.getTabCount() > 2 ) {
            tabbedPane.remove(index);
            removeRow(index);
        } else if ( e.getClickCount() == 1 ) {
            if ( index == tabbedPane.getTabCount() - 1 ) {
                tabbedPane.remove(tabbedPane.getTabCount()-1);
                Számlaadatok újTab = new Számlaadatok();
                tabbedPane.addTab("Számlaadatok", újTab);
                tabbedPane.setSelectedIndex(index);
                
                tabbedPane.addTab("Új...", null);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tabbedPane = new javax.swing.JTabbedPane();
        számlaadatok = new telenorkalkuláció.Számlaadatok();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        táblázat = new javax.swing.JTable();
        mentés = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Telenor tarifa kalkuláció");
        setIconImage(logo);

        tabbedPane.addTab("Számlaadatok", számlaadatok);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Új...", jPanel1);

        táblázat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hónap", "35 Perc", "180 Perc", "Klasszik 1", "Klasszik 2", "Smarttarifa 2", "Smarttarifa Extra 1", "Smarttarifa Extra 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(táblázat);

        mentés.setText("Mentés");
        mentés.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mentésActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(mentés)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mentés)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mentésActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mentésActionPerformed
        save();
    }//GEN-LAST:event_mentésActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton mentés;
    private telenorkalkuláció.Számlaadatok számlaadatok;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable táblázat;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

}
