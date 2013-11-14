/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kgtUtility;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
                Transformer transformer;
          
                transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
                DOMSource source = new DOMSource(doc);
                File xml=new File(pathProgetto+"/"+".kgtproject.xml");
                StreamResult result = new StreamResult(xml.getPath());
                transformer.transform(source, result);
                String os=System.getProperty("os.name").toLowerCase();
                if(os.indexOf("win")>=0){
                    Path pathhide = FileSystems.getDefault().getPath(xml.getAbsolutePath());
                try {
                    Files.setAttribute(pathhide, "dos:hidden", true);
                } catch (IOException ex) {
                    Logger.getLogger(KgtXml.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                System.out.println("File saved!");
        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(KgtXml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
