/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Controllori.ControlloreProgetto;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

/**
 *
 * @author Lipari
 */
public class MainFrame extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScegliDir = new javax.swing.JFileChooser();
        jMenuBar1 = new javax.swing.JMenuBar();
        jProj = new javax.swing.JMenu();
        apriItem = new javax.swing.JMenuItem();
        nuovoitem = new javax.swing.JMenuItem();
        chiudiItem = new javax.swing.JMenuItem();
        jSalva = new javax.swing.JMenu();
        reqItem = new javax.swing.JMenuItem();
        salvaGrafo = new javax.swing.JMenuItem();

        jScegliDir.setDialogTitle(org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.jScegliDir.dialogTitle")); // NOI18N
        jScegliDir.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.title")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jProj, org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.jProj.text")); // NOI18N

        apriItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        org.openide.awt.Mnemonics.setLocalizedText(apriItem, org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.apriItem.text")); // NOI18N
        apriItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apriItemActionPerformed(evt);
            }
        });
        jProj.add(apriItem);

        nuovoitem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        org.openide.awt.Mnemonics.setLocalizedText(nuovoitem, org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.nuovoitem.text")); // NOI18N
        nuovoitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuovoitemActionPerformed(evt);
            }
        });
        jProj.add(nuovoitem);

        chiudiItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        org.openide.awt.Mnemonics.setLocalizedText(chiudiItem, org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.chiudiItem.text")); // NOI18N
        jProj.add(chiudiItem);

        jMenuBar1.add(jProj);

        org.openide.awt.Mnemonics.setLocalizedText(jSalva, org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.jSalva.text")); // NOI18N

        reqItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        org.openide.awt.Mnemonics.setLocalizedText(reqItem, org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.reqItem.text")); // NOI18N
        jSalva.add(reqItem);

        salvaGrafo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        org.openide.awt.Mnemonics.setLocalizedText(salvaGrafo, org.openide.util.NbBundle.getMessage(MainFrame.class, "MainFrame.salvaGrafo.text")); // NOI18N
        jSalva.add(salvaGrafo);

        jMenuBar1.add(jSalva);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void apriItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apriItemActionPerformed
        ControlloreProgetto cp=ControlloreProgetto.getIstance();
        int returnVal = jScegliDir.showOpenDialog(this);
        if (returnVal == jScegliDir.APPROVE_OPTION) {
          File file = jScegliDir.getSelectedFile();
          try {
            cp.apriProgetto(file.getCanonicalPath());
           } catch (IOException ex) {
            System.out.println("problem accessing file"+file.getAbsolutePath());
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_apriItemActionPerformed

    private void nuovoitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuovoitemActionPerformed
        ControlloreProgetto cp=ControlloreProgetto.getIstance();
        int returnVal = jScegliDir.showOpenDialog(this);
        if (returnVal == jScegliDir.APPROVE_OPTION) {
          File file = jScegliDir.getSelectedFile();
          try {
            cp.creaProgetto(file.getCanonicalPath(),"progetto");
           } catch (IOException ex) {
            System.out.println("problem accessing file"+file.getAbsolutePath());
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_nuovoitemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem apriItem;
    private javax.swing.JMenuItem chiudiItem;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jProj;
    private javax.swing.JMenu jSalva;
    private javax.swing.JFileChooser jScegliDir;
    private javax.swing.JMenuItem nuovoitem;
    private javax.swing.JMenuItem reqItem;
    private javax.swing.JMenuItem salvaGrafo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}