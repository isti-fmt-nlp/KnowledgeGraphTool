/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import controllers.ProjectController;
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
public class RemoveDocuments extends Observable implements ActionListener{
    private ProjectController cp=ProjectController.getInstance();
    private String sub;
    public RemoveDocuments(String sub){
    this.sub=sub;
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
             }
         }
         FileNameExtensionFilter filter = new FileNameExtensionFilter("Only Txt and Pdf", "txt", "pdf");
         String subject=null;
         if(sub.equals(cp.SUB1))
             subject=File.separator+"1Subject";
         if(sub.equals(cp.SUB2))
             subject=File.separator+"2Subject";
         final File  dirToLock = new File(cp.getSource()+subject);
         JFileChooser fileChooser = new JFileChooser(dirToLock);
         fileChooser.setFileView(new FileView() {
                @Override
                public Boolean isTraversable(File f) {
                    return dirToLock.equals(f);
                }
         });
         fileChooser.setAcceptAllFileFilterUsed(false);
         fileChooser.setFileFilter(filter);
         fileChooser.setDialogTitle("Select Files");
         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
         fileChooser.setMultiSelectionEnabled(true);
         fileChooser.setApproveButtonText("Delete");
         if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                      System.out.println("Choosen Directory: "+fileChooser.getSelectedFile());
                      for(File f:fileChooser.getSelectedFiles())
                        cp.eliminaDocumento(sub, f.getName());
                      this.setChanged();
                      this.notifyObservers();
                   }else{
                   System.out.println("Operation aborted");
               }
    }
    
}
