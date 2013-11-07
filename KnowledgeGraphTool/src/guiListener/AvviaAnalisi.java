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
import org.openide.util.Exceptions;

/**
 *
 * @author Peppe
 */
public class AvviaAnalisi extends Observable implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.setChanged();
        this.notifyObservers("analysis");
        ControlloreProgetto cp=ControlloreProgetto.getIstance();
        File f=new File("./src/py/Analisi.py");
        String pathScript="";
        try {
            pathScript = f.getCanonicalPath();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        if(ControlloreProgetto.getIstance().AnalysisCompleted()){
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
            cp.setAnalysis(false);
        if(CallPyScript.analisi(pathScript)==0){
            cp.setAnalysis(true);
            this.setChanged();
            this.notifyObservers();
        }
        else
            JOptionPane.showMessageDialog(null, "Analysis Fail");
            this.setChanged();
            this.notifyObservers("fail");
     }
}
    

