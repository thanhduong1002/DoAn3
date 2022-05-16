package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import service.BeverageService;
import utility.SortColumnTable;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class JPanelBeverage extends JPanel implements ActionListener{
	
	private JTextField txtSearch;
	private JTable jtbBeverage;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnEdit;
	private JScrollPane scrollPane;
	
	public JPanelBeverage() {
		initComponents();
		showBeverage("");
		SortColumnTable sort = new SortColumnTable(jtbBeverage);
		
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                if (text.trim().length() == 0) {
                	sort.getRowSorter().setRowFilter(null);
                } else {
                	sort.getRowSorter().setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                if (text.trim().length() == 0) {
                	sort.getRowSorter().setRowFilter(null);
                } else {
                	sort.getRowSorter().setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
	}
	
	private void initComponents() {
		setLayout(null);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 73, 610, 357);
		add(scrollPane);
		
		jtbBeverage = new JTable();
		scrollPane.setViewportView(jtbBeverage);
		

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 203, 430);
		add(panel);
		panel.setLayout(null);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setBackground(new Color(102, 204, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setForeground(Color.DARK_GRAY);
		btnDelete.setBounds(20, 208, 173, 45);
		btnDelete.addActionListener(this);
		panel.add(btnDelete);
		
		btnAdd = new JButton("Thêm");
		btnAdd.setForeground(Color.DARK_GRAY);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBackground(new Color(102, 204, 255));
		btnAdd.setBounds(20, 72, 173, 45);
		btnAdd.addActionListener(this);
		panel.add(btnAdd);
		
		btnEdit = new JButton("Sửa");
		btnEdit.setForeground(Color.DARK_GRAY);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEdit.setBackground(new Color(102, 204, 255));
		btnEdit.setBounds(20, 139, 173, 45);
		btnEdit.addActionListener(this);
		panel.add(btnEdit);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(588, 27, 135, 20);
		add(txtSearch);
		txtSearch.setColumns(10);
				
		JLabel jlbSearch = new JLabel("Tìm kiếm");
		jlbSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbSearch.setBounds(519, 30, 59, 14);
		add(jlbSearch);
	}
	JPanelBeverage getThis() {
		return this;
	}
	public void createFrame(int ID) {

		JFrameAddvsEditBeverage addvsEditBeverage = new JFrameAddvsEditBeverage(ID, this);
		addvsEditBeverage.setVisible(true);
	}
		
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Xóa")) {
			if(jtbBeverage.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(getThis(), "Bạn chưa chọn hàng để xoá", "Error",
						JOptionPane.ERROR_MESSAGE);
			}else {
				int getID = Integer.parseInt(jtbBeverage.getModel().getValueAt(jtbBeverage.getSelectedRow(), 0).toString());
				BeverageService.getInstance().deleteBeverage(getID);
				showBeverage("");
			}	
		} else if (e.getActionCommand().equals("Thêm")) {
			createFrame(0);
		} else if (e.getActionCommand().equals("Sửa")) {
			if(jtbBeverage.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(getThis(), "Bạn chưa chọn hàng để sửa dữ liệu", "Error",
						JOptionPane.ERROR_MESSAGE);
			}else {
				createFrame(
						Integer.parseInt(jtbBeverage.getModel().getValueAt(jtbBeverage.getSelectedRow(), 0).toString()));
			}
		}
	}
		
	public void showBeverage(String name) {
		Object[][] data = BeverageService.getInstance().showBeverages(name);
		String col[] = {"ID Beverage", "Name", "Measure", "Origin Price", "Price", "Mount"};
		DefaultTableModel model = (DefaultTableModel) jtbBeverage.getModel();
        model.setDataVector(data, col);
	}
}