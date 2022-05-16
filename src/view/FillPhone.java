package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Customer;
import entity.Order;
import service.CustomerService;
import service.OrderService;
import utility.Button_Yard;
import utility.CatchError;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Action;
import javax.swing.JButton;

public class FillPhone extends JFrame {

	private JPanel contentPane;
	private JTextField tfPhone;
	private JLabel jlbPhone;
	private JButton btnOk;
	private JButton btnFind;
	private JLabel jlbName;
	private Button_Yard button_Yard;
	private Date date;
	private int idTime;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FillPhone frame = new FillPhone();
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
	public FillPhone() {
		
	}
	
	
	public FillPhone(Button_Yard button_Yard, Date date, int idTime) {
		initComponents();
		this.button_Yard = button_Yard;
		this.date = date;
		this.idTime = idTime;
		ButtonFind buttonFind = new ButtonFind();
		btnFind.addActionListener(buttonFind);
		ButtonOk buttonOk = new ButtonOk();
		btnOk.addActionListener(buttonOk);
	}
	
	
	
	private void initComponents() {
		setTitle("Điền số điện thoại");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 324, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jlbPhone = new JLabel("Phone");
		jlbPhone.setHorizontalAlignment(SwingConstants.CENTER);
		jlbPhone.setBounds(10, 41, 46, 14);
		contentPane.add(jlbPhone);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(66, 38, 163, 20);
		contentPane.add(tfPhone);
		tfPhone.setColumns(10);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(111, 144, 89, 23);
		contentPane.add(btnOk);
		
		btnFind = new JButton("Find");
		btnFind.setBounds(239, 37, 59, 23);
		contentPane.add(btnFind);
		
		jlbName = new JLabel("");
		jlbName.setBounds(66, 69, 163, 33);
		contentPane.add(jlbName);
	}
	public FillPhone getFillPhone() {
		return this;
	}
	
	public JLabel getJlbName() {
		return jlbName;
	}

	public void setJlbName(JLabel jlbName) {
		this.jlbName = jlbName;
	}

	private class ButtonFind implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(CatchError.Instance().KieuSo(tfPhone.getText(), 10) == true) {
				Customer customer = CustomerService.getInstance().searchPhone(tfPhone.getText());
				if (customer == null) {
					jlbName.setText("Không tìm thấy số điện thoại");
					int a = JOptionPane.showConfirmDialog(getFillPhone(), "Bạn có muốn thêm số điện thoại này vào?");
					if (a == JOptionPane.YES_OPTION) {
						int nextID = CustomerService.getInstance().getNextIdCustomer();
						JFrameAddvsEditCustomer addvsEditCustomer = new JFrameAddvsEditCustomer(nextID, getFillPhone());
						addvsEditCustomer.setTextFieldPhone(tfPhone.getText());
						addvsEditCustomer.setVisible(true);
					} else {
						getFillPhone().dispose();
					}
				} else {
					jlbName.setText(customer.getNameCustomer());
				}
			}else {
				JOptionPane.showMessageDialog(getFillPhone(), "Nhập sai định dạng số điện thoại", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private class ButtonOk implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(tfPhone.getText().equals("")) {
				JOptionPane.showMessageDialog(getFillPhone(), "Vui lòng nhập số điện thoại.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}else {
				if(jlbName.getText().equals("")) {
					JOptionPane.showMessageDialog(getFillPhone(), "Vui lòng bấm tìm kiếm số điện thoại.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else {
					Order order = new Order();
					order.setIdOrder(OrderService.getInstance().getNextID());
					order.setIdYard(button_Yard.getIdYard());
					order.setIdCustomer(CustomerService.getInstance().searchPhone(tfPhone.getText()).getIdCustomer());
					order.setIdCateYard_Time(OrderService.getInstance().getPriceByTime_Yard(idTime, button_Yard.getIdYard()).getIdCateYard_Time());
					order.setDate(date);
					OrderService.getInstance().insertOrder(order);
					button_Yard.getButton().setBackground(new Color(50, 205, 50));
					button_Yard.setStatus(1);
					getFillPhone().dispose();
				}
			}
		}
	}
}
