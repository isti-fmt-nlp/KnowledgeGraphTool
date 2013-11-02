/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiComponents;

import Controllori.ControlloreProgetto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Peppe
 */
public class ApriProgetto extends Observable implements ActionListener{
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
         
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setDialogTitle("Seleziona directory progetto");
         //indica che dobbiamo scegliere solo le cartelle ( se non specificato, potranno essere selezionati solo i file)
         fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         //mostra la finestra per scegliere la cartella
         //restituisce l'intero JFileChooser.APPROVE_OPTION solo se si ha premuto su "Apri"
         if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                      System.out.println("Cartella selezionata: "+fileChooser.getSelectedFile());
                      if(cp.apriProgetto(fileChooser.getSelectedFile().getAbsolutePath()).equals("progetto_inesistente")){
                          JOptionPane.showMessageDialog(null,"Progetto inesistente");
                          return;
                      }
                      this.setChanged();
                      this.notifyObservers();
                   }else{
                   System.out.println("Operazione annullata");
               }
    }
    
}
