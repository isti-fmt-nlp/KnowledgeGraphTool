/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import controllers.ControlloreProgetto;
/**
 *
 * @author Lipari
 */
public class TestControlloreProgetto {
    public static void main(String[] args) {
        ControlloreProgetto cp=ControlloreProgetto.getIstance();
        cp.creaProgetto("C:/Users/Lipari/Documents/Prova", "Progetto1");
        System.out.println(cp.apriProgetto(cp.getSource())); 
        cp.chiudiProgetto();
        System.out.println(cp.apriProgetto(cp.getSource()));
        System.out.println(cp.apriProgetto(""));
        System.out.println(cp.apriProgetto("C:/Users/Lipari/Documents/Prova/Progetto1"));
        String pathF1="C:\\Users\\Lipari\\Documents\\NetBeansProjects\\KnowledgeGraphTool\\build.xml";
        String pathF2="C:\\Users\\Lipari\\Documents\\NetBeansProjects\\KnowledgeGraphTool\\nbproject\\build-impl.xml";
        String pathR1="C:\\Users\\Lipari\\Downloads\\297749RevF.doc";
        String pathR2="C:\\Users\\Lipari\\Downloads\\warc-tools_phase_III_frs_v8.pdf";
        System.out.println("Aggiungo Documento1:"+cp.aggiungiDocumento("dominio_1", pathF1));
        System.out.println("Aggiungo Documento2:"+cp.aggiungiDocumento("dominio_2", pathF2));
         System.out.println("Aggiungo DovumentoPathVuoto:"+cp.aggiungiDocumento("dominio_2", ""));
        System.out.println("Eliminazione Documento1: "+cp.eliminaDocumento("dominio_1", "build.xml"));
        System.out.println("Eliminazione Documento con path vuoto: "+cp.eliminaDocumento("dominio_1", ""));
        System.out.println("Aggiungo PrimoReq: "+cp.aggiungiRequisiti(pathR1));
        System.out.println("Aggiungo SecondoReq:"+cp.aggiungiRequisiti(pathR2));
        System.out.println("Eliminazione R1: "+cp.eliminaRequisito());
        System.out.println("Aggiungo SecondoReq:"+cp.aggiungiRequisiti(pathR2));
        System.out.println("Aggiungo SecondoReqVuoto:"+cp.aggiungiRequisiti(""));
        System.out.println("Aggiungo Risultato1:"+cp.aggiungiRisultato(pathR1));
        System.out.println("Aggiungo Risultato2:"+cp.aggiungiRisultato(pathR2));
        System.out.println("Aggiungo RisutltatoVuoto:"+cp.aggiungiRisultato(""));
    }
}
