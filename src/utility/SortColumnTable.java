package utility;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



public class SortColumnTable {
	private TableRowSorter<TableModel> rowSorter = null;
	private JTable jtb;
	public SortColumnTable(JTable jtb) {
		super();
		this.jtb = jtb;
		rowSorter = new TableRowSorter<>(this.jtb.getModel());
		this.jtb.setRowSorter(rowSorter);
	}
	public TableRowSorter<TableModel> getRowSorter() {
		return rowSorter;
	}
	public JTable getJtb() {
		return jtb;
	}
	
	
	
}
	