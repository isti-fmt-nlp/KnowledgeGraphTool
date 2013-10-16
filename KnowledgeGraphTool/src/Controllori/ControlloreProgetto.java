package Controllori;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**Classe che contiene le funzioni per la gestione
 * dei Progetti
 *
 * @author Lipari
 */
public class ControlloreProgetto {
    private String root=null;
    /**Costruttore 
     
    */
    public ControlloreProgetto(){
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
        new File(root+"/conf").mkdir();
        new File(root+"/Dominio1").mkdir();
        new File(root+"/Dominio2").mkdir();
        new File(root+"/Requisiti").mkdir();
        new File(root+"/Risultati").mkdir();
        creaXML(root,nomeProgetto);
       
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
                    }catch(ParserConfigurationException | SAXException | IOException pce){
                            return pce.getMessage();}
                }
            }
        if(exist==true){
            root=path;
            return "progetto_aperto";}
        else
            return "progetto_inesistente";
   }
    /**
     * Crea un file xml idenficativo del progetto
     * @param pathProgetto directory dove verrà salvato l'xml
     */
    private void creaXML(String pathProgetto,String nomeProgetto){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
	Document doc = docBuilder.newDocument();
	Element rootElement = doc.createElement("project");
		doc.appendChild(rootElement);
                // data elements
		Element data = doc.createElement("data");
		rootElement.appendChild(data);
 
		// nome elements
		Element nome = doc.createElement("nome");
		data.appendChild(nome);
                nome.setTextContent(nomeProgetto);
                
                // root elements
		Element path = doc.createElement("root");
		path.setTextContent(root);
                data.appendChild(path);
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(pathProgetto+"/"+".kgtproject.xml").getPath());
                transformer.transform(source, result);
 
		System.out.println("File saved!");
                
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}    

    public String getSource() {
        return root;
    }

    public void chiudiProgetto() {
        root=null;
    }
    //TODO
    public void aggiungiConf(){}
    //TODO
    public void aggiungiDocumento(String dom,String path){}
    //TODO
    public void aggiungiRisultato(String path){}
    //TODO
    public void aggiungiRequisito(){}
    //TODO
    public void eliminaConf(){}
    //TODO
    public void eliminaDocumento(String dom,String name){}
    //TODO
    public void eliminaRisultato(String name){}
    //TODO
    public void eliminaRequisito(){}
}

