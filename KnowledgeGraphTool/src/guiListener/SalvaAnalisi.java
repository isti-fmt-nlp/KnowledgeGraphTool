/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import controllers.ControlloreProgetto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Observable;
import javax.swing.JOptionPane;

/**
 *
 * @author Lipari
 */
public class SalvaAnalisi extends Observable implements ActionListener{
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        ControlloreProgetto cp=ControlloreProgetto.getInstance();
        if(cp.analysisCompleted()){
            Calendar c=Calendar.getInstance();
            String nameDir = JOptionPane.showInputDialog(null, "Insert save dir name","Result-"+c.get(Calendar.DATE)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR));
        if(nameDir==null || nameDir.isEmpty())
             JOptionPane.showMessageDialog(null, "Save Fail!");
        else{
           cp.salvaRisultati(nameDir);
           this.setChanged();
           this.notifyObservers();
        }
        
        }
    }
    
}
