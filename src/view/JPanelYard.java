package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entity.Yard;
import service.CustomerService;
import service.PriceService;
import service.YardService;
import utility.Button_Yard;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class JPanelYard extends JPanel {
	private JTable table;
	private List<Button_Yard> listButtonYard = null;
	
	private JButton btnSan5A;
	private JButton btnSan5B;
	private JButton btnSan5C;
	private JButton btnSan5D;
	private JButton btnSan5E;
	private JButton btnSan5F;
	private JButton btnSan7A;
	private JButton btnSan7B;
	private JButton btnEdit;
	/**
	 * Create the panel.
	 */
	
	public JPanelYard() {
		
		initComponents();
		
		listButtonYard = new ArrayList<Button_Yard>();
		listButtonYard.add(new Button_Yard(1,btnSan5A,1));
		listButtonYard.add(new Button_Yard(2,btnSan5B,1));
		listButtonYard.add(new Button_Yard(3,btnSan5C,1));
		listButtonYard.add(new Button_Yard(4,btnSan5D,1));
		listButtonYard.add(new Button_Yard(5,btnSan5E,1));
		listButtonYard.add(new Button_Yard(6,btnSan5F,1));
		listButtonYard.add(new Button_Yard(7,btnSan7A,1));
		listButtonYard.add(new Button_Yard(8,btnSan7B,1));
		
		ButtonYard buttonYard = new ButtonYard();
		
		btnSan5A.addActionListener(buttonYard);
		btnSan5B.addActionListener(buttonYard);
		btnSan5C.addActionListener(buttonYard);
		btnSan5D.addActionListener(buttonYard);
		btnSan5E.addActionListener(buttonYard);
		btnSan5F.addActionListener(buttonYard);
		btnSan7A.addActionListener(buttonYard);
		btnSan7B.addActionListener(buttonYard);
		
		List<Yard> listYard = YardService.getInstance().getAllYard();
		for(int i = 0 ; i < listYard.size() ; i++) {
			if(listYard.get(i).getStatus() == 0) {
				listButtonYard.get(i).setStatus(0);
				listButtonYard.get(i).getButton().setBackground(new Color(255,0,0));
			}
		}
		
		EditPrice editPrice = new EditPrice();
		btnEdit.addActionListener(editPrice);
		
		showData();
	}
	public void showData() {
		Object[][] data = PriceService.getInstance().showPrice();
		String col[] = {"ID","Category Yard","Time","Price"};
		DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setDataVector(data, col);
	}
	private void initComponents() {
		setLayout(null);
		
		btnSan5A = new JButton("Sân 5A");
		btnSan5A.setBackground(new Color(50, 205, 50));
		btnSan5A.setBounds(387, 78, 89, 61);
		add(btnSan5A);
		
		btnSan5B = new JButton("Sân 5B");
		btnSan5B.setBackground(new Color(50, 205, 50));
		btnSan5B.setBounds(387, 179, 89, 61);
		add(btnSan5B);
		
		btnSan5C = new JButton("Sân 5C");
		btnSan5C.setBackground(new Color(50, 205, 50));
		btnSan5C.setBounds(387, 286, 89, 61);
		add(btnSan5C);
		
		btnSan5D = new JButton("Sân 5D");
		btnSan5D.setBackground(new Color(50, 205, 50));
		btnSan5D.setBounds(521, 78, 89, 61);
		add(btnSan5D);
		
		btnSan5E = new JButton("Sân 5E");
		btnSan5E.setBackground(new Color(50, 205, 50));
		btnSan5E.setBounds(521, 179, 89, 61);
		add(btnSan5E);
		
		btnSan5F = new JButton("Sân 5F");
		btnSan5F.setBackground(new Color(50, 205, 50));
		btnSan5F.setBounds(521, 286, 89, 61);
		add(btnSan5F);
		
		btnSan7A = new JButton("Sân 7A");
		btnSan7A.setBackground(new Color(50, 205, 50));
		btnSan7A.setBounds(671, 78, 122, 91);
		add(btnSan7A);
		
		btnSan7B = new JButton("Sân 7B");
		btnSan7B.setBackground(new Color(50, 205, 50));
		btnSan7B.setBounds(671, 256, 122, 91);
		add(btnSan7B);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 78, 305, 269);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel jlbKhungGioSan = new JLabel("Khung giờ sân");
		jlbKhungGioSan.setFont(new Font("Tahoma", Font.BOLD, 17));
		jlbKhungGioSan.setHorizontalAlignment(SwingConstants.CENTER);
		jlbKhungGioSan.setBounds(90, 26, 195, 41);
		add(jlbKhungGioSan);
		
//		JButton btnAdd = new JButton("Th\u00EAm");
//		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
//		btnAdd.setBackground(new Color(0, 191, 255));
//		btnAdd.setBounds(33, 367, 89, 23);
//		add(btnAdd);
		
		btnEdit = new JButton("Sửa");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEdit.setBackground(new Color(0, 191, 255));
		btnEdit.setBounds(143, 367, 89, 23);
		add(btnEdit);
		
//		JButton btnDelete = new JButton("Xo\u00E1");
//		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
//		btnDelete.setBackground(new Color(0, 191, 255));
//		btnDelete.setBounds(249, 367, 89, 23);
//		add(btnDelete);
		
		JLabel jlbCacSan = new JLabel("Các sân trong khu vực");
		jlbCacSan.setFont(new Font("Tahoma", Font.BOLD, 16));
		jlbCacSan.setHorizontalAlignment(SwingConstants.CENTER);
		jlbCacSan.setBounds(430, 26, 330, 30);
		add(jlbCacSan);
		
	}
	
	private JPanelYard getThis() {
		return this;
	}
	
	private class ButtonYard implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			for(int i = 0 ; i < 8 ; i++) {
				if(e.getActionCommand() == listButtonYard.get(i).getButton().getText()) {
					if(listButtonYard.get(i).getStatus() == 1) {//nếu không phải màu đỏ thì...
						int a = JOptionPane.showConfirmDialog(getThis(),"Bạn chắc chắn tạm dừng phục vụ sân này?\n");
						if(a == JOptionPane.YES_OPTION) {
							YardService.getInstance().setStatus(0, listButtonYard.get(i).getIdYard());
							listButtonYard.get(i).getButton().setBackground(new Color(255,0,0) );
							listButtonYard.get(i).setStatus(0);
							YardService.getInstance().setStatus(0, listButtonYard.get(i).getIdYard());
						}
					}else {
						int b = JOptionPane.showConfirmDialog(getThis(),"Bạn muốn mở lại phục vụ sân này?\n");
						if(b == JOptionPane.YES_OPTION) {
							YardService.getInstance().setStatus(1, listButtonYard.get(i).getIdYard());
							listButtonYard.get(i).getButton().setBackground(new Color(50, 205, 50));
							listButtonYard.get(i).setStatus(1);
							YardService.getInstance().setStatus(1, listButtonYard.get(i).getIdYard());
						}			
					}
					break;
				}
			}
		}
	}
	private class EditPrice implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(table.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(getThis(), "Bạn chưa chọn hàng để sửa", "Error",
						JOptionPane.ERROR_MESSAGE);
			}else {
				int getID = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
				JFrameEditPrice f = new JFrameEditPrice(getThis());
				f.getTfID().setText(String.valueOf(getID));
				f.getTfCateYard().setText(table.getModel().getValueAt(table.getSelectedRow(),1).toString());
				f.getTfTime().setText(table.getModel().getValueAt(table.getSelectedRow(),2).toString());
				f.getTfPrice().setText(table.getModel().getValueAt(table.getSelectedRow(),3).toString());
				f.setVisible(true);
			}
		}
	}
}
