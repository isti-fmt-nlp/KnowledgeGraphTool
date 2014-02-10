/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import data.Requirements;
import java.util.Observable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Peppe
 */
public class ShowRequirements extends Observable implements ListSelectionListener{
    Requirements r;
    public ShowRequirements(Requirements r){
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
                    if (lsm.isSelectedIndex(i)&& r.getSize()!=0) {
                        req=r.getReq(i).getReq();
                    }
                    if(r.getSize()==0)
                        req="";
                } 
            }
         this.setChanged();
         this.notifyObservers(req);
        }
    
}
