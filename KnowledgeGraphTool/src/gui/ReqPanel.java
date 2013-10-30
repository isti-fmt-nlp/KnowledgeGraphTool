package gui;

import guiComponents.ModelloTabella;
import guiComponents.RendererCelleTabella;

import java.util.ArrayList;

import javax.swing.JPanel;

import data.Requirements;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReqPanel extends JPanel{
	private Requirements reqs;
	private ModelloTabella tm=new ModelloTabella();
	private JTable table;
	private RendererCelleTabella rc=new RendererCelleTabella();
	
	public ReqPanel() {
		setPreferredSize(new Dimension(300, 400));
		setMinimumSize(new Dimension(300, 400));
		reqs=new Requirements();
		
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
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
	}
	public void loadReqs(String path){
		table.setModel(tm);
		tm.addColumn("requisito");
		tm.addColumn("jaccard");
		reqs.loadReqs(path);
		table.getColumnModel().getColumn(0).setCellRenderer(rc);
		table.getColumnModel().getColumn(1).setCellRenderer(rc);
		String txt;
		Double jac;
		
		for(int i=0;i<reqs.getSize();i++){
			txt=reqs.getReq(i).getReq();
			jac=reqs.getReq(i).getVal();
			tm.addRow(new Object[]{"R"+(i+1)+"-"+txt ,jac});
		}
	}
	public Requirements getRequirements(){
		return reqs;
	}
	public JTable getTable(){
		return table;
	}
}
