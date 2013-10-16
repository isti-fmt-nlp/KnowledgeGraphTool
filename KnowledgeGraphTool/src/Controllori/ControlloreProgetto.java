package Controllori;

import java.io.File;

/**Classe che contiene le funzioni per la gestione
 * dei Progetti
 *
 * @author Lipari
 */
public class ControlloreProgetto {
    /**
     * Crea un nuovo progetto all'interno della directory passata come argomento
     * 
     * @param path Path directory nella quale si vuole creare il progetto
     * @param nomeProgetto nome da assegnare al progetto
     * @return root Directory Root del progetto, null se non è riuscito a creare
     * il progetto
     */
    public String creaProgetto(String path,String nomeProgetto){
        String root;
        boolean success = (new File(path+"/"+nomeProgetto)).mkdir();
        if (!success)
            return null;
        root=path+"/"+nomeProgetto;
        success = (new File(root+"/conf")).mkdir();
        if (!success)
            
            return null;
        return root;
    }
}
