package Controllori;

import KgtUtility.KgtXml;
import KgtUtility.KgtFile;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openide.util.Exceptions;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**Classe che contiene le funzioni per la gestione
 * di un Progetto
 *
 * @author Lipari
 */
public class ControlloreProgetto {
    private String root=null;
    private String dom1=null;
    private String dom2=null;
    private String reqs=null;
    private String res=null;
    private static ControlloreProgetto cP=null; //riferimento all' istanza
    public final String DOM1="dominio_1";
    public final String DOM2="dominio_2";
    /**Costruttore 
     
    */
    private ControlloreProgetto(){
    }
    public static ControlloreProgetto getIstance(){
            if(cP==null)
                        cP = new ControlloreProgetto();
             return cP;
        }
   
    /**
     * Crea un nuovo progetto all'interno della directory passata come argomento
     * 
     * @param path Path directory nella quale si vuole creare il progetto
     * @param nomeProgetto nome da assegnare al progetto
     * @return root Directory Root del progetto, null se non è riuscito a creare
     * il progetto
     */
    public String creaProgetto(String path,String nomeProgetto){
        File f;
        f=new File(path+"/"+nomeProgetto);
        f.mkdir();
        root=f.getPath();
        new File(root+File.separator+"Dominio1").mkdir();
        new File(root+File.separator+"Dominio2").mkdir();
        new File(root+File.separator+"Requisiti").mkdir();
        new File(root+File.separator+"Risultati").mkdir();
        KgtXml.creaProjectXML(root,nomeProgetto);
        dom1=root+File.separator+"Dominio1";
        dom2=root+File.separator+"Dominio2";
        reqs=root+File.separator+"Requisiti";
        res=root+File.separator+"Risultati";

        return root;
    }
    public String apriProgetto(String path){
        if(root!=null)
            return "progetto_esistente";
        if(path==null)
            return "path_non_valido";
        File f=new File(path);
        String nomeProgetto=f.getName();
        boolean exist=false;
        if(f.isDirectory())
           for(File p: f.listFiles()){
               if(p.getName().equals(".kgtproject.xml")){
                    try{
                      exist=true;
                      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();// root elements
                      Document doc = docBuilder.parse(p.getPath());
                      doc.normalizeDocument();
                      NodeList nodeLst = doc.getElementsByTagName("nome");
                      if(!(nodeLst.item(0).getTextContent()).equals(nomeProgetto))
                          return "progetto_inesistente";
                      nodeLst = doc.getElementsByTagName("root");
                     if((nodeLst.item(0).getTextContent()).equals((f.getPath()))) {
                        } else {return "progetto_inesistente";}
                    }catch(Exception pce){
                            return pce.getMessage();}
                }
            }
        if(exist==true){
            root=path;
            dom1=root+File.separator+"Dominio1";
            dom2=root+File.separator+"Dominio2";
            reqs=root+File.separator+"Requisiti";
            res=root+File.separator+"Risultati";

            return "progetto_aperto";}
        else
            return "progetto_inesistente";
   }
       

    public String getSource() {
        return root;
    }

    public void chiudiProgetto() {
        root=null;
    }


    public boolean aggiungiDocumento(String dom,String path){
        try {
             if(dom.equals(DOM1))
                return KgtFile.copiaFile(path,dom1);

            if(dom.equals(DOM2))
                return KgtFile.copiaFile(path,dom2);
            
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);}
        return false;
    }
    public boolean aggiungiRisultato(String path){
            try {
            return KgtFile.copiaFile(path,res);
            } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
            }
            return false;
    }
   public boolean aggiungiRequisiti(String path){
        File req=new File(reqs);
        System.out.println(req.getPath());
        if(req.listFiles().length!=0)
            return false;
        else
            try {
            return KgtFile.copiaFile(path,req.getPath());
                } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
            }
        return false;
    } public boolean eliminaDocumento(String dom,String name){
        boolean ok=false;
        if(name.length()==0)
            return false;
        if(dom.equals(DOM1))
            ok=new File(dom1+File.separator+name).delete();
        if(dom.equals(DOM2))
            ok=new File(dom2+File.separator+name).delete();
        return ok;
    }
    public boolean eliminaRisultato(String name){
    File ris=new File(res);
    for(File f: ris.listFiles())
        if(f.getName().equals(name))
               return f.delete();
    return false;
    }
    public boolean eliminaRequisito(){
        File req=new File(reqs);    
        return req.listFiles()[0].delete();
    }
    public boolean isOpen(){
        if(root!=null)
            return true;
        else
            return false;
    }
    public boolean isReady(){
        if(new File(dom1).listFiles().length==0)
            return false;
        if(new File(dom2).listFiles().length==0)
                    return false;
        if(new File(reqs).listFiles().length!=1)
                    return false;
        return true;
    }
}
