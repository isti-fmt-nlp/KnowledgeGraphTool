/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiComponents;

import data.Requirements;
import java.util.Observable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Peppe
 */
public class VisualizzaRequisito extends Observable implements ListSelectionListener{
    Requirements r;
    public VisualizzaRequisito(Requirements r){
        this.r=r;
    }
    @Override
    public void valueChanged(ListSelectionEvent lse) {
        ListSelectionModel lsm = (ListSelectionModel)lse.getSource();
        String req="";
            if(!lse.getValueIsAdjusting()){
                // Find out which indexes are selected.
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                    req=r.getReq(i).getReq();
                    }
                } 
            }
         this.setChanged();
         this.notifyObservers(req);
        }
    
}
