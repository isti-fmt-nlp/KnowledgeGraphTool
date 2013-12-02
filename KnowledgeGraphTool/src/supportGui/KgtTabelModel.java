package supportGui;

import javax.swing.table.DefaultTableModel;

public class KgtTabelModel extends DefaultTableModel{
	
	public KgtTabelModel(){
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
