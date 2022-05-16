package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.BeverageBillDAO;
import dao.BeverageDAO;
import entity.Beverage;
import service.BeverageService;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameAddvsEditBeverage extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtMeasure;
	private JTextField txtOriginPrice;
	private JTextField txtPrice;
	private JTextField txtMount;
	private JPanelBeverage jpnBeverage;

	/**
	 * Create the frame.
	 */
	public JFrameAddvsEditBeverage(int ID, JPanelBeverage jpnBeverage) {
		initComponents();
		this.jpnBeverage = jpnBeverage;
		SetUp(ID);
	}
	
	private void SetUp(int ID)
	{
		if(ID != 0)
		{
			Beverage b = BeverageService.getInstance().checkID(ID);
			txtID.setText(String.valueOf(b.getIdBeverage()));
			txtName.setText(b.getNameBeverage().toString());
			txtMeasure.setText(b.getMeasure().toString());
			txtOriginPrice.setText(String.valueOf(b.getOriginalPrice()));
			txtPrice.setText(String.valueOf(b.getPrice()));
			txtMount.setText(String.valueOf(b.getMount()));
		}
		else txtID.setText(String.valueOf(BeverageService.getInstance().getNextIdBeverage()));
	}
	public 	JFrameAddvsEditBeverage getThis() {
		return this;
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 432, 191);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblID.setBounds(10, 11, 51, 16);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtID.setBounds(74, 9, 109, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblName = new JLabel("Tên");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(10, 38, 46, 16);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(74, 37, 109, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblMeasure = new JLabel("Đơn Vị");
		lblMeasure.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMeasure.setBounds(10, 65, 51, 17);
		contentPane.add(lblMeasure);
		
		txtMeasure = new JTextField();
		txtMeasure.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMeasure.setBounds(74, 63, 109, 20);
		contentPane.add(txtMeasure);
		txtMeasure.setColumns(10);
		
		JLabel lblOriginPrice = new JLabel("Giá Gốc");
		lblOriginPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOriginPrice.setBounds(235, 11, 51, 16);
		contentPane.add(lblOriginPrice);
		
		txtOriginPrice = new JTextField();
		txtOriginPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtOriginPrice.setBounds(296, 10, 109, 20);
		contentPane.add(txtOriginPrice);
		txtOriginPrice.setColumns(10);
		
		JLabel lblPrice = new JLabel("Giá");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrice.setBounds(235, 40, 51, 17);
		contentPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPrice.setBounds(296, 37, 109, 20);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblMount = new JLabel("Số Lượng");
		lblMount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMount.setBounds(235, 67, 63, 15);
		contentPane.add(lblMount);
		
		txtMount = new JTextField();
		txtMount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMount.setBounds(296, 64, 109, 20);
		contentPane.add(txtMount);
		txtMount.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancel.setBounds(235, 119, 89, 23);
		contentPane.add(btnCancel);
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//bắt exception 3 dòng này, nếu 1 trong 3 dòng này bị lỗi thì thực hiện tronng khối catch
					Integer.parseInt(txtOriginPrice.getText());
					Integer.parseInt(txtPrice.getText());
					Integer.parseInt(txtMount.getText());
					
					//nếu 3 dòng trên không lỗi thì tiếp tục thực thi
					if(txtName.getText().equals("")||txtMeasure.getText().equals("")) {
						JOptionPane.showMessageDialog(getThis(), "Nhập thiếu thông tin.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}else {
						Beverage b = new Beverage();
						b.setIdBeverage(Integer.parseInt(txtID.getText()));
						b.setNameBeverage(txtName.getText());
						b.setMeasure(txtMeasure.getText());
						b.setOriginalPrice(Integer.parseInt(txtOriginPrice.getText()));	
						b.setPrice(Integer.parseInt(txtPrice.getText()));
						b.setMount(Integer.parseInt(txtMount.getText()));
						BeverageService.getInstance().UpdateOrAdd(b);
						dispose();
						jpnBeverage.showBeverage("");
					}
				}catch(NumberFormatException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(getThis(), "Lỗi thực hiện!\nVui lòng xem lại thông tin!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOK.setBounds(94, 118, 89, 23);
		contentPane.add(btnOK);
	}
}