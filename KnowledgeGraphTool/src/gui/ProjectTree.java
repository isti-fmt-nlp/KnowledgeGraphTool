package gui;

import guiListener.AggiungiDominio;
import guiListener.AggiungiRequisiti;
import guiListener.OverlapTreeLeafSelection;
import guiListener.RemoveDominio;
import guiListener.RemoveRequirements;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultTreeCellRenderer;

public class ProjectTree extends JPanel{
	private JTree tree = null;
        private AggiungiDominio addDom1=new AggiungiDominio("dominio_1");
        private AggiungiDominio addDom2=new AggiungiDominio("dominio_2");
        private AggiungiRequisiti addReq=new AggiungiRequisiti();
        private RemoveDominio remDom1=new RemoveDominio("dominio_1");
        private RemoveDominio remDom2=new RemoveDominio("dominio_2");
        private RemoveRequirements remReq=new RemoveRequirements();
        private OverlapTreeLeafSelection js;
        private JPopupMenu popupMenu;
        JMenuItem mntmAddFileDomain;
        JMenuItem mntmAddFileDomain_1;
        JMenuItem mntmAddFileRequirments;
        JMenuItem mntmRemoveFileDomain;
        JMenuItem mntmRemoveFileDomain_1;
        JMenuItem mntmRemoveRequirements;
	public ProjectTree() {
		
		JScrollPane scrollPane = new JScrollPane();
                tree=new JTree();
                tree.setRootVisible(true);
		tree.setModel(null);
                
                scrollPane.setViewportView(tree);
                
                popupMenu = new JPopupMenu();
                addPopup(tree, popupMenu);
                
                mntmAddFileDomain = new JMenuItem("Add Domain1 file");
                popupMenu.add(mntmAddFileDomain);
                mntmAddFileDomain.addActionListener(addDom1);
                
                mntmAddFileDomain_1 = new JMenuItem("Add Domain2 file");
                popupMenu.add(mntmAddFileDomain_1);
                mntmAddFileDomain_1.addActionListener(addDom2);

                
                mntmAddFileRequirments = new JMenuItem("Add Requirements");
                popupMenu.add(mntmAddFileRequirments);
                mntmAddFileRequirments.addActionListener(addReq);
                
                mntmRemoveFileDomain = new JMenuItem("Remove Domain1 file");
                popupMenu.add(mntmRemoveFileDomain);
                mntmRemoveFileDomain.addActionListener(remDom1);
                
                
                mntmRemoveFileDomain_1 = new JMenuItem("Remove Domain2 file");
                popupMenu.add(mntmRemoveFileDomain_1);
                mntmRemoveFileDomain_1.addActionListener(remDom2);
                
                mntmRemoveRequirements = new JMenuItem("Remove Requirements");
                popupMenu.add(mntmRemoveRequirements);
                mntmRemoveRequirements.addActionListener(remReq);
                
                tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
					.addGap(1))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
					.addGap(1))
		);
		setLayout(groupLayout);
                enablePopUp(false);
                js=new OverlapTreeLeafSelection(tree);
                tree.addTreeSelectionListener(js);
                DefaultTreeCellRenderer dtcr=new DefaultTreeCellRenderer();
                dtcr.setLeafIcon(null);
                tree.setCellRenderer(dtcr);
	}
	public JTree getTree(){
		return tree;
	}
        public void enablePopUp(boolean t){
		popupMenu.setEnabled(t);
                mntmAddFileDomain.setEnabled(t);
                mntmAddFileDomain_1.setEnabled(t);
                mntmRemoveFileDomain.setEnabled(t);
                mntmRemoveFileDomain_1.setEnabled(t);
                mntmAddFileRequirments.setEnabled(t);
                mntmRemoveRequirements.setEnabled(t);
         }
        public void enableAddRequirements(boolean t){
            mntmAddFileRequirments.setEnabled(t);
            mntmRemoveRequirements.setEnabled(!t);
        }
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
        public Observable[] getObservable(){
            return new Observable[]{addDom1,addDom2,addReq,remDom1,remDom2,remReq,js};
        }
}
