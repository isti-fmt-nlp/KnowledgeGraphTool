package guiListener;

import controllers.GraphEditor;
import supportGui.RangeSlider;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JCheckBox;

public class ApplyFilter extends Observable implements ActionListener {
	GraphEditor ge;
        JCheckBox ckDegree;
	JCheckBox ckInDegree;
	JCheckBox ckEdge;
	RangeSlider deg;
	RangeSlider inDeg;
	RangeSlider edg;
	Boolean rimuovi=false;
	public ApplyFilter(JCheckBox ckDegree,RangeSlider deg,JCheckBox ckInDegree,RangeSlider inDeg,JCheckBox ckEdge,RangeSlider edg){
		ge=GraphEditor.getInstance();
                this.ckDegree=ckDegree;
		this.ckInDegree=ckInDegree;
		this.ckEdge=ckEdge;
		this.deg=deg;
		this.edg=edg;
		this.inDeg=inDeg;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		boolean degree=false;
                boolean indegree=false;
                boolean edge=false;
                if(ckDegree.isSelected())
                    degree=true;
		if(ckInDegree.isSelected())
                    indegree=true;
		if(ckEdge.isSelected())
                    edge=true;
                ge.ApplyFilters(indegree, deg.getValue(), deg.getUpperValue(), indegree, inDeg.getValue(), inDeg.getUpperValue(), edge, edg.getValue(), edg.getUpperValue());
        }

}
