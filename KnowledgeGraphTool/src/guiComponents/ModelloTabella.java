package guiComponents;

import javax.swing.table.DefaultTableModel;

public class ModelloTabella extends DefaultTableModel{
	
	public ModelloTabella(){
		super();
	}
	@Override
	public boolean isCellEditable(int row, int column){
            if(column==2)
		return true;
            else
                return false;
	}
}
