/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kgtUtility;
import controllers.ProjectController;
import java.io.*;
/**
 *
 * @author Lipari
 */
public class CallPyScript {
    private static BufferedReader input;
    
    static public int analisiScript(String method,String priority){
        File f=new File("./src/py/Analisi.py");
        String pathScript="";
        try {
            pathScript = f.getCanonicalPath();
        } catch (IOException ex) { }
        String os=System.getProperty("os.name").toLowerCase();
        try{
            ProjectController cp=ProjectController.getInstance();
            File pathLib=new File("../conceptLinkNetwork");
            String pathRoot=cp.getSource();
            System.out.println(pathRoot);
            Runtime rt = Runtime.getRuntime();
            Process pr=null;
            if(os.indexOf("win")>=0){
                System.out.println(os);
                pr = rt.exec("cmd /c cd ../conceptLinkNetwork & python "+pathScript+" \""+pathRoot+"\" "+pathLib.getCanonicalPath()+" "+method+" "+priority);                
                System.out.println(("cmd /c cd ../conceptLinkNetwork/ & python "+pathScript+" "+pathRoot+" "+pathLib.getCanonicalPath()+" "+method+" "+priority));
            }
                
                 if(os.indexOf("mac")>=0){
                    System.out.println(os);
                    pr = rt.exec("python "+pathScript+" \""+pathRoot+"\" "+pathLib.getCanonicalPath()+" "+method+" "+priority,null,new File("../conceptLinkNetwork"));
                }
                //Da provare
                if(os.indexOf("nix")>=0){
                    System.out.println(os);
                     pr = rt.exec("python "+pathScript+" \""+pathRoot+"\" "+pathLib.getCanonicalPath()+" "+method+" "+priority,null,new File("../conceptLinkNetwork"));
                }
              
           input=new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line=null;
                while((line=input.readLine()) != null) {
                    System.out.println(line);
                }
            int exitVal = pr.waitFor();
                
            System.out.println("Exited with error code "+exitVal);
            return exitVal;
            } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
            return 1;
    }
}
