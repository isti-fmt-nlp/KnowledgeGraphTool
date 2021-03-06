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
public class ProjectController{
    private String method="jaccard";
    private String root=null;
    private String path_sub1=null;
    private String path_sub2=null;
    private String path_reqs=null;
    private String path_res=null;
    private String path_oldRes=null;
    private Requirements reqs=new Requirements();
    private boolean analysis=false;
    private static ProjectController cP=null; //riferimento all' istanza
    public final String SUB1="subject_1";
    public final String SUB2="subject_2";
    /**Costruttore 
     
    */
    private ProjectController(){
    }
    public static ProjectController getInstance(){
            if(cP==null)
                        cP = new ProjectController();
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
    public String createProject(String path,String nomeProgetto){
        reqs.clearReq();
        File f;
        f=new File(path+"/"+nomeProgetto);
        f.mkdir();
        root=f.getPath();
        new File(root+File.separator+"1Subject").mkdir();
        new File(root+File.separator+"2Subject").mkdir();
        new File(root+File.separator+"Requirements").mkdir();
        new File(root+File.separator+"Result").mkdir();
        new File(root+File.separator+"Saved Result").mkdir();
        
        KgtXml.creaProjectXML(root,nomeProgetto);
        path_sub1=root+File.separator+"1Subject";
        path_sub2=root+File.separator+"2Subject";
        path_reqs=root+File.separator+"Requirements";
        path_res=root+File.separator+"Result";
        path_oldRes=root+File.separator+"Saved Result";
        analysis=false;

        return root;
    }
    public String openProject(String path){
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
               if(name.equals("1Subject"))
                   path_sub1=root+File.separator+"1Subject";
               if(name.equals("2Subject"))
                   path_sub2=root+File.separator+"2Subject";
               if(name.equals("Result"))
                   path_res=root+File.separator+"Result";
               if(name.equals("Requirements"))
                   path_reqs=root+File.separator+"Requirements";
               if(name.equals("Saved Result"))
                   path_oldRes=root+File.separator+"Saved Result";
           }
        if(exist==true){
            if(path_sub1==null){
               new File(root+File.separator+"1Subject").mkdir();
               path_sub1=root+File.separator+"1Subject";}
            if(path_sub2==null){
                new File(root+File.separator+"2Subject").mkdir();
                path_sub2=root+File.separator+"2Subject";}
            if(path_res==null){
                new File(root+File.separator+"Result").mkdir();
                path_res=root+File.separator+"Result";}
            if(path_reqs==null){
                new File(root+File.separator+"Requirements").mkdir();
                path_reqs=root+File.separator+"Requirements";}
            if(path_oldRes==null){
                new File(root+File.separator+"Saved Result").mkdir();
                path_oldRes=root+File.separator+"Saved Result";}
            if(new File(path_res).listFiles().length>1)
                analysis=true;
            if(new File(path_reqs).listFiles().length!=0){
                reqs.loadReqs(root);
                if(analysis)
                    reqs.loadAnalysis(root);
            }
            return "progetto_aperto";
        }
        else
            closeProject();
            return "progetto_inesistente";
   }
       

    public String getSource() {
        return root;
    }

    public void closeProject() {
        root=null;
        path_sub1=null;
        path_sub2=null;
        path_reqs=null;
        path_res=null;
        path_oldRes=null;
        analysis=false;
        reqs.clearReq();
    }


    public boolean addDocument(String sub,String path){
        try {
             if(sub.equals(SUB1))
                return KgtFile.copiaFile(path,path_sub1);
                
                
            if(sub.equals(SUB2))
                return KgtFile.copiaFile(path,path_sub2);
            
            } catch (IOException ex) {}
              return false;
    }
    public boolean addResult(String path){
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
   public boolean addRequirements(String path){
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
   
   public boolean deleteDocument(String doc,String name){
        boolean ok=false;
        if(name.length()==0)
            return false;
        if(doc.equals(SUB1))
            ok=new File(path_sub1+File.separator+name).delete();
        if(doc.equals(SUB2))
            ok=new File(path_sub2+File.separator+name).delete();
        return ok;
    }
    public void deleteResults(){
    File ris=new File(path_res);
    for(File f: ris.listFiles())
        f.delete();
    reqs.clearVal();
    setAnalysis(false);
    }
    
    public void saveAndDeleteResults(String dirName){
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
         reqs.clearVal();
         setAnalysis(false);
     }
    
     public void saveResults(String dirName){
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
                 if(!f.getName().equals("knowledge_overlap.txt")&& !f.getName().equals("understand.txt")  && f.getName().substring(f.getName().length()-4,f.getName().length()).equals(".txt")){
                     deleteRequirements();
                     KgtFile.copiaFile(f.getAbsolutePath(),path_reqs );
                     reqs.loadReqs(root);
                 }else{
                     if(f.getName().equals("knowledge_overlap.txt")){
                         setAnalysis(true);
                     }
                     KgtFile.copiaFile(f.getAbsolutePath(), path_res);}
             }
             if(analysisCompleted())
                 reqs.loadAnalysis(root);
         }catch (IOException ex) {}
      }
    public boolean deleteRequirements(){
        File req=new File(path_reqs);    
        if(Requirements()){
           for(File f:req.listFiles()){
               f.delete();
           }
           reqs.clearReq();
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
        if(new File(path_sub1).listFiles().length==0)
            return false;
        if(new File(path_sub2).listFiles().length==0)
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
    public String getMethod(){
        return method;
    }
}
