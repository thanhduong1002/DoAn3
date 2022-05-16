package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Employee;
import service.EmployeeService;
import utility.CatchError;
import utility.RadioButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JButton;

public class JFrameAddvsEditEmployee extends JFrame {
	private JPanelEmployee jpnEmployee;
	
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfOld;
	private JTextField tfAddress;
	private JTextField tfPhone;
	private JTextField tfPass;
	private JRadioButton rdNam;
	private JRadioButton rdNu;
	private JRadioButton rdAdmin;
	private JRadioButton rdNhanvien;
	private JTextField tfIdentity;
	private JButton btnOk;
	private JButton btnCancel;

	/**
	 * Create the frame.
	 */
	public JFrameAddvsEditEmployee(Employee employee, JPanelEmployee jpnEmployee) {
		initComponents();
		setTitle("Xem/Chỉnh sửa thông tin nhân viên");
		tfID.setText(String.valueOf(employee.getIdCustomer()));
		tfAddress.setText(employee.getAddress());
		tfName.setText(employee.getNameCustomer());
		tfOld.setText(String.valueOf(employee.getOld()));
		tfPass.setText(employee.getPassword());
		tfPhone.setText(employee.getPhoneCustomer());
		tfIdentity.setText(employee.getIdentityNumber());
		RadioButton radioButtonController1 = new RadioButton(rdNam, rdNu);
		RadioButton radioButtonController2 = new RadioButton(rdAdmin, rdNhanvien);
		
		if(employee.getGender() == 1) {
			rdNam.setSelected(true);
		}else {
			rdNu.setSelected(true);
		}
		
		if(employee.getRole() == 1) {
			rdAdmin.setSelected(true);
		}else {
			rdNhanvien.setSelected(true);
		}
		
		this.jpnEmployee = jpnEmployee;
		
		ButtonListener buttonListener = new ButtonListener();
		btnOk.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
	}
	public JFrameAddvsEditEmployee(int idEmployee, JPanelEmployee jpnEmployee) {
		initComponents();
		setTitle("Thêm mới một nhân viên");
		tfID.setText(String.valueOf(idEmployee));
		
		RadioButton radioButtonController1 = new RadioButton(rdNam, rdNu);
		RadioButton radioButtonController2 = new RadioButton(rdAdmin, rdNhanvien);
		
		rdNam.setSelected(true);
		rdNhanvien.setSelected(true);
		
		this.jpnEmployee = jpnEmployee;
		
		ButtonListener buttonListener = new ButtonListener();
		btnOk.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 599, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jlbId = new JLabel("ID");
		jlbId.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbId.setHorizontalAlignment(SwingConstants.CENTER);
		jlbId.setBounds(29, 11, 59, 14);
		contentPane.add(jlbId);
		
		JLabel jlbName = new JLabel("Name");
		jlbName.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbName.setHorizontalAlignment(SwingConstants.CENTER);
		jlbName.setBounds(29, 61, 59, 14);
		contentPane.add(jlbName);
		
		JLabel jlbOld = new JLabel("Old");
		jlbOld.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbOld.setHorizontalAlignment(SwingConstants.CENTER);
		jlbOld.setBounds(29, 114, 59, 14);
		contentPane.add(jlbOld);
		
		JLabel jlbAdress = new JLabel("Address");
		jlbAdress.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbAdress.setHorizontalAlignment(SwingConstants.CENTER);
		jlbAdress.setBounds(29, 166, 59, 14);
		contentPane.add(jlbAdress);
		
		JLabel lblNewLabel_4 = new JLabel("Phone");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(29, 215, 59, 14);
		contentPane.add(lblNewLabel_4);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setBounds(98, 8, 118, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setBounds(98, 58, 118, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfOld = new JTextField();
		tfOld.setBounds(98, 111, 118, 20);
		contentPane.add(tfOld);
		tfOld.setColumns(10);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(98, 163, 118, 20);
		contentPane.add(tfAddress);
		tfAddress.setColumns(10);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(98, 212, 118, 20);
		contentPane.add(tfPhone);
		tfPhone.setColumns(10);
		
		JLabel jlbPass = new JLabel("Password");
		jlbPass.setHorizontalAlignment(SwingConstants.CENTER);
		jlbPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbPass.setBounds(301, 166, 121, 14);
		contentPane.add(jlbPass);
		
		rdNam = new JRadioButton("Nam");
		rdNam.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdNam.setBounds(301, 7, 65, 23);
		contentPane.add(rdNam);
		
		rdNu = new JRadioButton("N\u1EEF");
		rdNu.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdNu.setBounds(381, 9, 65, 23);
		contentPane.add(rdNu);
		
		rdAdmin = new JRadioButton("Admin");
		rdAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdAdmin.setBounds(301, 110, 78, 23);
		contentPane.add(rdAdmin);
		
		rdNhanvien = new JRadioButton("Nh\u00E2n vi\u00EAn");
		rdNhanvien.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdNhanvien.setBounds(381, 110, 95, 23);
		contentPane.add(rdNhanvien);
		
		tfPass = new JTextField();
		tfPass.setBounds(432, 165, 134, 20);
		contentPane.add(tfPass);
		tfPass.setColumns(10);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(172, 290, 89, 23);
		contentPane.add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(301, 290, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel jlbIdentity = new JLabel("Identity Number");
		jlbIdentity.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbIdentity.setBounds(301, 63, 134, 14);
		contentPane.add(jlbIdentity);
		
		tfIdentity = new JTextField();
		tfIdentity.setBounds(432, 60, 134, 20);
		contentPane.add(tfIdentity);
		tfIdentity.setColumns(10);
		
		
	}
	public void closeFrame() {//dùng để đóng frame
		this.dispose();
	}
	public JFrameAddvsEditEmployee get() {
		return this;
	}
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("OK")) {//nếu nhấn vào nút okay thì ...
				if(EmployeeService.getInstance().checkIDEmployee(Integer.parseInt(tfID.getText())) != null) {//kiểm tra xem có mã tồn tại chưa, nếu có thì thực hiện update
					try {
						if(!CatchError.Instance().KieuSo(tfIdentity.getText(), 9) || !CatchError.Instance().KieuSo(tfPhone.getText(), 10)) {
							JOptionPane.showMessageDialog(get(), "Định dạng SĐT hoặc CMND sai.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						//cố tình bắt lỗi định dạng
						Integer.parseInt(tfOld.getText());//cố tình chuyển từ string sang int để bắt lỗi exception
//						Integer.parseInt(tfIdentity.getText());// cố tình chuyển từ string sang int để bắt lỗi exception
//						Integer.parseInt(tfPhone.getText());// cố tình chuyển từ string sang int để bắt lỗi exception
						//
						if(tfName.getText().equals("")||tfAddress.getText().equals("")||tfPass.getText().equals("")) {
							JOptionPane.showMessageDialog(get(), "Nhập thiếu thông tin!", "Error",
									JOptionPane.ERROR_MESSAGE);
						}else {
							Employee employee = new Employee();
							employee.setIdCustomer(Integer.parseInt(tfID.getText()));
							employee.setNameCustomer(tfName.getText());
							employee.setAddress(tfAddress.getText());
							employee.setOld(Integer.parseInt(tfOld.getText()));
							employee.setIdentityNumber(tfIdentity.getText());
							employee.setPassword(tfPass.getText());
							employee.setPhoneCustomer(tfPhone.getText());
							if(rdNam.isSelected() == true) {
								employee.setGender(1);
							}
							else {
								employee.setGender(0);
							}
							if(rdAdmin.isSelected() == true) { 
								employee.setRole(1);
							}
							else { 
								employee.setRole(0);
							}
							if(EmployeeService.getInstance().updateEmployee(employee) != null) {
								JOptionPane.showMessageDialog(get(), "Update successfully!","Alert",JOptionPane.CLOSED_OPTION);
								jpnEmployee.showEmployee();
								closeFrame();//xong thì đóng, để làm gì :v
							}else {
								JOptionPane.showMessageDialog(get(), "Lỗi update!", "Error",
										JOptionPane.ERROR_MESSAGE);
								closeFrame();
							}
						}
					}catch (Exception e1) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(get(), "Có lỗi định dạng!\nVui lòng kiểm tra lại thông tin.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}				
				}else {//nếu mã chưa tồn tại thì tức là thực hiện chức năng thêm
					try {
						//cố tình bắt lỗi định dạng
						if(!CatchError.Instance().KieuSo(tfIdentity.getText(), 9) || !CatchError.Instance().KieuSo(tfPhone.getText(), 10)) {
							JOptionPane.showMessageDialog(get(), "Định dạng SĐT hoặc CMND sai.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						Integer.parseInt(tfOld.getText());//cố tình chuyển từ string sang int để bắt lỗi exception
//						Integer.parseInt(tfIdentity.getText());// cố tình chuyển từ string sang int để bắt lỗi exception
//						Integer.parseInt(tfPhone.getText());// cố tình chuyển từ string sang int để bắt lỗi exception
						//
						if(tfName.getText().equals("")||tfAddress.getText().equals("")||tfPass.getText().equals("")) {
							JOptionPane.showMessageDialog(get(), "Nhập thiếu thông tin!", "Error",
									JOptionPane.ERROR_MESSAGE);
						}else {
							Employee employee = new Employee();
							employee.setIdCustomer(Integer.parseInt(tfID.getText()));
							employee.setNameCustomer(tfName.getText());
							employee.setAddress(tfAddress.getText());
							employee.setOld(Integer.parseInt(tfOld.getText()));
							employee.setIdentityNumber(tfIdentity.getText());
							employee.setPassword(tfPass.getText());
							employee.setPhoneCustomer(tfPhone.getText());
							if(rdNam.isSelected() == true) {
								employee.setGender(1);
							}
							else {
								employee.setGender(0);
							}
							if(rdAdmin.isSelected() == true) { 
								employee.setRole(1);
							}
							else { 
								employee.setRole(0);
							}
							if(EmployeeService.getInstance().insertEmployee(employee) != null) {
								JOptionPane.showMessageDialog(get(), "Insert successfully!","Alert",JOptionPane.CLOSED_OPTION);
								jpnEmployee.showEmployee();
								closeFrame();//xong thì đóng, để làm gì :v
							}else {
								JOptionPane.showMessageDialog(get(), "Lỗi insert!", "Error",
										JOptionPane.ERROR_MESSAGE);
								closeFrame();
							}
						}
					}catch (Exception e1) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(get(), "Có lỗi định dạng!\nVui lòng kiểm tra lại thông tin.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}							
				}
			}else if(e.getActionCommand().equals("Cancel")) {//nếu bấm vào nút huỷ
				closeFrame();
			}
		}
	}
}