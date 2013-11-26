package gui;

import controllers.ControlloreProgetto;
import supportGui.ModelloTabella;
import supportGui.RendererCelleTabella;
import javax.swing.JPanel;
import supportGui.ButtonColumn;
import guiListener.OpenGraph;
import guiListener.VisualizzaRequisito;
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
	private ModelloTabella tm=new ModelloTabella();
	private JTable table;
	private RendererCelleTabella rc=new RendererCelleTabella();
	private VisualizzaRequisito vr;
        private ControlloreProgetto cp=ControlloreProgetto.getInstance();
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
		tm.addColumn("Domain Overlap");
                tm.addColumn("Graph");
		table.getColumn("Requirements").setCellRenderer(rc);
		table.getColumn("Domain Overlap").setCellRenderer(rc);
                table.getColumn("Graph").setMaxWidth(50);
                table.setRowHeight(19);
                scrollPane.setViewportView(table);
                vr=new VisualizzaRequisito(cp.getReqs());
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
                clearRows();
                if(!cp.Requirements()){
                    return;
                }
                open=new OpenGraph(cp.getReqs());
                if(cp.analysisCompleted()){
                    new ButtonColumn(table, (Action) open,2);
                }
                else{
                    table.getColumn("Graph").setCellEditor(null);
                    table.getColumn("Graph").setCellRenderer(null);
                }
                String txt;
		float overlap;
                ImageIcon view=new ImageIcon(new File("src"+File.separator+"icon"+File.separator+"graph2.png").getAbsolutePath());
                for(int i=0;i<cp.getNReqs();i++){
			txt=cp.getReq(i).getReq();
                        overlap=cp.getReq(i).getVal();
                        if(overlap>=0)
                            tm.addRow(new Object[]{"R"+(i+1)+"-"+txt ,overlap,view});
                        else{
                            tm.addRow(new Object[]{"R"+(i+1)+"-"+txt,0});
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
