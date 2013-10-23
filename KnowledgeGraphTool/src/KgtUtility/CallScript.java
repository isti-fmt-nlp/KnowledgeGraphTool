/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KgtUtility;
import java.io.*;
/**
 *
 * @author Lipari
 */
public class CallScript {
    
    static public void analisi(String pathScript) throws InterruptedException{
        try
        {
          File pathLib=new File("..\\conceptLinkNetwork");
          String pathRoot="C:\\Users\\Lipari\\Documents\\Prova\\Progetto1";
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
