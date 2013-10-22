/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KgtUtility;
import java.awt.Desktop;
import java.io.*;
/**
 *
 * @author Lipari
 */
public class CallScript {
    
    static public void analisi(String pathScript){
    try
      {
         Desktop.getDesktop().open(new File(pathScript));
      }
      catch(IOException e)
      {
         System.err.println("Error on exec() method");
         e.printStackTrace();
      }
    }
}
