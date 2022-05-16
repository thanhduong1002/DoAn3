package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.PriceService;
import utility.CatchError;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameEditPrice extends JFrame {

	private JPanel contentPane;
	private JTextField tfCateYard;
	private JTextField tfTime;
	private JTextField tfPrice;
	private JTextField tfID;
	private JButton btnOk;
	private JButton btnCancel;
	
	private JPanelYard jPanelYard;

	/**
	 * Create the frame.
	 */
	public JFrameEditPrice(JPanelYard jPanelYard1) {
		initComponents();
		
		this.jPanelYard = jPanelYard1;
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Integer.parseInt(tfID.getText());
					PriceService.getInstance().updatePrice(Integer.parseInt(tfID.getText()), Integer.parseInt(tfPrice.getText()));
					closeFrame();
					jPanelYard.showData();
				}catch (NumberFormatException e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(getThis(), "Tiền phải là chuỗi số!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				closeFrame();
			}
		});
	}
	private void closeFrame() {
		this.dispose();
	}
	private JFrameEditPrice getThis() {
		return this;
	}
	private void initComponents() {
		setTitle("Sửa giá tiền sân");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(116, 134, 89, 23);
		contentPane.add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(231, 134, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel jlbID = new JLabel("ID");
		jlbID.setFont(new Font("Tahoma", Font.BOLD, 11));
		jlbID.setHorizontalAlignment(SwingConstants.CENTER);
		jlbID.setBounds(10, 11, 70, 14);
		contentPane.add(jlbID);
		
		JLabel jlbCateYard = new JLabel("Category Yard");
		jlbCateYard.setFont(new Font("Tahoma", Font.BOLD, 11));
		jlbCateYard.setBounds(10, 62, 81, 14);
		contentPane.add(jlbCateYard);
		
		tfID = new JTextField();
		tfID.setEnabled(false);
		tfID.setBounds(106, 8, 86, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		tfCateYard = new JTextField();
		tfCateYard.setEnabled(false);
		tfCateYard.setBounds(106, 59, 86, 20);
		contentPane.add(tfCateYard);
		tfCateYard.setColumns(10);
		
		tfTime = new JTextField();
		tfTime.setEnabled(false);
		tfTime.setBounds(278, 8, 86, 20);
		contentPane.add(tfTime);
		tfTime.setColumns(10);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(278, 59, 86, 20);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);
		
		JLabel jlbTime = new JLabel("Time");
		jlbTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		jlbTime.setHorizontalAlignment(SwingConstants.CENTER);
		jlbTime.setBounds(222, 11, 46, 14);
		contentPane.add(jlbTime);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(222, 62, 46, 14);
		contentPane.add(lblNewLabel_3);
	}

	public JTextField getTfCateYard() {
		return tfCateYard;
	}

	public JTextField getTfTime() {
		return tfTime;
	}

	public JTextField getTfPrice() {
		return tfPrice;
	}

	public JTextField getTfID() {
		return tfID;
	}
	
}
