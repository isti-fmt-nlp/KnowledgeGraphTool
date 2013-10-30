package gui;

import guiComponents.FloatSlider;

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
	private FloatSlider floatSlider;
	/**
	 * Create the panel.
	 */
	public MenuBar() {
		setSize(new Dimension(700, 22));
		setPreferredSize(new Dimension(900, 22));
		setMinimumSize(new Dimension(550, 22));
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JMenu mnProgetto = new JMenu("Progetto");
		menuBar.add(mnProgetto);
		
		mntmNuovoProgetto = new JMenuItem("Nuovo Progetto");
		mnProgetto.add(mntmNuovoProgetto);
		mntmNuovoProgetto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));

		
		JMenuItem mntmApriProgetto = new JMenuItem("Apri Progetto");
		mnProgetto.add(mntmApriProgetto);
		mntmApriProgetto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
		
		JMenuItem mntmChiudiProgetto = new JMenuItem("Chiudi Progetto");
		mnProgetto.add(mntmChiudiProgetto);
		mntmChiudiProgetto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));

		JMenu mnSalva = new JMenu("Salva");
		menuBar.add(mnSalva);
		
		
		JMenuItem mntmSalvaAnalisi = new JMenuItem("Salva Analisi");
		mnSalva.add(mntmSalvaAnalisi);
		mntmSalvaAnalisi.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));

		
		JMenuItem mntmExportPdfGrafi = new JMenuItem("Export Pdf Grafi");
		mnSalva.add(mntmExportPdfGrafi);
		mntmExportPdfGrafi.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));

		
		JButton btnNewButton = new JButton("Avvia Analisi");
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
	
	public Observable getObservable(){
		return (Observable)floatSlider.getChangeListener();
	}
}
