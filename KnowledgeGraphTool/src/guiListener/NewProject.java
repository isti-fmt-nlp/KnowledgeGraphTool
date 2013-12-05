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
public class NewProject extends Observable implements ActionListener{
    ProjectController cp=ProjectController.getInstance();
    @Override
    public void actionPerformed(ActionEvent ae) {
         if(cp.isOpen()){
             int dialogButton = JOptionPane.YES_NO_OPTION;
             int dialogResult = JOptionPane.showConfirmDialog (null, "The current project will be closed. Do you confirm?","Warning",dialogButton);
             if(dialogResult == JOptionPane.NO_OPTION){
                 return;
             }else{
                 CloseProject p=new CloseProject();
                 p.actionPerformed(ae);
             }
         }
         String nprogetto = JOptionPane.showInputDialog(null, "Insert Project Name","New Project");
         if(nprogetto==null || nprogetto.isEmpty())
             return;
         
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setDialogTitle("Select new Project folder");
         fileChooser.setApproveButtonText("Create");
         //indica che dobbiamo scegliere solo le cartelle ( se non specificato, potranno essere selezionati solo i file)
         fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         //mostra la finestra per scegliere la cartella
         //restituisce l'intero JFileChooser.APPROVE_OPTION solo se si ha premuto su "Apri"
         if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                      cp.createProject(fileChooser.getSelectedFile().getAbsolutePath(),nprogetto);
                      this.setChanged();
                      this.notifyObservers();
         }
    }
}
