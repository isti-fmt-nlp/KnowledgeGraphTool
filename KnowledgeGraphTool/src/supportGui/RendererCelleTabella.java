package supportGui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RendererCelleTabella extends DefaultTableCellRenderer {
	ArrayList<Integer> alertRows = new ArrayList<Integer>();
	public RendererCelleTabella(){
		super();
	}
	public void setAlert(int row){
		int index=alertRows.size();
		if(row>=index)
			for(int i=index; i<=row;i++)
				alertRows.add(null);
		alertRows.add(row, row);
	}
	public void removeAlert(int row){
		alertRows.set(row, null);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		Component c = super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
		if (alertRows.size()!=0 && row<alertRows.size() && alertRows.get(row)!=null) {
			this.setBackground(Color.RED);
			this.setForeground(Color.WHITE);
			this.setFont(new Font("Verdana", Font.BOLD, 13));
		}else{
			this.setBackground(Color.WHITE);
			this.setForeground(Color.BLACK);
		}
		return c;
	} 
     
}
