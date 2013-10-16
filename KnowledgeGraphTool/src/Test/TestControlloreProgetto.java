/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Controllori.ControlloreProgetto;
/**
 *
 * @author Lipari
 */
public class TestControlloreProgetto {
    public static void main(String[] args) {
        ControlloreProgetto cp=new ControlloreProgetto();
        cp.creaProgetto("C:/Users/Lipari/Documents/Prova", "Progetto1");
        System.out.println(cp.apriProgetto(cp.getSource())); 
        cp.chiudiProgetto();
        System.out.println(cp.apriProgetto(cp.getSource()));
        System.out.println(cp.apriProgetto(""));
        System.out.println(cp.apriProgetto("C:/Users/Lipari/Documents/Prova/"));
    }
}
