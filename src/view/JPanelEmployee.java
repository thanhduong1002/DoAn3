package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import entity.Employee;
import service.CustomerService;
import service.EmployeeService;
import utility.SortColumnTable;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelEmployee extends JPanel {
	private JTextField textField;
	private JTable jtbEmployee;
	private JScrollPane scrollPane;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnEdit;
	
	/**
	 * Create the panel.
	 */
	public JPanelEmployee() {
		
		initComponents();
		showEmployee();
		
		ButtonListener buttonListener = new ButtonListener();
		btnAdd.addActionListener(buttonListener);
		btnEdit.addActionListener(buttonListener);
		btnDelete.addActionListener(buttonListener);
		
		SortColumnTable columnTable = new SortColumnTable(jtbEmployee);
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = textField.getText();
                if (text.trim().length() == 0) {
                	columnTable.getRowSorter().setRowFilter(null);
                } else {
                	columnTable.getRowSorter().setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = textField.getText();
                if (text.trim().length() == 0) {
                	columnTable.getRowSorter().setRowFilter(null);
                } else {
                	columnTable.getRowSorter().setRowFilter(RowFilter.regexFilter("(?i)" + text));
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
		
		jtbEmployee = new JTable();
		scrollPane.setViewportView(jtbEmployee);
		

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 203, 430);
		add(panel);
		panel.setLayout(null);
		
		btnDelete = new JButton("Xoá");
		btnDelete.setBackground(new Color(102, 204, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setForeground(Color.DARK_GRAY);
		btnDelete.setBounds(20, 208, 173, 45);
		panel.add(btnDelete);
		
		btnAdd = new JButton("Thêm");
		btnAdd.setForeground(Color.DARK_GRAY);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBackground(new Color(102, 204, 255));
		btnAdd.setBounds(20, 72, 173, 45);
		panel.add(btnAdd);
		
		btnEdit = new JButton("Sửa/Xem");
		btnEdit.setForeground(Color.DARK_GRAY);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEdit.setBackground(new Color(102, 204, 255));
		btnEdit.setBounds(20, 139, 173, 45);
		panel.add(btnEdit);
		
		textField = new JTextField();
		textField.setBounds(588, 27, 135, 20);
		add(textField);
		textField.setColumns(10);
		
		
		JLabel jlbSearch = new JLabel("Tìm kiếm");
		jlbSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbSearch.setBounds(519, 30, 59, 14);
		add(jlbSearch);
		
		
	}
	JPanelEmployee getThis() {
		return this;
	}
	
	public void createFrame(boolean b) {
		if( b == true) {
			int getID = Integer.parseInt(jtbEmployee.getModel().getValueAt(jtbEmployee.getSelectedRow(),0).toString());
			Employee employee = EmployeeService.getInstance().checkIDEmployee(getID);
			JFrameAddvsEditEmployee addvsEditEmployee = new JFrameAddvsEditEmployee(employee,this);
			addvsEditEmployee.setVisible(true);
			System.out.println("Đã tạo frame sửa");
		}else if(b == false){
			int nextID = EmployeeService.getInstance().getNextIdEmployee();
			JFrameAddvsEditEmployee addvsEditEmployee = new JFrameAddvsEditEmployee(nextID,this);
			addvsEditEmployee.setVisible(true);
			System.out.println("Đã tạo frame thêm");
		}
	}
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("Xoá")) {//nếu nhấn vào nút xoá thì ...
				if(jtbEmployee.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(getThis(), "Bạn chưa chọn hàng để xoá", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else {
					int getID = Integer.parseInt(jtbEmployee.getModel().getValueAt(jtbEmployee.getSelectedRow(),0).toString());
					if(getID == EmployeeService.getInstance().getStoreUser().getIdCustomer()) {
						JOptionPane.showMessageDialog(getThis(), "Bạn không thể xoá dữ liệu của chính mình", "Error",
								JOptionPane.ERROR_MESSAGE);
					}else {
						if(EmployeeService.getInstance().checkIDEmployee(getID).getRole() == 1) {
							JOptionPane.showMessageDialog(getThis(), "Bạn không thể xoá dữ liệu của admin. Nếu muốn xoá, hãy sửa admin thành nhân viên.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}else {
							EmployeeService.getInstance().deleteEmployee(getID);
							JOptionPane.showMessageDialog(getThis(),"Xoá thành công");
							showEmployee();
						}			
					}				
				}
			}else if(e.getActionCommand().equals("Thêm")) {
				createFrame(false);
			}else if(e.getActionCommand().equals("Sửa/Xem")) {
				if(jtbEmployee.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(getThis(), "Bạn chưa chọn hàng để sửa dữ liệu", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else {
					createFrame(true);
				}
			}

		}
		
	}
	public void showEmployee() {
		Object[][] data = EmployeeService.getInstance().showEmployees();
		String col[] = {"ID","Name","Old","Gender","Address","Phone","IdentityNumber","Password","Role"};
		DefaultTableModel model = (DefaultTableModel) jtbEmployee.getModel();
        model.setDataVector(data, col);
        
	}
}
