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
    private static BufferedReader input;
    
    static public int analisi(String pathScript){
       String os=System.getProperty("os.name").toLowerCase();
            try
            {
                ControlloreProgetto cp=ControlloreProgetto.getIstance();
                File pathLib=new File("../conceptLinkNetwork");
                String pathRoot=cp.getSource();
                Runtime rt = Runtime.getRuntime();
                Process pr=null;
                if(os.indexOf("win")>=0){
                     System.out.println(os);
                     pr = rt.exec("cmd /c cd ../conceptLinkNetwork & python "+pathScript+" "+pathRoot+" "+pathLib.getCanonicalPath());                
                     System.out.println(("cmd /c cd ../conceptLinkNetwork & python "+pathScript+" "+pathRoot+" "+pathLib.getCanonicalPath()));
                }
                
                 if(os.indexOf("mac")>=0){
                    System.out.println(os);
                    pr = rt.exec("python "+pathScript+" "+pathRoot+" "+pathLib.getCanonicalPath(),null,new File("../conceptLinkNetwork"));
                }
                //Da provare
                if(os.indexOf("nix")>=0){
                    System.out.println(os);
                     pr = rt.exec("python "+pathScript+" "+pathRoot+" "+pathLib.getCanonicalPath(),null,new File("../conceptLinkNetwork"));
                }
              
            /*input=new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line=null;
                while((line=input.readLine()) != null) {
                    System.out.println(line);
                }
            int exitVal = pr.waitFor();
                
            System.out.println("Exited with error code "+exitVal);
            return exitVal;
           */return 0;
            } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
            return 1;
    }
}
