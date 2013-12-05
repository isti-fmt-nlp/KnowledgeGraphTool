/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import controllers.ProjectController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Peppe
 */
public class OpenProject extends Observable implements ActionListener{
    ProjectController cp=ProjectController.getInstance();
    @Override
    public void actionPerformed(ActionEvent ae) {
         if(cp.isOpen()){
             int dialogButton = JOptionPane.YES_NO_OPTION;
             int dialogResult = JOptionPane.showConfirmDialog (null, "The current project will be closed. Do you Confirm?","Warning",dialogButton);
             if(dialogResult == JOptionPane.NO_OPTION){
                 return;
             }else{
                 CloseProject p=new CloseProject();
                 p.actionPerformed(ae);
             }
         }
         
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setDialogTitle("Select directory project");
         //indica che dobbiamo scegliere solo le cartelle ( se non specificato, potranno essere selezionati solo i file)
         fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         //mostra la finestra per scegliere la cartella
         //restituisce l'intero JFileChooser.APPROVE_OPTION solo se si ha premuto su "Apri"
         if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                      if(cp.openProject(fileChooser.getSelectedFile().getAbsolutePath()).equals("progetto_inesistente")){
                          JOptionPane.showMessageDialog(null,"Folder is not a project");
                          return;
                      }
                      this.setChanged();
                      this.notifyObservers();
                   }else{
                   System.out.println("Operation aborted");
               }
    }
    
}
