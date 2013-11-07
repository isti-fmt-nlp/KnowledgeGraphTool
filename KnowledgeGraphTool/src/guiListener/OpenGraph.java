/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import data.Requirements;
import graphView.GraphWindow;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;


/**
 *
 * @author Lipari
 */
public class OpenGraph implements Action{
    Requirements reqs=null;
    public OpenGraph(Requirements reqs){
        this.reqs=reqs;
    }
    @Override
    public Object getValue(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putValue(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEnabled(boolean bln) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnabled() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
            int row=Integer.parseInt(ae.getActionCommand());
            System.out.println(reqs.getSize());
            String path=reqs.getReq(row).getPathD1();
            System.out.println(path);
            new Thread(new GraphWindow(path)).start();
            path=reqs.getReq(row).getPathD2();
            new Thread(new GraphWindow(path)).start();
    }
    
}
