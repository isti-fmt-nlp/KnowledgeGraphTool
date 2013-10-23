/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KgtUtility;
import Controllori.ControlloreProgetto;
import java.io.*;
/**
 *
 * @author Lipari
 */
public class CallPyScript {
    
    static public void analisi(String pathScript){
        try
        {
          ControlloreProgetto cp=ControlloreProgetto.getIstance();
          File pathLib=new File("..\\conceptLinkNetwork");
          String pathRoot=cp.getSource();
          Runtime rt = Runtime.getRuntime();
          Process pr = rt.exec("cmd /c python "+pathScript+" "+pathRoot+" "+pathLib.getCanonicalPath());
          BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
          String line=null;
          while((line=input.readLine()) != null) {
                System.out.println(line);
            }
            int exitVal = pr.waitFor();
            System.out.println("Exited with error code "+exitVal);

        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
