/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import supportGui.FileNode;

/**
 *
 * @author Lipari
 */
public class JaccardSelection extends Observable implements TreeSelectionListener{
    private JTree tree;
    public JaccardSelection(JTree tree){
        this.tree=tree;
    }

    @Override
    public void valueChanged(TreeSelectionEvent tse) {
        String jacc="";
        FileNode node = (FileNode)tree.getLastSelectedPathComponent();
            if (node == null)
            //Nothing is selected.  
            return;

        if (tree.getModel().isLeaf(node)) {
            if(node.getName().equals("jaccard.txt")){
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader(node));
                    String line = in.readLine();
                    while(line != null){
                     jacc+=line+"\n";
                    line = in.readLine();
                    }
               } catch (FileNotFoundException ex) {
                    Logger.getLogger(JaccardSelection.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(JaccardSelection.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(JaccardSelection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                this.setChanged();
                this.notifyObservers(jacc);
        }
        }
    }
}
