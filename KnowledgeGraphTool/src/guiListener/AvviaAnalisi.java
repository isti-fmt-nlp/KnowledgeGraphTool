/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import controllers.ControlloreProgetto;
import kgtUtility.CallPyScript;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Observable;

import javax.swing.JOptionPane;

/**
 *
 * @author Peppe
 */
public class AvviaAnalisi extends Observable implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent ae) {
        ControlloreProgetto cp=ControlloreProgetto.getInstance();
        if(ControlloreProgetto.getInstance().analysisCompleted()){
             int dialogButton = JOptionPane.YES_NO_OPTION;
             int dialogResult = JOptionPane.showConfirmDialog (null, "Result exists. Do you want to save them?","Warning",dialogButton);
             if(dialogResult == JOptionPane.NO_OPTION){
                cp.eliminaRisultati();
              }else{
                 Calendar c=Calendar.getInstance();
                 String nameDir = JOptionPane.showInputDialog(null, "Insert save dir name","Result-"+c.get(Calendar.DATE)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR));
                 if(nameDir==null || nameDir.isEmpty())
                 return;
                 cp.salvaCancRisultati(nameDir);
                }
            }
        this.setChanged();
        this.notifyObservers("analysis");
        cp.setAnalysis(false);
        if(CallPyScript.analisiScript(cp.getMethod())==0){
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
    

