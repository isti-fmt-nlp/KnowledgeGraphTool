/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import controllers.ProjectController;
/**
 *
 * @author Lipari
 */
public class TestControlloreProgetto {
    public static void main(String[] args) {
        ProjectController cp=ProjectController.getInstance();
        cp.createProject("C:/Users/Lipari/Documents/Prova", "Progetto1");
        System.out.println(cp.openProject(cp.getSource())); 
        cp.closeProject();
        System.out.println(cp.openProject(cp.getSource()));
        System.out.println(cp.openProject(""));
        System.out.println(cp.openProject("C:/Users/Lipari/Documents/Prova/Progetto1"));
        String pathF1="C:\\Users\\Lipari\\Documents\\NetBeansProjects\\KnowledgeGraphTool\\build.xml";
        String pathF2="C:\\Users\\Lipari\\Documents\\NetBeansProjects\\KnowledgeGraphTool\\nbproject\\build-impl.xml";
        String pathR1="C:\\Users\\Lipari\\Downloads\\297749RevF.doc";
        String pathR2="C:\\Users\\Lipari\\Downloads\\warc-tools_phase_III_frs_v8.pdf";
        System.out.println("Aggiungo Documento1:"+cp.addDocument("dominio_1", pathF1));
        System.out.println("Aggiungo Documento2:"+cp.addDocument("dominio_2", pathF2));
         System.out.println("Aggiungo DovumentoPathVuoto:"+cp.addDocument("dominio_2", ""));
        System.out.println("Eliminazione Documento1: "+cp.deleteDocument("dominio_1", "build.xml"));
        System.out.println("Eliminazione Documento con path vuoto: "+cp.deleteDocument("dominio_1", ""));
        System.out.println("Aggiungo PrimoReq: "+cp.addRequirements(pathR1));
        System.out.println("Aggiungo SecondoReq:"+cp.addRequirements(pathR2));
        System.out.println("Eliminazione R1: "+cp.deleteRequirements());
        System.out.println("Aggiungo SecondoReq:"+cp.addRequirements(pathR2));
        System.out.println("Aggiungo SecondoReqVuoto:"+cp.addRequirements(""));
        System.out.println("Aggiungo Risultato1:"+cp.addResult(pathR1));
        System.out.println("Aggiungo Risultato2:"+cp.addResult(pathR2));
        System.out.println("Aggiungo RisutltatoVuoto:"+cp.addResult(""));
    }
}
