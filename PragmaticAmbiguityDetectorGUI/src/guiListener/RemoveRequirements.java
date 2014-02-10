/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import controllers.ProjectController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Observable;
import javax.swing.JOptionPane;

/**
 *
 * @author Lipari
 */
public class RemoveRequirements extends Observable implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent ae) {
        ProjectController cp=ProjectController.getInstance();
         if(cp.analysisCompleted()){
             int dialogButton = JOptionPane.YES_NO_OPTION;
             int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to save the analysis results before removing?","Warning",dialogButton);
             if(dialogResult == JOptionPane.YES_OPTION){
                 Calendar c=Calendar.getInstance();
                 String nameDir = JOptionPane.showInputDialog(null, "Insert save dir name","Result-"+c.get(Calendar.DATE)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR));
                 if(nameDir==null || nameDir.isEmpty())
                 return;
                 cp.saveAndDeleteResults(nameDir);
             }
             else{
                 cp.deleteResults();
             }
         }
         if(cp.deleteRequirements()){
            this.setChanged();
            this.notifyObservers();
           }
             else
               JOptionPane.showMessageDialog(null,"Delete failed");
    }
}
    