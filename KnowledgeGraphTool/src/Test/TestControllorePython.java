/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import controllers.ControlloreProgetto;
import kgtUtility.CallPyScript;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author Lipari
 */
public class TestControllorePython {
    public static void main(String[] args) {
        ControlloreProgetto cP=ControlloreProgetto.getInstance();
        cP.apriProgetto("C:/Users/Lipari/Documents/Prova/Progetto1");
        File f=new File("./src/py/Analisi.py");
        String pathScript="";
        try {
            pathScript = f.getCanonicalPath();
        } catch (IOException ex) {}
            System.out.println(pathScript);

        CallPyScript.analisi(pathScript);
    }
}
