package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utility.CategoryPanel;
import utility.ChangeColor;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JLabel jlbNameEmployee;
	private JLabel jlbRole;
	private JLabel jlbIconLogOut;
	private JPanel panel;
	private JPanel panelOrder;
	private JLabel jlbOrder;
	private JLabel jlbIconOrder;
	private JPanel panelBill;
	private JLabel jlbBill;
	private JLabel jlbIconBill;
	private JPanel panelBeverage;
	private JLabel jlbBeverage;
	private JLabel jlbIconBeverage;
	private JPanel panelCustomer;
	private JLabel jlbCustomer;
	private JLabel jlbIconCustomer;
	private JPanel panelEmployee;
	private JLabel jlbEmployee;
	private JLabel jlbIconEmployee;
	private JPanel panelYard;
	private JLabel jlbYard;
	private JLabel JlbIconYard;
	private JPanel panelInformation;
	private JPanel pnRoot;
	private JPanel panelSB;
	private JLabel jlbSanBongTLTD;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView("abc",1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView(String nameEmployee, int role) {
		
		initComponents();
		
		jlbNameEmployee.setText(nameEmployee);
		if(role == 1) {
			jlbRole.setText("Admin");
			List<CategoryPanel> list = new ArrayList<>();
			list.add(new CategoryPanel("Order",jlbOrder,panelOrder));
			list.add(new CategoryPanel("Employee",jlbEmployee,panelEmployee));
			list.add(new CategoryPanel("Customer",jlbCustomer,panelCustomer));
			list.add(new CategoryPanel("Beverage",jlbBeverage,panelBeverage));
			list.add(new CategoryPanel("Bill",jlbBill,panelBill));
			list.add(new CategoryPanel("Yard",jlbYard,panelYard));
			ChangeColor sc = new ChangeColor(pnRoot);
			sc.setView(panelOrder, jlbOrder);
			sc.setEvent(list);
		}else {
			jlbRole.setText("Nhân viên");
			List<CategoryPanel> list = new ArrayList<>();
			list.add(new CategoryPanel("Order",jlbOrder,panelOrder));
//			list.add(new CategoryPanel("Employee",jlbEmployee,panelEmployee));
			list.add(new CategoryPanel("Customer",jlbCustomer,panelCustomer));
			list.add(new CategoryPanel("Beverage",jlbBeverage,panelBeverage));
			list.add(new CategoryPanel("Bill",jlbBill,panelBill));
//			list.add(new CategoryPanel("Yard",jlbYard,panelYard));
			ChangeColor sc = new ChangeColor(pnRoot);
			sc.setView(panelOrder, jlbOrder);
			sc.setEvent(list);
			
			panelEmployee.setVisible(false);
			panelYard.setVisible(false);
		}
		
		ButtonLogout buttonLogout = new ButtonLogout();
		jlbIconLogOut.addMouseListener(buttonLogout);
	}
	
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1086, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 231, 507);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panelOrder = new JPanel();
		panelOrder.setBackground(new Color(50, 205, 50));
		panelOrder.setBounds(10, 77, 211, 51);
		panel.add(panelOrder);
		panelOrder.setLayout(null);
		
		jlbOrder = new JLabel("Qu\u1EA3n l\u00FD \u0111\u1EB7t s\u00E2n");
		jlbOrder.setForeground(Color.WHITE);
		jlbOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbOrder.setHorizontalAlignment(SwingConstants.CENTER);
		jlbOrder.setBounds(51, 0, 160, 51);
		panelOrder.add(jlbOrder);
		
		jlbIconOrder = new JLabel("New label");
		jlbIconOrder.setIcon(new ImageIcon("E:\\pbl3\\QLSB-master\\QLSB-master\\images\\icon-order.png"));
		jlbIconOrder.setBounds(0, 0, 51, 51);
		panelOrder.add(jlbIconOrder);
		
		panelBill = new JPanel();
		panelBill.setBackground(new Color(50, 205, 50));
		panelBill.setBounds(10, 139, 211, 51);
		panel.add(panelBill);
		panelBill.setLayout(null);
		
		jlbBill = new JLabel("Qu\u1EA3n l\u00FD ho\u00E1 \u0111\u01A1n");
		jlbBill.setForeground(Color.WHITE);
		jlbBill.setHorizontalAlignment(SwingConstants.CENTER);
		jlbBill.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbBill.setBounds(51, 0, 160, 51);
		panelBill.add(jlbBill);
		
		jlbIconBill = new JLabel("New label");
		jlbIconBill.setIcon(new ImageIcon("E:\\pbl3\\QLSB-master\\QLSB-master\\images\\icon-bill.png"));
		jlbIconBill.setBounds(0, 0, 51, 51);
		panelBill.add(jlbIconBill);
		
		panelBeverage = new JPanel();
		panelBeverage.setBackground(new Color(50, 205, 50));
		panelBeverage.setBounds(10, 201, 211, 51);
		panel.add(panelBeverage);
		panelBeverage.setLayout(null);
		
		jlbBeverage = new JLabel("Qu\u1EA3n l\u00FD m\u1EB7t h\u00E0ng");
		jlbBeverage.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbBeverage.setHorizontalAlignment(SwingConstants.CENTER);
		jlbBeverage.setForeground(Color.WHITE);
		jlbBeverage.setBounds(51, 0, 160, 51);
		panelBeverage.add(jlbBeverage);
		
		jlbIconBeverage = new JLabel("New label");
		jlbIconBeverage.setIcon(new ImageIcon("E:\\pbl3\\QLSB-master\\QLSB-master\\images\\icon-beverage.png"));
		jlbIconBeverage.setBounds(0, 0, 51, 51);
		panelBeverage.add(jlbIconBeverage);
		
		panelCustomer = new JPanel();
		panelCustomer.setBackground(new Color(50, 205, 50));
		panelCustomer.setBounds(10, 263, 211, 51);
		panel.add(panelCustomer);
		panelCustomer.setLayout(null);
		
		jlbCustomer = new JLabel("Qu\u1EA3n l\u00FD kh\u00E1ch h\u00E0ng");
		jlbCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		jlbCustomer.setForeground(Color.WHITE);
		jlbCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbCustomer.setBounds(51, 0, 160, 51);
		panelCustomer.add(jlbCustomer);
		
		jlbIconCustomer = new JLabel("New label");
		jlbIconCustomer.setIcon(new ImageIcon("E:\\pbl3\\QLSB-master\\QLSB-master\\images\\icon-customer.png"));
		jlbIconCustomer.setBounds(0, 0, 51, 51);
		panelCustomer.add(jlbIconCustomer);
		
		panelEmployee = new JPanel();
		panelEmployee.setBackground(new Color(50, 205, 50));
		panelEmployee.setBounds(10, 325, 211, 51);
		panel.add(panelEmployee);
		panelEmployee.setLayout(null);
		
		jlbEmployee = new JLabel("Qu\u1EA3n l\u00FD nh\u00E2n vi\u00EAn");
		jlbEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		jlbEmployee.setForeground(Color.WHITE);
		jlbEmployee.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbEmployee.setBounds(51, 0, 160, 51);
		panelEmployee.add(jlbEmployee);
		
		jlbIconEmployee = new JLabel("New label");
		jlbIconEmployee.setIcon(new ImageIcon("E:\\pbl3\\QLSB-master\\QLSB-master\\images\\icon-nhan-vien.png"));
		jlbIconEmployee.setBounds(0, 0, 51, 51);
		panelEmployee.add(jlbIconEmployee);
		
		panelYard = new JPanel();
		panelYard.setBackground(new Color(50, 205, 50));
		panelYard.setBounds(10, 387, 211, 51);
		panel.add(panelYard);
		panelYard.setLayout(null);
		
		jlbYard = new JLabel("Qu\u1EA3n l\u00FD s\u00E2n");
		jlbYard.setForeground(Color.WHITE);
		jlbYard.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbYard.setHorizontalAlignment(SwingConstants.CENTER);
		jlbYard.setBounds(51, 0, 160, 51);
		panelYard.add(jlbYard);
		
		JlbIconYard = new JLabel("New label");
		JlbIconYard.setIcon(new ImageIcon("E:\\pbl3\\QLSB-master\\QLSB-master\\images\\icon-yard.jpg"));
		JlbIconYard.setBounds(0, 0, 51, 51);
		panelYard.add(JlbIconYard);
		
		panelSB = new JPanel();
		panelSB.setBackground(Color.RED);
		panelSB.setBounds(0, 0, 231, 66);
		panel.add(panelSB);
		panelSB.setLayout(null);
		
		jlbSanBongTLTD = new JLabel("S\u00E2n b\u00F3ng TLTD");
		jlbSanBongTLTD.setFont(new Font("Tahoma", Font.BOLD, 17));
		jlbSanBongTLTD.setForeground(Color.WHITE);
		jlbSanBongTLTD.setHorizontalAlignment(SwingConstants.CENTER);
		jlbSanBongTLTD.setBounds(10, 11, 211, 44);
		panelSB.add(jlbSanBongTLTD);
		
		panelInformation = new JPanel();
		panelInformation.setBackground(Color.DARK_GRAY);
		panelInformation.setBounds(230, 0, 840, 66);
		contentPane.add(panelInformation);
		panelInformation.setLayout(null);
		
		jlbRole = new JLabel("");
		jlbRole.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbRole.setForeground(Color.WHITE);
		jlbRole.setFont(new Font("Tahoma", Font.BOLD, 16));
		jlbRole.setBounds(74, 11, 101, 29);
		panelInformation.add(jlbRole);
		
		jlbNameEmployee = new JLabel("");
		jlbNameEmployee.setForeground(Color.WHITE);
		jlbNameEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		jlbNameEmployee.setBounds(185, 11, 148, 29);
		panelInformation.add(jlbNameEmployee);
		
		jlbIconLogOut = new JLabel("");
		jlbIconLogOut.setIcon(new ImageIcon("E:\\pbl3\\QLSB-master\\QLSB-master\\images\\icon-log-out.png"));
		jlbIconLogOut.setBounds(784, 11, 46, 44);
		panelInformation.add(jlbIconLogOut);
		
		pnRoot = new JPanel();
		pnRoot.setBounds(230, 66, 840, 441);
		contentPane.add(pnRoot);
	}
	private MainView getThis() {
		return this;
	}
	private class ButtonLogout implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int a = JOptionPane.showConfirmDialog(getThis(),"Bạn chắc chắn muốn đăng xuất?\n");
			if(a == JOptionPane.YES_OPTION) {
				getThis().dispose();
				Login login  = new Login();
				login.setVisible(true);	
			}else {
				//không làm gì cả
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
