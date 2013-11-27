/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import java.io.BufferedReader;
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
public class OverlapTreeLeafSelection extends Observable implements TreeSelectionListener{
    private JTree tree;
    public OverlapTreeLeafSelection(JTree tree){
        this.tree=tree;
    }

    @Override
    public void valueChanged(TreeSelectionEvent tse) {
        String overlap="";
        FileNode node = (FileNode)tree.getLastSelectedPathComponent();
            if (node == null)
            //Nothing is selected.  
            return;

        if (tree.getModel().isLeaf(node)) {
            if(node.getName().equals("domain_overlap.txt")){
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader(node));
                    String line = in.readLine();
                    while(line != null){
                     overlap+=line+"\n";
                    line = in.readLine();
                    }
               } catch (FileNotFoundException ex) {
                    Logger.getLogger(OverlapTreeLeafSelection.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(OverlapTreeLeafSelection.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(OverlapTreeLeafSelection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                this.setChanged();
                this.notifyObservers(overlap);
        }
        }
    }
}
