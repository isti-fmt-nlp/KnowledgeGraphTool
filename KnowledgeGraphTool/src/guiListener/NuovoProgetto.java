/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import controllers.ControlloreProgetto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Peppe
 */
public class NuovoProgetto extends Observable implements ActionListener{
    ControlloreProgetto cp=ControlloreProgetto.getIstance();
    @Override
    public void actionPerformed(ActionEvent ae) {
         if(cp.isOpen()){
             int dialogButton = JOptionPane.YES_NO_OPTION;
             int dialogResult = JOptionPane.showConfirmDialog (null, "Il progetto esistente verrà chiuso. Confermi?","Warning",dialogButton);
             if(dialogResult == JOptionPane.NO_OPTION){
                 return;
             }else{
                 ChiudiProgetto p=new ChiudiProgetto();
                 p.actionPerformed(ae);
             }
         }
         String nprogetto = JOptionPane.showInputDialog(null, "Inserisci il nome del Progetto","Nuovo Progetto");
         if(nprogetto==null || nprogetto.isEmpty())
             return;
         
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setDialogTitle("Seleziona directory del Nuovo progetto");
         fileChooser.setApproveButtonText("Crea");
         //indica che dobbiamo scegliere solo le cartelle ( se non specificato, potranno essere selezionati solo i file)
         fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         //mostra la finestra per scegliere la cartella
         //restituisce l'intero JFileChooser.APPROVE_OPTION solo se si ha premuto su "Apri"
         if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                      System.out.println("Cartella selezionata: "+fileChooser.getSelectedFile());
                      cp.creaProgetto(fileChooser.getSelectedFile().getAbsolutePath(),nprogetto);
                      this.setChanged();
                      this.notifyObservers();
         }
    }
}
