/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package KgtUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Lipari
 */
public class KgtFile {
    
    public KgtFile(){
    }
    public static boolean copiaFile(String origine, String destinazione) throws IOException{
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File orig = new File(origine);
        File dest = new File(destinazione);
        //controllo che dest sia una cartella altrimenti chiudo
        if (dest.isFile())
        {
            return false;
        }
        if (orig.isFile()){ 
          dest=new File(destinazione+"\\"+orig.getName());
           int letti = 0;
           long tot = 0;
          // inizializzo uno buffer di 4Kb
            byte[] buffer = new byte[4096];
            // inizializzo stream per la copia del file
            try {
              fis = new FileInputStream(orig);
              fos = new FileOutputStream(dest);
              } catch (FileNotFoundException e) {
              return false;
           }
           System.out.println(orig+" , "+orig.length()+" byte ");
          while((letti = fis.read(buffer)) != -1){
                fos.write(buffer, 0, letti);
                tot += letti;
           }
         System.out.println("**File copiato. "+tot+"/"+orig.length()+" byte");
          // chiudo il file d´origine
          fis.close();
          //chiudo il file di destinazione
          fos.close();
          return true;
        }
        else{return false;}
    }
}
