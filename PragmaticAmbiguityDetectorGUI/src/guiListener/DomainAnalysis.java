/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Lipari
 */
public class DomainAnalysis  extends Observable implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent ae){
        FileNameExtensionFilter filter = new FileNameExtensionFilter("only Txt", "txt");
        JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(filter);
            fileChooser.setDialogTitle("Select Requirement Document for analysis");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                kgtUtility.CallPyScript.domainAnalysis(fileChooser.getSelectedFile().getAbsolutePath());
                this.setChanged();
                this.notifyObservers();
            }
            else{
                System.out.println("Operation aborted");
            }
   }
}
