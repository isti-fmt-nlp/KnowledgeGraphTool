/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import Controllori.ControlloreProgetto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Peppe
 */
public class AggiungiRequisiti extends Observable implements ActionListener{
   private ControlloreProgetto cp=ControlloreProgetto.getIstance();
   @Override
    public void actionPerformed(ActionEvent ae) {
         FileNameExtensionFilter filter = new FileNameExtensionFilter("Solo Txt", "txt");
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setAcceptAllFileFilterUsed(false);
         fileChooser.setFileFilter(filter);
         fileChooser.setDialogTitle("Seleziona File");
         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
         if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                      System.out.println("Cartella selezionata: "+fileChooser.getSelectedFile());
                      if(cp.aggiungiRequisiti(fileChooser.getSelectedFile().getAbsolutePath()));
                      this.setChanged();
                      this.notifyObservers();
                   }else{
                   System.out.println("Operazione annullata");
               }
    }

    
}
