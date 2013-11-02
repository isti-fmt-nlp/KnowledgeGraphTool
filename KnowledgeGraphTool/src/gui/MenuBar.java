package gui;

import guiComponents.AggiungiDominio;
import guiComponents.AggiungiRequisiti;
import guiComponents.ApriProgetto;
import guiComponents.AvviaAnalisi;
import guiComponents.ChiudiProgetto;
import guiComponents.FloatSlider;
import guiComponents.NuovoProgetto;

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
        private JMenuItem mntmAggiungiDominio1;
        private JMenuItem mntmAggiungiDominio2;
        private JButton btnNewButton;

	private FloatSlider floatSlider;
        private ApriProgetto apriAction=new ApriProgetto();
        private AvviaAnalisi analisi=new AvviaAnalisi();
        private NuovoProgetto nuovoAction=new NuovoProgetto();
        private ChiudiProgetto chiudiAction=new ChiudiProgetto();
        private AggiungiDominio addDom1=new AggiungiDominio("dominio_1");
        private AggiungiDominio addDom2=new AggiungiDominio("dominio_2");
        private AggiungiRequisiti addReq=new AggiungiRequisiti();


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
		
		JMenu mnProgetto = new JMenu("Progetto");
		menuBar.add(mnProgetto);
		
		mntmNuovoProgetto = new JMenuItem("Nuovo Progetto");
                mntmNuovoProgetto.addActionListener(nuovoAction);
		mnProgetto.add(mntmNuovoProgetto);
		mntmNuovoProgetto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
           
		JMenuItem mntmApriProgetto = new JMenuItem("Apri Progetto");
                mntmApriProgetto.addActionListener(apriAction);
		mnProgetto.add(mntmApriProgetto);
		mntmApriProgetto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
		
		JMenuItem mntmChiudiProgetto = new JMenuItem("Chiudi Progetto");
		mntmChiudiProgetto.addActionListener(chiudiAction);
                mnProgetto.add(mntmChiudiProgetto);
                mntmChiudiProgetto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));

		mntmAggiungiDominio1 = new JMenuItem("Aggiungi File Dominio1");
                mntmAggiungiDominio1.addActionListener(addDom1);
		mnProgetto.add(mntmAggiungiDominio1);
		mntmAggiungiDominio1.setEnabled(false);
                mntmAggiungiDominio1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
                
                mntmAggiungiDominio2 = new JMenuItem("Aggiungi File Dominio2");
                mntmAggiungiDominio2.addActionListener(addDom2);
		mnProgetto.add(mntmAggiungiDominio2);
                mntmAggiungiDominio2.setEnabled(false);
		mntmAggiungiDominio2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
                
                mntmAggiungiRequisiti= new JMenuItem("Aggiungi File Requisito");
                mntmAggiungiRequisiti.addActionListener(addReq);
		mnProgetto.add(mntmAggiungiRequisiti);
                mntmAggiungiRequisiti.setEnabled(false);
		mntmAggiungiRequisiti.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));

                
                JMenu mnSalva = new JMenu("Salva");
		menuBar.add(mnSalva);
		
		
		JMenuItem mntmSalvaAnalisi = new JMenuItem("Salva Analisi");
		mnSalva.add(mntmSalvaAnalisi);
		mntmSalvaAnalisi.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));

		
		JMenuItem mntmExportPdfGrafi = new JMenuItem("Export Pdf Grafi");
		mnSalva.add(mntmExportPdfGrafi);
		mntmExportPdfGrafi.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));

		
		btnNewButton = new JButton("Avvia Analisi");
                btnNewButton.addActionListener(analisi);
		btnNewButton.setToolTipText("Analisi abilitata solo con documenti disponibili\r\n");
		btnNewButton.setVerticalTextPosition(SwingConstants.TOP);
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.setEnabled(false);
		
		floatSlider = new FloatSlider();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
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
	}
	
	public Observable [] getObservable(){
                Observable [] obs={(Observable)floatSlider.getChangeListener() , (Observable)apriAction,chiudiAction,nuovoAction,addDom1,addDom2,addReq,analisi};
                return obs;
	}
        public void setMenuItemsEnable(boolean t){
            mntmAggiungiDominio1.setEnabled(t);
            mntmAggiungiDominio2.setEnabled(t);
            mntmAggiungiRequisiti.setEnabled(t);
        }
       public void enableAnalisi(boolean t){
            btnNewButton.setEnabled(t);
       }
}
