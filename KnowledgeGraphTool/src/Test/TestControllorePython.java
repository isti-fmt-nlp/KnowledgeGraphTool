/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import KgtUtility.CallScript;
import java.io.File;
import java.io.IOException;
import org.openide.util.Exceptions;
/**
 *
 * @author Lipari
 */
public class TestControllorePython {
    public static void main(String[] args) {
        File f=new File(".\\src\\py\\Analisi.py");
        String pathScript="";
        try {
            pathScript = f.getCanonicalPath();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        CallScript.analisi(pathScript);
        
    }
}
