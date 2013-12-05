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

/**
 *
 * @author Peppe
 */
public class AddDocuments extends Observable implements ActionListener{
    private ProjectController cp=ProjectController.getInstance();
    private String subject;
    public AddDocuments(String sub){
    this.subject=sub;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
         FileNameExtensionFilter filter = new FileNameExtensionFilter("Only Txt e Pdf", "txt", "pdf");
           if(cp.analysisCompleted()){
             int dialogButton = JOptionPane.YES_NO_OPTION;
             int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to save the analysis results before adding?","Warning",dialogButton);
             if(dialogResult == JOptionPane.YES_OPTION){
                 Calendar c=Calendar.getInstance();
                 String nameDir = JOptionPane.showInputDialog(null, "Insert save dir name","Result-"+c.get(Calendar.DATE)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR));
                 if(nameDir==null || nameDir.isEmpty())
                 return;
                 cp.saveAndDeleteResults(nameDir);
                 cp.setAnalysis(false);
             }
             else{
                 cp.deleteResults();
                 cp.setAnalysis(false);
             }
         }
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setAcceptAllFileFilterUsed(false);
         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
         fileChooser.setFileFilter(filter);
         fileChooser.setDialogTitle("Select Files");
         fileChooser.setMultiSelectionEnabled(true);
         fileChooser.setApproveButtonText("Load");
         if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            for(File f:fileChooser.getSelectedFiles()){
                f.getName();
                cp.addDocument(subject,f.getAbsolutePath());
            }
            this.setChanged();
            this.notifyObservers();
         }else{
            System.out.println("Operation Aborted");
            this.setChanged();
            this.notifyObservers();
          }
    }
    
}
