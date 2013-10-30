package guiComponents;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class FloatSlider extends JPanel {
	private JLabel sliderLabel = new JLabel();
    private JLabel sliderValue = new JLabel();
    private JSlider jSlider=new JSlider();
    private final int n;
    private ThresholdChange threshold;
    
    public FloatSlider() {
    	n=100000000;
    	threshold=new ThresholdChange(n,sliderValue);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
        jSlider.setSnapToTicks(true);
        jSlider.setBorder(null);
        jSlider.setMaximum(n);
        jSlider.setValue(n);
        
        // Add listener to update display.
        jSlider.addChangeListener(threshold );
         sliderLabel.setMaximumSize(new Dimension(75, 22));
         sliderLabel.setPreferredSize(new Dimension(75, 22));
         sliderLabel.setMinimumSize(new Dimension(75, 22));
         sliderLabel.setSize(new Dimension(75, 22));
                
         sliderLabel.setText("Soglia Jaccard:");
         sliderValue.setMaximumSize(new Dimension(60, 22));
         sliderValue.setMinimumSize(new Dimension(60, 22));
         sliderValue.setPreferredSize(new Dimension(60, 22));
         sliderValue.setSize(new Dimension(60, 22));
         
         sliderValue.setText(""+jSlider.getValue()/n);
         sliderValue.setHorizontalAlignment(JLabel.LEFT);
         GroupLayout groupLayout = new GroupLayout(this);
         groupLayout.setHorizontalGroup(
         	groupLayout.createParallelGroup(Alignment.TRAILING)
         		.addGroup(groupLayout.createSequentialGroup()
         			.addGap(1)
         			.addComponent(jSlider, GroupLayout.PREFERRED_SIZE, 309, Short.MAX_VALUE)
         			.addGap(1)
         			.addComponent(sliderLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
         			.addGap(4)
         			.addComponent(sliderValue, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
         );
         groupLayout.setVerticalGroup(
         	groupLayout.createParallelGroup(Alignment.LEADING)
         		.addGroup(groupLayout.createSequentialGroup()
         			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
         				.addGroup(groupLayout.createSequentialGroup()
         					.addGap(1)
         					.addComponent(sliderLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
         				.addGroup(groupLayout.createSequentialGroup()
         					.addGap(1)
         					.addComponent(sliderValue, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
         				.addComponent(jSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
         			.addContainerGap())
         );
         setLayout(groupLayout);
    }
    public JSlider getFloatSlider(){
    	return jSlider;
    }
    public void setText(String val){
    	sliderLabel.setText(val);
    }
    public ChangeListener getChangeListener(){
    	return threshold;
    }

}
