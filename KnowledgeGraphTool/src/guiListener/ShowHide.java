/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JToggleButton;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.openide.util.Lookup;

/**
 *
 * @author Lipari
 */
public class ShowHide extends Observable implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
        JToggleButton jb=(JToggleButton)ae.getSource();
        if(jb.isSelected()){
        GraphModel graphModel=Lookup.getDefault().lookup(GraphController.class).getModel();
        for(Node node: graphModel.getGraphVisible().getNodes().toArray())
            node.getNodeData().getTextData().setVisible(false);
        jb.setText("Show Labels");
        }
        else{
            GraphModel graphModel=Lookup.getDefault().lookup(GraphController.class).getModel();
            for(Node node: graphModel.getGraphVisible().getNodes().toArray())
                node.getNodeData().getTextData().setVisible(true);
            jb.setText("Hide Labels");
        }
         this.setChanged();
         this.notifyObservers();
    }
}