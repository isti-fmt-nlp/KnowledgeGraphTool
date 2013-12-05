/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import controllers.ProjectController;
import kgtUtility.CallPyScript;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Observable;

import javax.swing.JOptionPane;

/**
 *
 * @author Peppe
 */
public class StartAnalysis extends Observable implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent ae) {
        ProjectController cp=ProjectController.getInstance();
        if(ProjectController.getInstance().analysisCompleted()){
             int dialogButton = JOptionPane.YES_NO_OPTION;
             int dialogResult = JOptionPane.showConfirmDialog (null, "Result exists. Do you want to save them?","Warning",dialogButton);
             if(dialogResult == JOptionPane.NO_OPTION){
                cp.deleteResults();
              }else{
                 Calendar c=Calendar.getInstance();
                 String nameDir = JOptionPane.showInputDialog(null, "Insert save dir name","Result-"+c.get(Calendar.DATE)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR));
                 if(nameDir==null || nameDir.isEmpty())
                 return;
                 cp.saveAndDeleteResults(nameDir);
                }
            }
        this.setChanged();
        this.notifyObservers("analysis");
        cp.setAnalysis(false);
        if(CallPyScript.analisiScript(cp.getMethod(),"nopriority")==0){
         //if(CallPyScript.analisiScript(cp.getMethod(),"priority")==0){
            cp.loadAnalysis(cp.getSource());
            this.setChanged();
            this.notifyObservers();
        }
        else
            JOptionPane.showMessageDialog(null, "Analysis Fail");
            this.setChanged();
            this.notifyObservers("fail");
     }
}
    

