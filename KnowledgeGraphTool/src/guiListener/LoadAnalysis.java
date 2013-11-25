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
import javax.swing.filechooser.FileView;

/**
 *
 * @author Lipari
 */
public class LoadAnalysis extends Observable implements ActionListener{
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        ControlloreProgetto cp=ControlloreProgetto.getInstance();
        if(cp.analysisCompleted()){
             int dialogButton = JOptionPane.YES_NO_OPTION;
             int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to save the current results and requirements?","Warning",dialogButton);
             if(dialogResult == JOptionPane.YES_OPTION){
                Calendar c=Calendar.getInstance();
                String nameDir = JOptionPane.showInputDialog(null, "Insert save dir name","Result-"+c.get(Calendar.DATE)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR));
                if(nameDir==null || nameDir.isEmpty()){
                JOptionPane.showMessageDialog(null, "Save Fail!");
                return;
                }else{
                    cp.salvaRisultati(nameDir);
                 }
         }
        }
         cp.eliminaRisultati();
         cp.eliminaRequisito();
         final File  dirToLock = new File(cp.getSource()+File.separator+"Old Result");
         JFileChooser fileChooser = new JFileChooser(dirToLock);
         fileChooser.setFileView(new FileView() {
                @Override
                public Boolean isTraversable(File f) {
                    return dirToLock.equals(f);
                }
         });
         fileChooser.setDialogTitle("Select results");
         //indica che dobbiamo scegliere solo le cartelle ( se non specificato, potranno essere selezionati solo i file)
         fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
         fileChooser.setApproveButtonText("Load");
         if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                      cp.loadResult(fileChooser.getSelectedFile().getName());
                      this.setChanged();
                      this.notifyObservers();
         }else{
                   System.out.println("Operation aborted");
               }
        }        
}

