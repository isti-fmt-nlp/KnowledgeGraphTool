/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import KgtUtility.CallScript;
import java.io.File;
/**
 *
 * @author Lipari
 */
public class TestControllorePython {
    public static void main(String[] args) {
        File f=new File("src");
        String pathScript=f.getAbsolutePath()+"\\py\\Analisi.py";
        CallScript.analisi(pathScript);
        
    }
}
