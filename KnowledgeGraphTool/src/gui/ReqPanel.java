package gui;

import controllers.ProjectController;
import supportGui.KgtTabelModel;
import supportGui.KgtRendererTabelCell;
import javax.swing.JPanel;
import supportGui.ButtonColumn;
import guiListener.OpenGraph;
import guiListener.ShowRequirements;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import java.io.File;
import java.util.Observable;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ReqPanel extends JPanel{
	private KgtTabelModel tm=new KgtTabelModel();
	private JTable table;
	private KgtRendererTabelCell rc=new KgtRendererTabelCell();
	private ShowRequirements vr;
        private ProjectController cp=ProjectController.getInstance();
        private OpenGraph open;

	public ReqPanel() {
		setPreferredSize(new Dimension(300, 400));
		setMinimumSize(new Dimension(300, 400));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
		);
		table = new JTable();
		table.setFillsViewportHeight(true);
                table.setModel(tm);
		tm.addColumn("Requirements");
		tm.addColumn("Knowledge Overlap");
                tm.addColumn("Graph");
		table.getColumn("Requirements").setCellRenderer(rc);
		table.getColumn("Knowledge Overlap").setCellRenderer(rc);
                table.getColumn("Graph").setMaxWidth(50);
                table.setRowHeight(19);
                scrollPane.setViewportView(table);
                vr=new ShowRequirements(cp.getReqs());
                table.getSelectionModel().addListSelectionListener(vr);
		setLayout(groupLayout);
	}
        public void clearRows(){
            int n=tm.getRowCount();
            for(int i=0;i<n;i++){
                tm.removeRow(0);
            }
        }
	public void viewReqs(String path){
                ImageIcon view=null;
                clearRows();
                if(!cp.Requirements()){
                    return;
                }
                open=new OpenGraph(cp.getReqs());
                if(cp.analysisCompleted()){
                    new ButtonColumn(table, (Action) open,2);
                    view=new ImageIcon(new File("src"+File.separator+"icon"+File.separator+"graph2.png").getAbsolutePath());

                }
                else{
                    table.getColumn("Graph").setCellEditor(null);
                    table.getColumn("Graph").setCellRenderer(null);
                }
                String txt;
		float overlap;
                for(int i=0;i<cp.getNReqs();i++){
			txt=cp.getReq(i).getReq();
                        overlap=cp.getReq(i).getVal();
                        if(overlap>=0 && cp.analysisCompleted()&& cp.getReq(i).getPathD1()!=null)
                            tm.addRow(new Object[]{"R"+(i+1)+"-"+txt ,overlap,view});
                        else{
                            if(cp.getReq(i).getVal()==0)
                                tm.addRow(new Object[]{"R"+(i+1)+"-"+txt,0});
                            else
                                tm.addRow(new Object[]{"R"+(i+1)+"-"+txt,overlap});

                        }
		}
	}
	public JTable getTable(){
		return table;
        }
        public Observable getObservable(){
            return vr;
        }
}
