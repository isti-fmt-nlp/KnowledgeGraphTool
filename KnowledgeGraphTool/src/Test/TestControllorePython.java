/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import Controllori.ControlloreProgetto;
import KgtUtility.CallPyScript;
import java.io.File;
import java.io.IOException;
import org.openide.util.Exceptions;
/**
 *
 * @author Lipari
 */
public class TestControllorePython {
    public static void main(String[] args) {
        ControlloreProgetto cP=ControlloreProgetto.getIstance();
        cP.apriProgetto("C:/Users/Lipari/Documents/Prova/Progetto1");
        File f=new File("./src/py/Analisi.py");
        String pathScript="";
        try {
            pathScript = f.getCanonicalPath();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
            System.out.println(pathScript);

        CallPyScript.analisi(pathScript);
    }
}
