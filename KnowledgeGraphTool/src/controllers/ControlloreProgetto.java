package controllers;

import data.Requirement;
import data.Requirements;
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
    private String path_dom1=null;
    private String path_dom2=null;
    private String path_reqs=null;
    private String path_res=null;
    private String path_oldRes=null;
    private Requirements reqs=new Requirements();
    private boolean analysis=false;
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
        reqs.clearReq();
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
        path_dom1=root+File.separator+"Domain1";
        path_dom2=root+File.separator+"Domain2";
        path_reqs=root+File.separator+"Requirements";
        path_res=root+File.separator+"Result";
        path_oldRes=root+File.separator+"Old Result";
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
                   path_dom1=root+File.separator+"Domain1";
               if(name.equals("Domain2"))
                   path_dom2=root+File.separator+"Domain2";
               if(name.equals("Result"))
                   path_res=root+File.separator+"Result";
               if(name.equals("Requirements"))
                   path_reqs=root+File.separator+"Requirements";
               if(name.equals("Old Result"))
                   path_oldRes=root+File.separator+"Old Result";
           }
        if(exist==true){
            if(path_dom1==null){
               new File(root+File.separator+"Domain1").mkdir();
               path_dom1=root+File.separator+"Domain1";}
            if(path_dom2==null){
                new File(root+File.separator+"Domain2").mkdir();
                path_dom2=root+File.separator+"Domain2";}
            if(path_res==null){
                new File(root+File.separator+"Result").mkdir();
                path_res=root+File.separator+"Result";}
            if(path_reqs==null){
                new File(root+File.separator+"Requirements").mkdir();
                path_reqs=root+File.separator+"Requirements";}
            if(path_oldRes==null){
                new File(root+File.separator+"Old Result").mkdir();
                path_oldRes=root+File.separator+"Old Result";}
            if(new File(path_res).listFiles().length!=0)
                analysis=true;
            if(new File(path_reqs).listFiles().length!=0){
                reqs.loadReqs(root);
                if(analysis)
                    reqs.loadAnalysis(root);
            }
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
        path_dom1=null;
        path_dom2=null;
        path_reqs=null;
        path_res=null;
        path_oldRes=null;
        analysis=false;
        reqs.clearReq();
    }


    public boolean aggiungiDocumento(String dom,String path){
        try {
             if(dom.equals(DOM1))
                return KgtFile.copiaFile(path,path_dom1);
                
                
            if(dom.equals(DOM2))
                return KgtFile.copiaFile(path,path_dom2);
            
            } catch (IOException ex) {}
              return false;
    }
    public boolean aggiungiRisultato(String path){
            try {
            return KgtFile.copiaFile(path,path_res);
            } catch (IOException ex) {
            }
            return false;
    }
   public boolean Requirements(){
       if(reqs.getSize()>0)
            return true;
       else
            return false;
   }
   public boolean aggiungiRequisiti(String path){
        File req=new File(path_reqs);
        if(Requirements())
            return false;
        else
            try {
                if(KgtFile.copiaFile(path,req.getPath())){
                    reqs.loadReqs(root);
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
            ok=new File(path_dom1+File.separator+name).delete();
        if(dom.equals(DOM2))
            ok=new File(path_dom2+File.separator+name).delete();
        return ok;
    }
    public void eliminaRisultati(){
    File ris=new File(path_res);
    for(File f: ris.listFiles())
        f.delete();
    setAnalysis(false);
    }
    
    public void salvaCancRisultati(String dirName){
         File ris=new File(path_res);
         File req=new File(path_reqs);
         File oldris=new File(path_oldRes+File.separator+dirName);
         oldris.mkdir();
          try {
         for(File f: ris.listFiles()){
            KgtFile.copiaFile(f.getAbsolutePath(),oldris.getAbsolutePath());
            f.delete();
         }
         KgtFile.copiaFile(req.listFiles()[0].getAbsolutePath(), oldris.getAbsolutePath());
         } catch (IOException ex){}
         setAnalysis(false);
     }
    
     public void salvaRisultati(String dirName){
         File ris=new File(path_res);
         File req=new File(path_reqs);
         File oldris=new File(path_oldRes+File.separator+dirName);
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
         File load=new File(path_oldRes+File.separator+dirName);
         try{
             System.out.println(load.getAbsolutePath());
             for(File f: load.listFiles()){
                 System.out.println(f.getName());
                 if(!f.getName().equals("domain_overlap.txt") && f.getName().substring(f.getName().length()-4,f.getName().length()).equals(".txt")){
                     eliminaRequisito();
                     KgtFile.copiaFile(f.getAbsolutePath(),path_reqs );
                     reqs.loadReqs(root);
                 }else{
                     if(f.getName().equals("domain_overlap.txt")){
                         setAnalysis(true);
                     }
                     KgtFile.copiaFile(f.getAbsolutePath(), path_res);}
             }
             if(analysisCompleted())
                 reqs.loadAnalysis(root);
         }catch (IOException ex) {}
      }
    public boolean eliminaRequisito(){
        File req=new File(path_reqs);    
        if(Requirements()){
           for(File f:req.listFiles()){
               if(f.delete())
                   reqs.clearReq();
           }
        }
        return !Requirements();
    }
    public boolean isOpen(){
        if(root!=null)
            return true;
        else
            return false;
    }
    public boolean isReady(){
        if(new File(path_dom1).listFiles().length==0)
            return false;
        if(new File(path_dom2).listFiles().length==0)
                    return false;
        if(new File(path_reqs).listFiles().length!=1)
                    return false;
        return true;
    }
    public void setAnalysis(boolean t){
        analysis=t;
    }
    public boolean analysisCompleted(){
        return analysis;
    }
    public int getNReqs(){
        return reqs.getSize();
    }
    public Requirements getReqs(){
        return reqs;
    }
    public void clearReqs(){
        reqs.clearReq();
    }

    public void loadReqs(String path) {
        clearReqs();
        reqs.loadReqs(path);
    }

    public void loadAnalysis(String path) {
        reqs.loadAnalysis(path);
        setAnalysis(true);
    }
    public Requirement getReq(int n){
        return reqs.getReq(n);
    }
}
