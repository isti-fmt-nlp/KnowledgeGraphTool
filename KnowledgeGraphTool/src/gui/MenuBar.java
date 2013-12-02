package gui;

import guiListener.AddDocuments;
import guiListener.AddRequirements;
import guiListener.OpenProject;
import guiListener.StartAnalysis;
import guiListener.CloseProject;
import guiListener.LoadAnalysis;
import supportGui.FloatSlider;
import guiListener.NewProject;
import guiListener.RemoveDocuments;
import guiListener.RemoveRequirements;
import guiListener.SaveAnalysis;

import javax.swing.JPanel;
import javax.swing.JMenuBar;

import java.util.Observable;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.Dimension;


public class MenuBar extends JPanel {
	private JMenuItem mntmNuovoProgetto;
        private JMenuItem mntmAggiungiRequisiti;
        private JMenuItem mntmAggiungiSubject1;
        private JMenuItem mntmAggiungiSubject2;
        private JMenuItem mntmChiudiProgetto;
        private JMenu mnSalva;
        private JButton btnNewButton;

	private FloatSlider floatSlider;
        private OpenProject apriAction=new OpenProject();
        private StartAnalysis analisi=new StartAnalysis();
        private NewProject nuovoAction=new NewProject();
        private CloseProject chiudiAction=new CloseProject();
        private AddDocuments addSub1=new AddDocuments("subject_1");
        private RemoveDocuments remSub1=new RemoveDocuments("subject_1");
        private RemoveDocuments remSub2=new RemoveDocuments("subject_2");
        private RemoveRequirements remReq=new RemoveRequirements();
        private AddDocuments addSub2=new AddDocuments("subject_2");
        private AddRequirements addReq=new AddRequirements();
        private LoadAnalysis la=new LoadAnalysis();
        JMenuItem mntmRemoveFileSubject;
        JMenuItem mntmRemoveFileSubject_1;
        JMenuItem mntmRemoveRequirements;


        /**
	 * Create the panel.
	 */
	public MenuBar() {
		setSize(new Dimension(700, 22));
		setPreferredSize(new Dimension(900, 25));
		setMinimumSize(new Dimension(550, 25));
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JMenu mnProgetto = new JMenu("Project");
		menuBar.add(mnProgetto);
		
		mntmNuovoProgetto = new JMenuItem("New Project");
                mntmNuovoProgetto.addActionListener(nuovoAction);
		mnProgetto.add(mntmNuovoProgetto);
		mntmNuovoProgetto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
           
		JMenuItem mntmApriProgetto = new JMenuItem("Open Project");
                mntmApriProgetto.addActionListener(apriAction);
		mnProgetto.add(mntmApriProgetto);
		mntmApriProgetto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
		
		mntmChiudiProgetto = new JMenuItem("Close Project");
                mntmChiudiProgetto.setEnabled(false);
		mntmChiudiProgetto.addActionListener(chiudiAction);
                mnProgetto.add(mntmChiudiProgetto);
                mntmChiudiProgetto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));

		mntmAggiungiSubject1 = new JMenuItem("Add Subject1 file");
                mntmAggiungiSubject1.addActionListener(addSub1);
		mnProgetto.add(mntmAggiungiSubject1);
		mntmAggiungiSubject1.setEnabled(false);
                mntmAggiungiSubject1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
                
                mntmAggiungiSubject2 = new JMenuItem("Add Subject2 file");
                mntmAggiungiSubject2.addActionListener(addSub2);
		mnProgetto.add(mntmAggiungiSubject2);
                mntmAggiungiSubject2.setEnabled(false);
		mntmAggiungiSubject2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
                
                mntmAggiungiRequisiti= new JMenuItem("Add Requirements file");
                mntmAggiungiRequisiti.addActionListener(addReq);
		mnProgetto.add(mntmAggiungiRequisiti);
                mntmAggiungiRequisiti.setEnabled(false);
		mntmAggiungiRequisiti.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
                
                mntmRemoveFileSubject = new JMenuItem("Remove Subject1 file");
                mntmRemoveFileSubject.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.SHIFT_MASK));
                mnProgetto.add(mntmRemoveFileSubject);
                mntmRemoveFileSubject.addActionListener(remSub1);
                
                
                mntmRemoveFileSubject_1 = new JMenuItem("Remove Subject2 file");
                mntmRemoveFileSubject_1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F,java.awt.event.InputEvent.SHIFT_MASK));

                mnProgetto.add(mntmRemoveFileSubject_1);
                mntmRemoveFileSubject_1.addActionListener(remSub2);
                
                mntmRemoveRequirements = new JMenuItem("Remove Requirements");
                mnProgetto.add(mntmRemoveRequirements);
                mntmRemoveRequirements.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK));
                mntmRemoveRequirements.addActionListener(remReq);
                
                mnSalva = new JMenu("Save/Load");
                mnSalva.setEnabled(false);
                menuBar.add(mnSalva);
		
		
		JMenuItem mntmSalvaAnalisi = new JMenuItem("Save Analysis");
                mnSalva.add(mntmSalvaAnalisi);
		SaveAnalysis save=new SaveAnalysis();
                mntmSalvaAnalisi.addActionListener(save);
                mntmSalvaAnalisi.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));

		
		JMenuItem mntmLoadAnalysis = new JMenuItem("Load Analysis");
		mnSalva.add(mntmLoadAnalysis);
		mntmLoadAnalysis.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
                mntmLoadAnalysis.addActionListener(la);
		
		btnNewButton = new JButton("Start Analysis");
                btnNewButton.addActionListener(analisi);
		btnNewButton.setToolTipText("Analtsis enable only with all required file\r\n");
		btnNewButton.setVerticalTextPosition(SwingConstants.TOP);
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.setEnabled(false);
		
		floatSlider = new FloatSlider();
                floatSlider.getFloatSlider().setEnabled(false);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(floatSlider, GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
					.addGap(2))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(floatSlider, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
                setMenuItemsEnable(false);
	}
	
	public Observable [] getObservable(){
                Observable [] obs={(Observable)floatSlider.getChangeListener() , (Observable)apriAction,chiudiAction,nuovoAction,addSub1,addSub2,addReq,analisi,la};
                return obs;
	}
        public void setMenuItemsEnable(boolean t){
            mntmAggiungiSubject1.setEnabled(t);
            mntmAggiungiSubject2.setEnabled(t);
            mntmAggiungiRequisiti.setEnabled(t);
            mntmChiudiProgetto.setEnabled(t);
            mntmRemoveFileSubject.setEnabled(t);
            mntmRemoveFileSubject_1.setEnabled(t);
            mntmRemoveRequirements.setEnabled(t);
       }
       public void setMenuAddRequirementsEnable(boolean t){
            mntmAggiungiRequisiti.setEnabled(t);
            mntmRemoveRequirements.setEnabled(!t);
        
       }
       
       public void enableAnalisi(boolean t){
            btnNewButton.setEnabled(t);
       }
       public void enableThreshold(boolean t){
            floatSlider.getFloatSlider().setEnabled(t);
       }
       public void enableSave(boolean t){
            mnSalva.setEnabled(t);
       }
       public void setThreshold(int n){
           floatSlider.getFloatSlider().setValue(n);
       }
}
