package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import entity.Customer;
import entity.Employee;
import service.CustomerService;
import service.EmployeeService;
//import view.JFrameAddvsEditEmployee.ButtonListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

public class JFrameAddvsEditCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfPhone;
	private JPanelCustomer jpnCustomer;
	private FillPhone fillPhone;
	private JButton btnOk;
	private JButton btnCancel;
	
	public JFrameAddvsEditCustomer(Customer customer, JPanelCustomer jpnCustomer) {//giao diện này dành cho quản lý khách hàng, sửa thông tin khách hàng
		initComponents();
		setTitle("Sửa thông tin khách hàng");
		tfID.setText(String.valueOf(customer.getIdCustomer()));
		tfName.setText(customer.getNameCustomer());
		tfPhone.setText(customer.getPhoneCustomer());
		
		this.jpnCustomer = jpnCustomer;
		
		Action_In_Panel buttonListener = new Action_In_Panel();
		btnOk.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
	}
	public JFrameAddvsEditCustomer(int idCustomer, JPanelCustomer jpnCustomer) {//giao diện này dành cho quản lý khách hàng, thêm thông tin khách hàng
		initComponents();
		setTitle("Thêm mới khách hàng");
		tfID.setText(String.valueOf(idCustomer));
		
		this.jpnCustomer = jpnCustomer;
		
		Action_In_Panel buttonListener = new Action_In_Panel();
		btnOk.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
	}
	public JFrameAddvsEditCustomer(int idCustomer,FillPhone fillPhone) {//giao diện này dành trong chức năng đặt sân, thêm thông tin khách hàng
		initComponents();
		setTitle("Thêm mới một khách hàng");
		tfID.setText(String.valueOf(idCustomer));
		this.fillPhone = fillPhone;
		Action_In_FillPhone action_In_FillPhone = new Action_In_FillPhone();
		btnCancel.addActionListener(action_In_FillPhone);
		btnOk.addActionListener(action_In_FillPhone);
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jlbID = new JLabel("ID");
		jlbID.setBounds(29, 24, 45, 13);
		contentPane.add(jlbID);
		
		JLabel jlbName = new JLabel("Name");
		jlbName.setBounds(29, 96, 45, 13);
		contentPane.add(jlbName);
		
		JLabel jlbPhone = new JLabel("Phone");
		jlbPhone.setBounds(29, 176, 45, 13);
		contentPane.add(jlbPhone);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setBounds(83, 21, 147, 19);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(83, 93, 147, 19);
		contentPane.add(tfName);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(83, 173, 147, 19);
		contentPane.add(tfPhone);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(116, 219, 85, 21);
		contentPane.add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(276, 219, 85, 21);
		contentPane.add(btnCancel);
	}

	public void closeFrame() {
		this.dispose();
	}

	public JFrameAddvsEditCustomer getFrame() {
		return this;
	}

	public void setTextFieldPhone(String phone) {
		tfPhone.setText(phone);
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}
	
	
	public JButton getBtnCancel() {
		return btnCancel;
	}
	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}


	private class Action_In_Panel implements ActionListener{// cái này dùng cho khi quản lý khách hàng trong panel khách hàng

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("OK")) {//nếu nhấn vào nút ok thì ...
				if(CustomerService.getInstance().checkID(Integer.parseInt(tfID.getText())) != null) {//kiểm tra xem có id tồn tại chưa, nếu có rồi thì thực hiện update từ id đó
					try {
						Integer.parseInt(tfPhone.getText());//kiểm tra exception
						if(tfName.getText().equals("")) {
							JOptionPane.showMessageDialog(getFrame(), "Nhập thiếu thông tin", "Error",
									JOptionPane.ERROR_MESSAGE);
						}else {
							if(tfPhone.getText().length() != 10) {
								JOptionPane.showMessageDialog(getFrame(), "Số điện thoại phải có độ dài là 10!", "Error",
										JOptionPane.ERROR_MESSAGE);
							}else {
								Customer customer = new Customer();
								customer.setIdCustomer(Integer.parseInt(tfID.getText()));
								customer.setNameCustomer(tfName.getText());
								customer.setPhoneCustomer(tfPhone.getText());
								if(CustomerService.getInstance().updateCustomer(customer) != null) {
									JOptionPane.showMessageDialog(getFrame(), "Update successfully!","Alert",JOptionPane.CLOSED_OPTION);
									jpnCustomer.showCustomer();
									closeFrame();
								}else {
									JOptionPane.showMessageDialog(getFrame(), "Lỗi update!", "Error",
											JOptionPane.ERROR_MESSAGE);
									closeFrame();
								}
							}
						}
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(getFrame(), "Lỗi định dạng!\nVui lòng nhập lại.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					
				}else {//nếu mã chưa tồn tại thì tức là thêm
					try {
						Integer.parseInt(tfPhone.getText());//kiểm tra exception
						if(tfName.getText().equals("")) {
							JOptionPane.showMessageDialog(getFrame(), "Nhập thiếu thông tin", "Error",
									JOptionPane.ERROR_MESSAGE);
						}else {
							if(tfPhone.getText().length() != 10) {
								JOptionPane.showMessageDialog(getFrame(), "Số điện thoại phải có độ dài là 10!", "Error",
										JOptionPane.ERROR_MESSAGE);
							}else {
								Customer customer = new Customer();
								customer.setIdCustomer(Integer.parseInt(tfID.getText()));
								customer.setNameCustomer(tfName.getText());
								customer.setPhoneCustomer(tfPhone.getText());
								if(CustomerService.getInstance().insertCustomer(customer) != null) {
									JOptionPane.showMessageDialog(getFrame(), "Insert successfully!","Alert",JOptionPane.CLOSED_OPTION);
									jpnCustomer.showCustomer();
									closeFrame();//xong thì đóng, để làm gì :v
								}else {
									JOptionPane.showMessageDialog(getFrame(), "Lỗi insert!", "Error",
											JOptionPane.ERROR_MESSAGE);
									closeFrame();//xong thì đóng, để làm gì :v
								}
							}
						}
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(getFrame(), "Lỗi định dạng số!\nVui lòng nhập lại.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}else if(e.getActionCommand().equals("Cancel")) {//nếu bấm vào nút huỷ
				closeFrame();
			}
		}
		
	}
	private class Action_In_FillPhone implements ActionListener{//cái này dùng cho thêm khách hàng sau khi điền số điện thoại trong quản lý đặt sân

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("OK")) {//nếu nhấn vào nút okay thì ...
				try {
					Integer.parseInt(tfPhone.getText());//kiểm tra exception
					if(tfName.getText().equals("")) {
						JOptionPane.showMessageDialog(getFrame(), "Nhập thiếu thông tin", "Error",
								JOptionPane.ERROR_MESSAGE);
					}else {
						if(tfPhone.getText().length() != 10) {
							JOptionPane.showMessageDialog(getFrame(), "Số điện thoại phải có độ dài là 10!", "Error",
									JOptionPane.ERROR_MESSAGE);
						}else {
							Customer customer = new Customer();
							customer.setIdCustomer(Integer.parseInt(tfID.getText()));
							customer.setNameCustomer(tfName.getText());
							customer.setPhoneCustomer(tfPhone.getText());
							if(CustomerService.getInstance().insertCustomer(customer) != null) {
								JOptionPane.showMessageDialog(getFrame(), "Insert successfully!","Alert",JOptionPane.CLOSED_OPTION);
								fillPhone.getJlbName().setText(customer.getNameCustomer());
								closeFrame();//thành công rồi thì đóng
							}else {
								JOptionPane.showMessageDialog(getFrame(), "Lỗi Insert!", "Error",
										JOptionPane.ERROR_MESSAGE);
								closeFrame();//xong thì đóng, để làm gì :v
							}
						}
					}
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(getFrame(), "Lỗi định dạng!\nVui lòng nhập lại.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}else {//nếu nhấn vào nút Cancel
				closeFrame();
			}
		}
	}
}