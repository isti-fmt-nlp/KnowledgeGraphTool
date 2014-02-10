package gui;

import guiListener.AddDocuments;
import guiListener.AddRequirements;
import guiListener.ResultTreeLeafSelection;
import guiListener.RemoveDocuments;
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
        private AddDocuments addDoc1=new AddDocuments("subject_1");
        private AddDocuments addDoc2=new AddDocuments("subject_2");
        private AddRequirements addReq=new AddRequirements();
        private RemoveDocuments remDoc1=new RemoveDocuments("subject_1");
        private RemoveDocuments remDoc2=new RemoveDocuments("subject_2");
        private RemoveRequirements remReq=new RemoveRequirements();
        private ResultTreeLeafSelection js;
        private JPopupMenu popupMenu;
        JMenuItem mntmAddFileSubject;
        JMenuItem mntmAddFileSubject_1;
        JMenuItem mntmAddFileRequirments;
        JMenuItem mntmRemoveFileSubject;
        JMenuItem mntmRemoveFileSubject_1;
        JMenuItem mntmRemoveRequirements;
	public ProjectTree() {
		
		JScrollPane scrollPane = new JScrollPane();
                tree=new JTree();
                tree.setRootVisible(true);
		tree.setModel(null);
                
                scrollPane.setViewportView(tree);
                
                popupMenu = new JPopupMenu();
                addPopup(tree, popupMenu);
                
                mntmAddFileSubject = new JMenuItem("Add Subject1 file");
                popupMenu.add(mntmAddFileSubject);
                mntmAddFileSubject.addActionListener(addDoc1);
                
                mntmAddFileSubject_1 = new JMenuItem("Add Subject2 file");
                popupMenu.add(mntmAddFileSubject_1);
                mntmAddFileSubject_1.addActionListener(addDoc2);

                
                mntmAddFileRequirments = new JMenuItem("Add Requirements");
                popupMenu.add(mntmAddFileRequirments);
                mntmAddFileRequirments.addActionListener(addReq);
                
                mntmRemoveFileSubject = new JMenuItem("Remove Subject1 file");
                popupMenu.add(mntmRemoveFileSubject);
                mntmRemoveFileSubject.addActionListener(remDoc1);
                
                
                mntmRemoveFileSubject_1 = new JMenuItem("Remove Subject2 file");
                popupMenu.add(mntmRemoveFileSubject_1);
                mntmRemoveFileSubject_1.addActionListener(remDoc2);
                
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
                js=new ResultTreeLeafSelection(tree);
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
                mntmAddFileSubject.setEnabled(t);
                mntmAddFileSubject_1.setEnabled(t);
                mntmRemoveFileSubject.setEnabled(t);
                mntmRemoveFileSubject_1.setEnabled(t);
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
            return new Observable[]{addDoc1,addDoc2,addReq,remDoc1,remDoc2,remReq,js};
        }
}
