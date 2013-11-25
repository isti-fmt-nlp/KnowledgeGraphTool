/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import controllers.ControlloreProgetto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import java.util.Observable;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;

/**
 *
 * @author Lipari
 */
public class RemoveDominio extends Observable implements ActionListener{
    private ControlloreProgetto cp=ControlloreProgetto.getInstance();
    private String dom;
    public RemoveDominio(String dom){
    this.dom=dom;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
            if(cp.analysisCompleted()){
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to save the analysis results before removing?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                    Calendar c=Calendar.getInstance();
                    String nameDir = JOptionPane.showInputDialog(null, "Insert save dir name","Result-"+c.get(Calendar.DATE)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR));
                    if(nameDir==null || nameDir.isEmpty())
                        return;
                    cp.salvaCancRisultati(nameDir);
                    cp.setAnalysis(false);
             }
             else{
                 cp.eliminaRisultati();
                 cp.setAnalysis(false);
             }
         }
         FileNameExtensionFilter filter = new FileNameExtensionFilter("Solo Txt e Pdf", "txt", "pdf");
         String domain=null;
         if(dom.equals(cp.DOM1))
             domain=File.separator+"Domain1";
         if(dom.equals(cp.DOM2))
             domain=File.separator+"Domain2";
         final File  dirToLock = new File(cp.getSource()+domain);
         JFileChooser fileChooser = new JFileChooser(dirToLock);
         fileChooser.setFileView(new FileView() {
                @Override
                public Boolean isTraversable(File f) {
                    return dirToLock.equals(f);
                }
         });
         fileChooser.setAcceptAllFileFilterUsed(false);
         fileChooser.setFileFilter(filter);
         fileChooser.setDialogTitle("Seleziona File");
         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
         fileChooser.setMultiSelectionEnabled(true);
         fileChooser.setApproveButtonText("Elimina");
         if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                      System.out.println("Cartella selezionata: "+fileChooser.getSelectedFile());
                      for(File f:fileChooser.getSelectedFiles())
                        cp.eliminaDocumento(dom, f.getName());
                      this.setChanged();
                      this.notifyObservers();
                   }else{
                   System.out.println("Operazione annullata");
               }
    }
    
}
