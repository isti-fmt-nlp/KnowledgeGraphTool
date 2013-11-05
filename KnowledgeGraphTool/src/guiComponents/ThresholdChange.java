package guiComponents;

import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ThresholdChange extends Observable implements ChangeListener {
	int n=0;
	JLabel l=null;
	public ThresholdChange(int n,JLabel l){
		this.n=n;
		this.l=l;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		 JSlider slider = (JSlider) e.getSource();
         l.setText(""+(float)slider.getValue()/n);
         this.setChanged();
         this.notifyObservers((float)slider.getValue()/n);
	}

}
