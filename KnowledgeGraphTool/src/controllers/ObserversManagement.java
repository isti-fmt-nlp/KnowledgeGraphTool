
package controllers;

import gui.MenuBar;
import gui.ProjectTree;
import gui.ReqBox;
import gui.ReqPanel;
import guiListener.AddDocuments;
import guiListener.AddRequirements;
import guiListener.OpenProject;
import guiListener.StartAnalysis;
import guiListener.CloseProject;
import guiListener.OverlapTreeLeafSelection;
import guiListener.LoadAnalysis;
import supportGui.FileSelectorModel;
import guiListener.NewProject;
import guiListener.RemoveDocuments;
import guiListener.RemoveRequirements;
import supportGui.KgtRendererTabelCell;
import guiListener.ThresholdChange;
import guiListener.ShowRequirements;
import java.awt.Cursor;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author Lipari
 */
public class ObserversManagement implements Observer{
    private JFrame main=null;
    private MenuBar menuBar;
    private ProjectTree projectTree;
    private ReqBox reqBox;
    private ReqPanel reqPanel;
    
    public ObserversManagement(JFrame main,MenuBar menuBar,ProjectTree projectTree,ReqBox reqBox,ReqPanel reqPanel){
        this.main=main;
        this.menuBar=menuBar;
        this.projectTree=projectTree;
        this.reqBox=reqBox;
        this.reqPanel=reqPanel;
    }
    public void addObservables(Observable[] o){
         for(Observable ob:o){
          ob.addObserver(this);
        }
    }
    public void addObservable (Observable o){
         o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object o1) {
        ProjectController cp=ProjectController.getInstance();
        
        if(o.getClass().equals(ShowRequirements.class))
            reqBox.getTextBox().setText((String)o1);
        
        if(o.getClass().equals(OverlapTreeLeafSelection.class)){
           reqBox.getTextBox().setText((String)o1);
        }
        
        if(o.getClass().equals(ThresholdChange.class)){
            JTable t=reqPanel.getTable();
            KgtRendererTabelCell rc=(KgtRendererTabelCell)t.getCellRenderer(0, 0);
            for(int i=0;i<t.getRowCount();i++){
                if(t.getValueAt(i, 1)==0)
                    rc.removeAlert(i);
                else{
                    if((Float)t.getValueAt(i, 1)<=(Float)o1)
                        rc.setAlert(i);
                    else
                        rc.removeAlert(i);
                }
		t.repaint();
            }
        }
        if(o.getClass().equals(OpenProject.class)||o.getClass().equals(NewProject.class)){
            FileSelectorModel fs=new FileSelectorModel(cp.getSource());
            projectTree.getTree().setModel(fs);
            projectTree.enablePopUp(true);
            projectTree.enableAddRequirements(!cp.Requirements());
            menuBar.setMenuItemsEnable(true);
            menuBar.setMenuAddRequirementsEnable(!cp.Requirements());
            menuBar.enableAnalisi(cp.isReady());
            if(cp.analysisCompleted()){
                menuBar.enableThreshold(true);
                menuBar.setThreshold(0);
            }
            menuBar.enableSave(true);
            reqPanel.viewReqs(cp.getSource());
        }
        if(o.getClass().equals(CloseProject.class)){
            projectTree.getTree().setModel(null);
            projectTree.enablePopUp(false);
            reqPanel.clearRows();
            menuBar.setMenuItemsEnable(false);
            menuBar.enableAnalisi(false);
            menuBar.enableThreshold(false);
            menuBar.setThreshold(0);
        }
        if(o.getClass().equals(AddDocuments.class)||o.getClass().equals(RemoveDocuments.class)){
            FileSelectorModel fs=new FileSelectorModel(cp.getSource());
            projectTree.getTree().setModel(fs);
            menuBar.enableAnalisi(cp.isReady());
            menuBar.enableThreshold(cp.analysisCompleted());
        }
       
        if(o.getClass().equals(AddRequirements.class)||o.getClass().equals(RemoveRequirements.class)){
            FileSelectorModel fs=new FileSelectorModel(cp.getSource());
            projectTree.getTree().setModel(fs);
            reqPanel.viewReqs(cp.getSource());
            menuBar.enableAnalisi(cp.isReady());
            menuBar.enableThreshold(cp.analysisCompleted());
            projectTree.enableAddRequirements(!cp.Requirements());
            menuBar.setMenuAddRequirementsEnable(!cp.Requirements());
        }
        
        if(o.getClass().equals(LoadAnalysis.class)){
            FileSelectorModel fs=new FileSelectorModel(cp.getSource());
            projectTree.getTree().setModel(fs);
            menuBar.setMenuItemsEnable(true);
            menuBar.setMenuAddRequirementsEnable(!cp.Requirements());
            projectTree.enableAddRequirements(!cp.Requirements());
            menuBar.enableAnalisi(cp.isReady());
            if(cp.analysisCompleted()){
                menuBar.enableThreshold(true);
            }
            reqPanel.viewReqs(cp.getSource());
        }
        if(o.getClass().equals(StartAnalysis.class)){
            if(o1==null){
                main.setEnabled(true);
                main.setVisible(true);
                menuBar.enableThreshold(cp.analysisCompleted());
                FileSelectorModel fs=new FileSelectorModel(cp.getSource());
                projectTree.getTree().setModel(fs);
                menuBar.enableSave(cp.analysisCompleted());
                reqPanel.viewReqs(cp.getSource());
            }
            else{
                if(o1.equals("fail")){
                    main.setEnabled(true);
                    main.setVisible(true);
                    main.setCursor(null);
                    reqPanel.viewReqs(cp.getSource());
                }
                if(o1.equals("analysis")){
                    main.setEnabled(false);
                    main.setVisible(false);
                    main.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
            }
        }
    }
}