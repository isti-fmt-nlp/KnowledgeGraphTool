/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Observable;
import javax.swing.JOptionPane;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.GraphView;
import org.gephi.graph.api.Node;
import org.openide.util.Lookup;

/**
 *
 * @author Lipari
 */
public class FindNodeName extends Observable implements ActionListener{
    GraphView view=null;
    
    @Override
    public void actionPerformed(ActionEvent ae) {
       GraphModel graphModel=Lookup.getDefault().lookup(GraphController.class).getModel();
       view = graphModel.newView();     //Duplicate main view
        DirectedGraph subGraph = graphModel.getDirectedGraph(view);
        String nodename = JOptionPane.showInputDialog(null, "Insert node name","nodeName");
        for(Node node: subGraph.getGraphModel().getDirectedGraphVisible().getNodes().toArray()){
            if(node.getNodeData().getTextData().getText().equals(nodename)){
                node.getNodeData().getTextData().setVisible(true);
            }else
                node.getNodeData().getTextData().setVisible(false);

        }
    }
}