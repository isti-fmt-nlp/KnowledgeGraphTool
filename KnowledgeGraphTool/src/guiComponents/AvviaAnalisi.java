/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiComponents;

import Controllori.ControlloreProgetto;
import KgtUtility.CallPyScript;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
        ControlloreProgetto cP=ControlloreProgetto.getIstance();
        File f=new File("./src/py/Analisi.py");
        String pathScript="";
        try {
            pathScript = f.getCanonicalPath();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
            System.out.println(pathScript);

        if(CallPyScript.analisi(pathScript)==0){
            this.setChanged();
            this.notifyObservers();
        }
        else
            JOptionPane.showMessageDialog(null, "Analisi Fallita");
        
    }
}
    

