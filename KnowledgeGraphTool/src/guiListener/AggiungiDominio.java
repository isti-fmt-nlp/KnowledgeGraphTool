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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.ui.ExtensionFileFilter;

/**
 *
 * @author Peppe
 */
public class AggiungiDominio extends Observable implements ActionListener{
    private ControlloreProgetto cp=ControlloreProgetto.getIstance();
    private String dom;
    public AggiungiDominio(String dom){
    this.dom=dom;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
         FileNameExtensionFilter filter = new FileNameExtensionFilter("Solo Txt e Pdf", "txt", "pdf");
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setAcceptAllFileFilterUsed(false);
         fileChooser.setFileFilter(filter);
         fileChooser.setDialogTitle("Seleziona File");
         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
         if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                      System.out.println("Cartella selezionata: "+fileChooser.getSelectedFile());
                      cp.aggiungiDocumento(dom,fileChooser.getSelectedFile().getAbsolutePath());
                      this.setChanged();
                      this.notifyObservers();
                   }else{
                   System.out.println("Operazione annullata");
               }
    }
    
}
