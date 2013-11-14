package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;

import supportGui.FilterRangeSlider;

import javax.swing.LayoutStyle.ComponentPlacement;

import supportGui.FilterDecimalSlider;
import guiListener.ApplyFilter;
import guiListener.ShowHide;
import guiListener.SubGraph;
import java.util.Observable;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class FilterPane extends JPanel {
        private SubGraph sg=new SubGraph();
	private ApplyFilter af;
        private ShowHide sh=new ShowHide();
         public Observable[] getObservable(){
            return new Observable[]{sg,sh};
        }
	/**
	 * Create the panel.
	 */
	public FilterPane() {
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Graph Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE))
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(1, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(5))
		);
		
		JButton btnSottoGrafo = new JButton("Sub Graph");
                btnSottoGrafo.addActionListener(sg);
		
		JButton btnapplica = new JButton("Apply Filters");
                
		
		JToggleButton tglbtnNomiNodi = new JToggleButton("Hide Labels");
                tglbtnNomiNodi.addActionListener(sh);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(20)
					.addComponent(tglbtnNomiNodi, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnSottoGrafo, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnapplica, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(154, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSottoGrafo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnapplica, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnNomiNodi, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(0, 0, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNewLabel = new JLabel("Nodes:");
		
		JLabel lblNewLabel_1 = new JLabel("Edges:");
		
		lblNodi = new JLabel("0");
		lblNodi.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblArchi = new JLabel("0");
		lblArchi.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(1)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblArchi, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNodi, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(54))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(11, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNodi)
							.addComponent(lblNewLabel)
							.addComponent(lblArchi)))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		chckbxDegreeFilter = new JCheckBox("Degree Filter");
		
		filterRangeSlider = new FilterRangeSlider(0, 100);
		
		chckbxInDegreeFilter = new JCheckBox("In Degree Filter");
		
		filterRangeSlider_1 = new FilterRangeSlider(0, 100);
		
		chckbxEdgeWeightFilter = new JCheckBox("Edge Weight Filter");
		
		filterDecimalSlider = new FilterDecimalSlider(0, 1000);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(filterDecimalSlider, GroupLayout.PREFERRED_SIZE, 193, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(chckbxEdgeWeightFilter)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(filterRangeSlider_1, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
						.addComponent(chckbxInDegreeFilter))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(filterRangeSlider, GroupLayout.PREFERRED_SIZE, 202, Short.MAX_VALUE)
						.addComponent(chckbxDegreeFilter))
					.addGap(1))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(chckbxDegreeFilter)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(filterRangeSlider, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(chckbxEdgeWeightFilter)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(filterDecimalSlider, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(chckbxInDegreeFilter)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(filterRangeSlider_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
                af=new ApplyFilter(chckbxDegreeFilter,filterRangeSlider.getRangeSlider(),chckbxInDegreeFilter,filterRangeSlider_1.getRangeSlider(), chckbxEdgeWeightFilter, filterDecimalSlider.getRangeSlider());
                btnapplica.addActionListener(af);

	}
	private JCheckBox chckbxDegreeFilter;
	
	private FilterRangeSlider filterRangeSlider;
	
	private JCheckBox chckbxInDegreeFilter;
	
	private FilterRangeSlider filterRangeSlider_1;
	
	private JCheckBox chckbxEdgeWeightFilter;
	
	private FilterDecimalSlider filterDecimalSlider;
	private JLabel lblArchi;
	private JLabel lblNodi;
	
	

    public FilterRangeSlider getDegreeSlider(){
    	return filterRangeSlider;
    }
    public FilterRangeSlider getInDegreeSlider(){
    	return filterRangeSlider_1;
    }
    public FilterDecimalSlider getEdgeSlider(){
    	return filterDecimalSlider;
    }
public void setInfo(int archi,int nodi){
    lblArchi.setText(""+archi);
    lblNodi.setText(""+nodi);
}
}
