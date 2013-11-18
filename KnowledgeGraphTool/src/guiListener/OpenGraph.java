/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiListener;

import data.Requirements;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.swing.Action;
import java.io.File;



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
            Runtime rt = Runtime.getRuntime();
            Runtime rt2 = Runtime.getRuntime();
            Process pr=null;
            Process pr2=null;
            String path=reqs.getReq(row).getPathD1();
            File GraphWindow=new File("lib"+File.separator+"GraphWindow.jar");
            System.out.println(GraphWindow.getAbsolutePath());
        try {
            pr = rt.exec("java -jar "+GraphWindow.getAbsolutePath()+" \""+path+"\"");
        } catch (IOException ex) {}
        path=reqs.getReq(row).getPathD2();
        try {
            pr2 = rt2.exec("java -jar "+GraphWindow.getAbsolutePath()+" \""+path+"\"");
        } catch (IOException ex) {}
        }
    
}
