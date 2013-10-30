package guiComponents;

import javax.swing.table.DefaultTableModel;

public class ModelloTabella extends DefaultTableModel{
	
	public ModelloTabella(){
		super();
		DefaultTableModel pippo= new DefaultTableModel();
	}
	@Override
	public boolean isCellEditable(int row, int column){
		return false;
	}
}
