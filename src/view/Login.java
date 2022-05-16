package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Employee;
import service.EmployeeService;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JPasswordField passwordField;
	private JButton btnReset;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	
	public Login() {
		initCompoents();
		
		ButtonLogin buttonLogin = new ButtonLogin();
		btnLogin.addActionListener(buttonLogin);
		ButtonReset buttonReset = new ButtonReset();
		btnReset.addActionListener(buttonReset);
	}
	
	private void initCompoents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(380, 66, 161, 31);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(380, 147, 161, 31);
		contentPane.add(passwordField);
		
		JLabel jlbID = new JLabel("ID");
		jlbID.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
		jlbID.setHorizontalAlignment(SwingConstants.CENTER);
		jlbID.setBounds(280, 70, 68, 23);
		contentPane.add(jlbID);
		
		JLabel jlbPassword = new JLabel("Password");
		jlbPassword.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
		jlbPassword.setHorizontalAlignment(SwingConstants.CENTER);
		jlbPassword.setBounds(280, 151, 68, 23);
		contentPane.add(jlbPassword);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 218, 381);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel jlbPicture = new JLabel("New label");
		jlbPicture.setIcon(new ImageIcon("E:\\pbl3\\QLSB-master\\QLSB-master\\images\\icon-login.png"));
		jlbPicture.setBounds(21, 22, 175, 191);
		panel.add(jlbPicture);
		
		JLabel jlbSanBong = new JLabel("S\u00C2N B\u00D3NG TLTD");
		jlbSanBong.setForeground(new Color(47, 79, 79));
		jlbSanBong.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 24));
		jlbSanBong.setHorizontalAlignment(SwingConstants.CENTER);
		jlbSanBong.setBounds(10, 288, 198, 82);
		panel.add(jlbSanBong);
		
		JLabel jlbWelcome = new JLabel("Welcome");
		jlbWelcome.setForeground(new Color(105, 105, 105));
		jlbWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		jlbWelcome.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		jlbWelcome.setBounds(57, 258, 102, 30);
		panel.add(jlbWelcome);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(217, 0, 370, 23);
		contentPane.add(panel_1);
		
		btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 16));
		btnLogin.setSelectedIcon(null);
		btnLogin.setIcon(null);
		btnLogin.setBounds(300, 273, 89, 41);
		contentPane.add(btnLogin);
		
		btnReset = new JButton("Reset");
		btnReset.setBackground(Color.LIGHT_GRAY);
		btnReset.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 16));
		btnReset.setBounds(438, 273, 89, 41);
		contentPane.add(btnReset);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(217, 371, 370, 10);
		contentPane.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(577, 23, 10, 347);
		contentPane.add(panel_3);
	}
	
	private class ButtonLogin implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			@SuppressWarnings("deprecation")
			String password = passwordField.getText();
			String id = textFieldID.getText();
			if(EmployeeService.getInstance().checkEmployee(password, Integer.parseInt(id)) != null) {
				EmployeeService.setStoreUser(EmployeeService.getInstance().checkEmployee(password, Integer.parseInt(id)));
				Employee employee = EmployeeService.getInstance().checkEmployee(password, Integer.parseInt(id));
				MainView mainView = new MainView(employee.getNameCustomer(),employee.getRole());
				mainView.setVisible(true);
				getThis().dispose();
			}else {
				JOptionPane.showMessageDialog(getThis(), "Mật khẩu hoặc ID không đúng.\nVui lòng kiểm tra lại.");
			}
		}
	}
	private class ButtonReset implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			passwordField.setText("");
			textFieldID.setText("");
		}
	}
	private Login getThis() {
		return this;
	}
}