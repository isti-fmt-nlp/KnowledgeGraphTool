/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kgtUtility;

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
import org.openide.util.Exceptions;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Lipari
 */
public class KgtXml {
     /**
     * Crea un file xml idenficativo del progetto
     * @param pathProgetto directory dove verrà salvato l'xml
     */
    public static void creaProjectXML(String pathProgetto,String nomeProgetto){
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
		path.setTextContent(pathProgetto);
                data.appendChild(path);
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
		DOMSource source = new DOMSource(doc);
                File xml=new File(pathProgetto+"/"+".kgtproject.xml");
		StreamResult result = new StreamResult(xml.getPath());
                transformer.transform(source, result);
                String os=System.getProperty("os.name").toLowerCase();
                if(os.indexOf("win")>=0){
                try {
                    Runtime.getRuntime().exec("attrib +H " + xml.getAbsolutePath());
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex); }
                }
		System.out.println("File saved!");
                
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}
