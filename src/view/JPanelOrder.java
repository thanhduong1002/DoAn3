package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import entity.Order;
import entity.Time;
import entity.Yard;
import service.CustomerService;
import service.OrderService;
import service.TimeService;
import service.YardService;
import utility.Button_Yard;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JPanelOrder extends JPanel {

	private JDateChooser dateChooser;
	private JComboBox cbbChooseTime;
	
	private JButton btnSan5A;
	private JButton btnSan5B;
	private JButton btnSan5C;
	private JButton btnSan5D;
	private JButton btnSan5E;
	private JButton btnSan5F;
	private JButton btnSan7A;
	private JButton btnSan7B;
	private JButton btnShow;
	
	private List<Time> listTime = null;
	private List<Button_Yard> listButtonYard = null;
	private List<Order> listOrder = null;
	
	public JPanelOrder() {
		initComponents();
		listButtonYard = new ArrayList<Button_Yard>();
		listButtonYard.add(new Button_Yard(1,btnSan5A,0));
		listButtonYard.add(new Button_Yard(2,btnSan5B,0));
		listButtonYard.add(new Button_Yard(3,btnSan5C,0));
		listButtonYard.add(new Button_Yard(4,btnSan5D,0));
		listButtonYard.add(new Button_Yard(5,btnSan5E,0));
		listButtonYard.add(new Button_Yard(6,btnSan5F,0));
		listButtonYard.add(new Button_Yard(7,btnSan7A,0));
		listButtonYard.add(new Button_Yard(8,btnSan7B,0));
		
		listTime = TimeService.getInstance().getAllTime();
		cbbChooseTime.addItem("Hãy chọn giờ");
		
		
		for(Time item : listTime) {
			cbbChooseTime.addItem(item);
		}
		
		List<Yard> listYard = YardService.getInstance().getAllYard();
		for(int i = 0 ; i < listYard.size() ; i++) {
			if(listYard.get(i).getStatus() == 0) {
				listButtonYard.get(i).setStatus(2);//set trạng thái không cho đặt sân
				listButtonYard.get(i).getButton().setBackground(new Color(255,0,0));
			}
		}
		
		ButtonShow buttonShow = new ButtonShow();
		btnShow.addActionListener(buttonShow);
		ButtonYard buttonYard = new ButtonYard();
		btnSan5A.addActionListener(buttonYard);
		btnSan5B.addActionListener(buttonYard);
		btnSan5C.addActionListener(buttonYard);
		btnSan5D.addActionListener(buttonYard);
		btnSan5E.addActionListener(buttonYard);
		btnSan5F.addActionListener(buttonYard);
		btnSan7A.addActionListener(buttonYard);
		btnSan7B.addActionListener(buttonYard);
		
		btnSan5A.setEnabled(false);
		btnSan5B.setEnabled(false);
		btnSan5C.setEnabled(false);
		btnSan5D.setEnabled(false);
		btnSan5E.setEnabled(false);
		btnSan5F.setEnabled(false);
		btnSan7A.setEnabled(false);
		btnSan7B.setEnabled(false);
	}
	private void initComponents() {
		setLayout(null);
	
		
		JLabel jlbDateChooser = new JLabel("Ng\u00E0y");
		jlbDateChooser.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbDateChooser.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbDateChooser.setBounds(55, 11, 46, 34);
		add(jlbDateChooser);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(111, 11, 171, 34);
		add(dateChooser);
		
		JLabel jlbChooseTime = new JLabel("Gi\u1EDD thi \u0111\u1EA5u");
		jlbChooseTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlbChooseTime.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbChooseTime.setBounds(292, 11, 125, 34);
		add(jlbChooseTime);
		
		cbbChooseTime = new JComboBox();
		cbbChooseTime.setBounds(432, 13, 165, 34);
		add(cbbChooseTime);
		
		btnSan5A = new JButton("S\u00E2n 5A");
		btnSan5A.setBackground(Color.ORANGE);
		btnSan5A.setBounds(55, 114, 147, 59);
		add(btnSan5A);
		
		btnSan5B = new JButton("S\u00E2n 5B");
		btnSan5B.setBackground(Color.ORANGE);
		btnSan5B.setBounds(55, 194, 147, 59);
		add(btnSan5B);
		
		btnSan5C = new JButton("S\u00E2n 5C");
		btnSan5C.setBackground(Color.ORANGE);
		btnSan5C.setBounds(55, 273, 147, 59);
		add(btnSan5C);
		
		btnSan5D = new JButton("S\u00E2n 5D");
		btnSan5D.setBackground(Color.ORANGE);
		btnSan5D.setBounds(270, 114, 147, 59);
		add(btnSan5D);
		
		btnSan5E = new JButton("S\u00E2n 5E");
		btnSan5E.setBackground(Color.ORANGE);
		btnSan5E.setBounds(270, 194, 147, 59);
		add(btnSan5E);
		
		btnSan5F = new JButton("S\u00E2n 5F");
		btnSan5F.setBackground(Color.ORANGE);
		btnSan5F.setBounds(270, 273, 147, 59);
		add(btnSan5F);
		
		btnSan7A = new JButton("S\u00E2n 7A");
		btnSan7A.setBackground(Color.ORANGE);
		btnSan7A.setBounds(485, 114, 128, 218);
		add(btnSan7A);
		
		btnSan7B = new JButton("S\u00E2n 7B");
		btnSan7B.setBackground(Color.ORANGE);
		btnSan7B.setBounds(682, 114, 128, 218);
		add(btnSan7B);
		
		btnShow = new JButton("Hi\u1EC3n th\u1ECB");
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnShow.setBounds(626, 11, 103, 34);
		add(btnShow);

	}
	private class ButtonShow implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(cbbChooseTime.getSelectedIndex() == 0 || dateChooser.getDate() == null) {
				JOptionPane.showMessageDialog(getPanel(), "Hãy đảm bảo đã chọn đủ ngày và giờ.\n");
			}else {
				
				for(int i = 0 ; i < 8 ; i ++) {// reset tất cả các màu các nút sẽ là màu cam
					if(listButtonYard.get(i).getStatus() != 2) {
						listButtonYard.get(i).getButton().setBackground(Color.ORANGE);
						listButtonYard.get(i).setStatus(0);// đánh dấu là chưa đặt
						listButtonYard.get(i).getButton().setEnabled(true);
					}
				}
				listOrder = OrderService.getInstance().getOrderByDate_Time(dateChooser.getDate(), ((Time)cbbChooseTime.getSelectedItem()).getIdTime());//lấy danh sách Order theo ngày và time
				for(int i = 0 ; i < listOrder.size() ; i++) {
					int id = listOrder.get(i).getIdYard();//lấy id Sân đã được đặt
					for(Button_Yard item : listButtonYard) {
						if(item.getIdYard() == id) {
							item.getButton().setBackground(new Color(50, 205, 50));// set Color cho sân đã được đặt là màu xanh lá cây
							item.setStatus(1);// đánh dấu sân đã được đặt
						}
					}
				}
			}
		}
	}

	private class ButtonYard implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (cbbChooseTime.getSelectedIndex() == 0 || dateChooser.getDate() == null) {
				JOptionPane.showMessageDialog(getPanel(), "Hãy đảm bảo đã chọn đủ ngày và giờ.\n");
			} else {
				for (int i = 0; i < 8; i++) {
					if (listButtonYard.get(i).getButton().getText().equals(e.getActionCommand())) {// sân đã được bấm
						if (listButtonYard.get(i).getStatus() == 2) {// nếu sân bị vô hiệu hoá
							JOptionPane.showMessageDialog(getPanel(), "Sân tạm thời không phục vụ.\n");
						} else {
							if (listButtonYard.get(i).getStatus() == 0) {// nếu sân vẫn chưa ai đặt thì...
								FillPhone fillPhone = new FillPhone(listButtonYard.get(i), dateChooser.getDate(),
										((Time) cbbChooseTime.getSelectedItem()).getIdTime());
								fillPhone.setVisible(true);
							} else if (listButtonYard.get(i).getStatus() == 1) {
								int idYard = 0;
								switch (e.getActionCommand()) {
								case "Sân 5A":
									idYard = 1;
									break;
								case "Sân 5B":
									idYard = 2;
									break;
								case "Sân 5C":
									idYard = 3;
									break;
								case "Sân 5D":
									idYard = 4;
									break;
								case "Sân 5E":
									idYard = 5;
									break;
								case "Sân 5F":
									idYard = 6;
									break;
								case "Sân 7A":
									idYard = 7;
									break;
								case "Sân 7B":
									idYard = 8;
									break;
								default:
									break;
								}
								Order order = OrderService.getInstance().getOrderByTime_Date_Yard(
										((Time) cbbChooseTime.getSelectedItem()).getIdTime(), idYard,
										dateChooser.getDate());
								int a = JOptionPane.showConfirmDialog(getPanel(), "Bạn chắc chắn muốn huỷ sân?\n"
										+ CustomerService.getInstance().checkID(order.getIdCustomer()).toString());
								if (a == JOptionPane.YES_OPTION) {
									OrderService.getInstance().deleteOrder(order.getIdOrder());
									List<Yard> listYard = YardService.getInstance().getAllYard();
									if(listYard.get(i).getStatus() == 0) {
										listButtonYard.get(i).setStatus(2);//set trạng thái không cho đặt sân
										listButtonYard.get(i).getButton().setBackground(new Color(255,0,0));
									}else {
										listButtonYard.get(i).setStatus(0);
										listButtonYard.get(i).getButton().setBackground(Color.ORANGE);
									}
									
								}
							}
						}
					}

				}
			}
		}
	}
	public JPanel getPanel() {
		return this;
	}
}
