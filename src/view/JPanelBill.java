package view;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import service.BeverageBillService;
import service.BillService;
import service.EmployeeService;
import utility.SortColumnTable;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.Color;
import javax.swing.SwingConstants;

public class JPanelBill extends JPanel implements ActionListener{
	private JTable jtbBill;
	private JTextField txtSearch;
	private JDateChooser dateChooser;
	private DateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
	private JButton btnDelbyDate;
	private JDateChooser dateChoDel1;
	private JDateChooser dateChoDel2;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnShowAll;
	private JLabel jlbHienThiTatCa;
	private JPanel pnlDel;
	/**
	 * Create the panel.
	 */
	public JPanelBill() {
		initComponents();
		if(EmployeeService.getInstance().getStoreUser().getRole() == 1) {
			showBill(0, dformat.format(dateChooser.getDate()));
			btnDelbyDate.setVisible(true);
			btnShowAll.setVisible(true);
			jlbHienThiTatCa.setVisible(true);
			pnlDel.setVisible(true);
		}else {
			showBill(EmployeeService.getInstance().getStoreUser().getIdCustomer(), dformat.format(dateChooser.getDate()));
			
			
			btnDelbyDate.setVisible(false);
			btnShowAll.setVisible(false);
			jlbHienThiTatCa.setVisible(false);
			pnlDel.setVisible(false);
		}
		
		SortColumnTable sort = new SortColumnTable(jtbBill);
		
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
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 205, 422);
		add(panel);
		
		btnAdd = new JButton("Thêm");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setForeground(Color.DARK_GRAY);
		btnAdd.setBackground(new Color(102, 204, 255));
		btnAdd.setBounds(10, 76, 173, 45);
		btnAdd.addActionListener(this);
		panel.add(btnAdd);
		
		btnEdit = new JButton("Sửa/Xem");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEdit.setForeground(Color.DARK_GRAY);
		btnEdit.setBounds(10, 135,  173, 45);
		btnEdit.setBackground(new Color(102, 204, 255));
		btnEdit.addActionListener(this);
		panel.add(btnEdit);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setForeground(Color.DARK_GRAY);
		btnDelete.setBounds(10, 194, 173, 45);
		btnDelete.setBackground(new Color(102, 204, 255));
		btnDelete.addActionListener(this);
		panel.add(btnDelete);
		
