package controllers;

import kgtUtility.KgtXml;
import kgtUtility.KgtFile;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**Classe che contiene le funzioni per la gestione
 * di un Progetto
 *
 * @author Lipari
 */
public class ControlloreProgetto{
    private String root=null;
    private String dom1=null;
    private String dom2=null;
    private String reqs=null;
    private String res=null;
    private String oldRes=null;
    private boolean analysis=false;
    private boolean requirements=false;
    private int nreq=0;
    private static ControlloreProgetto cP=null; //riferimento all' istanza
    public final String DOM1="dominio_1";
    public final String DOM2="dominio_2";
    /**Costruttore 
     
    */
    private ControlloreProgetto(){
    }
    public static ControlloreProgetto getInstance(){
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
        new File(root+File.separator+"Domain1").mkdir();
        new File(root+File.separator+"Domain2").mkdir();
        new File(root+File.separator+"Requirements").mkdir();
        new File(root+File.separator+"Result").mkdir();
        new File(root+File.separator+"Old Result").mkdir();
        
        KgtXml.creaProjectXML(root,nomeProgetto);
        dom1=root+File.separator+"Domain1";
        dom2=root+File.separator+"Domain2";
        reqs=root+File.separator+"Requirements";
        res=root+File.separator+"Result";
        oldRes=root+File.separator+"Old Result";
        analysis=false;

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
               String name=p.getName();
               if(name.equals(".kgtproject.xml")){
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
               root=path;
               if(name.equals("Domain1"))
                   dom1=root+File.separator+"Domain1";
               if(name.equals("Domain2"))
                   dom2=root+File.separator+"Domain2";
               if(name.equals("Result"))
                   res=root+File.separator+"Result";
               if(name.equals("Requirements"))
                   reqs=root+File.separator+"Requirements";
               if(name.equals("Old Result"))
                   oldRes=root+File.separator+"Old Result";
           }
        if(exist==true){
            if(dom1==null){
               new File(root+File.separator+"Domain1").mkdir();
               dom1=root+File.separator+"Domain1";}
            if(dom2==null){
                new File(root+File.separator+"Domain2").mkdir();
                dom2=root+File.separator+"Domain2";}
            if(res==null){
                new File(root+File.separator+"Result").mkdir();
                res=root+File.separator+"Result";}
            if(reqs==null){
                new File(root+File.separator+"Requirements").mkdir();
                reqs=root+File.separator+"Requirements";}
            if(oldRes==null){
                new File(root+File.separator+"Old Result").mkdir();
                oldRes=root+File.separator+"Old Result";}
            if(new File(res).listFiles().length!=0)
                analysis=true;
            if(new File(reqs).listFiles().length!=0)
                requirements=true;
            return "progetto_aperto";
        }
        else
            chiudiProgetto();
            return "progetto_inesistente";
   }
       

    public String getSource() {
        return root;
    }

    public void chiudiProgetto() {
        root=null;
        dom1=null;
        dom2=null;
        reqs=null;
        res=null;
        oldRes=null;
        analysis=false;
        requirements=false;
    }


    public boolean aggiungiDocumento(String dom,String path){
        try {
             if(dom.equals(DOM1))
                return KgtFile.copiaFile(path,dom1);
                
                
            if(dom.equals(DOM2))
                return KgtFile.copiaFile(path,dom2);
            
            } catch (IOException ex) {}
              return false;
    }
    public boolean aggiungiRisultato(String path){
            try {
            return KgtFile.copiaFile(path,res);
            } catch (IOException ex) {
            }
            return false;
    }
   public boolean Requirements(){
     return requirements;
   }
   public boolean aggiungiRequisiti(String path){
        File req=new File(reqs);
        if(Requirements())
            return false;
        else
            try {
                if(KgtFile.copiaFile(path,req.getPath())){
                    requirements=true;
                    return true;
                }
            } catch (IOException ex) {
        }
        return false;
    } 
   
   public boolean eliminaDocumento(String dom,String name){
        boolean ok=false;
        if(name.length()==0)
            return false;
        if(dom.equals(DOM1))
            ok=new File(dom1+File.separator+name).delete();
        if(dom.equals(DOM2))
            ok=new File(dom2+File.separator+name).delete();
        return ok;
    }
    public void eliminaRisultati(){
    File ris=new File(res);
    for(File f: ris.listFiles())
        f.delete();
    }
    
    public void salvaCancRisultati(String dirName){
         File ris=new File(res);
         File oldris=new File(oldRes+File.separator+dirName);
         oldris.mkdir();
         for(File f: ris.listFiles()){
            try {
                KgtFile.copiaFile(f.getAbsolutePath(),oldris.getAbsolutePath());
                f.delete();
            } catch (IOException ex) {
            }
         }
    }
     public void salvaRisultati(String dirName){
         File ris=new File(res);
         File req=new File(reqs);
         File oldris=new File(oldRes+File.separator+dirName);
         oldris.mkdir();
         try {
            for(File f: ris.listFiles()){
                KgtFile.copiaFile(f.getAbsolutePath(),oldris.getAbsolutePath());
            }
            KgtFile.copiaFile(req.listFiles()[0].getAbsolutePath(), oldris.getAbsolutePath());
         } catch (IOException ex) {
            }
    }
     public void loadResult(String dirName){
         File load=new File(oldRes+File.separator+dirName);
         try{
             System.out.println(load.getAbsolutePath());
             for(File f: load.listFiles()){
                 System.out.println(f.getName());
                 if(!f.getName().equals("jaccard.txt") && f.getName().substring(f.getName().length()-4,f.getName().length()).equals(".txt")){
                     eliminaRequisito();
                     KgtFile.copiaFile(f.getAbsolutePath(),reqs );
                     requirements=true;
                 }else{
                     if(f.getName().equals("jaccard.txt"))
                         setAnalysis(true);
                     KgtFile.copiaFile(f.getAbsolutePath(), res);}
             }
         }catch (IOException ex) {}
      }
    public boolean eliminaRequisito(){
        File req=new File(reqs);    
        if(Requirements()){
           for(File f:req.listFiles()){
               System.out.println(f.getName());
               requirements=!f.delete();
           }
           return !requirements;
           
        }
        return false;
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
    public void setAnalysis(boolean t){
        analysis=t;
    }
    public boolean AnalysisCompleted(){
        return analysis;
    }
    public void setNReqs(int n){
        nreq=n;
    }
    public int getNReqs(){
        return nreq;
    }
}