		btnDelbyDate = new JButton("Xóa Theo Ngày");
		btnDelbyDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BillService.getInstance().DelByDate(dformat.format(dateChoDel1.getDate())
						, dformat.format(dateChoDel2.getDate()));
				showBill(0, "");
			}
		});
		btnDelbyDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelbyDate.setForeground(Color.DARK_GRAY);
		btnDelbyDate.setBackground(new Color(102, 204, 255));
		btnDelbyDate.setBounds(10, 253, 173, 45);
		panel.add(btnDelbyDate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 73, 610, 318);
		add(scrollPane);
		
		jtbBill = new JTable();
		jtbBill.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtbBill.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Bill", "Create Time", "Create Date", "Total", "ID Order", "ID Employee"
			}
		));
		scrollPane.setViewportView(jtbBill);
		Date date = new Date();
		
		pnlDel = new JPanel();
		pnlDel.setBounds(10, 312, 185, 99);
		panel.add(pnlDel);
		pnlDel.setLayout(null);
		
		dateChoDel1 = new JDateChooser();
		dateChoDel1.getSpinner().setFont(new Font("Tahoma", Font.BOLD, 12));
		dateChoDel1.getSpinner().setBackground(new Color(211, 211, 211));
		dateChoDel1.setBounds(10, 26, 165, 20);
		pnlDel.add(dateChoDel1);
		
		JLabel jlbTuNgay = new JLabel("Từ ngày");
		jlbTuNgay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jlbTuNgay.setBounds(10, 0, 73, 25);
		pnlDel.add(jlbTuNgay);
		
		JLabel jlbDenNgay = new JLabel("Đến ngày");
		jlbDenNgay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jlbDenNgay.setBounds(10, 48, 134, 20);
		pnlDel.add(jlbDenNgay);
		
		dateChoDel2 = new JDateChooser();
		dateChoDel2.getSpinner().setFont(new Font("Tahoma", Font.BOLD, 12));
		dateChoDel2.getSpinner().setBackground(new Color(211, 211, 211));
		dateChoDel2.setBounds(10, 68, 165, 20);
		pnlDel.add(dateChoDel2);
		
		
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSearch.setBounds(681, 44, 149, 20);
		add(txtSearch);
		txtSearch.setColumns(10);
		
		btnShowAll = new JButton("ShowAll");
		btnShowAll.setBackground(new Color(102, 204, 255));
		btnShowAll.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowAll.setBounds(380, 402, 89, 23);
		add(btnShowAll);
		
		jlbHienThiTatCa = new JLabel("Hiển thị tất cả hoá đơn");
		jlbHienThiTatCa.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbHienThiTatCa.setFont(new Font("Tahoma", Font.BOLD, 11));
		jlbHienThiTatCa.setBounds(233, 398, 137, 31);
		add(jlbHienThiTatCa);
		
		JLabel jlbCacHoaDon = new JLabel("Các hoá đơn");
		jlbCacHoaDon.setBounds(233, 37, 123, 27);
		add(jlbCacHoaDon);
		jlbCacHoaDon.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		
		dateChooser = new JDateChooser();
		dateChooser.getSpinner().setFont(new Font("Tahoma", Font.BOLD, 13));
		dateChooser.getSpinner().setBackground(new Color(210, 180, 140));
		dateChooser.setBounds(354, 37, 185, 27);
		add(dateChooser);
		dateChooser.setDate(date);
		dateChooser.getSpinner().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int i = (EmployeeService.getInstance().getStoreUser().getRole() == 1) ? 0
                        : EmployeeService.getInstance().getStoreUser().getIdCustomer();
                showBill(i, dformat.format(dateChooser.getDate()));
			}
		});
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showBill(0, "");
			}
		});
		
		
	}
	JPanelBill getThis() {
		return this;
	}
	public void createFrame(int ID) 
	{
		JFrameBill Frame = new JFrameBill(ID ,this);
		Frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Xóa")) {
			if(jtbBill.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(getThis(), "Bạn chưa chọn hàng để xoá", "Error",
						JOptionPane.ERROR_MESSAGE);
			}else {
				int getID = Integer.parseInt(jtbBill.getModel().getValueAt(jtbBill.getSelectedRow(),0).toString());
				int i = BillService.getInstance().checkID(getID).getIdOrder();
				BeverageBillService.getInstance().deleteBillBeveOfBill(
				BeverageBillService.getInstance().getAllBeverageBill(getID));
				BillService.getInstance().deleteBill(getID);
				BillService.getInstance().DelOrder(i);
				if(EmployeeService.getInstance().getStoreUser().getRole() == 1) {
					showBill(0, "");
				}else {
					showBill(EmployeeService.getInstance().getStoreUser().getIdCustomer(), dformat.format(dateChooser.getDate()));
				}
			}
		}else if(e.getActionCommand().equals("Thêm")) {
			createFrame(BillService.getInstance().getNextIdBill());
		}else if(e.getActionCommand().equals("Sửa/Xem")) {
			if(jtbBill.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(getThis(), "Bạn chưa chọn hàng để sửa", "Error",
						JOptionPane.ERROR_MESSAGE);
			}else {
				createFrame(Integer.parseInt(jtbBill.getModel().getValueAt(jtbBill.getSelectedRow(),0).toString()));
			}		
		}
	}
	public void showBill(int IDEmpl, String d) {
		Object[][] data = BillService.getInstance().showBills(IDEmpl, d);
		String col[] = {"ID Bill", "Create Date", "Create Time", "Total", "ID Employee", "ID Order"};
		DefaultTableModel model = (DefaultTableModel) jtbBill.getModel();
        model.setDataVector(data, col);
	}

	public String getDateChooser() {
		return dformat.format(dateChooser.getDate());
	}
}